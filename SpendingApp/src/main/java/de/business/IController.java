package de.business;

public interface IController {
	public void initUserInterface();
	public boolean load(String command);
	public boolean save(String command);
	public boolean pull(String command);
}