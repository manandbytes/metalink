/*
 * This file is part of Heresylabs' Metalink.
 * 
 * Metalink is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * Metalink is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 * 
 * You should have received a copy of the GNU Lesser General Public License
 * along with Metalink.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.heresylabs.metalink;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

/**
 *
 * @author Aekold Helbrass <Helbrass@gmail.com>
 */
public class StaxMetalinkParserStraight implements MetalinkParser, XMLStreamConstants {

    private final XMLStreamReader in;

    StaxMetalinkParserStraight(XMLStreamReader in) {
        this.in = in;
    }

    @Override
    public Metalink parse() throws MetalinkFormatException {
        try {
            Metalink metalink = new Metalink();
            while (in.hasNext()) {
                int event = in.next();
                String localName = in.getLocalName();

                if (event == START_ELEMENT) {

                    if ("metalink".equals(localName)) {
                        metalink.setHeader(parseHeader(in));
                        continue;
                    }

                    if ("publisher".equals(localName)) {
                        metalink.setPublisher(parsePublisher(in));
                        continue;
                    }

                    if ("description".equals(localName)) {
                        metalink.setDescription(in.getElementText());
                        continue;
                    }

                    if ("files".equals(localName)) {
                        metalink.setFiles(parseFiles(in));
                        continue;
                    }

                    if ("identity".equals(localName)) {
                        metalink.setIdentity(in.getElementText());
                        continue;
                    }

                    if ("version".equals(localName)) {
                        metalink.setVersion(in.getElementText());
                        continue;
                    }

                    if ("license".equals(localName)) {
                        metalink.setLicense(parseLicense(in));
                        continue;
                    }

                    if ("tags".equals(localName)) {
                        metalink.setTags(in.getElementText());
                    }

                }

            }

            return metalink;
        }
        catch (XMLStreamException ex) {
            throw new MetalinkFormatException(ex);
        }
    }

    protected MetalinkHeader parseHeader(XMLStreamReader in) {
        MetalinkHeader header = new MetalinkHeader();
        String xmlns = in.getNamespaceURI();
        header.setXmlns(xmlns);
        int count = in.getAttributeCount();
        for (int i = 0; i < count; i++) {
            String name = in.getAttributeLocalName(i);
            String value = in.getAttributeValue(i);
            switch (getHeaderElementSwitchNumber(name)) {
                case HEADER_VERSION: {
                    header.setVersion(value);
                    break;
                }
                /*
                case HEADER_XMLNS: {
                header.setXmlns(value);
                break;
                }
                 */
                case HEADER_ORIGIN: {
                    header.setOrigin(value);
                    break;
                }
                case HEADER_TYPE: {
                    header.setType(value);
                    break;
                }
                case HEADER_PUBDATE: {
                    header.setPubdate(value);
                    break;
                }
                case HEADER_REFRESHDATE: {
                    header.setRefreshdate(value);
                    break;
                }
                case HEADER_GENERATOR: {
                    header.setGenerator(value);
                    break;
                }
            }
        }
        if (header.getXmlns() == null) {
            System.out.println("WARNING: xmlns has not been read");
        }
        return header;
    }

    protected MetalinkPublisher parsePublisher(XMLStreamReader in) throws XMLStreamException {
        MetalinkPublisher publisher = new MetalinkPublisher();
        while (in.hasNext()) {
            int event = in.next();
            String localName = in.getLocalName();
            if (event == END_ELEMENT && "publisher".equals(localName)) {
                return publisher;
            }
            if (event == START_ELEMENT && "name".equals(localName)) {
                publisher.setName(in.getElementText());
                continue;
            }
            if (event == START_ELEMENT && "url".equals(localName)) {
                publisher.setUrl(in.getElementText());
                continue;
            }
        }
        return publisher;
    }

