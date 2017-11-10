package paint.eg.edu.alexu.csd.oop.draw.cs62_67.model;

import java.awt.Color;
import java.awt.Point;
import java.util.HashMap;
import java.util.Map;

import paint.eg.edu.alexu.csd.oop.draw.Shape;

//An abstract class that implement the common methods between all shapes

public abstract class MyShape implements Shape, Cloneable{

	//common parameters between shapes
	private static Map<String, Integer> numOfShapes = new HashMap<String, Integer>(){
		{
		put("Circle",1);
		put("Ellipse",1);
		put("LineSegment",1);
		put("Rectangle",1);
		put("Square",1);
		put("Triangle",1);
		}
	};
	private Map<String, Double> properties = new HashMap<>();
	private Point position;
	private String name;
	private Color color;
	private Color fillColor;
	
	@Override
	//common
	public void setPosition(Point position) {
		this.position = position;
	}

	@Override
	//common
	public Point getPosition() {
		return this.position;
	}

	@Override
	//common
	public void setColor(Color color) {
		this.color = color;
	}

	@Override
	//common
	public Color getColor() {
		return this.color;
	}
	
	@Override
	//common
	public void setProperties(Map<String, Double> properties) {
		this.properties = properties;	
	}

	@Override
	//common
	public Map<String, Double> getProperties() {
		return this.properties;
	}

	@Override
	//common
	public void setFillColor(Color color) {
		this.fillColor = color;
		
	}

	@Override
	//common
	public Color getFillColor() {
		return this.fillColor;
	}
	
	@Override
	//not common
	public void draw(java.awt.Graphics canvas){
		
	}

	@Override
	//not common
    public Object clone() throws CloneNotSupportedException{
		return null;
			
    }

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getNumOfShape(String type) {
		return this.numOfShapes.get(type);
	}

	public void increaseNumOfShape(String type) {
		this.numOfShapes.put(type, numOfShapes.get(type) +1);
	}
	
	
}
