package com.mycompany.a3.Commands;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a3.GameWorld;

public class FightCommand extends Command{
	private GameWorld gWorld;
	public FightCommand(GameWorld gw) {
		super("Fight");
		gWorld = gw;
	}
	
	public void actionPerformed(ActionEvent e) {
		if (gWorld.isGamePaused() == true) {
			this.setEnabled(false);
		} else if (gWorld.isGamePaused() == false) {
			gWorld.animalFight();
		}
	}
}
