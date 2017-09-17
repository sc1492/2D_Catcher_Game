package com.mycompany.a3;

import java.util.Vector;

import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;


/**
 * Created by Sean on 2/22/2016.
 */
public class Net extends Catcher implements IDrawable {


    public String toString() {
    	Vector<Integer>temp = Util.intToRGB(getColor());
        return "Net: loc=" + getLocation().getX() + "," + getLocation().getY() + " color=[" + temp.get(0) + "," + temp.get(1) + "." + temp.get(2) + "]" + " size="+getSize();
    }


    @Override
    public void draw(Graphics g, Point pCmpRelPrnt) {
        int x = (int) (pCmpRelPrnt.getX() + (int)this.getLocation().getX() - (super.getSize()/2));
        int y = (int) (pCmpRelPrnt.getY() + (int)this.getLocation().getY() - (super.getSize()/2));

        g.setColor(super.getColor());
        g.drawRect(x,y,(int) super.getSize(),(int)super.getSize());
    }
}
