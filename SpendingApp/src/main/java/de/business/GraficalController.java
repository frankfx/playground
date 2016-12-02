package de.business;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import de.application.spending.SpendingModel;
import de.presentation.OGLView;
import de.presentation.spending.graphical.SpendingAppView;
import de.application.Controller;
import de.application.spending.SpendingItem;

public class GraficalController implements IController{

	private SpendingModel model;
	private SpendingAppView view;	
	private Properties prop = new Properties();
	private File configFile = new File("src" +File.separator+ "main" +File.separator+ "resources" +File.separator+ "config.properties");
	
	public GraficalController() {}
	
	public void writeConfigProperties(){
		try {
			if (!configFile.exists()){
				view.mMessageboxPanel.appendTextConsole("could not write to config.properties. File not found.");
			} else {
				FileOutputStream output = new FileOutputStream(configFile);
				prop.setProperty("maxamount", model.getMaxAmount()+"");
				prop.store(output, null);
				output.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}		
	}
	
	public void loadConfigProperties(){
		String [] values = null;
		try {
			values = loadProperties(configFile.getPath(), new String[]{"maxamount"});
		} catch (IOException e1) {
			view.mMessageboxPanel.setTextConsole("ERROR while loading config.properties");;
		}					
			
		if (values != null){
			try{
				double val = Double.parseDouble(values[0]);
				model.setMaxAmount(val);
			} catch (NumberFormatException e){
				view.mMessageboxPanel.setTextConsole("NumberFormatException");
			} catch (NullPointerException e){
				view.mMessageboxPanel.setTextConsole("Config Properties, unknown key");
			}	
		}
	}
	
	private String [] loadProperties(String filename, String [] keys) throws IOException,FileNotFoundException {
		String [] values = null;
		
		InputStream input = new FileInputStream(filename);
		
		if (input != null){
			prop.load(input);

			values = new String [keys.length];
			for (int i=0; i<keys.length; i++)
				values[i] = prop.getProperty(keys[i]);
		}
		return values;		
	}	

	private void addListener() {
		view.setButtonExitListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});

		view.setButtonSubmitListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				push();
				fireUpdateProgressBar();
			}
		});
		
		view.setButtonDeleteListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int rowCount = model.getSpendingTableModel().getRowCount(); 
				boolean rowIsSelected = view.mSummaryPanel.getSelectedTableRow() >= 0 && view.mSummaryPanel.getSelectedTableRow() < rowCount;
				
				if (rowIsSelected){
					model.getSpendingTableModel().removeItem(view.mSummaryPanel.getSelectedTableRow());
					view.mSummaryPanel.updateTable();
					fireUpdateProgressBar();
				}
			}
		});
		

		view.setProgressBarListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				view.mMessageboxPanel.appendTextConsole("" + view.mStatisticPanel.getProgressBarValue());
			}
		});

		view.setMenuItemOpenListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				view.mMessageboxPanel.setTextConsole("Open triggerd");
				
		        // dialog to open file
		        int returnVal = view.getOpenFileChooser().showOpenDialog(null);

		        // check if the open button was pressed
		        if(returnVal == JFileChooser.APPROVE_OPTION){
		            String filename = view.getOpenFileChooser().getSelectedFile().getAbsolutePath();
		        	if(load(filename)){
		        		view.mSummaryPanel.updateTable();
		        		fireUpdateProgressBar();
		        	}
		        	else
		        		view.mMessageboxPanel.appendTextConsole("couldn't load file: " + filename);
		        }
			}
		});

		view.setMenuItemSaveListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				view.mMessageboxPanel.setTextConsole("Save triggerd");
				
		        // dialog to open file
		        int returnVal = view.getOpenFileChooser().showSaveDialog(null);

		        // check if the open button was pressed
		        if(returnVal == JFileChooser.APPROVE_OPTION){
		            String filename = view.getOpenFileChooser().getSelectedFile().getAbsolutePath();
		            try {
		            	if (filename.endsWith(".csv"))
		            		model.writeToCSV(filename);
		            	else
		            		model.writeToCSV(filename+".csv");
					} catch (IOException e1) {
						view.mMessageboxPanel.appendTextConsole("couldn't save file: " + filename);
					}
		        }				
			}
		});

		view.setMenuItemExportPDFListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				view.mMessageboxPanel.appendTextConsole("pdf export triggered");
				// allocate the openGL application
				
