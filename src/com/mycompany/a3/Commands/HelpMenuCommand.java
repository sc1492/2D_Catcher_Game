package com.mycompany.a3.Commands;

import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.events.ActionEvent;

public class HelpMenuCommand extends Command{
	public HelpMenuCommand() {
		super("Help?");
	}
	
	public void actionPerformed(ActionEvent e) {
		System.out.println("Help? command evoked!");
		Dialog.show("Commands: ", " e - expands net\n" + "u - moves net up\n"
					+ "l - moves net down\n" + "o - jumps to a dog\n"
					+ "c - contracts the net\n" + "d - moves net down\n"
					+ "r - moves the net right\n" + "a - jumps to a cat\n"
					+ "s - scoops the area\n" + "h - heals a dog\n"
					+ "x - exits the game", "OK", "Cancel");
	}
}
