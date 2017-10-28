package paint.eg.edu.alexu.csd.oop.draw.cs62_67;

import java.awt.Graphics;
import java.awt.Point;
import java.util.Map;
import java.util.HashMap;


import paint.eg.edu.alexu.csd.oop.draw.Shape;

public class Ellipse extends MyShape{
	private Map<String, Double> properties = getProperties();
	public static final String X_KEY = "xAxis";
	public static final String Y_KEY = "yAxis";
	
	public Ellipse() {
		setColor(this.getColor());
		setPosition(this.getPosition());
		this.properties.put(X_KEY, 0.0);
		this.properties.put(Y_KEY, 0.0);
		setProperties(this.properties);
		setFillColor(this.getFillColor());
	}
	
	@Override
	public void draw(Graphics canvas) {
		Point position = getPosition();
		double x = properties.get(X_KEY);
		double y = properties.get(Y_KEY);
		canvas.drawOval(position.x, position.y, (int)x, (int)y);
	}
	@Override
	public Object clone() throws CloneNotSupportedException{
		Shape clonedShape = new Ellipse();
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
