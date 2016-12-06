package cde;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent; 
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseListener;
import javax.swing.JPanel;
import javax.swing.JFrame; 
import javax.swing.JPanel;


public abstract class DrawElement extends JPanel{
		
	public int getThickness() {
		return m_thickness;
	}

	public void setThickness(int m_thickness) {
		this.m_thickness = m_thickness;
	}

	public void setColour(Color m_colour) {
	 	this.m_colour = m_colour;
	}
	
	public int getPointCount(){
		return m_PointCount;
	}

	public DrawElement(){
		PaintHandler handler = new PaintHandler();
		this.addMouseListener(handler);
		this.addMouseMotionListener(handler);
	}
	
	abstract void draw(Graphics g);
	

	private class PaintHandler implements MouseListener, MouseMotionListener{
		
	public void mouseDragged(MouseEvent event){
		boolean test = false;
		if(test){
			System.out.println("PaintHandler::mouseDragged() " + event.toString());
		}
		
	}
	
	public void mouseMoved(MouseEvent event){
		boolean test = false;
		if(test){
			System.out.println("PaintHandler::mouseMoved() " +event.toString());
			
		}
	}
	
	public void mouseEntered(MouseEvent event){
		boolean test = true;
		if(test){
			System.out.println("PaintHandler::mouseEntered() " + event.toString());
		}
	}
	
	public void mouseExited(MouseEvent event){
		boolean test = true;
		if(test){
			System.out.println("PaintHandler::mouseExited() " + event.toString());
		}
	}
	
	public void mouseClicked(MouseEvent event){
		boolean test = true;
		if(test){
			System.out.println("PaintHandler::mouseClicked() " + event.toString());
		}
	}
	public void mousePressed(MouseEvent event){
		boolean test = true;
		if (test){
			System.out.println("PaintHandler::mousePresses() " + event.toString());
		}
	}
	
	public void mouseReleased(MouseEvent event){
		boolean test = true;
		if(test){
			System.out.println("PaintHandler::mouseReleased() " + event.toString());
		}
	}
	
	}
	protected int m_thickness;
	protected Color m_colour;
	public final static int FRAME_WIDTH = 500;
	public final static int FRAME_HEIGHT = 500;
	private final int MAX_POINTS = 1000;
	private int m_PointCount = 0;
	private Point m_Points[]  = new Point[MAX_POINTS];
	
}
