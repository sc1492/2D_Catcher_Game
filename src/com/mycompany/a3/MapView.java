package com.mycompany.a3;

import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Container;
import com.codename1.ui.Graphics;
import com.codename1.ui.plaf.Border;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by seanpc on 3/3/16.
 */
public class MapView extends Container implements Observer{
	private GameWorld gameWorld;
    private int gWidth = 1000;
    private int gHeight = 610;

    public void setMapWidth() {
        this.setWidth(gWidth);
    }

    public void setMapHeight() {
        this.setHeight(gHeight);
    }

//    public int getWidth() {
//        return gWidth;
//    }
//
//    public int getHeight() {
//        return gHeight;
//    }

    public MapView(Observable obs) {
        setMapHeight();
        setMapWidth();
        this.gameWorld = (GameWorld) obs;
		obs.addObserver(this);
        this.getAllStyles().setBorder(Border.createLineBorder(4, ColorUtil.BLUE));
    }

//	public MapView() {
//
//	}

    public void update (Observable o, Object arg) {
//        repaint();
    }

	public void paint(Graphics g) {
        super.paint(g);
        Point point = new Point(getX(), getY());
        IIterator itr = gameWorld.iterator();
        while (itr.hasNext()) {
            Object tempObj = itr.getNext();
            if (tempObj instanceof GameObject) {
                GameObject tempGO = (GameObject) tempObj;
                tempGO.draw(g, point);
//                System.out.println(tempGO.getLocation().getX() + ", " + tempGO.getLocation().getY());
//                System.out.println("DRAW TEST");
            }
        }


//        GameObjectCollection gameObjects = gameWorld.getCollection();
//        for (int i = 0; i < gameObjects.size(); i++) {
//            if (gameObjects.get(i) instanceof Net) {
//                ((Net) gameObjects.get(i)).draw(g, gameObjects.get(i).getLocation());
//                                System.out.println("DRAWING");
//
//            }
//            if (gameObjects.get(i) instanceof Dog) {
//                ((Dog) gameObjects.get(i)).draw(g, gameObjects.get(i).getLocation());
//                System.out.println("DRAWING");
//
//            }
//            if (gameObjects.get(i) instanceof Cat) {
//                ((Cat) gameObjects.get(i)).draw(g, gameObjects.get(i).getLocation());
//                System.out.println("DRAWING");
//
//            }
//
//        }
	}

    public void pointerPressed(int x, int y) {
        if (gameWorld.isGamePaused() == true) {
            x = x - getParent().getAbsoluteX();
            y = y - getParent().getAbsoluteY();

            Point pPtrRelPrnt = new Point(x,y);
            Point pCmpRelPrnt = new Point(getX(),getY());
            IIterator iterator = gameWorld.iterator();
            while (iterator.hasNext()) {
                Object tempObj = iterator.getNext();
                if (tempObj instanceof ISelectable) {
                    ISelectable selectableAnimal = (ISelectable) tempObj;
                    if (selectableAnimal.contains(pPtrRelPrnt, pCmpRelPrnt)) {
                        selectableAnimal.setSelected(true);
                    } else {
                        selectableAnimal.setSelected(false);
                    }
                }
            }
            repaint();
        }
    }
}
