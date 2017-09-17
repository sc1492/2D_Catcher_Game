package com.mycompany.a3.Commands;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a3.GameWorld;

/**
 * Created by Sean on 5/5/2016.
 */
public class HealCommand extends Command {
    private GameWorld gWorld;

    public HealCommand(GameWorld gw) {
        super("Heal");
        gWorld = gw;
    }

    public void actionPerformed(ActionEvent e) {
        gWorld.dogHeal();
    }
}
