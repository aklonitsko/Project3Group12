/**
 * 
 */
package ui;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JTabbedPane;

/**
 * @author Andrew,Brandon,Brian
 *
 */
public class MainGUI extends JFrame implements PropertyChangeListener {

	JTabbedPane myTabbedPane;

	public static void main(String[] args) {
		MainGUI frame = new MainGUI();
		UserSelectorGUI userSelector = new UserSelectorGUI();

		System.out.println("hello brain");
		// Display User selector GUI and listen to user selection
		userSelector.addPropertyChangeListener(frame);
		userSelector.setVisible(true);

	}

	/**
	 * Launches the GUI, starting point of application
	 */

	public MainGUI() {
		super("Student Database System");
		createComponents();

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(700, 700);
		setLocationRelativeTo(null);
	}

	/**
	 * Create GUI components. This includes the UserSelectorGUI
	 */
	private void createComponents() {
		// Create the main tabbed pane
		myTabbedPane = new JTabbedPane();

		// Create the user selector GUI

	}

	private void createStaffComponents() {
		createTabbedGUI(new String[] { "Student", "Academic", "Employment", "Reports" });
	}

	private void createFacultyComponents() {
		createTabbedGUI(new String[] { "Reports" });
	}

	private void createStudentComponents() {
		createTabbedGUI(new String[] { "Employment" });
	}

	private void createTabbedGUI(String[] tabs) {
		for (String tabName : tabs) {
			JComponent newPanel = makeTextPanel(tabName);
			myTabbedPane.addTab(tabName, newPanel);
		}

		this.add(myTabbedPane);
		this.setVisible(true);
	}

	private JComponent makeTextPanel(String type) {

		return null;
	}

	@Override
	public void propertyChange(PropertyChangeEvent e) {
		// TODO Auto-generated method stub

		// Debug only
		System.out.println(e.getPropertyName() + " Old Value: " + e.getOldValue() + " New Value: " + e.getNewValue());

		if (e.getPropertyName().equals("user")) {
			String user = (String) e.getNewValue();
			if (user.equals("Staff")) {
				createStaffComponents();
			} else if (user.equals("Faculty")) {
				createFacultyComponents();
			} else {
				// Must be student if not Staff or Faculty
				createStudentComponents();
			}
		}

	}
}
