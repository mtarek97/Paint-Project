package paint.eg.edu.alexu.csd.oop.draw.cs62_67.model;

import java.awt.Graphics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import paint.eg.edu.alexu.csd.oop.draw.DrawingEngine;
import paint.eg.edu.alexu.csd.oop.draw.Shape;
public class MyDrawingEngine implements DrawingEngine{

	private List<ICommand> undoActions = new ArrayList<ICommand>();
	private List<ICommand> redoActions = new ArrayList<ICommand>();
	private ArrayList<Shape> shapes = new ArrayList<>();
	private ArrayList<MyShape> myShapes = new ArrayList<>();
	private List<Class<? extends Shape>> supportedShapes = new ArrayList<Class<? extends Shape>>();

	public MyDrawingEngine() {
		JavaClassLoader classLoader = new JavaClassLoader();
		String packageBinName = "paint.eg.edu.alexu.csd.oop.draw.cs62_67.model.";
		this.supportedShapes.add(classLoader.loadExtraClass(packageBinName + "Ellipse"));
		this.supportedShapes.add(classLoader.loadExtraClass(packageBinName + "Circle"));
		this.supportedShapes.add(classLoader.loadExtraClass(packageBinName + "LineSegment"));
		this.supportedShapes.add(classLoader.loadExtraClass(packageBinName + "Rectangle"));
		this.supportedShapes.add(classLoader.loadExtraClass(packageBinName + "Square"));
		this.supportedShapes.add(classLoader.loadExtraClass(packageBinName + "Triangle"));
	}

	@Override
	public void refresh(Graphics canvas) {
		Shape[] shapes = getShapes();
		for (Shape i : shapes) {
			i.draw(canvas);
		}
	}

	@Override
	public void addShape(Shape shape) {
		AddShape addShape = new AddShape(this.shapes, shape);
		addShape.execute();
		undoActions.add(addShape);
		redoActions.clear();
		if (undoActions.size() > 20) {
			undoActions.remove(0);
		}
	}

	@Override
	public void removeShape(Shape shape) {
		RemoveShape removeShape = new RemoveShape(this.shapes, shape);
		removeShape.execute();
		undoActions.add(removeShape);
		if (undoActions.size() > 20) {
			undoActions.remove(0);
		}
	}

	@Override
	public void updateShape(Shape oldShape, Shape newShape) {
		UpdateShape updateShape = new UpdateShape(this.shapes, oldShape, newShape);
		updateShape.execute();
		undoActions.add(updateShape);
		if (undoActions.size() > 20) {
			undoActions.remove(0);
		}
	}

	@Override
	public Shape[] getShapes() {

		Shape[] shapesArr = new Shape[this.shapes.size()];
		int cnt = 0;
		for (Shape i : this.shapes) {
			shapesArr[cnt++] = i;
		}
		return shapesArr;
	}

	@Override
	public List<Class<? extends Shape>> getSupportedShapes() {
		return this.supportedShapes;
	}

	@Override
	public void undo() {
		try {
			ICommand action = undoActions.remove(undoActions.size() - 1);
			action.unexecute();
			redoActions.add(action);
			if (redoActions.size() > 20) {
				redoActions.remove(0);
			}
		} catch (Exception e) {
			throw new RuntimeException("nothing to undo");
		}
	}

	@Override
	public void redo() {
		try {
			ICommand action = redoActions.remove(redoActions.size() - 1);
			action.execute();
			undoActions.add(action);
			if (undoActions.size() > 20) {
				undoActions.remove(0);
			}
		} catch (Exception e) {
			throw new RuntimeException("nothing to redo");
		}
	}

	@Override
	public void save(String path) {
		int dotIndex = path.lastIndexOf('.');
		String extension = path.substring(dotIndex + 1);

		if (extension.equals("XmL")) {
			XML xml = new XML(this);
			try {
				xml.save(path, this.shapes);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (extension.equals("JsOn")) {
			Json json = new Json(this);
			try {
				json.save(path, this.shapes);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			throw new RuntimeException("unexpected extension");
		}

	}

	@Override
	public void load(String path) {
		this.shapes.clear();

		int dotIndex = path.lastIndexOf('.');
		String extension = path.substring(dotIndex + 1);
		if (extension.equals("XmL")) {
			XML xml = new XML(this);
			xml.load(path, this.shapes);
		} else if (extension.equals("JsOn")) {
			Json json = new Json(this);
			try {
				json.load(path, this.shapes);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			throw new RuntimeException("unexpected extension");
		}

	}
	
	public void addPlugin(Class<? extends Shape> newClass){
		this.supportedShapes.add(newClass);
	}


}
