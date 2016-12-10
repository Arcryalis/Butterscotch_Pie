import javax.swing.JFrame;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class ParticleTrace extends DrawElement{
	public static void main(String [] args){
		
		JFrame cde = new JFrame ("Creative Drawing Environment");
		
		DrawElement particles = new DrawElement();
		
		cde.add(particles, BorderLayout.CENTER);
		
		cde.setVisible(true);
		
		cde.setSize(MAX_WIDTH, MAX_HEIGHT);
		
		cde.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	static final int MAX_HEIGHT = 500;
	
	static final int MAX_WIDTH = 500;
}

