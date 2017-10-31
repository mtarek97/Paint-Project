package paint.eg.edu.alexu.csd.oop.draw.cs62_67;

import java.awt.Graphics;
import java.awt.Point;
import java.util.HashMap;
import java.util.Map;

import paint.eg.edu.alexu.csd.oop.draw.Shape;

public class Rectangle extends MyShape {
	private Map<String, Double> properties = getProperties();
	public static final String LENGTH_KEY = "xAxis";
	public static final String WIDTH_KEY = "yAxis";
	
	public Rectangle() {
		setColor(this.getColor());
		// center of mass
		setPosition(this.getPosition());
		this.properties.put(LENGTH_KEY, 0.0);
		this.properties.put(WIDTH_KEY, 0.0);
		setProperties(this.properties);
		setFillColor(this.getFillColor());
	}
	
	@Override
	public void draw(Graphics canvas) {
		Point position = getPosition();
		double length = properties.get(LENGTH_KEY);
		double width = properties.get(WIDTH_KEY);
		canvas.drawRect(position.x, position.y, (int)width, (int)length);
	}
	
	@Override
	public Object clone() throws CloneNotSupportedException{
		Shape clonedShape = new Rectangle();
        clonedShape.setColor(this.getColor());
        clonedShape.setFillColor(this.getFillColor());
        clonedShape.setPosition(this.getPosition());
        Map<String, Double> newprop = new HashMap<String,Double>();
        for (Map.Entry s: this.properties.entrySet()){
            String key = (String) s.getKey(); 
            Double value = (Double) s.getValue();
        	newprop.put(key, value);
        }
        clonedShape.setProperties(newprop);
        return clonedShape;	
    }
}
