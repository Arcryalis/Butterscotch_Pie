import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.geom.Line2D;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class StraightLine {
	public static void main(String args[]) throws Exception {
	    JFrame cde = new JFrame("Draw a Red Line");
	    cde.setSize(500, 500);
	    cde.setLocation(300, 300);
	    cde.setResizable(true);
	    cde.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    
	    ArrayList<Line2D> lines = new ArrayList<Line2D>();
	    
	    JPanel p = new JPanel() {
	        Point pointStart = null;
	        Point pointEnd   = null;
	        {
	            addMouseListener(new MouseAdapter() {
	                public void mousePressed(MouseEvent e) {
	                    pointStart = e.getPoint();
	                }

	                public void mouseReleased(MouseEvent e) {
	                    pointStart = null;
	                }
	            });
	            addMouseMotionListener(new MouseMotionAdapter() {
	                public void mouseMoved(MouseEvent e) {
	                    pointEnd = e.getPoint();
	                }

	                public void mouseDragged(MouseEvent e) {
	                    pointEnd = e.getPoint();
	                    repaint();
	                }
	            });
	        }
	        public void paint(Graphics g) {
	            super.paint(g);
	            if (pointStart != null) {
	                g.setColor(Color.RED);
	                g.drawLine(pointStart.x, pointStart.y, pointEnd.x, pointEnd.y);
	                //make an arraylist 
	            }
	        }
	    };
	    cde.add(p);
	    cde.setVisible(true); 
	}
	
}