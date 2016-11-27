/**
 * 
 */
package ui;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

/**
 * @author Andrew,Brandon,Brian
 *
 */
public class UserSelectorGUI extends JFrame 
							implements ActionListener {
	//Define Vars
	static String studentString = "Student";
	static String staffString = "Staff";
	static String facultyString = "Faculty";
	static String jlblString = "Select What User You Are:";
	
	//Creating components 
	JButton okButton = new JButton("OK");
	JLabel selectText = new JLabel(jlblString);
	JPanel selectPanel = new JPanel();
	JRadioButton studentRButton = new JRadioButton(studentString);
	JRadioButton staffRButton = new JRadioButton(staffString);
	JRadioButton facultyRButton = new JRadioButton(facultyString);
	ButtonGroup groupRButton = new ButtonGroup();
	
	public static void main(String[]args){
		UserSelectorGUI selectAccount= new UserSelectorGUI();
		
	}
	
	
	public UserSelectorGUI(){
		super("Select User Type");
		
		//setting up radio buttons
		studentRButton.setMnemonic(KeyEvent.VK_B);
		studentRButton.setSelected(true);
		studentRButton.setActionCommand(studentString);
		
		staffRButton.setMnemonic(KeyEvent.VK_B);
		staffRButton.setActionCommand(staffString);
		
		facultyRButton.setMnemonic(KeyEvent.VK_B);
		facultyRButton.setActionCommand(facultyString);
		
		//adding radio buttons to group
		groupRButton.add(studentRButton);
		groupRButton.add(staffRButton);
		groupRButton.add(facultyRButton);
		
		//adding a listener for radio buttons
		studentRButton.addActionListener(this);
		staffRButton.addActionListener(this);
		facultyRButton.addActionListener(this);
	
		
		
		//Put radio button in a row in a panel
		JPanel radioPanel = new JPanel(new GridLayout(0,3));
		radioPanel.add(studentRButton);
		radioPanel.add(staffRButton);
		radioPanel.add(facultyRButton);
		
		JPanel buttonPanel =new JPanel(new GridLayout(0,3));
		buttonPanel.add(new JPanel());
		buttonPanel.add(okButton);
		
		//put everything in a grid pane
		selectPanel.setLayout(new GridLayout(7,0));
		selectPanel.add(new JPanel());
		selectPanel.add(selectText);
		selectPanel.add(new JPanel());
		selectPanel.add(radioPanel);
		selectPanel.add(new JPanel());
		selectPanel.add(buttonPanel);
		
		
		setSize(300,160);
		setLocation(500,280);
		
		
		
		
		getContentPane().add(selectPanel);
		//getContentPane().add(okButton);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}


	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	

}
