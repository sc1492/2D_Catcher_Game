package com.mycompany.a3;

/**
 * Created by Sean on 3/15/2016.
 */
public interface ICollection {
    void add(GameObject gameObject);

    IIterator getIterator();
}
