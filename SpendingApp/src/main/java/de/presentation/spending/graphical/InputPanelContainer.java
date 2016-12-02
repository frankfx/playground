package de.presentation.spending.graphical;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.JFormattedTextField.AbstractFormatter;
import javax.swing.border.Border;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.UtilDateModel;

import de.application.spending.ActivityEnum;
import de.presentation.AbstractPanelContainer;
import de.utils.DateService;
import de.utils.DoubleInputVerifier;
import de.utils.DoubleKeyAdapter;
import de.utils.SpringUtilities;

public class InputPanelContainer extends AbstractPanelContainer{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JComboBox<ActivityEnum> comboBoxActivities = new JComboBox<ActivityEnum>();	
	private JButton btnSubmit = new JButton("Submit");
	private JButton btnDelete = new JButton("Delete");	
	private JTextField textAmount = new JTextField(); 
	private JTextField textDesc = new JTextField(); 
	private JDatePickerImpl datePicker;	
	
	private Map<String, Boolean> markedComponentsMap = new HashMap<String, Boolean>();
	
	private Border borderRed = BorderFactory.createLineBorder(Color.red);
	private Border borderDefault = textAmount.getBorder();	
	
	
	public InputPanelContainer() {
		// create an default panel
		initPanel("Input", new BorderLayout(), Color.WHITE);
		
		JPanel panelForm = new JPanel(new SpringLayout());
		JPanel panelBtn  = new JPanel(new GridLayout(1,2));
		
		panelForm.setBackground(Color.WHITE);
		
		// create all form labels
		JLabel [] label = {new JLabel("Date" , JLabel.TRAILING), new JLabel("Activity", JLabel.TRAILING), 
				new JLabel("Description", JLabel.TRAILING), new JLabel("Amount in €", JLabel.TRAILING)};
		
		// fills combobox with all activities
		setupActivityCombobox(comboBoxActivities);
		
		// create date field with date picker
		UtilDateModel dateModel = new UtilDateModel();
		Properties p = new Properties();
		p.put("text.today", "Today");
		p.put("text.month", "Month");
		p.put("text.year", "Year");
		JDatePanelImpl datePanel = new JDatePanelImpl(dateModel, p);
		datePicker = new JDatePickerImpl(datePanel,new DateLabelFormatter());
		datePicker.setTextEditable(true);
	
		// set all components and labels to the form panel
		panelForm.add(label[0]);
		label[0].setLabelFor(datePicker);
		panelForm.add(datePicker);
		
		panelForm.add(label[1]);
		label[1].setLabelFor(comboBoxActivities);
		panelForm.add(comboBoxActivities);		
		
		panelForm.add(label[2]);
		label[2].setLabelFor(textDesc);
		panelForm.add(textDesc);
		
		panelForm.add(label[3]);
		label[3].setLabelFor(textAmount);
		textAmount.setInputVerifier(new DoubleInputVerifier());
		textAmount.addKeyListener(new DoubleKeyAdapter(textAmount));
		panelForm.add(textAmount);	
		
		//Lay out the panel.
		int labelCnt = label.length;
		SpringUtilities.makeCompactGrid(panelForm,
		                                labelCnt, 2, //rows, cols
		                                6, 6,        //initX, initY
		                                6, 6);       //xPad, yPad

		// set all buttons to the button panel
		panelBtn.add(btnSubmit);
		panelBtn.add(btnDelete);
		
		// create the layout and add the two panels to the main panel
		this.add(panelForm, BorderLayout.CENTER);
		this.add(panelBtn, BorderLayout.SOUTH);
	}
	
	private void setupActivityCombobox(JComboBox<ActivityEnum> comboBoxActivities){
		comboBoxActivities.addItem(null);
		comboBoxActivities.addItem(ActivityEnum.FOOD);
		comboBoxActivities.addItem(ActivityEnum.SPORT);
		comboBoxActivities.addItem(ActivityEnum.LEISURE_TIME);
		comboBoxActivities.addItem(ActivityEnum.OTHERS);		
	}
	
	public ActivityEnum getComboBoxSelectedActivity() {
		return ActivityEnum.getType(comboBoxActivities.getSelectedItem());
	}

	public void setComboBoxSelectedActivity(ActivityEnum activity) {
		this.comboBoxActivities.setSelectedItem(activity);
	}	
	
	public LocalDate getDatePicked() {
		return DateService.getLocalDate((Date)datePicker.getModel().getValue());
	}

	public void setDatePicked(LocalDate date) {
		UtilDateModel model =  (UtilDateModel)this.datePicker.getModel();
		model.setDate(date.getYear(), date.getMonthValue(), date.getDayOfMonth());
	}	
	
	public JButton getBtnSubmit() {
		return btnSubmit;
	}

	public void setBtnSubmit(JButton btnSubmit) {
		this.btnSubmit = btnSubmit;
	}

	public JButton getBtnDelete() {
		return btnDelete;
	}

	public void setBtnDelete(JButton btnDelete) {
		this.btnDelete = btnDelete;
	}	
	
	public String getTextAmount() {
		return textAmount.getText();
	}

	public void setTextAmount(String amount) {
		this.textAmount.setText(amount);
	}

	public JTextField getTextFieldAmount(){
		return this.textAmount;
	}
	
	public void markTextFieldAmount(){
		setComponentMarkedFlag(this.textAmount.getName(), true);
		this.textAmount.setBorder(borderRed);
	}
	
	public void unmarkTextFieldAmount(){
		setComponentMarkedFlag(this.textAmount.getName(), false);
		this.textAmount.setBorder(borderDefault);		
	}
	
	public String getTextDesc() {
		return textDesc.getText();
	}

	public void setTextDesc(String description) {
		this.textDesc.setText(description);
	}
	
    private void setComponentMarkedFlag(String component, boolean flag){
    	this.markedComponentsMap.put(component, flag);
    }
    
    public boolean isComponentMarked(String component){
    	return markedComponentsMap.isEmpty() ? false : markedComponentsMap.get(component);
    }	
}

/**
 * Formatter for LocalDate values
 */
class DateLabelFormatter extends AbstractFormatter {

    /**
	 * 
	 */
	private static final long serialVersionUID = 3145722969420106642L;
	private String datePattern = "yyyy-MM-dd";
    private SimpleDateFormat dateFormatter = new SimpleDateFormat(datePattern);

    @Override
    public Object stringToValue(String text) throws ParseException {
        return dateFormatter.parseObject(text);
    }

    @Override
    public String valueToString(Object value) throws ParseException {
        if (value != null) {
            Calendar cal = (Calendar) value;
            return dateFormatter.format(cal.getTime());
        }
        return "";
    }
}
