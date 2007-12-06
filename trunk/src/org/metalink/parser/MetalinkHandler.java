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

package org.metalink.parser;

import org.metalink.Metalink;
import org.xml.sax.Attributes;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;

/**
 *
 * @author <a href="mailto:paranoid.tiberiumlabs@gmail.com">Paranoid</a>
 */
public class MetalinkHandler extends DefaultHandler {
    
    private Metalink metalink;

    public Metalink getMetalink() {
        return metalink;
    }

    @Override
    public void startDocument() throws SAXException {
        metalink = new Metalink();
    }
    
    
    // <editor-fold defaultstate="collapsed" desc=" other methods ">
    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        System.out.println("characters: " + String.valueOf(ch, start, length));
    }

    @Override
    public void endDocument() throws SAXException {
        System.out.println("endDocument");
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        System.out.println("endElement");
    }

    @Override
    public void endPrefixMapping(String prefix) throws SAXException {
        System.out.println("endPrefixMapping");
    }

    @Override
    public void error(SAXParseException e) throws SAXException {
        System.out.println("error");
    }

    @Override
    public void fatalError(SAXParseException e) throws SAXException {
        System.out.println("fatalError");
    }

    @Override
    public void ignorableWhitespace(char[] ch, int start, int length) throws SAXException {
        System.out.println("ignorableWhitespace: " + String.valueOf(ch, start, length));
    }

    @Override
    public void notationDecl(String name, String publicId, String systemId) throws SAXException {
        System.out.println("notationDecl");
    }

    @Override
    public void processingInstruction(String target, String data) throws SAXException {
        System.out.println("processingInstruction: target=" + target + ";data=" + data);
    }

    @Override
    public void setDocumentLocator(Locator locator) {
        super.setDocumentLocator(locator);
    }

    @Override
    public void skippedEntity(String name) throws SAXException {
        System.out.println("skippedEntity: " + name);
    }


    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        //System.out.println("startElement: uri=" + uri + ";localName=" + localName + ";qName=" + qName);
        System.out.println("startElement, qName=" + qName);
        System.out.print("Attributes:");
        for (int i = 0; i < attributes.getLength(); i++) {
            if (attributes.getValue(i) != null) {
                System.out.print(' ' + i + '=' + attributes.getValue(i));
            }
        }
        System.out.println();

    }

    @Override
    public void startPrefixMapping(String prefix, String uri) throws SAXException {
        System.out.println("startPrefixMapping: prefix=" + prefix + ";uri=" + uri);
    }

    @Override
    public void unparsedEntityDecl(String name, String publicId, String systemId, String notationName) throws SAXException {
        System.out.println("unparsedEntityDecl: name=" + name);
    }

    // </editor-fold>

}
