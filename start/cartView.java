package start;

/*
 * HelloWorldSwing.java requires no other files. 
 */
import javax.swing.*;        

public class HelloWorldSwing {
    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event-dispatching thread.
     */
    private static void createAndShowGUI() {
	//Create and set up the window.
	JFrame frame = new JFrame("HelloWorldSwing");
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	JPanel panel = new JPanel();
	//Add the ubiquitous "Hello World" label.
	JLabel label = new JLabel("Hello World");
	panel.add(label);

	JPanel cartPanel = new JPanel();
	addFoodBtn = new JButton("addFood");
	addFoodBtn.setActionCommand("addddd");
	cartPanel.add(addFoodBtn);

	frame.getContentPane().add(panel);
	frame.getContentPane().add(cartPanel);

	//Display the window.
	frame.pack();
	frame.setVisible(true);
    }
}

public void actionPerformed(ActionEvent e) {
    addFoodBtn.setText("hello");
}
