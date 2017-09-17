package com.mycompany.a3;

/**
 * Created by seanpc on 2/22/16.
 */
public interface IGuidable {

    void moveUp(float amount);

    void moveDown(float amount);

    void moveLeft(float amount);

    void moveRight(float amount);

    void jumpToCat(Cat cat);

    void jumpToDog(Dog dog);

}
