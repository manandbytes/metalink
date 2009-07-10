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

import java.util.List;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

/**
 *
 * @author Aekold Helbrass <Helbrass@gmail.com>
 */
public class StaxMetalinkWriter implements MetalinkWriter {

    private final XMLStreamWriter out;

    StaxMetalinkWriter(XMLStreamWriter out) {
        this.out = out;
    }

    public void writeMetalink(Metalink metalink) throws XMLStreamException {
        out.writeStartDocument();

        // <editor-fold defaultstate="collapsed" desc=" writing header ">
        MetalinkHeader header = metalink.getHeader();
        out.writeStartElement("metalink");
        out.writeDefaultNamespace(header.getXmlns());
        out.writeAttribute("version", header.getVersion());
        //out.writeAttribute("xmlns", header.getXmlns());
        if (header.getOrigin() != null) {
            out.writeAttribute("origin", header.getOrigin());
        }
        if (header.getPubdate() != null) {
            out.writeAttribute("pubdate", header.getPubdate());
        }
        if (header.getGenerator() != null) {
            out.writeAttribute("generator", header.getGenerator());
        }
        if (header.getRefreshdate() != null) {
            out.writeAttribute("refreshdate", header.getRefreshdate());
        }
        if (header.getType() != null) {
            out.writeAttribute("type", header.getType());
        }
        // </editor-fold>

        writeBody(metalink);
        writeFiles(metalink);
        out.writeEndElement();

        out.writeEndDocument();
    }

    private void writeBody(Metalink metalink) throws XMLStreamException {
        if (metalink.getPublisher() != null) {
            MetalinkPublisher publisher = metalink.getPublisher();
            out.writeStartElement("publisher");
            out.writeStartElement("name");
            out.writeCharacters(publisher.getName());
            out.writeEndElement();
            out.writeStartElement("url");
            out.writeCharacters(publisher.getUrl());
            out.writeEndElement();
            out.writeEndElement();
        }
        if (metalink.getLicense() != null) {
            MetalinkLicense license = metalink.getLicense();
            out.writeStartElement("license");
            out.writeStartElement("name");
            out.writeCharacters(license.getName());
            out.writeEndElement();
            out.writeStartElement("url");
            out.writeCharacters(license.getUrl());
            out.writeEndElement();
            out.writeEndElement();
        }
        if (metalink.getIdentity() != null) {
            out.writeStartElement("identity");
            out.writeCharacters(metalink.getIdentity());
            out.writeEndElement();
        }
        if (metalink.getVersion() != null) {
            out.writeStartElement("version");
            out.writeCharacters(metalink.getVersion());
            out.writeEndElement();
        }
        if (metalink.getDescription() != null) {
            out.writeStartElement("description");
            out.writeCharacters(metalink.getDescription());
            out.writeEndElement();
        }
        if (metalink.getTags() != null) {
            out.writeStartElement("tags");
            out.writeCharacters(metalink.getTags());
            out.writeEndElement();
        }
    }

    private void writeFiles(Metalink metalink) throws XMLStreamException {
        out.writeStartElement("files");
        List<MetalinkFile> files = metalink.getFiles();
        for (MetalinkFile f : files) {
            writeFile(f);
        }
        out.writeEndElement();
    }

    private void writeFile(MetalinkFile file) throws XMLStreamException {
        out.writeStartElement("file");
        out.writeAttribute("name", file.getName());

        if (file.getSize() > 0) {
            out.writeStartElement("size");
            out.writeCharacters(String.valueOf(file.getSize()));
            out.writeEndElement();
        }

        if (file.getOs() != null) {
            out.writeStartElement("os");
            out.writeCharacters(file.getOs());
            out.writeEndElement();
        }

        if (file.getLanguage() != null) {
            out.writeStartElement("language");
            out.writeCharacters(file.getLanguage());
            out.writeEndElement();
        }

        if (file.getVersion() != null) {
            out.writeStartElement("version");
            out.writeCharacters(file.getVersion());
            out.writeEndElement();
        }

        writeVerification(file.getVerification());

        out.writeStartElement("resources");
        List<MetalinkUrl> resources = file.getResources();
        for (MetalinkUrl r : resources) {
            writeResource(r);
        }
        out.writeEndElement();

        out.writeEndElement();
    }

    private void writeResource(MetalinkUrl r) throws XMLStreamException {
        out.writeStartElement("url");
        if (r.getType() != null) {
            out.writeAttribute("type", r.getType());
        }
        if (r.getLocation() != null) {
            out.writeAttribute("location", r.getLocation());
        }
        if (r.getMaxconnections() != 0) {
            out.writeAttribute("maxconnections", String.valueOf(r.getMaxconnections()));
        }
        if (r.getPreference() != 0) {
            out.writeAttribute("preference", String.valueOf(r.getPreference()));
        }
        out.writeCharacters(r.getValue());
        out.writeEndElement();
    }

    private void writeVerification(MetalinkVerification verification) throws XMLStreamException {
        if (verification == null) {
            return;
        }
        out.writeStartElement("verification");

        String[] hashTypes = verification.getAvailableHashTypes();
        if (hashTypes != null) {
            for (int i = 0; i < hashTypes.length; i++) {
                String hashType = hashTypes[i];
                String hash = verification.getHash(hashType);
                out.writeStartElement("hash");
                out.writeAttribute("type", hashType);
                out.writeCharacters(hash);
                out.writeEndElement();
            }
        }

        List<MetalinkVerificationPiece> pieces = verification.getPieces();
        if (pieces != null && pieces.size() > 0) {
            for (MetalinkVerificationPiece p : pieces) {
                out.writeStartElement("pieces");
                out.writeAttribute("type", p.getType());
                out.writeAttribute("length", String.valueOf(p.getLength()));

                List<String> hashes = p.getHashes();
                for (int i = 0; i < hashes.size(); i++) {
                    out.writeStartElement("hash");
                    out.writeAttribute("piece", String.valueOf(i));
                    out.writeCharacters(hashes.get(i));
                    out.writeEndElement();
                }

                out.writeEndElement();
            }
        }

        out.writeEndElement();
    }

}
