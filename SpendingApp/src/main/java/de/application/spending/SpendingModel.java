package de.application.spending;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.regex.Pattern;

import com.opencsv.CSVReader;
import com.opencsv.CSVWriter;

import de.application.Model;

public class SpendingModel implements Model{
	private SpendingTableModel spendingTableModel;
	private double maxAmount = 1.0;

	public SpendingModel() {
		spendingTableModel = new SpendingTableModel();
	}
	
	
	@Override
	public void readCSVData(String filename) throws Exception {
		spendingTableModel.removeAll();
		CSVReader reader = new CSVReader(new FileReader(filename), ';');
		String[] nextLine;
		while ((nextLine = reader.readNext()) != null) {
			spendingTableModel.addItem(new SpendingItem(nextLine));
		}
		reader.close();
	}

	public void writeToCSV() throws IOException {
		writeToCSV("");
	}	
	
	public void writeToCSV(String filename) throws IOException {
		CSVWriter writer = new CSVWriter(new FileWriter(filename), ';');
		for (SpendingItem item : spendingTableModel)
			writer.writeNext(item.toArray());
		writer.close();
	}
	
	public double getAmountSummary(){
		double sum = 0.0;
		for(SpendingItem item : this.spendingTableModel){
			sum += item.getAmount();
		}
		return sum;
	}

	public double getMaxAmount() {
		return maxAmount;
	}

	public boolean setMaxAmount(double maxAmount) {
		if (maxAmount > 1.0)
			this.maxAmount = maxAmount;
		return maxAmount > 1.0;
	}	
	
	public String getWebserviceData() {
		return null;
	}

	public String getJasonData() {
		return null;
	}

	public String getSQLData() {
		return null;
	}

	public boolean doubleValidator(String input) {
		return Pattern.matches("0(\\.\\d+)?", input) || Pattern.matches("[1-9]\\d*(\\.\\d+)?", input);
	}
	
	public SpendingTableModel getSpendingTableModel() {
		return spendingTableModel;
	}	
}
