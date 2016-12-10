import javax.imageio.ImageIO;
import javax.swing.JFrame;
import java.awt.BorderLayout;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.swing.JLabel;
import javax.swing.JPanel;


public class ParticleTrace extends DrawElement{
	
	 public void SavePaint()
	    {
		 JFrame cde = new JFrame ("Creative Drawing Environment");
			
			DrawElement particles = new DrawElement();
			
			cde.add(particles, BorderLayout.CENTER);
			
			cde.setVisible(true);
			
			cde.setSize(MAX_WIDTH, MAX_HEIGHT);
			
			cde.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	        try
	        {
	            BufferedImage image = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);
	            Graphics2D graphics2D = image.createGraphics();
	            cde.paint(graphics2D);
	            ImageIO.write(image,"jpeg", new File("C:/Users/Rhydz97/Documents/Year 2/CS-230"));
	        }
	        catch(Exception exception){
	           
	        }
	    }

	
	static final int MAX_HEIGHT = 500;
	
	static final int MAX_WIDTH = 500;
}

