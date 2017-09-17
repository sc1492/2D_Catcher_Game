package com.mycompany.a3;

import com.codename1.media.Media;
import com.codename1.media.MediaManager;
import com.codename1.ui.Display;

import java.io.InputStream;

/**
 * Created by seanpc on 4/29/16.
 */
public class Sound {
    private Media soundEffect;
    public Sound(String fileName) {
        try{
            InputStream is = Display.getInstance().getResourceAsStream(getClass(),
                    "/"+fileName);
            soundEffect = MediaManager.createMedia(is, "audio/wav");
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    public void play() {
//start playing the sound from time zero (beginning of the sound file)
        soundEffect.setTime(0);
        soundEffect.play();
    }
}
