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

import java.util.List;
import java.util.Map;
import org.metalink.content.File;
import org.metalink.content.Publisher;

/**
 * Class, representing Metalink file.
 *
 * @author <a href="mailto:paranoid.tiberiumlabs@gmail.com">Paranoid</a>
 */
public class Metalink {
    
    public static final int hashCode = "Metalink".hashCode();
    
    private Publisher publisher;
    private String description;
    private String tags;
    private String identity;
    private String version;
    private Map<String, String> params;
    private List<File> files;

    public Metalink(Map<String, String> params, Publisher publisher, String description, String tags, String identity, String version, List<File> files) {
        this.params = params;
        this.publisher = publisher;
        this.description = description;
        this.tags = tags;
        this.identity = identity;
        this.version = version;
        this.files = files;
    }

    public List<? extends File> getAllFiles() {
        return files;
    }

    public String getDescription() {
        return description;
    }

    public String getIdentity() {
        return identity;
    }

    public Publisher getPublisher() {
        return publisher;
    }

    public String getTags() {
        return tags;
    }

    public String getVersion() {
        return version;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return new Metalink(params, publisher, description, tags, identity, version, files);
    }

    @Override
    public int hashCode() {
        return hashCode + (identity != null ? identity.hashCode() : 0) + (version != null ? version.hashCode() : 0);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Metalink other = (Metalink) obj;
        if (this.identity != other.identity && (this.identity == null || !this.identity.equals(other.identity))) {
            return false;
        }
        if (this.version != other.version && (this.version == null || !this.version.equals(other.version))) {
            return false;
        }
        if (this.files != other.files && (this.files == null || !this.files.equals(other.files))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getName()).append('[');
        sb.append("identity=").append(identity).append(';');
        sb.append("version=").append(version).append(';');
        sb.append("files=").append(files.size()).append(']');
        return sb.toString();
    }

}
