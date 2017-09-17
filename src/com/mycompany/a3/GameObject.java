package com.mycompany.a3;

import com.codename1.charts.models.Point;
import com.codename1.ui.Graphics;

/**
 * Created by Sean on 2/11/2016.
 */
public abstract class GameObject implements IDrawable {

//    private float size;
    private int color, size;
    private Point location;

    /**
     * Getter for property 'size'.
     *
     * @return Value for property 'size'.
     */
    public int getSize() {
        return size;
    }

    /**
     * Setter for property 'size'.
     *
     * @param size Value to set for property 'size'.
     */
    public void setSize(int size) {
        this.size = size;
    }

    /**
     * Getter for property 'color'.
     *
     * @return Value for property 'color'.
     */
    public int getColor() {
        return color;
    }

    /**
     * Setter for property 'color'.
     *
     * @param color Value to set for property 'color'.
     */
    public void setColor(int color) {
        this.color = color;
    }

    /**
     * Getter for property 'location'.
     *
     * @return Value for property 'location'.
     */
    public Point getLocation() {
        return location;
    }

    /**
     * Setter for property 'location'.
     *
     * @param location Value to set for property 'location'.
     */
    public void setLocation(Point location) {
        this.location = location;
    }

    public abstract void draw(Graphics g, Point pCmpRelPrnt);

//    @Override
//    public String toString() {
//        return "GameObject{" +
//                "color=" + color +
//                ", size=" + size +
//                ", location=" + location.getX() + "," + location.getY() +
//                '}';
//    }
}
