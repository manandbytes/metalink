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

import java.io.OutputStream;
import java.io.Writer;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;

/**
 *
 * @author Aekold Helbrass <Helbrass@gmail.com>
 */
public class MetalinkWriterFactory {

    private XMLOutputFactory outputFactory;

    private MetalinkWriterFactory() {
    }

    public static MetalinkWriterFactory newInstance() {
        return new MetalinkWriterFactory();
    }

    private synchronized XMLOutputFactory getXMLOutputFactory() {
        if (outputFactory == null) {
            outputFactory = XMLOutputFactory.newInstance();
        }
        return outputFactory;
    }

    public MetalinkWriter createMetalinkWriter(Writer out) throws XMLStreamException {
        return createMetalinkWriter(getXMLOutputFactory().createXMLStreamWriter(out));
    }

    public MetalinkWriter createMetalinkWriter(OutputStream out) throws XMLStreamException {
        return createMetalinkWriter(getXMLOutputFactory().createXMLStreamWriter(out));
    }

    public MetalinkWriter createMetalinkWriter(OutputStream out, String encoding) throws XMLStreamException {
        return createMetalinkWriter(getXMLOutputFactory().createXMLStreamWriter(out, encoding));
    }

    public MetalinkWriter createMetalinkWriter(XMLStreamWriter out) {
        return new StaxMetalinkWriter(out);
    }

}
