package com.mycompany.a3.Commands;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a3.GameWorld;

public class SoundCommand extends Command{
	private GameWorld gWorld;
	public SoundCommand(GameWorld gw) {
		super("Sound");
		gWorld = gw;
	}
	
	public void actionPerformed(ActionEvent e) {
        gWorld.toggleSoundCheckBox();
        if (gWorld.isGamePaused() == true) {    // if game is in a paused state
            if (gWorld.getSoundCheckBox() == true) {     // if sound is checked off, turn it on
                gWorld.setSound(true);
                gWorld.soundOn();
            } else if (gWorld.getSoundCheckBox() == false) {     // if sound is checked on, turn it off
                gWorld.setSound(false);
                gWorld.soundOff();
            }
        }
        if (gWorld.isGamePaused() == false) {   // if game is in a play state
            if (gWorld.getSound() == false) {   // if sound is off, turn it on
                gWorld.setSound(true);
                gWorld.soundOn();
            } else if (gWorld.getSound() == true) {
                gWorld.setSound(false);
                gWorld.soundOff();
            }
        }
    }
}
