package paint.eg.edu.alexu.csd.oop.draw.cs62_67.model;

import java.awt.Color;
import java.awt.Point;
import java.util.HashMap;
import java.util.Map;

import com.sun.xml.internal.fastinfoset.sax.Properties;

import paint.eg.edu.alexu.csd.oop.draw.Shape;

//An abstract class that implement the common methods between all shapes

public abstract class MyShape implements Shape, Cloneable{

	//common parameters between shapes
	private Map<String, Double> properties = new HashMap<>();
	private Point position = new Point(13, 12);
	private String name;
	private Color color = Color.black;
	private Color fillColor = color.white;
	
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
			
	}

	@Override
	//common
	public Map<String, Double> getProperties() {
		return null;
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

}
