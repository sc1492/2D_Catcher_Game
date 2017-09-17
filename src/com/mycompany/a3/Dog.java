package com.mycompany.a3;

import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;
import com.codename1.maps.BoundingBox;
import com.codename1.media.Media;
import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Dimension;
import com.codename1.ui.geom.Rectangle;

import java.util.Random;
import java.util.Vector;


/**
 * Created by Sean on 2/21/2016.
 */
public class Dog extends Animal implements IDrawable, ISelectable {
    private Random rand;
    private Vector<Integer> collidedWith;

    private int scratches;
    private int boundingX, boundingY;
    private boolean isSelected;

    public Dog(float x, float y, int size, int direction, int color, int speed) {

        isSelected = false;
        collidedWith = new Vector<>();

        super.setSize(size);
        super.setDirection(direction);
        super.setColor(color);
        super.setLocation(new Point(x,y));
        super.setSpeed(speed);
        this.setScratches(0);
    }

    /**
     * Getter for property 'scratches'.
     *
     * @return Value for property 'scratches'.
     */
    public int getScratches() {
        return scratches;
    }

    /**
     * Setter for property 'scratches'.
     *
     * @param scratches Value to set for property 'scratches'..
     */
    public void setScratches(int scratches) {
        if (this.scratches < 5) {
            this.scratches = scratches;
        }
    }

    @Override
    public String toString() {
    	Vector<Integer>temp = Util.intToRGB(getColor());
        return "Dog: loc=" + getLocation().getX() + "," + getLocation().getY() + " color=[" + temp.get(0) + ","
                + temp.get(1) + "," + temp.get(2) + "]" + " size=" + getSize()
                + " speed=" + getSpeed() + " dir=" + getDirection() + " scratches=" + getScratches();
    }

    @Override
    public void draw(Graphics g, Point pCmpRelPrnt) {
        int x = (int) (pCmpRelPrnt.getX() + (int)this.getLocation().getX()-(super.getSize()/2));
        int y = (int) (pCmpRelPrnt.getY() + (int)this.getLocation().getY() -(super.getSize()/2));
        g.setColor(super.getColor());
        if (isSelected == true) {
            g.drawArc(x, y, (int) super.getSize(), (int) super.getSize(), 0, 360);
        } else if (isSelected == false) {
            g.fillArc(x, y, (int) super.getSize(), (int) super.getSize(), 0, 360);
        }
        boundingX = x;
        boundingY = y;
    }

    @Override
    public boolean collidesWith(ICollider object) {
        if(object instanceof Cat){
            Cat cat = (Cat) object;
            //this
            Rectangle rectangleDog = new Rectangle(boundingX,boundingY,getSize(),getSize());
            Rectangle rectangleCat = new Rectangle(cat.getBoundingX() - cat.getSize() / 2,cat.getBoundingY() - cat.getSize() / 2,getSize(),getSize());
            if (cat.getCollidedWith().contains(hashCode())) {
                return false;
            }
            if(rectangleDog.intersects(rectangleCat)){
                cat.getCollidedWith().add(hashCode());
//                System.out.println("dog collided with cat");
//                setDirection((int) (getDirection() - 105));
                return true;
            }
        }
        return false;
    }

    @Override
    public void handleCollision(ICollider object, GameWorld gameWorld) {
        if (object instanceof Cat) {
            Cat cat = (Cat) object;
            cat.setDirection((int) cat.getDirection() * -1);
            this.setScratches(scratches + 1);
            if (this.getSpeed() > 1) {
                this.setSpeed(super.getSpeed() - 1);
            }
            this.setColor(dogColorChanger(this.scratches));
            System.out.println("dog collided with cat");
            this.setDirection((int) (getDirection() * -1));
            System.out.println("dog scratches: " + this.scratches);
            System.out.println("dog speed: " + this.getSpeed());
            if (this.getScratches() < 5) {
                gameWorld.animalFight();
            }
        }
    }

    public int dogColorChanger(int scratchValue) {
        switch (scratchValue) {
            case 0:
                return ColorUtil.rgb(222,184,135);
            case 1:
                return ColorUtil.rgb(244,164,96);
            case 2:
                return ColorUtil.rgb(210,105,3);
            case 3:
                return ColorUtil.rgb(139,69,19);
            case 4:
                return ColorUtil.rgb(165,42,42);
            case 5:
                return ColorUtil.rgb(128,0,0);
            default:
                return ColorUtil.rgb(222,184,135);
        }
    }

    /**
     * Getter for property 'boundingX'.
     *
     * @return Value for property 'boundingX'.
     */
    public int getBoundingX() {
        return boundingX;
    }

    /**
     * Setter for property 'boundingX'.
     *
     * @param boundingX Value to set for property 'boundingX'.
     */
    public void setBoundingX(int boundingX) {
        this.boundingX = boundingX;
    }

    /**
     * Getter for property 'boundingY'.
     *
     * @return Value for property 'boundingY'.
     */
    public int getBoundingY() {
        return boundingY;
    }

    /**
     * Setter for property 'boundingY'.
     *
     * @param boundingY Value to set for property 'boundingY'.
     */
    public void setBoundingY(int boundingY) {
        this.boundingY = boundingY;
    }

    @Override
    public void setSelected(boolean yesNo) {
        isSelected = yesNo;
    }

    @Override
    public boolean isSelected() {
        return isSelected;
    }

    @Override
    public boolean contains(Point pPtrRelPrnt, Point pCmpRelPrnt) {
        int x = (int) pPtrRelPrnt.getX();
        int y = (int) pPtrRelPrnt.getY();
        Rectangle rectangleDog = new Rectangle(boundingX,boundingY,getSize(),getSize());
        return rectangleDog.intersects(new Rectangle(x,y,1,1));
    }

    @Override
    public void drawOverlay(Graphics g, Point pCmpRelPrnt) {

    }

    public void heal() {
        setScratches(0);
        setColor(ColorUtil.rgb(222,184,135));
        setSpeed(6);
    }


}