//				StringBuffer sb = new StringBuffer();
//				sb.append(System.getProperty("user.dir"));
//				sb.append(File.separator);
//				sb.append("src");
//				sb.append(File.separator);
//				sb.append("main");
//				sb.append(File.separator);
//				sb.append("resources");
//				sb.append(File.separator);
//				sb.append("myData.xml");				
				
				String sb = "/home/rene/Documents/workspace/java/SpendingApp/src/main/resources/myData.xml"; 
				
				ShapeModel lModel = new ShapeModel(null, sb);
				OGLView lView = new OGLView(lModel.getVerticesAxis(), lModel.getColorAxis(), lModel.getVertices(), lModel.getColorArray());
				
				Controller lSample = new Controller(lModel, lView);
			
				lSample.runApp();
			}
		});
		
		view.setMenuItemExitListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});

		view.setMenuItemAboutListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null,
					    "Java SpendingApp\nVersion: 0.1\n\n2016-open Rene Frank\n\nOpenSoure",
					    "About",
					    JOptionPane.INFORMATION_MESSAGE);
			}
		});
		
		view.setMenuItemMaxAmountListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String val = JOptionPane.showInputDialog(null, "Enter maximum amount", model.getMaxAmount() );
				if (val != null){
					try{
						double maxAmount = Double.parseDouble(val);
						model.setMaxAmount(maxAmount);
					} catch (NumberFormatException e1){
						view.mMessageboxPanel.setTextConsole("NumberFormatException");
					}
				}
			}
		});
		
		
		view.setWindowListener( new WindowAdapter() {
            public void windowClosing(WindowEvent windowEvent){
            	dispose();
          }        
       });    
	}

	private void dispose(){
		writeConfigProperties();
		view.dispose();
	}
	
	
	public String getInformationFromDatabase() {
		SpendingModel dat = new SpendingModel();
		return dat.getSQLData();
	}

	public String getInformationFromWebservice() {
		SpendingModel dat = new SpendingModel();
		return dat.getWebserviceData();
	}

	private void fireUpdateProgressBar() {
		double value = model.getAmountSummary() * 100 / model.getMaxAmount();
		view.mStatisticPanel.setProgressBarValue((int) Math.round(value));
	}	
	
	@Override
	public void initUserInterface() {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				model = new SpendingModel();
				view  = new SpendingAppView();
				view.initView();
				view.mSummaryPanel.setTableModel(model.getSpendingTableModel());
				
				loadConfigProperties();
				addListener();
			}
		});
	}

	@Override
	public boolean load(String filename){
		try{
			model.readCSVData(filename);
			return true;
		} catch (Exception e){
			return false;
		}
	}	
	
	@Override
	public boolean save(String command) {
		return false;
	}

	public boolean push() {
		SpendingItem item = generateSpendingItem();
		if (item != null){
			model.getSpendingTableModel().addItem(item);	
			//view.setTableModel(model.getSpendingTableModel());
			view.mSummaryPanel.updateTable();
			return true;			
		} else {
			return false;
		}
	}

	@Override
	public boolean pull(String command) {
		return false;
	}

   /**
	* generates a new SpendingItem Object with the data of the text fields
	*
	* @return null if the amount value is not valid otherwise a new SpendingItem 
	*/
	protected SpendingItem generateSpendingItem(){
		// begin check field amount
		boolean isAmountValid = model.doubleValidator(view.mInputPanel.getTextAmount());
		boolean isAmountMarked = view.mInputPanel.isComponentMarked(view.mInputPanel.getTextFieldAmount().getName());
		
		if (!isAmountValid){
			view.mMessageboxPanel.setTextConsole("NotANumberException: " + view.mInputPanel.getTextAmount());
			if(!isAmountMarked)
				view.mInputPanel.markTextFieldAmount();
			return null;
		} else if (isAmountMarked)
			view.mInputPanel.unmarkTextFieldAmount();
		// end check field amount
		
		SpendingItem item = new SpendingItem();
		item.setId(model.getSpendingTableModel().generateValidID());
		item.setDate(view.mInputPanel.getDatePicked());
		item.setActivation(view.mInputPanel.getComboBoxSelectedActivity());
		item.setDescription(view.mInputPanel.getTextDesc());
		item.setAmount(Double.parseDouble(view.mInputPanel.getTextAmount()));
		
		return item;	
	}
}