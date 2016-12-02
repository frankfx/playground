package de.presentation.spending.graphical;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.JTextField;

import de.presentation.AbstractPanelContainer;

public class StatisticPanelContainer extends AbstractPanelContainer {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField mAmountSummary;
	private JProgressBar mProgressBar;
	
	public StatisticPanelContainer() {
		initPanel("Statistic", new GridLayout(3, 1), Color.WHITE);
		this.initAmountSummary();
		this.initProgressBar();
	}
	
	private void initAmountSummary() {
		mAmountSummary = new JTextField();
		JLabel lLabel = new JLabel("Amount summary");
		lLabel.setLabelFor(mAmountSummary);
		mAmountSummary.setEditable(false);
		this.add(lLabel);
		this.add(mAmountSummary);
	}

	private void initProgressBar() {
		mProgressBar = new JProgressBar(0, 100);
		mProgressBar.setValue(0);
		mProgressBar.setStringPainted(true);
		mProgressBar.setToolTipText("prog");
		mProgressBar.setForeground(Color.orange);
		this.add(mProgressBar);
	}

	public JTextField getAmountSummary() {
		return mAmountSummary;
	}

	public void setAmountSummary(JTextField pAmountSummary) {
		this.mAmountSummary = pAmountSummary;
	}

	public String getAmountSummaryValue() {
		return this.mAmountSummary.getText();
	}

	public void setAmountSummaryValue(String pAmountSummaryValue) {
		this.mAmountSummary.setText(pAmountSummaryValue);
	}

	public JProgressBar getProgressBar() {
		return mProgressBar;
	}

	public void setProgressBar(JProgressBar progressBar) {
		this.mProgressBar = progressBar;
	}

	public int getProgressBarValue(){
		return this.mProgressBar.getValue();
	}
	
	public void setProgressBarValue(int val){
		this.mProgressBar.setValue(val);
	}
}
