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

	private static final int WIDTH = 1000;
	private static final int HEIGHT = 1500;
	
	private static JLabel text;
	private static JLabel checklistImage;
	private JFrame frame;
	
	ImageGetter getIt = new ImageGetter();
	ImageIcon checkList = getIt.getImage("checklist.png");
	Item newChecklist = new Item();
	
	private static JButton addButton;
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
		text = new JLabel("Push a button to perform an action.", SwingConstants.CENTER);
	
		checklistImage = new JLabel(checkList);
		
		addButton = new JButton("Add Item");
		removeButton = new JButton("Remove Item");
		clearButton = new JButton("Clear all items.");
		quitButton = new JButton("Quit Checklist.");
		
		quitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		
		addButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String addedItem = (String)JOptionPane.showInputDialog(frame, "What would you like to add to the list?", "Add Item", JOptionPane.PLAIN_MESSAGE);
				try {
					newChecklist.newItem(addedItem);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		removeButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int removedItem = Integer.parseInt(JOptionPane.showInputDialog(frame, "Which item number would you like to remove from the list?", "Remove Item", JOptionPane.PLAIN_MESSAGE));
				newChecklist.removeItem(removedItem);
			}
		});
		
		clearButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					newChecklist.clearAll();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		
		JPanel masterPanel = new JPanel();
		JPanel buttonPanelAdd = new JPanel();
		JPanel buttonPanelRemove = new JPanel();
		JPanel buttonPanelClear = new JPanel();
		JPanel buttonPanelQuit = new JPanel();
		
		checklistImage.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		masterPanel.setLayout(new BoxLayout(masterPanel, BoxLayout.Y_AXIS));
		masterPanel.add(checklistImage);
		
		buttonPanelAdd.setLayout(new GridLayout (0, 1));
		buttonPanelRemove.setLayout(new GridLayout(0, 2));
		buttonPanelClear.setLayout(new GridLayout(0, 3));
		buttonPanelQuit.setLayout(new GridLayout(0, 4));
		
		buttonPanelAdd.add(addButton);
		
		buttonPanelRemove.add(removeButton);
		
		buttonPanelClear.add(clearText);
		
		buttonPanelQuit.add(quitButton);
		
		Container pane = getContentPane();
		pane.setLayout(new BorderLayout());
		pane.add(masterPanel, BorderLayout.CENTER);
		pane.add(buttonPanelAdd, BorderLayout.PAGE_END);
		pane.add(buttonPanelRemove, BorderLayout.PAGE_END);
		pane.add(buttonPanelClear, BorderLayout.PAGE_END);
		pane.add(buttonPanelQuit, BorderLayout.PAGE_END);
		
		masterPanel.setVisible(true);
		setSize(WIDTH, HEIGHT);
		setVisible(true);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
	}
	public static void main(String[] args) {
		newChecklist.loadItems();
		Screen checkScreen = new Screen();
	}

}
