import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JPanel;

public class RequestGUI {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RequestGUI window = new RequestGUI();
					//window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public RequestGUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 405, 330);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		// the panel
	    JPanel buttonPanel = new JPanel();
	    buttonPanel.setLayout(new GridBagLayout());
	    buttonPanel.setSize(new Dimension(400, 300)); // whatever

	    // the scrollpane
	    JScrollPane pane = new JScrollPane();
	    pane.setSize(new Dimension(400, 300)); // whatever

	    // GridBagConstraint for button
	    GridBagConstraints constraintCentre = new GridBagConstraints();
	    constraintCentre.anchor = GridBagConstraints.CENTER;
	    constraintCentre.fill = GridBagConstraints.NONE;
	    constraintCentre.gridx = 0;
	    constraintCentre.gridy = GridBagConstraints.RELATIVE;
	    constraintCentre.weightx = 1.0f;
	    constraintCentre.weighty = 1.0f;
	    
	    

	    int sizeOfButtons = 50;
	    for(int i = 0; i < sizeOfButtons; i++) {
	        JButton accept = new JButton();
	        accept.setText("Accept #" + i);
	        JButton cancel = new JButton();
	        cancel.setText("Cancel #" + i);
	        JLabel label = new JLabel();
	        label.setText("RequestUserName #" + i);

	        // other attributes you will set
	        //buttonPanel.add(label, constraint);
	        //buttonPanel.add(button, constraint);
	        buttonPanel.add(label, constraintCentre);
	        buttonPanel.add(accept, constraintCentre);
	        buttonPanel.add(cancel, constraintCentre);
	    }

	    pane.setViewportView(buttonPanel);
	    frame.getContentPane().add(pane); // or other panel etc.
	    pane.updateUI();
	    
	    frame.setVisible(true);
	}
}
