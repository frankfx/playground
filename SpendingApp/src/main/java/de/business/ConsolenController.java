package de.business;

import de.application.spending.SpendingModel;
import de.presentation.spending.console.ConsolenAppView;
import de.application.spending.SpendingItem;

public class ConsolenController implements IController{

	private SpendingModel model;
	private ConsolenAppView console;	

	private final String PUSH = "push";
	private final String PULL = "pull";
	private final String LOAD = "load";
	private final String HELP = "help";
	private final String SAVE = "save";
	private final String PRINT = "print";
	private final String EXIT = "exit";
	
	public ConsolenController() {
		this.model = new SpendingModel();
		this.console  = new ConsolenAppView(); 
	}	
	
	@Override
	public void initUserInterface() {
		String data = "";
		console.open();
		while(!data.equalsIgnoreCase("exit")){
			data = console.getRepuest();
			console.setResponse(readSpendingData(data));
		}
		console.close();
	}
	
	public String readSpendingData(String data){
		
		if (data.contains(LOAD))
			return load(data) ? "model loaded" : "failed load model";
		else if (data.contains(SAVE))
			return save(data) ? "successful saved" : "exception while saving";
		else if (data.contains(PUSH))
			return push(data) ? "succesful push" : "push failed" ;
		else if (data.contains(PULL))
			return pull(data) ? "successful pull" : "pull failed";
		else if (data.contains(HELP))
			return help() ? "\n" : "no help available";
		else if (data.contains(PRINT))
			return print() ? "printing successful" : "no data available";
		else if (data.contains(EXIT)){
			System.exit(0);
			return "";
		}
		else
			return "Unknown command";
	}
	
	@Override
	public boolean load(String command){
		try{
			model.readCSVData(command.substring(command.indexOf("(") + 1, command.indexOf(")")));
			return true;
		} catch (Exception e){
			return false;
		}
	}
	
	@Override
	public boolean save(String command){
		try{
			model.writeToCSV(command.substring(command.indexOf("(") + 1, command.indexOf(")")));
			return true;
		} catch (Exception e){
			return false;
		}
	}
	
	public boolean push(String command){
		try{
			String param = command.substring(command.indexOf("(") + 1, command.indexOf(")"));
			String [] tmp = param.split("[\\s]*,[\\s]*");
			SpendingItem item = new SpendingItem(tmp);
			model.getSpendingTableModel().addItem(item);	
			return true;
		} catch(Exception e){
			return false;
		}
	}
	
	@Override
	public boolean pull(String command){
		try{
			String param = command.substring(command.indexOf("(") + 1, command.indexOf(")"));
			echo(model.getSpendingTableModel().getElementByID(Integer.parseInt(param)).toString());
			return true;
		} catch (Exception e){
			return false;
		}
	}
	
	private boolean print(){
		System.out.println(model.getSpendingTableModel().toString());
		return true;
	}
	
	private boolean help(){
		echo("load\n" + "      load(/home/rene/Desktop/t.csv)");
		echo("save\n" + "      save(/home/rene/Desktop/test.csv)");
		echo("push\n" + "      push(1, 2016-03-05, HOLIDAY, Amsterdam Grachtenfahrt, 50.0, true)");
		echo("pull\n" + "      pull(1)");
		echo("print\n" + "      print");
		echo("exit\n" + "      exit");
		return true;
	}
	
	private void echo(String str){
		System.out.println(str);
	}
}
