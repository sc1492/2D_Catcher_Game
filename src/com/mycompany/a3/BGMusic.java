package com.mycompany.a3;

import com.codename1.media.Media;
import com.codename1.media.MediaManager;
import com.codename1.ui.Display;

import java.io.InputStream;

/**
 * Created by seanpc on 4/29/16.
 */
public class BGMusic implements Runnable {
    private Media music;
    public BGMusic(String fileName){
        try{

            InputStream is = Display.getInstance().getResourceAsStream(getClass(),
                    "/"+fileName);
//attach a runnable to run when media has finished playing
//as the last parameter
            music = MediaManager.createMedia(is, "audio/wav", this);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
    public void pause(){ music.pause();} //pause playing the sound
    public void play(){ music.play();} //continue playing from where we have left off
    //entered when media has finished playing
    public void run() {
//start playing from time zero (beginning of the sound file)
        music.setTime(0);
        music.play();
    }
}
