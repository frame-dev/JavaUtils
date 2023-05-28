package de.framedev.javautils;

import java.util.LinkedList;

/**
 * This is a custom LinkedList it contains the method to Replace objects in the list
 * / This Plugin was Created by FrameDev
 * / Package : de.framedev.javautils
 * / ClassName CustomString
 * / Date: 17.06.21
 * / Project: JavaUtils
 * / Copyrighted by FrameDev
 */

@SuppressWarnings("unused")
public class FrameList<T> extends LinkedList<T> {

    /**
     * Replace Data in the List
     *
     * @param oldData the OldData for Replace
     * @param newData the new Data to Replace with the oldData
     */
    public void replace(T oldData, T newData) {
        if (contains(oldData)) {
            set(indexOf(oldData), newData);
        }
    }

    /**
     * Replace Data in the List
     *
     * @param index   the Index in the List for Replace Data
     * @param newData the new Data to Replace with the oldData
     */
    public void replace(int index, T newData) {
        if (get(index) != null) {
            replace(get(index), newData);
        }
    }

}
