package paint.eg.edu.alexu.csd.oop.draw.cs62_67.model;

import java.util.ArrayList;

import paint.eg.edu.alexu.csd.oop.draw.Shape;

public class UpdateShape implements ICommand{

	private ArrayList<Shape> shapes;
	private Shape oldShape;
	private Shape newShape;
	public UpdateShape(ArrayList<Shape> shapes, Shape oldShape, Shape newShape){
		this.shapes = shapes;
		this.oldShape = oldShape;
		this.newShape = newShape;
	}
	@Override
	public void execute() {
		this.shapes.remove(oldShape);
		this.shapes.add(newShape);
	}

	@Override
	public void unexecute() {
		this.shapes.remove(newShape);
		this.shapes.add(oldShape);
	}
	@Override
	public Shape getNewShape() {
		// TODO Auto-generated method stub
		return newShape;
	}
	@Override
	public Shape getOldShape() {
		// TODO Auto-generated method stub
		return oldShape;
	}
	
}
