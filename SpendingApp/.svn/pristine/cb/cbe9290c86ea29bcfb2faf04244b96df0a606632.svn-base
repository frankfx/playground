package de.presentation.spending.graphical;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JTextArea;

import de.presentation.AbstractPanelContainer;


public class MessageBoxPanelContainer extends AbstractPanelContainer {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextArea textConsole = new JTextArea("Console", 5,5);	
	
	public MessageBoxPanelContainer() {
		initPanel("Console", new BorderLayout(), Color.WHITE);
		this.add(textConsole);
	}
	
	public String getTextConsole() {
		return textConsole.getText();
	}

	public void setTextConsole(String consolenOutput) {
		this.textConsole.setText(consolenOutput);
	}
	
	public void appendTextConsole(String output){
		this.textConsole.append(output);
	}
}
