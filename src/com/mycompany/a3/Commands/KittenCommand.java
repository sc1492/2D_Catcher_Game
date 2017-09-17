package com.mycompany.a3.Commands;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a3.GameWorld;

public class KittenCommand extends Command {
	private GameWorld gWorld;
	public KittenCommand(GameWorld gw) {
		super("Kitten");
		gWorld = gw;
	}
	
	public void actionPerformed(ActionEvent e) {
		gWorld.catCollision();
	}
}
