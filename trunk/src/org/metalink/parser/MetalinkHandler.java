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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.metalink.Metalink;
import org.metalink.content.File;
import org.metalink.content.Publisher;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 *
 * @author <a href="mailto:paranoid.tiberiumlabs@gmail.com">Paranoid</a>
 */
public class MetalinkHandler extends DefaultHandler {
    
    // <editor-fold defaultstate="collapsed" desc=" metalink result bean ">
    private Metalink metalink;
    public Metalink getMetalink() {
        return metalink;
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc=" xml parsing ">
    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        
        /*
         * first we process metalink information, everything is parameter
         * 2. publisher. have tags name and url
         * 3. some more metatags.
         * 4. files
         * 5. one file, including os, size and other.
         */
        
        this.needCharacters = true;
        if ("metalink".equals(qName)) {
            processMetalinkParams(attributes);
        }
        else if ("publisher".equals(qName)) {
            this.processingPublisher = true;
        }
        else if ("files".equals(qName)) {
            this.processingFiles = true;
        }
        else if ("file".equals(qName)) {
            this.processingFile = true;
            this.file = new File(attributes.getValue("name"));
        }
        this.attributes = attributes;
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        if (needCharacters) {
            this.characters = String.valueOf(ch, start, length).trim();
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        this.needCharacters = false;
        if ("publisher".equals(qName)) {
            this.processingPublisher = false;
            this.publisher = new Publisher(publisherName, publisherUrl);
        }
        else if ("name".equals(qName) && processingPublisher) {
            this.publisherName = characters;
        }
        else if ("url".equals(qName) && processingPublisher) {
            this.publisherUrl = characters;
        }
        else if ("description".equals(qName)) {
            this.description = characters;
        }
        else if ("tags".equals(qName)) {
            this.tags = characters;
        }
        else if("identity".equals(qName)) {
            this.identity = characters;
        }
        else if ("version".equals(qName)) {
            this.version = characters;
        }
        else if ("files".equals(qName)) {
            this.processingFiles = false;
        }
        else if ("file".equals(qName) && processingFiles && processingFile) {
            this.processingFile = false;
            files.add(file);
        }
        else if ("os".equals(qName) && processingFile) {
            this.file.setOs(characters);
        }
        else if ("size".equals(qName) && processingFile) {
            this.file.setSize(Long.parseLong(characters));
        }
        else if ("hash".equals(qName) && processingFile) {
            file.addHash(attributes.getValue("type"), characters);
        }
        else if ("url".equals(qName) && processingFile) {
            file.addLink(characters, attributes.getValue("type"), attributes.getValue("location"), attributes.getValue("preference"));
        }
    }

    @Override
    public void endDocument() throws SAXException {
        this.metalink = new Metalink(metalinkParams, publisher, description, tags, identity, version, files);
    }
    
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc=" current processing ">
    private Map<String, String> metalinkParams = new HashMap<String, String>();
    private String characters;
    private boolean needCharacters = false;
    private boolean processingPublisher = false;
    private boolean processingFiles = false;
    private boolean processingFile = false;
    private Attributes attributes;
    private String publisherName;
    private String publisherUrl;
    private File file;
    private void processMetalinkParams(Attributes attributes) {
        if (attributes != null && attributes.getLength() > 0) {
            for (int i = 0; i < attributes.getLength(); i++) {
                metalinkParams.put(attributes.getQName(i), attributes.getValue(i));
            }
        }   
    }
    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc=" processed data ">
    private Publisher publisher;
    private String description;
    private String tags;
    private String identity;
    private String version;
    private ArrayList<File> files = new ArrayList<File>();
    // </editor-fold>

}
