package com.mycompany.a3;

import com.codename1.charts.models.Point;
import com.codename1.util.MathUtil;

import java.util.ArrayList;

import static com.codename1.util.MathUtil.atan2;

/**
 * Created by Sean on 2/21/2016.
 */
public abstract class Animal extends GameObject implements IMovable, ICollider {

    private int speed;
    private int direction;

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getDirection() {
        return direction;
    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public void move(int elapsedTime, int worldWidth, int worldHeight) {
        int deltaX = (int) (Math.cos(getDirection()) * speed);
        int deltaY = (int) (Math.sin(getDirection()) * speed);
        int x = (int) (getLocation().getX() + deltaX);
        int y = (int) (getLocation().getY() + deltaY);
        setLocation(new Point(x, y));
        if(getLocation().getX() >= worldWidth - getSize() / 2){
            setDirection((int) MathUtil.atan2(deltaY, -deltaX));
            setLocation(new Point(worldWidth - getSize() / 2, getLocation().getY()));
        }else if(getLocation().getX() <= getSize() / 2){
            setDirection((int) MathUtil.atan2(deltaY, -deltaX));
        }else if(getLocation().getY() >= worldHeight - getSize() / 2){
            setDirection((int) MathUtil.atan2(-deltaY, deltaX));
            setLocation(new Point(getLocation().getX(),worldHeight - getSize() / 2));
        }else if (getLocation().getY() <= getSize() / 2){
            setDirection((int) MathUtil.atan2(-deltaY, deltaX));
        }

    }

    /** {@inheritDoc} */
    @Override
    public String toString() {
        return "Animal{" +
                "direction=" + direction +
                ", speed=" + speed +
                '}' + super.toString();
    }
}
