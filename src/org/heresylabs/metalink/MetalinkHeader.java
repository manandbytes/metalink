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

/**
 *
 * @author Aekold Helbrass <Helbrass@gmail.com>
 */
public class MetalinkHeader {

    // <editor-fold defaultstate="collapsed" desc=" required ">
    protected String version;
    protected String xmlns;
    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc=" recommended ">
    protected String origin;
    protected String type;
    protected String pubdate;
    protected String refreshdate;
    protected String generator;
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc=" getters/setters ">
    public String getGenerator() {
        return generator;
    }

    public void setGenerator(String generator) {
        this.generator = generator;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getPubdate() {
        return pubdate;
    }

    public void setPubdate(String pubdate) {
        this.pubdate = pubdate;
    }

    public String getRefreshdate() {
        return refreshdate;
    }

    public void setRefreshdate(String refreshdate) {
        this.refreshdate = refreshdate;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getXmlns() {
        return xmlns;
    }

    public void setXmlns(String xmlns) {
        this.xmlns = xmlns;
    }
    // </editor-fold>

}
