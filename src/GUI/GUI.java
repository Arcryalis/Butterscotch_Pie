import javax.swing.*;
import java.awt.Component;
import java.awt.Color;

/**
 * GUI.java
 * @author Ahmed Elmi
 *
 */
public abstract class GUI {
	
	
	public GUI(){
		
	}
	
	/*
	 * method to display the GUI
	 */
	public void displayGUI(){
		makeGUI();
	}
	
	/*
	 * abstract method to implement each individual GUI
	 */
	protected abstract void makeGUI();
	
	/**
	 * @param frm the specific frame
	 * @param vis boolean for changing visibility
	 */
	public void setVisible(JFrame frm, boolean vis){
		if (vis == true){
			frm.setVisible(true);
		}
		else{
			frm.setVisible(false);
		}
	}
	

	/**
	 * @param frame the specific frame
	 */
	public void closeOperation(JFrame frame){
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	/**
	 * sets the location of a component
	 * @param comp selected component
	 * @param x horizontal coordinates
	 * @param y vertical coordinates
	 */
	public void setPosition(Component comp, int x, int y ){
		comp.setLocation(x, y);
	}
	
	/**
	 * sets the size of a component
	 * @param comp
	 * @param x
	 * @param y
	 */
	public void setSize(Component comp, int x, int y ){
		comp.setSize(x, y);
	}
	
	/**
	 * @param frame selected frame
	 * @param r integer for the r value of a rgb
	 * @param g integer for the g value of a rgb
	 * @param b integer for the b value of a rgb
	 */
	public void setBackgroundColor(JFrame frame, Integer r, Integer g, Integer b){
		frame.getContentPane().setBackground(new Color(r, g, b));
	}
	
	
}

