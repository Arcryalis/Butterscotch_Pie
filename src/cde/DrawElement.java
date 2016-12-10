import java.awt.Color;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * 
 * @author Osian 
 * @author Sev
 * @author Rhydian
 * @version 2
 * @Date 10 12 16
 *
 */
public class DrawElement extends JPanel {

	public int getThickness() {
		return m_Thickness;
	}

	public void setThickness(int thickness) {
		this.m_Thickness = thickness;
	}

	public Color getColour() {
		return m_Colour;
	}

	public void setColour(Color colour) {
		this.m_Colour = colour;
	}

	public int getPointCount() {
		return m_PointCount;
	}

	public int increasePointCount() {
		return m_PointCount ++;
	}

	public Point[] getPoints() {
		return m_Points;
	}

	public void setPoint(Point point) {
		
	
		m_Points[getPointCount()] = point;
	
	}

	public DrawElement() {

		boolean test = false;
	

		PaintHandler handler = new PaintHandler();
		this.addMouseListener(handler);
		this.addMouseMotionListener(handler);
	}

	public void paintComponent(Graphics g) {

		boolean test = false;

		g.setColor(Color.red);
		super.paintComponent(g);

		for (int i = 0; i < this.getPointCount(); i++) {

			g.fillRect(getPoints()[i].x, getPoints()[i].y, 
					RECT_WIDTH, RECT_HEIGHT);
		}
	}

	private class PaintHandler implements MouseListener, MouseMotionListener {

		public void mouseDragged(MouseEvent event) {

			boolean test = false;
			if (test) {
				System.out.println("PaintHandler::mouseDragged() " + event.toString());
			}

			if (getPointCount() < getPoints().length) {

				setPoint(event.getPoint());

				increasePointCount();

				repaint();

			}
		}

		public void mouseMoved(MouseEvent event) {
		
		}

		public void mouseEntered(MouseEvent event) {
		
		}

		public void mouseExited(MouseEvent event) {
		
		}

		public void mouseClicked(MouseEvent event) {
			
		}

		public void mousePressed(MouseEvent event) {
			
		}

		public void mouseReleased(MouseEvent event) {
		
		}

	}

	protected Color red = Color.red;

	protected int m_Thickness;

	protected Color m_Colour;

	public final static int FRAME_WIDTH = 500;

	public final static int FRAME_HEIGHT = 500;

	private int m_PointCount = 0;

	private final int RECT_WIDTH = 5;

	private final int RECT_HEIGHT = 5;

	private final int MAX_POINTS = 100000;

	private Point m_Points[] = new Point[MAX_POINTS];

}
