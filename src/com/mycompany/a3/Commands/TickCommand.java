package com.mycompany.a3.Commands;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;
import com.mycompany.a3.GameWorld;

public class TickCommand extends Command{
	private GameWorld gWorld;
	public TickCommand(GameWorld gw) {
		super("Tick!");
		gWorld = gw;
	}
	
	public void actionPerformed(ActionEvent e) {
		int elapsedTime = 20;
		gWorld.gameClockTick(elapsedTime,gWorld.getGwWidth(), gWorld.getGwHeight());
	}
}
