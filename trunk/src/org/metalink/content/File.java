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

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author <a href="mailto:paranoid.tiberiumlabs@gmail.com">Paranoid</a>
 */
public class File {

    private static final Comparator<Url> PREFERENCE_COMPARATOR = new PreferenceComparator();
    private static final int hashPart = "MetalinkFile".hashCode();

    public File(String name) {
        this.name = name;
    }

    private String name;
    private String os;
    private long size;
    private Pieces pieces;

    private ArrayList<Url> urls = new ArrayList<Url>();
    private Map<String, String> hashes = new HashMap<String, String>();

    public void addUrl(String link, String type, String location, String preference) {
        urls.add(new Url(link, type, location, Integer.valueOf(preference)));
    }

    public void addHash(String type, String hash) {
        if (type != null && type.length() > 0 && hash != null && hash.length() > 0) {
            hashes.put(type, hash);
        }
    }

    public void setPieces(Pieces pieces) {
        this.pieces = pieces;
    }

    public Pieces getPieces() {
        return pieces;
    }

    public Map<String, String> getHashes() {
        return hashes;
    }

    // <editor-fold defaultstate="collapsed" desc=" bean object implementation ">
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

    public String getName() {
        return name;
    }

    public String getOs() {
        return os;
    }

    public void setOs(String os) {
        this.os = os;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public List<? extends Url> getUrls() {
        return urls;
    }

    public String getHash(String type) {
        return hashes.get(type);
    }

    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc=" different comparators and sorted getters ">
    private static class PreferenceComparator implements Comparator<Url> {

        public int compare(Url o1, Url o2) {
            return o1.getPreference() - o2.getPreference();
        }

    }

    /**
     * Method to get all types available for this file.
     * @return new String[] with all available types.
     */
    public String[] getTypes() {
        if (urls != null && !urls.isEmpty()) {
            HashSet<String> typesSet = new HashSet<String>();
            for (int i = 0; i < urls.size(); i++) {
                typesSet.add(urls.get(i).getType());
            }
            return typesSet.toArray(new String[typesSet.size()]);
        }
        return null;
    }

    /**
     * Method to get all locations available for this file.
     * @return new String[] with all available locations.
     */
    public String[] getLocations() {
        if (urls != null && !urls.isEmpty()) {
            HashSet<String> locationSets = new HashSet<String>();
            for (int i = 0; i < urls.size(); i++) {
                locationSets.add(urls.get(i).getLocation());
            }
            return locationSets.toArray(new String[locationSets.size()]);
        }
        return null;
    }
    
    public List<Url> getUrlSortedByPreference() {
        if (urls != null && !urls.isEmpty()) {
            ArrayList<Url> sortedList = new ArrayList<Url>(urls);
            Collections.sort(sortedList, PREFERENCE_COMPARATOR);
            return sortedList;
        }
        return null;
    }
    // </editor-fold>

}
