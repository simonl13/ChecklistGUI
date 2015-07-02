import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;


public class Screen extends JFrame{
	
	String checklistItem;
	private static final int WIDTH = 1000; //sets window width and height
	private static final int HEIGHT = 1500;
	
	private static JLabel text;
	private static JLabel checklistImage;
	private JFrame frame;
	
	ImageGetter getIt = new ImageGetter();
	ImageIcon checkList = getIt.getImage("checklist.png"); //sets checklist.png as image.
	Item newChecklist = new Item();
	
	private static JButton addButton; //adds button variables
	private static JButton clearButton;
	private static JButton removeButton;
	private static JButton quitButton;
	
	public Screen() {
		try {
			UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
		} catch (ClassNotFoundException | InstantiationException
				| IllegalAccessException | UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		frame = new JFrame();
		text = new JLabel("Push a button to perform an action.", SwingConstants.CENTER); //label
	
		checklistImage = new JLabel(checkList);
		
		addButton = new JButton("Add Item"); //sets button text
		removeButton = new JButton("Remove Item");
		clearButton = new JButton("Clear all items.");
		quitButton = new JButton("Quit Checklist.");
		
		quitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { //if button pressed, quit.
				System.exit(0);
			}
		});
		
		addButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) { //if button is pressed,
				String addedItem = (String)JOptionPane.showInputDialog(frame, "What would you like to add to the list?", "Add Item", JOptionPane.PLAIN_MESSAGE); //ask for input
				try {
					newChecklist.newItem(addedItem); //add new item
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		removeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int removedItem = Integer.parseInt(JOptionPane.showInputDialog(frame, "Which item number would you like to remove from the list?", "Remove Item", JOptionPane.PLAIN_MESSAGE)); //input number
				newChecklist.removeItem(removedItem); //remove item
			}
		});
		
		clearButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					newChecklist.clearAll(); //clears all
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		JPanel masterPanel = new JPanel(); //master panel
		JPanel buttonPanelAdd = new JPanel(); //button panels
		JPanel buttonPanelRemove = new JPanel();
		JPanel buttonPanelClear = new JPanel();
		JPanel buttonPanelQuit = new JPanel();
		
		checklistImage.setAlignmentX(Component.CENTER_ALIGNMENT); //aligns image to the center
		
		masterPanel.setLayout(new BoxLayout(masterPanel, BoxLayout.Y_AXIS)); //sets a boxlayout
		masterPanel.add(checklistImage);
		
		buttonPanelAdd.setLayout(new GridLayout (0, 1)); //aligns buttons to a grid, on top of each other
		buttonPanelRemove.setLayout(new GridLayout(0, 2));
		buttonPanelClear.setLayout(new GridLayout(0, 3));
		buttonPanelQuit.setLayout(new GridLayout(0, 4));
		
		buttonPanelAdd.add(addButton); //adds buttons to button panels.
		
		buttonPanelRemove.add(removeButton);
		
		buttonPanelClear.add(clearText);
		
		buttonPanelQuit.add(quitButton);
		
		Container pane = getContentPane(); //creates window panes
		pane.setLayout(new BorderLayout());
		pane.add(masterPanel, BorderLayout.CENTER);
		pane.add(buttonPanelAdd, BorderLayout.PAGE_END);
		pane.add(buttonPanelRemove, BorderLayout.PAGE_END);
		pane.add(buttonPanelClear, BorderLayout.PAGE_END);
		pane.add(buttonPanelQuit, BorderLayout.PAGE_END);
		
		masterPanel.setVisible(true);
		setSize(WIDTH, HEIGHT);
		setVisible(true); //sets visibility to true.
		setDefaultCloseOperation(EXIT_ON_CLOSE); //exit if closed,
		
		if((newChecklist.getTotal() > 0) && (newChecklist.getTotal() <= 10)) {
			for (int i = 0; i < newChecklist.getTotal(); i++) {
				checklistItem = checkList.get(i);
				//insert code to display the string in the areas on the picture.
				//superimpose
			}
		}
		else if (((newChecklist.getTotal() > 0)) && (newChecklist)
	}
	public static void main(String[] args) {
		newChecklist.loadItems(); //load previous items.
		Screen checkScreen = new Screen(); //call a Screen
	}

}
