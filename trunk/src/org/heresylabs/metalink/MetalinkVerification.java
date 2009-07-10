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

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * TODO signature is not yet supported
 *
 * @author Aekold Helbrass <Helbrass@gmail.com>
 */
public class MetalinkVerification {

    private final Map<String, String> hashes = new Hashtable<String, String>();
    private List<MetalinkVerificationPiece> pieces = new ArrayList<MetalinkVerificationPiece>();

    public void addHash(String type, String value) {
        hashes.put(type, value);
    }

    public String getHash(String type) {
        return hashes.get(type);
    }

    public String[] getAvailableHashTypes() {
        if (hashes.isEmpty()) {
            return null;
        }
        String[] result = new String[hashes.size()];
        Iterator<String> keysIterator = hashes.keySet().iterator();
        int i = 0;
        while (keysIterator.hasNext()) {
            result[i] = keysIterator.next();
            i++;
        }
        return result;
    }

    public boolean containsHash(String type) {
        return hashes.containsKey(type);
    }

    public void addPiece(MetalinkVerificationPiece piece) {
        this.pieces.add(piece);
    }
    public List<MetalinkVerificationPiece> getPieces() {
        return pieces;
    }

}
