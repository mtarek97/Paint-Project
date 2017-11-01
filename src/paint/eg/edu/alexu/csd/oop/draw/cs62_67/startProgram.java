package paint.eg.edu.alexu.csd.oop.draw.cs62_67;

import paint.eg.edu.alexu.csd.oop.draw.DrawingEngine;
import paint.eg.edu.alexu.csd.oop.draw.Shape;

public class startProgram {
	public static void main(String[] args){
		DrawingEngine engine = new MyDrawingEngine();
		ShapeFactory factory = new ShapeFactory();
		GUI Paint = new GUI();
		MainController main = new MainController(engine, factory, Paint);
		Paint.setVisible(true);
	}

}
