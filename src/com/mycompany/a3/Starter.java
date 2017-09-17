package com.mycompany.a3;

import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import java.io.IOException;

/**
 * Created by Sean on 2/11/2016.
 */
public class Starter {

    private Form current;

    public void init(Object context) {
        try{
            Resources theme = Resources.openLayered("/theme");
            UIManager.getInstance().setThemeProps(theme.getTheme(theme.getThemeResourceNames()[0]));
       }catch(IOException e){
            e.printStackTrace();
        }
    }

    public void start() {
        if(current != null){
            current.show();
            return;
        }

        new Game();
    }

    public void stop() {
        current = Display.getInstance().getCurrent();
    }

    public void destroy() {

    }
}
