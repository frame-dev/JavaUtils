package de.framedev.javautils;

import java.util.Map;
import java.util.TreeMap;

/**
 *
 * Create a Custom TreeMap
 * / This Plugin was Created by FrameDev
 * / Package : de.framedev.javautils
 * / ClassName FrameHashMap
 * / Date: 03.04.22
 * / Project: JavaUtils
 * / Copyrighted by FrameDev
 */

public class FrameHashMap<K, V> extends TreeMap<K, V> {

    public K getKey(V object) {
        if (containsValue(object))
            for (Map.Entry<K, V> map : this.entrySet())
                if (map.getValue() == object)
                    return map.getKey();
        return null;
    }
}
