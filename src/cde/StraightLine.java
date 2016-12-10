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
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;

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

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.RED);
		
		for(int i = 0; i < m_StartPoints.size(); i++){
			Point startPoints = m_StartPoints.get(i);
			Point endPoints = m_EndPoints.get(i);
			
			g.drawLine(startPoints.x, startPoints.y, endPoints.x, endPoints.y);
		}
		
	}
	private class PaintHandler implements MouseListener, MouseMotionListener {

		public void mousePressed(MouseEvent e) {
			System.out.println("PaintHandler::mousePressed() " + e.toString());
		}

		public void mouseMoved(MouseEvent e) {}

		public void mouseDragged(MouseEvent e) {
			System.out.println("PaintHandler::mouseDragged() " + e.toString());
		}
		
		public void mouseReleased(MouseEvent e) {
			System.out.println("PaintHandler::mouseReleased() " + e.toString());
		}

		public void mouseClicked(MouseEvent e) {
			System.out.println("PaintHandler::mouseClicked() " + e.toString());
			if (m_StartPoints.size() == m_EndPoints.size()){
				m_StartPoints.add(e.getPoint());
			}else {
				m_currPoint  = e.getPoint();
				m_EndPoints.add(m_currPoint);
				
			}
			repaint();
			
		}

		public void mouseEntered(MouseEvent e) {
			System.out.println("PaintHandler::mouseEntered() " + e.toString());
			
		}

		public void mouseExited(MouseEvent e) {
			System.out.println("PaintHandler::mouseExited() " + e.toString());
		}
	}

	
	private Point m_currPoint;
	private Point m_prevPoint;
	private List<Point> m_StartPoints = new ArrayList<Point>();
	private List<Point> m_EndPoints = new ArrayList<Point>();
}
