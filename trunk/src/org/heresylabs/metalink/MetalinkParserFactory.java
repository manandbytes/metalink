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

import java.io.InputStream;
import java.io.Reader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.transform.Source;

/**
 *
 * @author Aekold Helbrass <Helbrass@gmail.com>
 */
public class MetalinkParserFactory {

    private XMLInputFactory inputFactory;

    private MetalinkParserFactory() {
    }

    public static MetalinkParserFactory newInstance() {
        return new MetalinkParserFactory();
    }

    private synchronized XMLInputFactory getXMLInputFactory() {
        if (inputFactory == null) {
            inputFactory = XMLInputFactory.newInstance();
        }
        return inputFactory;
    }

    public MetalinkParser createMetalinkParser(InputStream in) throws XMLStreamException {
        return createMetalinkParser(getXMLInputFactory().createXMLStreamReader(in));
    }

    public MetalinkParser createMetalinkParser(InputStream in, String encoding) throws XMLStreamException {
        return createMetalinkParser(getXMLInputFactory().createXMLStreamReader(in, encoding));
    }

    public MetalinkParser createMetalinkParser(Reader in) throws XMLStreamException {
        return createMetalinkParser(getXMLInputFactory().createXMLStreamReader(in));
    }

    public MetalinkParser createMetalinkParser(Source source) throws XMLStreamException {
        return createMetalinkParser(getXMLInputFactory().createXMLStreamReader(source));
    }

    public MetalinkParser createMetalinkParser(XMLStreamReader in) {
        return new StaxMetalinkParserStraight(in);
    }

}
