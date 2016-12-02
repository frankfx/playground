package de.presentation.spending.graphical;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Month;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFormattedTextField.AbstractFormatter;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableModel;
import javax.swing.filechooser.FileNameExtensionFilter;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import de.application.spending.ActivityEnum;
import de.application.spending.SpendingTableModel;
import de.presentation.ButtonPanelContainer;
import de.presentation.IDefaultGUI;
import de.utils.DateService;
import de.utils.SpringUtilities;

import javax.swing.border.Border;

public class SpendingAppView extends JFrame implements IDefaultGUI{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JMenuItem menuItemOpen = new JMenuItem("Open");
	private JMenuItem menuItemSave = new JMenuItem("Save");
	private JMenuItem menuItemExit = new JMenuItem("Exit");
	private JMenuItem menuItemSetMaxAmount = new JMenuItem("Set Max. Amount ");
	private JMenuItem menuItemExportPDF = new JMenuItem("PDF Export");
	private JMenuItem menuItemAbout = new JMenuItem("About Spending App");
	private JFileChooser openFileChooser = new JFileChooser();

	public InputPanelContainer mInputPanel;
	public MessageBoxPanelContainer mMessageboxPanel;
	public StatisticPanelContainer mStatisticPanel;
	public SummaryPanelContainer mSummaryPanel;
	public ButtonPanelContainer mButtonPanel;
	
	public SpendingAppView(){}
	
	@Override
	public void initView() {
		setTitle("Spending App View");
		createGUIElements();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setMinimumSize(new Dimension(400, 300));
		pack();
		setVisible(true);		
	}

	@Override
	public void evaluateGuiState() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void disposeGUI() {
		// TODO Auto-generated method stub
		
	}	
	
	public void createGUIElements(){
		Container pane = new Container();
		pane.setLayout(new GridLayout(2, 2));
		
		mInputPanel = new InputPanelContainer();
		mMessageboxPanel = new MessageBoxPanelContainer();
		mStatisticPanel = new StatisticPanelContainer();
		mSummaryPanel = new SummaryPanelContainer();
		mButtonPanel = new ButtonPanelContainer();

		pane.add(mInputPanel);
		pane.add(mMessageboxPanel);
		pane.add(mStatisticPanel);
		pane.add(mSummaryPanel);
		
		getContentPane().add(pane, BorderLayout.CENTER);
		getContentPane().add(mButtonPanel, BorderLayout.SOUTH);

		createMenuBar();
	}

	public void createMenuBar(){
		JMenuBar bar = new JMenuBar();
		JMenu menuFile = new JMenu("File");
		JMenu menuExportS = new JMenu("Export");
		JMenu menuConfig = new JMenu("Configure");
		JMenu menuHelp = new JMenu("Help");
		menuFile.add(menuItemOpen);
		menuFile.add(menuItemSave);
		menuFile.add(menuExportS);
		menuFile.add(menuItemExit);
		menuExportS.add(menuItemExportPDF);
		menuConfig.add(menuItemSetMaxAmount);
		menuHelp.add(menuItemAbout);
		bar.add(menuFile);
		bar.add(menuConfig);
		bar.add(menuHelp);
		this.setJMenuBar(bar);	
		
		setupOpenFileChooser();
	}

	private void setupOpenFileChooser(){
        openFileChooser.setAcceptAllFileFilterUsed(false);
        FileNameExtensionFilter filter = new FileNameExtensionFilter("CSV-Files", "csv");
        openFileChooser.addChoosableFileFilter(filter);
	}
	
	
	private void setTableAlignCenter(JTable table) {
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(DefaultTableCellRenderer.CENTER);
	    
	    for (int i=0; i<table.getColumnCount()-1; i++){
	    	table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);		    	
	    }
	}

	public JFileChooser getOpenFileChooser() {
		return openFileChooser;
	}

	public void setOpenFileChooser(JFileChooser chooser) {
		this.openFileChooser = chooser;
	}    
	
    /**
     * Funktionen bereitstellen, mit denen man spaeter aus
     * dem Controller die noetigen Listener hinzufuegen kann
     */
    public void setButtonExitListener(ActionListener l){
        this.mButtonPanel.getBtnExit().addActionListener(l);
    }

    public void setButtonSubmitListener(ActionListener l){
        this.mInputPanel.getBtnSubmit().addActionListener(l);
    }

    public void setButtonDeleteListener(ActionListener l){
        this.mInputPanel.getBtnDelete().addActionListener(l);
    }    
    
    public void setProgressBarListener(ChangeListener l){
    	this.mStatisticPanel.getProgressBar().addChangeListener(l);
    }
    
    public void setMenuItemOpenListener(ActionListener l){
    	this.menuItemOpen.addActionListener(l);
    }    

    public void setMenuItemSaveListener(ActionListener l){
    	this.menuItemSave.addActionListener(l);
    }  
    
    public void setMenuItemExitListener(ActionListener l){
    	this.menuItemExit.addActionListener(l);
    }  
    
    public void setMenuItemExportPDFListener(ActionListener l){
    	this.menuItemExportPDF.addActionListener(l);
    }
    
    public void setMenuItemAboutListener(ActionListener l){
    	this.menuItemAbout.addActionListener(l);
    }   
    
    public void setMenuItemMaxAmountListener(ActionListener l){
    	this.menuItemSetMaxAmount.addActionListener(l);
    }

    public void setWindowListener(WindowAdapter l){
    	this.addWindowListener(l);
    }
}