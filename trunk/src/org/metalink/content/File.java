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
public class File {
    
    private static final int hashPart = "MetalinkFile".hashCode();
    
    private String name;
    private String os;

    @Override
    public String toString() {
        return getClass().getName() + '[' + name + ']';
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        // TODO
        return super.clone();
    }

    @Override
    public int hashCode() {
        return hashPart + (name != null ? name.hashCode() : 0);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final File other = (File) obj;
        if (this.name != other.name && (this.name == null || !this.name.equals(other.name))) {
            return false;
        }
        return true;
    }

}
