package de.application.spending;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

public class SpendingTableModel implements TableModel, Iterable<SpendingItem> {

	// storing the objects in list
	List<SpendingItem> list;
	// the headers
	String[] header;

	
	public SpendingTableModel(){
		this(null, new String[]{ "ID", "DATE", "ACTIVITY", "DESCRIPTION", "AMOUNT", "CHECKED" });
	}
	
	public SpendingTableModel(SpendingItem[] rows, String[] header) {
		this.header = header;
		list = new ArrayList<SpendingItem>();
		
		if (rows != null)
			for (int i = 0; i < rows.length; i++)
				list.add(rows[i]);
	}

	public void addItem(SpendingItem item){
		list.add(item);
	}
	
	public void removeItem(int index){
		if (index >= 0 || index < list.size())
			list.remove(index);
	}
	
	public void removeAll(){
		list.removeAll(list);
	}
	
	public SpendingItem getElementByID(int id){
		for(SpendingItem item : list)
			if(item.getId() == id)
				return item;
		return null;
	}
	
	
	public boolean isEmpty(){
		return list.isEmpty();
	}
	
	@Override
	public int getRowCount() {
		return list.size();
	}

	@Override
	public int getColumnCount() {
		return header.length;
	}

	@Override
	public String getColumnName(int columnIndex) {
		return header[columnIndex];
	}

	@Override
	public Class<?> getColumnClass(int columnIndex) {
		switch (columnIndex) {
		case 0:
			return Integer.class;
		case 1:
			return LocalDate.class;
		case 2:
			return ActivityEnum.class;
		case 3:
			return String.class;
		case 4:
			return Double.class;
		default:
			return Boolean.class;
		}
	}

	@Override
	public boolean isCellEditable(int rowIndex, int columnIndex) {
		switch (columnIndex) {
		case 0:
			return false;
		default:
			return true;
		}
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		switch (columnIndex) {
		case 0:
			return list.get(rowIndex).getId();
		case 1:
			return list.get(rowIndex).getDate();
		case 2:
			return list.get(rowIndex).getActivation();
		case 3:
			return list.get(rowIndex).getDescription();
		case 4:
			return list.get(rowIndex).getAmount();
		case 5:
			return list.get(rowIndex).getIsChecked();
		}		
		return null;
	}

	@Override
	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		System.out.println(columnIndex);
		
		switch (columnIndex) {
		case 0:
			list.get(rowIndex).setId((Integer) aValue); break;
		case 1:
			list.get(rowIndex).setDate((LocalDate) aValue); break;
		case 2:
			list.get(rowIndex).setActivation((ActivityEnum) aValue); break;
		case 3:
			list.get(rowIndex).setDescription(aValue.toString()); break;
		case 4:
			list.get(rowIndex).setAmount((Double) aValue); break;
		case 5:
			list.get(rowIndex).setIsChecked((Boolean) aValue); break;
		}
	}
	
	@Override
	public void addTableModelListener(TableModelListener l) {

	}

	@Override
	public void removeTableModelListener(TableModelListener l) {

	}
	
	@Override
	public String toString(){
		return this.list.toString();
	}

	public SpendingItem [] toArray(){
		return (SpendingItem [])list.toArray();
	}
	
	// Iterable fuctions
	@Override
	public Iterator<SpendingItem> iterator() {
		return this.list.iterator();
	}
	
	public boolean isIDValid(int id){
		for(SpendingItem item : list)
			if(item.getId() == id)
				return false;
		return true;
	}
	
	public int generateValidID(){
		return rec_generateValidID(list.size()+1);
	}
	
	private int rec_generateValidID(int id){
		if(isIDValid(id))
			return id;
		else
			return rec_generateValidID(id+1);
	}
}
