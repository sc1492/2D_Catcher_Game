package com.mycompany.a3;

import com.codename1.charts.models.Point;

/**
 * Created by Sean on 2/21/2016.
 */
public abstract class Catcher extends GameObject implements IGuidable {
    public void moveUp(float amount) {
        setLocation(new Point(getLocation().getX(), getLocation().getY() + amount));
    }


    public void moveDown(float amount) {
        setLocation(new Point(getLocation().getX(), getLocation().getY() - amount));
    }


    public void moveLeft(float amount) {
        setLocation(new Point(getLocation().getX() - amount, getLocation().getY()));

    }


    public void moveRight(float amount) {
        setLocation(new Point(getLocation().getX() + amount, getLocation().getY()));

    }


    public void jumpToCat(Cat cat) {
        setLocation(cat.getLocation());
    }


    public void jumpToDog(Dog dog) {
        setLocation(dog.getLocation());
    }
}
