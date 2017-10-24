package paint.eg.edu.alexu.csd.oop.draw.cs62_67;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import paint.eg.edu.alexu.csd.oop.draw.DrawingEngine;
import paint.eg.edu.alexu.csd.oop.draw.Shape;

public class MyDrawingEngine implements DrawingEngine {

	ArrayList<Shape> shapes;
	@Override
	public void refresh(Graphics canvas) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addShape(Shape shape) {
		// TODO Auto-generated method stub
		this.shapes.add(shape);
		
	}

	@Override
	public void removeShape(Shape shape) {
		// TODO Auto-generated method stub
		this.shapes.remove(shape);
	}

	@Override
	public void updateShape(Shape oldShape, Shape newShape) {
		// TODO Auto-generated method stub
		removeShape(oldShape);
		addShape(newShape);
	}

	@Override
	public Shape[] getShapes() {
		// TODO Auto-generated method stub
		Shape[] shapesArr = new Shape[this.shapes.size()];
		int cnt=0;
		for(Shape i : this.shapes){
			shapesArr[cnt++] = i;
		}
		return shapesArr;
	}

	@Override
	public List<Class<? extends Shape>> getSupportedShapes() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void undo() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void redo() {
		// TODO Auto-generated method stub
		
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
