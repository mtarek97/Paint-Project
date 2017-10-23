package paint.eg.edu.alexu.csd.oop.draw.cs62_67;

import paint.eg.edu.alexu.csd.oop.draw.Shape;

public class MainController{

	ShapeFactory factory;
	Shape shape;
	
	public void orderShape(String type){
		shape = factory.createShape(type);
	}
	
	
	public static void main(String args[]){
		GUi Paint = new GUi();
	}
	
}
