package com.mycompany.a3;

/**
 * Created by Sean on 3/15/2016.
 */
public interface IIterator {
    boolean hasNext();

    Object getNext();

    void remove();
}