    protected MetalinkLicense parseLicense(XMLStreamReader in) throws XMLStreamException {
        MetalinkLicense license = new MetalinkLicense();
        while (in.hasNext()) {
            int event = in.next();
            String localName = in.getLocalName();
            if (event == END_ELEMENT && "license".equals(localName)) {
                return license;
            }
            if (event == START_ELEMENT && "name".equals(localName)) {
                license.setName(in.getElementText());
                continue;
            }
            if (event == START_ELEMENT && "url".equals(localName)) {
                license.setUrl(in.getElementText());
                continue;
            }
        }
        return license;
    }

    protected List<MetalinkFile> parseFiles(XMLStreamReader in) throws XMLStreamException {

        List<MetalinkFile> files = new ArrayList<MetalinkFile>();

        while (in.hasNext()) {
            int event = in.next();
            String localName = in.getLocalName();

            if (event == END_ELEMENT && "files".equals(localName)) {
                return files;
            }

            if (event == START_ELEMENT && "file".equals(localName)) {
                files.add(parseFile(in));
                continue;
            }

        }

        return files;

    }

    protected MetalinkFile parseFile(XMLStreamReader in) throws XMLStreamException {

        MetalinkFile file = new MetalinkFile();

        int count = in.getAttributeCount();
        for (int i = 0; i < count; i++) {
            String localName = in.getAttributeLocalName(i);
            if ("name".equals(localName)) {
                file.setName(in.getAttributeValue(i));
            }
        }

        while (in.hasNext()) {
            int event = in.next();
            String localName = in.getLocalName();

            if (event == END_ELEMENT && "file".equals(localName)) {
                return file;
            }

            if (event == START_ELEMENT) {

                if ("version".equals(localName)) {
                    file.setVersion(in.getElementText());
                    continue;
                }

                if ("language".equals(localName)) {
                    file.setLanguage(in.getElementText());
                    continue;
                }

                if ("os".equals(localName)) {
                    file.setOs(in.getElementText());
                    continue;
                }

                if ("size".equals(localName)) {
                    String sizeString = in.getElementText();
                    long size = Long.parseLong(sizeString);
                    file.setSize(size);
                    continue;
                }

                if ("description".equals(localName)) {
                    file.setDescription(in.getElementText());
                    continue;
                }

                if ("verification".equals(localName)) {
                    MetalinkVerification verification = parseVerification(in);
                    file.setVerification(verification);
                    continue;
                }

                if ("resources".equals(localName)) {
                    List<MetalinkUrl> resources = parseResources(in);
                    file.setResources(resources);
                    continue;
                }
            }
        }

        return file;
    }

    protected List<MetalinkUrl> parseResources(XMLStreamReader in) throws XMLStreamException {

        List<MetalinkUrl> resources = new ArrayList<MetalinkUrl>();

        while (in.hasNext()) {
            int event = in.next();
            String localName = in.getLocalName();

            if (event == END_ELEMENT && "resources".equals(localName)) {
                return resources;
            }

            if (event == START_ELEMENT && "url".equals(localName)) {
                MetalinkUrl url = parseUrl(in);
                resources.add(url);
            }
        }

        return resources;
    }

    protected MetalinkUrl parseUrl(XMLStreamReader in) throws XMLStreamException {
        MetalinkUrl url = new MetalinkUrl();

        int count = in.getAttributeCount();
        for (int i = 0; i < count; i++) {
            String attributeName = in.getAttributeLocalName(i);
            if ("type".equals(attributeName)) {
                url.setType(in.getAttributeValue(i));
                continue;
            }
            if ("preference".equals(attributeName)) {
                String preferenceString = in.getAttributeValue(i);
                int preference = Integer.parseInt(preferenceString);
                url.setPreference(preference);
                continue;
            }
            if ("location".equals(attributeName)) {
                url.setLocation(in.getAttributeValue(i));
                continue;
            }
            if ("maxconnections".equals(attributeName)) {
                String maxconnectionsString = in.getAttributeValue(i);
                int maxconnections = Integer.parseInt(maxconnectionsString);
                url.setMaxconnections(maxconnections);
                continue;
            }
        }

        url.setValue(in.getElementText());

        return url;
    }

