package de.spending;

import de.business.ConsolenController;
import de.business.GraficalController;
import de.business.IController;
import de.business.UIType;

public class Main {
	public static void main(String[] args) {
		args = new String [0];
		IController controller = args.length > 0 && args[0].equals(UIType.CONSOLE.toString()) ? new ConsolenController() : new GraficalController();
		controller.initUserInterface();
	}	
}