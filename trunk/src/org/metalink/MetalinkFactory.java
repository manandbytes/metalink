/*
 * Copyright 2007 Tiberiumlabs
 *
 * This file is part of Java Metalink.
 *
 * Java Metalink is free software; you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation; either version 3 of the License, or
 * (at your option) any later version.
 *
 * Java Metalink is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.metalink;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.io.StringReader;
import java.security.MessageDigest;
import java.util.Iterator;
import java.util.List;
import static java.util.Map.Entry;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.metalink.content.File;
import org.metalink.content.Pieces;
import org.metalink.content.Publisher;
import org.metalink.content.Url;
import org.metalink.parser.DynamicChecker;
import org.metalink.parser.MetalinkHandler;
import org.metalink.parser.MetalinkParsingException;
import org.xml.sax.InputSource;

/**
 * Optional: Configurable options/settings (or detection of): language, location, operating system, etc.
 * Optional: For <verification>: Verify MD5, SHA-1, SHA-256 checksums. (Optional: OpenPGP).
 *
 * @author <a href="mailto:paranoid.tiberiumlabs@gmail.com">Paranoid</a>
 */
public class MetalinkFactory {

    private static final SAXParserFactory PARSER_FACTORY = SAXParserFactory.newInstance();

    public static Metalink createMetalink(String metalinkContent) throws MetalinkParsingException {
        return createMetalink(new StringReader(metalinkContent));
    }

    public static Metalink createMetalink(Reader reader) throws MetalinkParsingException {
        return createMetalink(new InputSource(reader));
    }

    public static Metalink createMetalink(InputStream in) throws MetalinkParsingException {
        return createMetalink(new InputSource(in));
    }

    public static Metalink createMetalink(InputSource source) throws MetalinkParsingException {
        try {

            SAXParser parser = PARSER_FACTORY.newSAXParser();
            MetalinkHandler handler = new MetalinkHandler();
            parser.parse(source, handler);
            return handler.getMetalink();

        } catch (Exception ex) {
            throw new MetalinkParsingException(ex);
        }
    }
    
    /**
     * Method to check is specified Metalink file is dynamic
     * @param source
     * @return
     */
    public static boolean isMetalinkDynamic(InputSource source) {
        try {
            PARSER_FACTORY.newSAXParser().parse(source, new DynamicChecker());
        } catch (RuntimeException rex) {
            if (DynamicChecker.TYPE_IS_DYNAMIC.equals(rex.getMessage())) {
                return true;
            }
        } catch (Exception ignore) {
        }
        return false;
    }

    /**
     * Method to veriby integrity of full file.
     * Note: for better performance try to use buffered streams.
     * Note: it gets algorithms in such order: sha1, md5, sha-256.
     * WARNING: do not forget to close stream by yourself!
     *
     * @param file file representation from metalink
     * @param in stream to read real file content
     * @return <code>true</code> if and only if hashes are equal.
     */
    public static boolean verify(File file, InputStream in) {
        try {
            String algorithm = "sha1";
            String hash = file.getHash(algorithm);
            if (hash == null || hash.length() == 0) {
                algorithm = "md5";
                hash = file.getHash(algorithm);
            }
            if (hash == null || hash.length() == 0) {
                algorithm = "sha-256";
                hash = file.getHash(algorithm);
            }
            if (hash == null || hash.length() == 0) {
                throw new RuntimeException("File have no hashes!");
            }
            MessageDigest messageDigest = MessageDigest.getInstance(algorithm);
            byte[] buffer = new byte[4096];
            int read = 0;
            while ((read = in.read(buffer)) >= 0) {
                messageDigest.update(buffer, 0, read);
            }
            byte[] result = messageDigest.digest();
            return hash.equalsIgnoreCase(byteArrayToHexString(result));
        } catch (Exception ex) {
        }
        return false;
    }

