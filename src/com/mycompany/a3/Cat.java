package com.mycompany.a3;

import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;
import com.codename1.ui.geom.Rectangle;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Vector;

/**
 * Created by Sean on 2/21/2016.
 */
public class Cat extends Animal implements IDrawable {
    private Random rand;
    private int boundingX, boundingY;
    private Vector<Integer> collidedWith,collidedWithCat;
    private boolean checkKitten;
    private Timer kittenTimer;


    public Cat(float x, float y, int size, int direction, int color, int speed, boolean isKitten) {

        kittenTimer = new Timer();
        kittenTimer.schedule(new ChangeToCat(this), 8000);

        collidedWith = new Vector<>();
        collidedWithCat = new Vector<>();

        checkKitten = isKitten;

        super.setSize(size);
        super.setColor(color);
        super.setDirection(direction);
        super.setLocation(new Point(x,y));
        super.setSpeed(speed);
    }
    public Cat() {

    }

    public void setKitten(boolean checkKitten) {
        this.checkKitten = checkKitten;
    }
    public boolean getKitten() {
        return checkKitten;
    }


    public String toString() {
        Vector<Integer> temp = Util.intToRGB(getColor());
        return "Cat: loc=" + getLocation().getX() + "," + getLocation().getY() + " color=[" + temp.get(0) + ","
                + temp.get(1) + "," + temp.get(2) + "]" + " size=" + getSize()
                + " speed=" + getSpeed() + " dir=" + getDirection();
    }

    @Override
    public void draw(Graphics g, Point pCmpRelPrnt) {
        int x = (int) (pCmpRelPrnt.getX() + (int)this.getLocation().getX() - (super.getSize()/2)) + (getSize() / 2);
        int y = (int) (pCmpRelPrnt.getY() + (int)this.getLocation().getY() - (super.getSize()/2)) + (getSize() / 2);
        int[] xArr = {x -(super.getSize()/2),x + (super.getSize()/2),x};
        int[] yArr = {y -(super.getSize()/2),y - (super.getSize()/2), y + (super.getSize()/2)};
        g.setColor(super.getColor());
        g.fillPolygon(xArr, yArr, 3);
        boundingX = x;
        boundingY = y;
    }

    @Override
    public boolean collidesWith(ICollider object) {
        if(object instanceof Dog){
            Dog dog = (Dog) object;
            Rectangle rectangleDog = new Rectangle(dog.getBoundingX(),dog.getBoundingY(),dog.getSize(),dog.getSize());
            Rectangle rectangleCat = new Rectangle(getBoundingX() - getSize() / 2,getBoundingY() - getSize() / 2,getSize(),getSize());
            if (collidedWith.contains(dog.hashCode())) {
                return false;
            }
            if(rectangleDog.intersects(rectangleCat)){
                collidedWith.add(dog.hashCode());
                return true;
            }
        }
        if (object instanceof Cat) {
            Cat cat = (Cat) object;
            Rectangle rectangleCat = new Rectangle(getBoundingX() - getSize() / 2,getBoundingY() - getSize() / 2,getSize(),getSize());
            Rectangle rectangleCat2 = new Rectangle(cat.getBoundingX() - cat.getSize() / 2,cat.getBoundingY() - cat.getSize() / 2,cat.getSize(),cat.getSize());
            if (collidedWithCat.contains(cat.hashCode()) || cat.collidedWithCat.contains(hashCode())) {
                return false;
            }
             else if (rectangleCat.intersects(rectangleCat2)) {
                collidedWithCat.add(cat.hashCode());
                cat.collidedWithCat.add(hashCode());
                return true;
            }

        }
        return false;
    }
    @Override
    public void handleCollision(ICollider object, GameWorld gameWorld) {
        if (object instanceof Cat) {
            Cat cat = (Cat) object;
            if (cat.checkKitten == true) {

            } else {
//                setColor(ColorUtil.rgb(124, 124, 145)); used for testing
                gameWorld.createKitten();
                this.setDirection((int) (getDirection() * -1));
                cat.setDirection((int) (cat.getDirection() * -1));      // originally minus 105
            }
        }
        if (object instanceof Dog) {
            Dog dog = (Dog) object;
            if (dog.getSpeed() > 1) {
                dog.setSpeed(dog.getSpeed() - 1);
            }
            dog.setDirection((int) dog.getDirection() * -1);
            dog.setScratches(dog.getScratches() + 1);
            dog.setColor(dog.dogColorChanger(dog.getScratches()));
            if (dog.getScratches() < 5) {
                gameWorld.animalFight();
            }
//            setColor(ColorUtil.rgb(0,0,0)); used for testing
            System.out.println("dog scratches: " + dog.getScratches());
            System.out.println("dog speed: " + dog.getSpeed());
            System.out.println("cat collided with dog");
            setDirection((int) (getDirection() * -1));
        }

    }

    public Vector<Integer> getCollidedWithCat() {
        return collidedWithCat;
    }

    public void setCollidedWithCat(Vector<Integer> collidedWithCat) {
        this.collidedWithCat = collidedWithCat;
    }

    public Vector<Integer> getCollidedWith() {
        return collidedWith;
    }

    public void setCollidedWith(Vector<Integer> collidedWith) {
        this.collidedWith = collidedWith;
    }

    public int getBoundingX() {
        return boundingX;
    }

    public void setBoundingX(int boundingX) {
        this.boundingX = boundingX;
    }

    public int getBoundingY() {
        return boundingY;
    }

    public void setBoundingY(int boundingY) {
        this.boundingY = boundingY;
    }

    // This class changes a kitten into a cat after 8 seconds has passed since creation
    private class ChangeToCat extends TimerTask {
        Cat kitten = new Cat();
        public ChangeToCat(GameObject o) {
            kitten = (Cat) o;
        }

        @Override
        public void run() {
            System.out.println("Kitten changed to Cat!");
            kitten.setKitten(false);
            kitten.setColor(ColorUtil.rgb(204,204,0));
        }

    }

}
