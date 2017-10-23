package paint.eg.edu.alexu.csd.oop.draw.cs62_67;

import paint.eg.edu.alexu.csd.oop.draw.Shape;

public class ShapeFactory {
	MyShape shape = null;

	public Shape createShape(String shape) {
		if (shape.equals("Circle")) {
			this.shape = new Circle(0);
			return this.shape;
		} else if (shape.equals("Ellipse")) {
			this.shape = new Ellipse(0, 0);
			return this.shape;
		} else if (shape.equals("Square")) {
			this.shape = new Square(0);
			return this.shape;
		} else if (shape.equals("Rectangle")) {
			this.shape = new Rectangle(0, 0);
			return this.shape;
		} else if (shape.equals("LineSegment")) {
			this.shape = new LineSegment(0, 0, 0, 0);
			return this.shape;
		} else if (shape.equals("Triangle")) {
			this.shape = new Triangle(0, 0, 0, 0, 0, 0);
			return this.shape;
		} else {
			return null;
		}
	}
}
