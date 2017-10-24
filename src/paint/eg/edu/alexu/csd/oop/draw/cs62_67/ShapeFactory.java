package paint.eg.edu.alexu.csd.oop.draw.cs62_67;

import paint.eg.edu.alexu.csd.oop.draw.Shape;

public class ShapeFactory {
	private MyShape shape = null;
	
	public Shape createShape(String shape) {
		if (shape.equals("Circle")) {
			this.shape = new Circle();
			return this.shape;
		} else if (shape.equals("Ellipse")) {
			this.shape = new Ellipse();
			return this.shape;
		} else if (shape.equals("Square")) {
			this.shape = new Square();
			return this.shape;
		} else if (shape.equals("Rectangle")) {
			this.shape = new Rectangle();
			return this.shape;
		} else if (shape.equals("LineSegment")) {
			this.shape = new LineSegment();
			return this.shape;
		} else if (shape.equals("Triangle")) {
			this.shape = new Triangle();
			return this.shape;
		} else {
			return null;
		}
	}
	public Shape createShape(Shape shape) {
		if (shape instanceof Circle) {
			this.shape = new Circle();
			return this.shape;
		} else if (shape instanceof Ellipse) {
			this.shape = new Ellipse();
			return this.shape;
		} else if (shape instanceof Square) {
			this.shape = new Square();
			return this.shape;
		} else if (shape instanceof Rectangle) {
			this.shape = new Rectangle();
			return this.shape;
		} else if (shape instanceof LineSegment) {
			this.shape = new LineSegment();
			return this.shape;
		} else if (shape instanceof Triangle) {
			this.shape = new Triangle();
			return this.shape;
		} else {
			return null;
		}
	}
}
