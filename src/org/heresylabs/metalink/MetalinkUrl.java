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
 * Object that represents {@code url} component in metalink file.<br/>
 * <br/>
 * <b>NOTE:</b> this file implements {@code Comparable} to compare preference of url in the way to most preferable to be
 * at the start of sorted list.
 *
 * @author Aekold Helbrass <Helbrass@gmail.com>
 */
public class MetalinkUrl implements Comparable<MetalinkUrl> {

    // <editor-fold defaultstate="collapsed" desc=" required ">
    protected String value;
    // </editor-fold>
    // <editor-fold defaultstate="collapsed" desc=" optional ">
    protected String type;
    protected int preference;
    protected String location;
    protected int maxconnections;
    // </editor-fold>

    public MetalinkUrl(String value, String type, int preference, String location, int maxconnections) {
        this.value = value;
        this.type = type;
        this.preference = preference;
        this.location = location;
        this.maxconnections = maxconnections;
    }

    public MetalinkUrl(String value, String type, int preference, String location) {
        this.value = value;
        this.type = type;
        this.preference = preference;
        this.location = location;
    }

    public MetalinkUrl(String value, String type, int preference) {
        this.value = value;
        this.type = type;
        this.preference = preference;
    }

    public MetalinkUrl(String value, String type) {
        this.value = value;
        this.type = type;
    }

    public MetalinkUrl(String value) {
        this.value = value;
    }

    public MetalinkUrl() {
    }

    // <editor-fold defaultstate="collapsed" desc=" getters/setters ">
    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getPreference() {
        return preference;
    }

    public void setPreference(int preference) {
        this.preference = preference;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public int getMaxconnections() {
        return maxconnections;
    }

    public void setMaxconnections(int maxconnections) {
        this.maxconnections = maxconnections;
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
        final MetalinkUrl other = (MetalinkUrl) obj;
        if ((this.value == null) ? (other.value != null) : !this.value.equals(other.value)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 11 * hash + (this.value != null ? this.value.hashCode() : 0);
        return hash;
    }

    @Override
    public int compareTo(MetalinkUrl o) {
        return o.getPreference() - preference;
    }

}
