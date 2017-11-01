package paint.eg.edu.alexu.csd.oop.draw.cs62_67;

import java.util.ArrayList;

import paint.eg.edu.alexu.csd.oop.draw.Shape;

public class test {

    
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		  Shape cr = new Circle();
		  Triangle tr = new Triangle();
		  ArrayList<Shape> savedShapes = new ArrayList<Shape>();
		  savedShapes.add(cr);
		  savedShapes.add(tr);
		XML xml = new XML();
		xml.save("test.xml", savedShapes);
		ArrayList<Shape> loadedShapes = new ArrayList<Shape>();
		xml.load("test.xml",loadedShapes);
		System.out.println(loadedShapes.get(0).getColor());
	}

}