    protected MetalinkVerification parseVerification(XMLStreamReader in) throws XMLStreamException {
        MetalinkVerification verification = new MetalinkVerification();

        while (in.hasNext()) {
            int event = in.next();
            String localName = in.getLocalName();

            if (event == END_ELEMENT && "verification".equals(localName)) {
                return verification;
            }

            if (event == START_ELEMENT) {

                if ("hash".equals(localName)) {
                    int count = in.getAttributeCount();
                    for (int i = 0; i < count; i++) {
                        if ("type".equals(in.getAttributeLocalName(i))) {
                            verification.addHash(in.getAttributeValue(i), in.getElementText());
                        }
                    }
                    continue;
                }

                if ("pieces".equals(localName)) {
                    String type = null;
                    String lengthString = "";
                    int count = in.getAttributeCount();
                    for (int i = 0; i < count; i++) {
                        String attributeName = in.getAttributeLocalName(i);
                        if ("type".equals(attributeName)) {
                            type = in.getAttributeValue(i);
                            continue;
                        }
                        if ("length".equals(attributeName)) {
                            lengthString = in.getAttributeValue(i);
                            continue;
                        }
                    }
                    int length = Integer.parseInt(lengthString);
                    List<String> hashes = parsePieces(in);
                    MetalinkVerificationPiece pieces = new MetalinkVerificationPiece(type, length, hashes);
                    verification.addPiece(pieces);
                }

            }

        }

        return verification;
    }

    private List<String> parsePieces(XMLStreamReader in) throws XMLStreamException {
        List<HashPiece> piecesList = new ArrayList<HashPiece>();
        while (in.hasNext()) {
            int event = in.next();
            String localName = in.getLocalName();
            if (event == END_ELEMENT && "pieces".equals(localName)) {
                break;
            }
            if (event == START_ELEMENT && "hash".equals(localName)) {
                int count = in.getAttributeCount();
                int piece = -1;
                for (int i = 0; i < count; i++) {
                    if ("piece".equals(in.getAttributeLocalName(i))) {
                        String pieceString = in.getAttributeValue(i);
                        piece = Integer.parseInt(pieceString);
                    }
                }
                String value = in.getElementText();
                piecesList.add(new HashPiece(piece, value));
            }
        }
        Collections.sort(piecesList);
        List<String> pieces = new ArrayList<String>(piecesList.size());
        for (HashPiece h : piecesList) {
            pieces.add(h.getValue());
        }
        return pieces;
    }

    // <editor-fold defaultstate="collapsed" desc=" internal optimisation hacks ">
    // <editor-fold defaultstate="collapsed" desc=" header ">
    private static final int HEADER_VERSION = 0;
    private static final int HEADER_XMLNS = 1;
    private static final int HEADER_ORIGIN = 2;
    private static final int HEADER_TYPE = 3;
    private static final int HEADER_PUBDATE = 4;
    private static final int HEADER_REFRESHDATE = 5;
    private static final int HEADER_GENERATOR = 6;
    private HashMap<String, Integer> headerElementsMap;

    private int getHeaderElementSwitchNumber(String name) {
        if (headerElementsMap == null) {
            HashMap<String, Integer> h = new HashMap<String, Integer>();
            h.put("version", HEADER_VERSION);
            h.put("xmlns", HEADER_XMLNS);
            h.put("origin", HEADER_ORIGIN);
            h.put("type", HEADER_TYPE);
            h.put("pubdate", HEADER_PUBDATE);
            h.put("refreshdate", HEADER_REFRESHDATE);
            h.put("generator", HEADER_GENERATOR);

            headerElementsMap = h;
        }
        return headerElementsMap.get(name);
    }
    // </editor-fold>
    // </editor-fold>

    private class HashPiece implements Comparable<HashPiece> {

        private final int piece;
        private final String value;

        public HashPiece(int piece, String value) {
            this.piece = piece;
            this.value = value;
        }

        public int getPiece() {
            return piece;
        }

        public String getValue() {
            return value;
        }

        @Override
        public int compareTo(HashPiece o) {
            return piece - o.piece;
        }

    }
}
