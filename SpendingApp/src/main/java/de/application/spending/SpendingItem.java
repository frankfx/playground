package de.application.spending;

import java.time.LocalDate;

public class SpendingItem implements Comparable<SpendingItem>{
	private int id;
	private LocalDate date;
	private ActivityEnum activation;
	private String description;
	private double amount;
	private boolean isChecked;
	
	public SpendingItem(int id, LocalDate date, ActivityEnum activation, String description, double amount, boolean isChecked) {
		this.id = id;
		this.date = date;
		this.activation = activation;
		this.description = description;
		this.amount = amount;
		this.isChecked = isChecked;
	}
	
	public SpendingItem(){
		// this form used by Hibernate
		this.id = -1;
		this.date = LocalDate.now();
		this.activation = ActivityEnum.OTHERS;
		this.description = "nothing";
		this.amount = 0.0;	
		this.isChecked = false;
	}

	public SpendingItem(String [] data){
		id = Integer.parseInt(data[0]);
		date = LocalDate.parse(data[1]);
		activation = ActivityEnum.getType(data[2]);
		description = data[3];
		amount = Double.parseDouble(data[4]);
		isChecked = Boolean.parseBoolean(data[5]);		
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public ActivityEnum getActivation() {
		return activation;
	}

	public void setActivation(ActivityEnum activation) {
		this.activation = activation;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}	
	
	public boolean getIsChecked() {
		return isChecked;
	}

	public void setIsChecked(boolean isChecked) {
		this.isChecked = isChecked;
	}	
	
	public String toString(){
		return "[" + id + ", " + date + ", " + activation + ", " + description + ", " + amount + ", " + isChecked + "]";
	}
	
	public String[] toArray(){
		return new String[]{""+this.id, this.date.toString(), this.activation.toString(), this.description, ""+this.amount, ""+this.isChecked};
	}
	
	@Override
	public int compareTo(SpendingItem o) {
		return this.getId() - o.getId();
	}
}
