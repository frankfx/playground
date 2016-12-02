package gui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.TreePath;

import fileScanner.Scanner;
import fileScanner.dataStructure.FileData;
import fileScanner.dataStructure.FileTree;
import fileScanner.dataStructure.Node;

class Gui extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JButton scanButton, exitButton;
	private JFileChooser chooser;
	private JTextArea log;
	private JTree tree;
	private FileTree fileTree;
	private JRadioButton printFile;
	private JRadioButton printAll;
	
	private Scanner scanner = new Scanner();
	
	public void createGUI() {

		setTitle("File-Scanner");

		createGuiElements();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(520, 240);
		setVisible(true);
	}
	
	public void createGuiElements(){
		scanButton = new JButton("scan");
		scanButton.addActionListener(this);
		scanButton.setActionCommand("scanButton");
		getContentPane().add(scanButton);

		exitButton = new JButton("exit");
		exitButton.addActionListener(this);
		exitButton.setActionCommand("exitButton");
		getContentPane().add(exitButton);

		printAll = new JRadioButton("print all");
		printAll.addActionListener(this);
		printAll.setActionCommand("printAllRadio");
		printAll.setSelected(true);
		getContentPane().add(printAll);
		
		printFile = new JRadioButton("print file");
		printFile.addActionListener(this);
		printFile.setActionCommand("printFileRadio");
		printFile.setSelected(false);
		getContentPane().add(printFile);
		
		ButtonGroup group = new ButtonGroup();
		group.add(printAll);
		group.add(printFile);
		
		chooser = new JFileChooser();
		chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);

		fileTree = scanner.scanFull(new File("pictures"));
		
		tree = new JTree(fileTree);
		tree.setCellRenderer(new DefaultTreeCellRenderer(){
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public Component getTreeCellRendererComponent(JTree tree, Object value,
			        boolean sel, boolean exp, boolean leaf, int row, boolean hasFocus) {
				
				super.getTreeCellRendererComponent( tree, value, selected, false, false,0, 
                        hasFocus );
				
				if (value instanceof Node<?>){
					Node<?> node = (Node<?>) value;
					if (node.getData() instanceof FileData){
						FileData dat = (FileData) node.getData();
						setText(dat.getName());
						
						if(!dat.getTypeDesc().equals("directory"))
							setIcon(getDefaultLeafIcon());
					}
				}
				setLeafIcon(getDefaultLeafIcon());
				return this;
			}
		});
		
		tree.addTreeSelectionListener(new TreeSelectionListener() {
			@Override
			public void valueChanged(TreeSelectionEvent e) {
				@SuppressWarnings("unchecked")
				Node<FileData> node = (Node<FileData>) e.getPath().getLastPathComponent();
				if(printAll.isSelected()){
					List<FileData> list = new ArrayList<FileData>();
					fileTree.preOrder(node, list);
					log.setText(list.toString());
				}else if (printFile.isSelected()){
					log.setText(node.getData().toString());
				}
			}
		});
		
		JScrollPane treeScrollPane = new JScrollPane(tree);

		log = new JTextArea();
		log.setMargin(new Insets(5, 5, 5, 5));
		log.setEditable(false);
		JScrollPane logScrollPane = new JScrollPane(log);

		// For layout purposes, put the buttons in a separate panel
		JPanel buttonPanel = new JPanel();
		buttonPanel.add(scanButton);
		buttonPanel.add(exitButton);
		buttonPanel.add(printAll);
		buttonPanel.add(printFile);

		Container contentPane = getContentPane();
		contentPane.setLayout(new BorderLayout());
		contentPane.add(buttonPanel, BorderLayout.PAGE_START);
		contentPane.add(logScrollPane, BorderLayout.CENTER);
		contentPane.add(treeScrollPane, BorderLayout.EAST);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("scanButton")) {
			int returnVal = chooser.showOpenDialog(null);
			if (returnVal == JFileChooser.APPROVE_OPTION){
				fileTree = scanner.scanFull(chooser.getSelectedFile());
				tree.setModel(fileTree);
				log.setText(chooser.getSelectedFile().getAbsolutePath());
			}
		} else if (e.getActionCommand().equals("exitButton"))
			this.dispose();
		else if (e.getActionCommand().equals("printAllRadio")){
			fireTreeSelectionEvent();
		}
		else if (e.getActionCommand().equals("printFileRadio")){
			fireTreeSelectionEvent();		
		}
	}
	
    public void fireTreeSelectionEvent(){
    	if(!tree.isSelectionEmpty()){
    		TreePath path = tree.getSelectionPath();
    		tree.setSelectionPath(path.getParentPath());
    		tree.setSelectionPath(path);
    	}
    }
}

public class ScannerGUI {
	public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				Gui gui = new Gui();
				gui.createGUI();
			}
		});
	}
}
