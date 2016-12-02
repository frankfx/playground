package de.presentation.spending.console;

import java.util.Scanner;

public class ConsolenAppView{
	private Scanner scanInput;
	
	public void open(){
		scanInput = new Scanner(System.in);
	}

	public void close(){
		scanInput.close();
	}
	
	public String getRepuest(){
		return scanInput != null ? scanInput.nextLine() : null;
	}
	
	public void setResponse(String response){
		System.out.println("<rene@rene-PC:~$ " + response);
	}
}