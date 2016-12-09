import javax.swing.*;
import java.util.LinkedList;
import java.awt.Component;
import java.awt.Color;

public abstract class GUI {

	
	public GUI(){
		
	}
	
	public void displayGUI(){
		makeGUI();
	}
	
	protected abstract void makeGUI();
	
	public void setVisible(JFrame frm, boolean vis){
		if (vis == true){
			frm.setVisible(true);
		}
		else{
			frm.setVisible(false);
		}
	}
	

	public void closeOperation(){
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public void setPosition(Component comp, int x, int y ){
		comp.setLocation(x, y);
	}
	
	public void setSize(Component comp, int x, int y ){
		comp.setSize(x, y);
	}
	
	public void setBackgroundColor(Integer r, Integer g, Integer b){
		frame.getContentPane().setBackground(new Color(r, g, b));
	}
	
	public void setBackgroundImage(String file){
		
	}
}

