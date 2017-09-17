package com.mycompany.a3.Commands;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a3.GameWorld;

public class ExitCommand extends Command {
	private GameWorld gWorld;
	public ExitCommand(GameWorld gw) {
		super("Exit");
		gWorld = gw;
	}
	public void actionPerformed(ActionEvent e) {
		gWorld.quit();;
	}
	
}
