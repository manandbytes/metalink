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
 * TODO some elements are unsupported:<br/>
 * - relations<br/>
 * - changelog<br/>
 * - copyright<br/>
 * - multimedia<br/>
 * - screenshot<br/>
 * - upgrade<br/>
 * - bittorrent<br/>
 *
 * @author Aekold Helbrass <Helbrass@gmail.com>
 */
public class MetalinkFile {

    // <editor-fold defaultstate="collapsed" desc=" required ">
    protected String name;
    protected List<MetalinkUrl> resources;
    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc=" recommended ">
    protected MetalinkVerification verification;
    protected long size;
    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc=" optional ">
    protected int resourcesMaxconnections;
    protected String description;
    protected String logo;
    protected String tags;
    protected String language;
    protected String os;
    protected String mimetype;
    protected String releasedate;
    protected String version;
    protected MetalinkPublisher publisher;
    protected MetalinkLicense license;
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc=" constructors ">
    public MetalinkFile() {
    }

    public MetalinkFile(String name) {
        this.name = name;
    }

    public MetalinkFile(String name, List<MetalinkUrl> resources) {
        this.name = name;
        this.resources = resources;
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc=" getters/setters ">
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public MetalinkLicense getLicense() {
        return license;
    }

    public void setLicense(MetalinkLicense license) {
        this.license = license;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public String getMimetype() {
        return mimetype;
    }

    public void setMimetype(String mimetype) {
        this.mimetype = mimetype;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOs() {
        return os;
    }

    public void setOs(String os) {
        this.os = os;
    }

    public MetalinkPublisher getPublisher() {
        return publisher;
    }

    public void setPublisher(MetalinkPublisher publisher) {
        this.publisher = publisher;
    }

    public String getReleasedate() {
        return releasedate;
    }

    public void setReleasedate(String releasedate) {
        this.releasedate = releasedate;
    }

    public List<MetalinkUrl> getResources() {
        return resources;
    }

    public void setResources(List<MetalinkUrl> resources) {
        this.resources = resources;
    }

    public int getResourcesMaxconnections() {
        return resourcesMaxconnections;
    }

    public void setResourcesMaxconnections(int resourcesMaxconnections) {
        this.resourcesMaxconnections = resourcesMaxconnections;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public MetalinkVerification getVerification() {
        return verification;
    }

    public void setVerification(MetalinkVerification verification) {
        this.verification = verification;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }
    // </editor-fold>

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final MetalinkFile other = (MetalinkFile) obj;
        if ((this.name == null) ? (other.name != null) : !this.name.equals(other.name)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 67 * hash + (this.name != null ? this.name.hashCode() : 0);
        return hash;
    }

}
