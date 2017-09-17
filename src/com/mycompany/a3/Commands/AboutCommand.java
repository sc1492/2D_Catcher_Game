package com.mycompany.a3.Commands;

import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.events.ActionEvent;

public class AboutCommand extends Command{
	public AboutCommand() {
		super("About");
	}
	
	public void actionPerformed(ActionEvent e) {
		System.out.println("About command evoked!");
		Dialog.show("Created by: Sean Cortez", "CSC 133\n" + "Dog Catcher Game v3\n" + "Song: Scheming Weasel\n" + "by Kevin MacLeod\n" +
				"www.incompetech.com\n" + "Licensed under Creative Commons", "OK", "Cancel");
	}
}
