package com.mycompany.a3.Commands;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a3.Game;
import com.mycompany.a3.GameWorld;

/**
 * Created by Sean on 5/3/2016.
 */
public class ToggleGameModeCommand extends Command {
    private Game game;
    public ToggleGameModeCommand(Game g) {
        super("Pause");
        game = g;
    }
    public void actionPerformed(ActionEvent e) {
        if (game.getGameRunning() == true) {
            game.gamePause();
            game.getP().setText("Play");
        } else if (game.getGameRunning() == false) {
            game.gamePlay();
            game.getP().setText("Pause");
        }

    }
}
