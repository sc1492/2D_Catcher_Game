package com.mycompany.a3.Commands;

import com.codename1.ui.Command;
import com.codename1.ui.events.ActionEvent;

public class SideMenuCommand extends Command {
	public SideMenuCommand() {
		super("Side Menu");
	}
	
	public void actionPerformed(ActionEvent e) {
		System.out.println("Side Menu command evoked!");
	}
}
