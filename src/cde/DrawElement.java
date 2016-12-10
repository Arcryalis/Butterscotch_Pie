import java.awt.Color;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class DrawElement extends JPanel {

	public int getThickness() {
		return m_Thickness;
	}

	public void setThickness(int m_Thickness) {
		this.m_Thickness = m_Thickness;
	}

	public Color getColour() {
		return m_Colour;
	}

	public void setColour(Color m_Colour) {
		this.m_Colour = Color.red;
	}

	public int getPointCount() {
		return m_PointCount;
	}

	public int increasePointCount() {
		m_PointCount++;
		return m_PointCount;
	}

	public Point[] getPoints() {
		return m_Points;
	}

	public boolean setPoint(Point point) {
		boolean test = false;
		if (test) {
			System.out.println("DrawElement::setPoint()" + m_PointCount + ", " + point.toString());
		}
		m_Points[getPointCount()] = point;
		return true;
	}

	public DrawElement() {

		boolean test = false;
		if (test) {
			System.out.println("DrawElement::constructor() ");
		}

		PaintHandler handler = new PaintHandler();
		this.addMouseListener(handler);
		this.addMouseMotionListener(handler);
	}

	public void paintComponent(Graphics g) {

		boolean test = false;
		if (test) {
			System.out.println("PaintPanel::paintComponent() " + g.toString());
		}

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
			boolean test = false;
			if (test) {
				System.out.println("PaintHandler::mouseMoved() " + event.toString());

			}
		}

		public void mouseEntered(MouseEvent event) {
			boolean test = true;
			if (test) {
				System.out.println("PaintHandler::mouseEntered() " + event.toString());
			}
		}

		public void mouseExited(MouseEvent event) {
			boolean test = true;
			if (test) {
				System.out.println("PaintHandler::mouseExited() " + event.toString());
			}
		}

		public void mouseClicked(MouseEvent event) {
			boolean test = true;
			if (test) {
				System.out.println("PaintHandler::mouseClicked() " + event.toString());
			}
		}

		public void mousePressed(MouseEvent event) {
			boolean test = true;
			if (test) {
				System.out.println("PaintHandler::mousePresses() " + event.toString());
			}
		}

		public void mouseReleased(MouseEvent event) {
			boolean test = true;
			if (test) {
				System.out.println("PaintHandler::mouseReleased() " + event.toString());
			}
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