    /**
     * Method to write back XML file from Metalink object. Without any formatting, just plain XML.
     * @param metalink object representing Metalink
     * @param out object to append tags and values.
     * @throws java.io.IOException
     */
    public static void writeBackToXML(Metalink metalink, Appendable out) throws IOException {
        final String fs = System.getProperty("line.separator");
        out.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>").append(fs);
        out.append("<metalink xmlns=\"http://www.metalinker.org/\"");
        Iterator<Entry<String, String>> entryIterator = metalink.getParams().entrySet().iterator();
        while (entryIterator.hasNext()) {
            Entry<String, String> entry = entryIterator.next();
            out.append(' ').append(entry.getKey()).append('=').append('"');
            out.append(entry.getValue()).append('"').append(fs);
        }
        out.append('>').append(fs).append(fs);

        Publisher publisher = metalink.getPublisher();
        if (publisher != null) {
            out.append("<publisher>").append(fs);
            out.append("<name>").append(publisher.getName()).append("</name>").append(fs);
            out.append("<url>").append(publisher.getUrl()).append("</url>").append(fs);
            out.append("</publisher>").append(fs).append(fs);
        }

        out.append("<description>").append(metalink.getDescription()).append("</description>").append(fs);
        out.append("<tags>").append(metalink.getTags()).append("</tags>").append(fs);
        out.append("<identity>").append(metalink.getIdentity()).append("</identity>").append(fs);
        out.append("<version>").append(metalink.getVersion()).append("</version>").append(fs).append(fs);

        List<? extends File> files = metalink.getFiles();
        out.append("<files>").append(fs).append(fs);
        for (int i = 0; i < files.size(); i++) {
            File file = files.get(i);
            out.append("<file name=\"").append(file.getName()).append("\">").append(fs);

            out.append("<os>").append(file.getOs()).append("</os>").append(fs);
            out.append("<size>").append(String.valueOf(file.getSize())).append("</size>").append(fs).append(fs);
            out.append("<verification>").append(fs).append(fs);
            Iterator<Entry<String, String>> hashesIterator = file.getHashes().entrySet().iterator();
            while (hashesIterator.hasNext()) {
                Entry<String, String> hashEntry = hashesIterator.next();
                out.append("<hash type=\"").append(hashEntry.getKey()).append("\">").append(hashEntry.getValue()).append("</hash>").append(fs);
            }
            out.append(fs);

            Pieces pieces = file.getPieces();
            if (pieces != null) {
                out.append("<pieces length=\"").append(String.valueOf(pieces.getLength()));
                out.append("\" type=\"").append(pieces.getType()).append("\">").append(fs);
                List<? extends String> piecesList = pieces.getPiecesList();
                for (int j = 0; j < piecesList.size(); j++) {
                    out.append("<hash piece=\"").append(String.valueOf(j));
                    out.append("\">").append(piecesList.get(j)).append("</hash>").append(fs);
                }

                out.append("</pieces>").append(fs);
            }

            out.append("</verification>").append(fs).append(fs);

            out.append("<resources>").append(fs);
            List<? extends Url> urls = file.getUrls();
            for (int j = 0; j < urls.size(); j++) {
                Url url = urls.get(j);
                out.append("<url type=\"").append(url.getType());
                out.append("\" location=\"").append(url.getLocation());
                out.append("\" preference=\"").append(String.valueOf(url.getPreference())).append("\">");
                out.append(url.getUrl());
                out.append("</url>").append(fs);
            }

            out.append("</resources>").append(fs).append(fs);

            out.append("</file>").append(fs);
        }
        out.append("</files>").append(fs);

        out.append("</metalink>").append(fs);

        //out.append("").append("").append("").append(fs);
    }

    private static final char[] hexChar = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    private static String byteArrayToHexString(byte[] b) {
        StringBuilder sb = new StringBuilder(b.length * 2);
        for (int i = 0; i < b.length; i++) {
            sb.append(hexChar[(b[i] & 0xf0) >>> 4]);
            sb.append(hexChar[b[i] & 0x0f]);
        }
        return sb.toString();
    }

}
