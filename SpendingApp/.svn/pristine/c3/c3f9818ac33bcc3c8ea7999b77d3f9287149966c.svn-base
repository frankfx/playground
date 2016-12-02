package de.presentation.spending.graphical;

import java.awt.BorderLayout;
import java.awt.Color;
import java.time.LocalDate;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableModel;

import de.application.spending.SpendingTableModel;
import de.presentation.AbstractPanelContainer;


public class SummaryPanelContainer extends AbstractPanelContainer{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTable table;
	private JScrollPane tablePane;
	
	public SummaryPanelContainer() {
		String[] columnNames = {"ID", "DATE", "ACTIVITY", "Description", "AMOUNT", "Test"};
		
        SpendingTableModel model = new SpendingTableModel(null, columnNames);
       
        table = new JTable(model);
        table.setPreferredScrollableViewportSize(table.getPreferredSize());
        table.setFillsViewportHeight(true);
        
	    setTableAlignCenter(table);
        
        tablePane = new JScrollPane(table);
        tablePane.setVisible(true);
	    
        initPanel("Summary of " + LocalDate.now().getMonth() , new BorderLayout(), Color.WHITE);
		//panel.add(table.getTableHeader(), BorderLayout.PAGE_START);
		//panel.add( table, BorderLayout.CENTER); 
		
		this.add(tablePane);
	}
	
	public JTable getTable() {
		return table;
	}

	public void setTable(JTable table) {
		this.table = table;
	}

	public JScrollPane getTablePane() {
		return tablePane;
	}

	public void setTablePane(JScrollPane tablePane) {
		this.tablePane = tablePane;
	}
	
	public void updateTable(){
    	table.invalidate();
    	tablePane.repaint();		
	}
	
	public void setTableModel(TableModel tableModel) {
		this.table.setModel(tableModel);
		this.setTableAlignCenter(table);
	}
	
	public Object getSelectedTableValue(){
		int row = this.table.getSelectedRow();
		int col = this.table.getSelectedColumn();
		
		return this.table.getValueAt(row, col); 
	}
	
	public int getSelectedTableRow(){
		return this.table.getSelectedRow();
	}	
	
	private void setTableAlignCenter(JTable table) {
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
	    
	    for (int i=0; i<table.getColumnCount()-1; i++){
	    	table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);		    	
	    }
	}
	
}
