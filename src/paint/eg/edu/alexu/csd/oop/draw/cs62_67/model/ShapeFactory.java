package paint.eg.edu.alexu.csd.oop.draw.cs62_67.model;

import paint.eg.edu.alexu.csd.oop.draw.Shape;

public class ShapeFactory {
	private MyShape shape = null;
	private MyDrawingEngine engine;

	public ShapeFactory() {
	};

	public ShapeFactory(MyDrawingEngine engine) {
		this.engine = engine;
	}

	public Shape createShape(String shape) {
		/*
		 * for (Class<? extends Shape> i : engine
		 * .getSupportedShapes()) { if
		 * (shape.equals(i.getSimpleName())) {
		 * 
		 * try { this.shape = (MyShape) i.newInstance();
		 * return this.shape;
		 * 
		 * } catch (InstantiationException e) {
		 * e.printStackTrace(); } catch
		 * (IllegalAccessException e) { e.printStackTrace();
		 * } } } return null
		 * 
		 * }
		 */

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

	public Shape createShape(
	Shape shape) {/*
					 * for (Class<? extends Shape> i :
					 * engine .getSupportedShapes()) { if
					 * (shape.equals(i)) {
					 * 
					 * try { this.shape = (MyShape)
					 * i.newInstance(); return this.shape;
					 * 
					 * } catch (InstantiationException e) {
					 * e.printStackTrace(); } catch
					 * (IllegalAccessException e) {
					 * e.printStackTrace(); } } } return
					 * null;
					 * 
					 * }
					 * 
					 * }
					 */

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
