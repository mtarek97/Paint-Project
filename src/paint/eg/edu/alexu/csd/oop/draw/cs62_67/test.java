package paint.eg.edu.alexu.csd.oop.draw.cs62_67;

import paint.eg.edu.alexu.csd.oop.draw.cs62_67.model.MyShape;
import paint.eg.edu.alexu.csd.oop.draw.cs62_67.model.Triangle;

public class test {

    
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		  MyShape tr = new Triangle();
		  MyShape tr2 = new Triangle();
		  MyShape tr3 = new Triangle();
		 // ArrayList<Shape> savedShapes = new ArrayList<Shape>();
		  //savedShapes.add(cr);
		  //savedShapes.add(tr);
		//XML xml = new XML();
		//xml.save("test.xml", savedShapes);
		//ArrayList<Shape> loadedShapes = new ArrayList<Shape>();
		//xml.load("test.xml",loadedShapes);
		//System.out.println(loadedShapes.get(0).getColor());
		  System.out.println(tr.getName());
		  System.out.println(tr2.getName());
		  System.out.println(tr3.getName());
		  //System.out.println(cr2.getName());
		 // System.out.println(cr2.getClass().getSimpleName());
		  System.out.println(tr2.getNumOfShape("Triangle"));
	}
}
