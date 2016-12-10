import java.awt.BorderLayout;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
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
public class StraightLine extends JPanel {

	public static void main(String args[]) {

		StraightLine line = new StraightLine();
		JFrame cde = new JFrame("Draw a Red Line");
		cde.add(line, BorderLayout.CENTER);
		cde.setSize(300, 300);
		cde.setLocation(300, 300);
		cde.setResizable(false);
		cde.setVisible(true);
	}

	//Constructor
	public StraightLine() {

		PaintHandler handler = new PaintHandler();
		this.addMouseListener(handler);
		this.addMouseMotionListener(handler);

	}

	//
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.RED);
		int i = 0;
		
		if(m_StartPoints[i] == null){
			i = m_StartPoints.length;
		}
		else {
			for(i = 0; i < m_StartPoints.length; i++){
				g.drawLine(m_StartPoints[i].x, m_StartPoints[i].y, m_EndPoints[i].x, m_EndPoints[i].y);
			}
		}
		
	}
	
	public int getPointCount() {
		return m_PointCount;
	}

	public int increasePointCount() {
		return m_PointCount++;
	}

	public boolean setPoint(Point point) {
		
		m_StartPoints[getPointCount()] = point;
		return true;
	}

	private class PaintHandler implements MouseListener, MouseMotionListener {

		public void mousePressed(MouseEvent e) {
			
		}

		public void mouseMoved(MouseEvent e) {

		}

		public void mouseDragged(MouseEvent e) {
			
		}
		
		public void mouseReleased(MouseEvent e) {
			Point endPoint = e.getPoint();
			m_EndPoints[m_PointCount] = endPoint;
			increasePointCount();

		}

		public void mouseClicked(MouseEvent e) {
			m_prevPoint = e.getPoint();
			m_StartPoints[m_PointCount] = m_prevPoint ;
			if(m_StartPoints.length > m_EndPoints.length){
				m_currPoint  = e.getPoint();
			}
			
			/*
			System.out.println(e.getPoint());
			Point currPoint = e.getPoint();
			m_StartPoints[m_PointCount] = currPoint ;
			repaint();*/
		}

		public void mouseEntered(MouseEvent e) {
		
		}

		public void mouseExited(MouseEvent e) {
			
		}
	}

	
	private Point m_currPoint;
	private Point m_prevPoint;
	private int m_PointCount = 0;
	private final int MAX_POINTS = 100000;
	private Point m_StartPoints[] = new Point[MAX_POINTS];
	private Point m_EndPoints[] = new Point[MAX_POINTS];
}
