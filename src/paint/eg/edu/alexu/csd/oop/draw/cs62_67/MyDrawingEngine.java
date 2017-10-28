package paint.eg.edu.alexu.csd.oop.draw.cs62_67;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import javax.management.RuntimeErrorException;

import paint.eg.edu.alexu.csd.oop.draw.DrawingEngine;
import paint.eg.edu.alexu.csd.oop.draw.Shape;

public class MyDrawingEngine implements DrawingEngine {
	
	private Stack<ICommand> undoActions = new Stack<ICommand>();
	private Stack<ICommand> redoActions = new Stack<ICommand>();
	private ShapeFactory shape = new ShapeFactory();
	private ArrayList<Shape> shapes;
	@Override
	public void refresh(Graphics canvas) {
		Shape [] shapes = getShapes();
		for(Shape i : shapes){
			shape.createShape(i).draw(canvas);
		}
	}

	@Override
	public void addShape(Shape shape) {
		AddShape addShape = new AddShape(this.shapes,shape);
		addShape.execute();
		undoActions.push(addShape);
	}

	@Override
	public void removeShape(Shape shape) {
		RemoveShape removeShape = new RemoveShape(this.shapes,shape);
		removeShape.execute();
		undoActions.push(removeShape);
	}

	@Override
	public void updateShape(Shape oldShape, Shape newShape) {
		UpdateShape updateShape = new UpdateShape(this.shapes, oldShape, newShape);
		updateShape.execute();
		undoActions.push(updateShape);
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
		try{
			ICommand action = undoActions.pop();
			action.unexecute();
			redoActions.push(action);
		}catch (Exception e) {
			throw new RuntimeException("nothing to undo");
		}
	}

	@Override
	public void redo() {
		try{
			ICommand action = redoActions.pop();
			action.execute();
			undoActions.push(action);
		}catch (Exception e) {
			throw new RuntimeException("nothing to undo");
		}
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
