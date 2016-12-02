package de.business;


public class ControllerFactory {
	
	public IController getShape(UIType uiType){
		if (uiType == null)
			return null;
		else if (uiType == UIType.GUI)
			return new GraficalController();
		else if (uiType == UIType.CONSOLE)
			return new ConsolenController();
		return null;
	}
}
