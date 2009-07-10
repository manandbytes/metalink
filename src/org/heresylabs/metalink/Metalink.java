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

import java.util.List;

/**
 * Class stands for Java representation of single .metalink file.
 *
 * @author Aekold Helbrass <Helbrass@gmail.com>
 */
public class Metalink {

    // <editor-fold defaultstate="collapsed" desc=" required ">
    protected MetalinkHeader header;
    protected List<MetalinkFile> files;
    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc=" recommended ">
    protected String identity;
    protected String version;
    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc=" optional ">
    protected MetalinkPublisher publisher;
    protected MetalinkLicense license;
    protected String description;
    protected String tags;
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc=" constructors ">
    public Metalink() {
    }

    public Metalink(MetalinkHeader header, List<MetalinkFile> files) {
        this.header = header;
        this.files = files;
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc=" getters/setters ">
    public List<MetalinkFile> getFiles() {
        return files;
    }

    public void setFiles(List<MetalinkFile> files) {
        this.files = files;
    }

    public MetalinkHeader getHeader() {
        return header;
    }

    public void setHeader(MetalinkHeader header) {
        this.header = header;
    }

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }

    public MetalinkPublisher getPublisher() {
        return publisher;
    }

    public void setPublisher(MetalinkPublisher publisher) {
        this.publisher = publisher;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public MetalinkLicense getLicense() {
        return license;
    }

    public void setLicense(MetalinkLicense license) {
        this.license = license;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }
    // </editor-fold>
}
