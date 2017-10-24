package paint.eg.edu.alexu.csd.oop.draw.cs62_67;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import paint.eg.edu.alexu.csd.oop.draw.DrawingEngine;
import paint.eg.edu.alexu.csd.oop.draw.Shape;

public class MyDrawingEngine implements DrawingEngine {
	
	ShapeFactory shape = new ShapeFactory();
	ArrayList<Shape> shapes;
	@Override
	public void refresh(Graphics canvas) {
		Shape [] shapes = getShapes();
		for(Shape i : shapes){
			shape.createShape(i).draw(canvas);
		}
	}

	@Override
	public void addShape(Shape shape) {
		this.shapes.add(shape);
		
	}

	@Override
	public void removeShape(Shape shape) {
				this.shapes.remove(shape);
	}

	@Override
	public void updateShape(Shape oldShape, Shape newShape) {
		
		removeShape(oldShape);
		addShape(newShape);
	}

	@Override
	public Shape[] getShapes() {
		
		Shape[] shapesArr = new Shape[this.shapes.size()];
		int cnt=0;
		for(Shape i : this.shapes){
			shapesArr[cnt++] = i;
		}
		return shapesArr;
	}

	@Override
	public List<Class<? extends Shape>> getSupportedShapes() {
				return null;
	}

	@Override
	public void undo() {
		
	}

	@Override
	public void redo() {
		
		
	}

	@Override
	public void save(String path) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void load(String path) {
		// TODO Auto-generated method stub
		
	}

}
