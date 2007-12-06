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

import java.io.InputStream;
import java.io.Reader;
import java.io.StringReader;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.metalink.parser.MetalinkHandler;
import org.metalink.parser.MetalinkParsingException;
import org.xml.sax.InputSource;

/**
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

}
