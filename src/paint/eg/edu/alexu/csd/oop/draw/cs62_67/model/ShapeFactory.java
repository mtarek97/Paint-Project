package paint.eg.edu.alexu.csd.oop.draw.cs62_67.model;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import paint.eg.edu.alexu.csd.oop.draw.Shape;

public class ShapeFactory {
	private MyShape shape = null;
	private MyDrawingEngine engine;

	public ShapeFactory() {};

	public ShapeFactory(MyDrawingEngine engine) {
		this.engine = engine;
	}

	public Shape createShape(String shape) {

		for (Class<? extends Shape> i : engine.getSupportedShapes()) {
			if (shape.equals(i.getSimpleName())) {
				try {
					Constructor<?> constuctor = null;
					try {
						constuctor = i.getConstructor();
					} catch (NoSuchMethodException e) {
						e.printStackTrace();
					} catch (SecurityException e) {
						e.printStackTrace();
					}

					try {
						this.shape = (MyShape) constuctor.newInstance();
					} catch (IllegalArgumentException e) {
						e.printStackTrace();
					} catch (InvocationTargetException e) {
						e.printStackTrace();
					}
					return this.shape;

				} catch (InstantiationException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				}
			}
		}

		return null;

	}

	public Shape createShape(Shape shape) {
		String shapeName = shape.getClass().getSimpleName();
		return createShape(shapeName);
	}
}
