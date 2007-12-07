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

package org.metalink.content;

/**
 *
 * @author <a href="mailto:paranoid.tiberiumlabs@gmail.com">Paranoid</a>
 */
public class Url {

    private String url;
    private String type;
    private String location;
    private int preference;

    public Url(String url, String type, String location, int preference) {
        this.url = url;
        this.type = type;
        this.location = location;
        this.preference = preference;
    }

    public String getLocation() {
        return location;
    }

    public int getPreference() {
        return preference;
    }

    public String getType() {
        return type;
    }

    public String getUrl() {
        return url;
    }

}
