package eg.edu.alexu.csd.oop.draw.cs62_67.model;

import java.util.ArrayList;

import eg.edu.alexu.csd.oop.draw.ICommand;
import eg.edu.alexu.csd.oop.draw.Shape;

public class RemoveShape implements ICommand{
	private ArrayList<Shape> shapes;
	private Shape shape;
	public RemoveShape(ArrayList<Shape> shapes, Shape shape){
		this.shapes = shapes;
		this.shape = shape;
	}
	@Override
	public void execute() {
		this.shapes.remove(shape);
		
	}
	@Override
	public void unexecute() {
		this.shapes.add(shape);
	}
	@Override
	public Shape getNewShape() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Shape getOldShape() {
		// TODO Auto-generated method stub
		return shape;
	}
	
	
}
