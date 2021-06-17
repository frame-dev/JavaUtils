package de.framedev.javautils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 * / This Plugin was Created by FrameDev
 * / Package : de.framedev.javautils
 * / ClassName CustomString
 * / Date: 17.06.21
 * / Project: JavaUtils
 * / Copyrighted by FrameDev
 */

public class CustomList<E> extends ArrayList<E> implements Serializable {


    public void replace(E oldData, E newData) {
        if(contains(oldData)) {
            set(indexOf(oldData), newData);
        }
    }

    @Override
    public boolean add(E newData) {
        if(!contains(newData)) {
            super.add(newData);
            return true;
        }
        return false;
    }
}
