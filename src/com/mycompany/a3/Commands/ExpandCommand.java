package com.mycompany.a3.Commands;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a3.GameWorld;

public class ExpandCommand extends Command {
	private GameWorld gWorld;
	public ExpandCommand(GameWorld gw) {
		super("Expand");
		gWorld = gw;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (gWorld.isGamePaused() == true) {
			this.setEnabled(false);
		} else if (gWorld.isGamePaused() == false) {
			gWorld.expandNet();
		}
	}
}
