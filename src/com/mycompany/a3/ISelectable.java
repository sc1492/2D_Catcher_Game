package com.mycompany.a3;

import com.codename1.charts.models.Point;
import com.codename1.ui.Graphics;

/**
 * Created by Sean on 4/26/2016.
 */
public interface ISelectable {
    void setSelected(boolean yesNo);
    boolean isSelected();
    boolean contains(Point pPtrRelPrnt, Point pCmpRelPrnt);
    void drawOverlay(Graphics g, Point pCmpRelPrnt);
}
