package paint.eg.edu.alexu.csd.oop.draw.cs62_67;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;
import java.util.Map;

import javax.swing.JComponent;

import paint.eg.edu.alexu.csd.oop.draw.DrawingEngine;
import paint.eg.edu.alexu.csd.oop.draw.Shape;

public class MainController{
    
	private DrawingEngine engine;
    private ShapeFactory factory;
    private Shape shape;
    private Shape dragedShape;
    private GUI Paint;
	public MainController(DrawingEngine engine,ShapeFactory factory,GUI Paint){
		this.engine = engine;
		this.factory = factory;
		this.Paint = Paint;
		this.Paint.getContentPane().add(new PaintSurface(), BorderLayout.CENTER);
		this.Paint.addCircleListner(new CircleListner());
		this.Paint.addLineListner(new LineListner());
		this.Paint.addSquareListner(new SquareListner());
		this.Paint.addRectangleListener(new RectangleListner());
		this.Paint.addEllipseListner(new EllipseListner());
	}
	
	public void orderShape(String type){
		shape = factory.createShape(type);
		dragedShape = factory.createShape(type);
	}
	
	private class PaintSurface extends JComponent {
		ArrayList<paint.eg.edu.alexu.csd.oop.draw.Shape> shapes = new ArrayList<paint.eg.edu.alexu.csd.oop.draw.Shape>();
		private	Point startDrag, endDrag;
		public PaintSurface() {
			this.addMouseListener(new MouseAdapter() {
			    public void mousePressed(MouseEvent e) {
			    	startDrag = new Point(e.getX(), e.getY());
			        endDrag = startDrag;
			        repaint();
			    }
	
				public void mouseReleased(MouseEvent e) {
					setProperties(shape,startDrag, e.getPoint());
				    shapes.add(shape);
				    startDrag = null;
				    endDrag = null;
				    repaint();
				}
			});

			this.addMouseMotionListener(new MouseMotionAdapter() {
				public void mouseDragged(MouseEvent e) {
			    	endDrag = new Point(e.getX(), e.getY());
			        repaint();
			    }
			});
		}
		  
		public void paint(Graphics g) {
			Graphics2D g2 = (Graphics2D) g;
		    for (paint.eg.edu.alexu.csd.oop.draw.Shape s : shapes) {
		    	g2.setPaint(Color.BLACK);
		        s.draw(g2);
		    }
		    if (startDrag != null && endDrag != null) {
		        g2.setPaint(Color.LIGHT_GRAY);
		        setProperties(dragedShape, startDrag, endDrag);
		      	dragedShape.draw(g2);
		     }
		}
		public void setProperties(Shape shape,Point start, Point end){
			shape.setPosition(startDrag);
			Map<String, Double> prep = shape.getProperties();
			if(shape instanceof Circle){	
				prep.put("xAxis", (double) end.getX() - start.getX());
				prep.put("yAxis", (double) end.getX() - start.getX());
			}
			else if(shape instanceof Square){	
				prep.put("xAxis", (double) end.getX() - start.getX());
				prep.put("yAxis", (double) end.getX() - start.getX());
			}
			else if(shape instanceof Rectangle){	
					prep.put("xAxis", (double) end.getY() - start.getY());
					prep.put("yAxis", (double) end.getX() - start.getX());
			}
			else if(shape instanceof Ellipse){	
				prep.put("xAxis", (double) end.getX() - start.getX());
				prep.put("yAxis", (double) end.getY() - start.getY());
			}
			else if(shape instanceof LineSegment){
				prep.put("x1", end.getX());
				prep.put("y1", end.getY());
				prep.put("x2", start.getX());
				prep.put("y2", start.getY());
			}
			shape.setProperties(prep);
		}
	}
	class CircleListner implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			orderShape("Circle");
		}
		
	}
	class LineListner implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			orderShape("LineSegment");
		}
		
	}
	class SquareListner implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			orderShape("Square");
			
		}
		
	}
	class RectangleListner implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			orderShape("Rectangle");
		}
	}
	class EllipseListner implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			orderShape("Ellipse");
			
		}
		
	}
}
