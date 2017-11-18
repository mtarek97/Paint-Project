package paint.eg.edu.alexu.csd.oop.draw.cs62_67.model;

import java.util.ArrayList;

import paint.eg.edu.alexu.csd.oop.draw.ICommand;
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
		for(int i=0;i<shapes.size();i++){
			if(shapes.get(i).equals(oldShape)){
				this.shapes.remove(oldShape);
				this.shapes.add(i, newShape);
			}
		}


	}

	@Override
	public void unexecute() {
		for(int i=0;i<shapes.size();i++){
			if(shapes.get(i).equals(newShape)){
				this.shapes.remove(newShape);
				this.shapes.add(i, oldShape);
			}
		}
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
