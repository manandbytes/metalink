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

import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamReader;

/**
 *
 * @author Aekold Helbrass <Helbrass@gmail.com>
 */
public class StaxMetalinkParser implements MetalinkParser, XMLStreamConstants {

    private final XMLStreamReader in;

    StaxMetalinkParser(XMLStreamReader in) {
        this.in = in;
    }

    @Override
    public Metalink parse() throws MetalinkFormatException {
        try {

            while (in.hasNext()) {
                int event = in.nextTag();
                
            }

            return null;
        }
        catch (Exception e) {
            throw new MetalinkFormatException(e);
        }
    }

}
