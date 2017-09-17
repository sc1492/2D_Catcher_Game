package com.mycompany.a3;

import com.codename1.ui.Button;
import com.codename1.ui.Form;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;

/**
 * Created by seanpc on 4/29/16.
 */
public class BGMusicForm extends Form implements ActionListener{
    private BGMusic bgMusic;
    private boolean bPause = false;
    public BGMusicForm() {
        Button bButton = new Button("Pause/Play");
//...[stylze and add bButton to the form]
        bButton.addActionListener(this);
        bgMusic = new BGMusic("schemingWeasel.wav");
        bgMusic.play();
    }
    public void actionPerformed(ActionEvent evt) {
        bPause = !bPause;
        if (bPause)
            bgMusic.pause();
        else
            bgMusic.play();
    }
}
