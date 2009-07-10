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

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 *
 * @author <a href="mailto:paranoid.tiberiumlabs@gmail.com">Paranoid</a>
 */
public class DynamicChecker extends DefaultHandler {
    
    public static final String TYPE_IS_DYNAMIC = "Metalink type is dymanic!";

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if ("metalink".equals(qName)) {
            if ("dynamic".equals(attributes.getValue("type"))) {
                throw new RuntimeException(TYPE_IS_DYNAMIC);
            }
        }
    }

}
