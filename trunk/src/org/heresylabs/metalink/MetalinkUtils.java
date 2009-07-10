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

import java.io.InputStream;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Collection of util methods to work with metalink objects.
 *
 * @author Aekold Helbrass <Helbrass@gmail.com>
 */
public class MetalinkUtils {

    private MetalinkUtils() {
    }

    // <editor-fold defaultstate="collapsed" desc=" resources methods ">
    public List<MetalinkUrl> resourcesForLocation(List<MetalinkUrl> resources, String location) {
        if (resources == null || resources.isEmpty()) {
            return null;
        }
        List<MetalinkUrl> result = new ArrayList<MetalinkUrl>(resources.size());
        for (MetalinkUrl u : resources) {
            if (location.equals(u.getLocation())) {
                result.add(u);
            }
        }
        return result;
    }

    public List<MetalinkUrl> resourcesOfType(List<MetalinkUrl> resources, String... types) {
        if (resources == null || resources.isEmpty()) {
            return null;
        }
        HashSet<String> typesSet = new HashSet<String>();
        for (int i = 0; i < types.length; i++) {
            typesSet.add(types[i]);
        }
        return resourcesOfType(resources, types);
    }

    public List<MetalinkUrl> resourcesOfType(List<MetalinkUrl> resources, Set<String> types) {
        if (resources == null || resources.isEmpty()) {
            return null;
        }
        List<MetalinkUrl> result = new ArrayList<MetalinkUrl>(resources.size());
        for (MetalinkUrl u : resources) {
            if (types.contains(u.getType())) {
                result.add(u);
            }
        }
        return result;
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc=" verification methods ">
    static boolean verify(InputStream in, MetalinkFile file) {
        return verify(in, file, 0, file.getSize());
    }

    static boolean verify(InputStream in, MetalinkFile file, long from, long offset) {
        // TODO implement
        // TODO workaround offset is 0
        return true;
    }

    /**
     * Char array required for byte-to-hexstring operation
     */
    private static final char[] hexChar = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    /**
     * Translate byte array to hex string. This operation required because of MessageDigest returns byte array, but
     * hashes in metalink file are stored as strings.
     * @param b byte array with hashsum.
     * @return hex-string representation of byte array.
     */
    private static String byteArrayToHexString(byte[] b) {
        StringBuilder sb = new StringBuilder(b.length * 2);
        for (int i = 0; i < b.length; i++) {
            sb.append(hexChar[(b[i] & 0xf0) >>> 4]);
            sb.append(hexChar[b[i] & 0x0f]);
        }
        return sb.toString();
    }
    // </editor-fold>

}
