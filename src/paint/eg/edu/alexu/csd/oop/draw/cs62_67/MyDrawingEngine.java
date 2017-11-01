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
	private ArrayList<Shape> shapes = new ArrayList<>();
	private List<Class<? extends Shape>> supportedShapes = new ArrayList<Class<? extends Shape>>();
	public MyDrawingEngine(){
		JavaClassLoader classLoader = new JavaClassLoader();
		String packageBinName = "paint.eg.edu.alexu.csd.oop.draw.cs62_67.";
		this.addPlugin((Class<? extends Shape>) classLoader.loadExtraClass(packageBinName + "Ellipse"));
		this.addPlugin((Class<? extends Shape>) classLoader.loadExtraClass(packageBinName + "Circle"));
		this.addPlugin((Class<? extends Shape>) classLoader.loadExtraClass(packageBinName + "LineSegment"));
		this.addPlugin((Class<? extends Shape>) classLoader.loadExtraClass(packageBinName + "Rectangle"));
		this.addPlugin((Class<? extends Shape>) classLoader.loadExtraClass(packageBinName + "Square"));
		this.addPlugin((Class<? extends Shape>) classLoader.loadExtraClass(packageBinName + "Triangle"));
	}
	
	@Override
	public void refresh(Graphics canvas) {
		Shape [] shapes = getShapes();
		for(Shape i : shapes){
			i.draw(canvas);
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
		return this.supportedShapes;
	}
	public void addPlugin(Class<? extends Shape> myClass){
		this.supportedShapes.add(myClass);
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
		int dotIndex = path.lastIndexOf('.');
		String extension =path.substring(dotIndex);
		if(extension.equals("xml")){
			XML.save(path, shapes);
		}else{
			throw new RuntimeException("unexpected extension");
		}
		
	}

	@Override
	public void load(String path) {
		int dotIndex = path.lastIndexOf('.');
		String extension =path.substring(dotIndex);
		if(extension.equals("xml")){
			XML.load(path, shapes);
		}else{
			throw new RuntimeException("unexpected extension");
		}
		
	}

}
