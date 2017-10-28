package paint.eg.edu.alexu.csd.oop.draw.cs62_67;

import java.awt.Graphics;
import java.awt.Point;
import java.util.HashMap;
import java.util.Map;

import paint.eg.edu.alexu.csd.oop.draw.Shape;

public class Triangle extends MyShape{
	private Map<String, Double> properties = getProperties();
	public static final String X1_KEY = "x1";
	public static final String Y1_KEY = "y1";
	public static final String X2_KEY = "x2";
	public static final String Y2_KEY = "y2";
	public static final String X3_KEY = "x3";
	public static final String Y3_KEY = "y3";
	
	public Triangle() {
		setColor(this.getColor());
		setPosition(this.getPosition());
		this.properties.put(X1_KEY, 0.0);
		this.properties.put(Y1_KEY, 0.0);
		this.properties.put(X2_KEY, 0.0);
		this.properties.put(Y2_KEY, 0.0);
		this.properties.put(X3_KEY, 0.0);
		this.properties.put(Y3_KEY, 0.0);
		setProperties(this.properties);
		setFillColor(this.getFillColor());
	}
	
	@Override
	public void draw(Graphics canvas) {
		Point position = getPosition();
		int x1 = properties.get(X1_KEY).intValue();
		int y1 = properties.get(Y1_KEY).intValue();
		int x2 = properties.get(X2_KEY).intValue();
		int y2 = properties.get(Y2_KEY).intValue();
		int x3 = properties.get(X3_KEY).intValue();
		int y3 = properties.get(Y3_KEY).intValue();
		
		canvas.drawLine(x1, y1, x2, y2);
		canvas.drawLine(x2, y2, x3, y3);
		canvas.drawLine(x3, y3, x2, y1);
	}
	
	@Override
	public Object clone() throws CloneNotSupportedException{
		Shape clonedShape = new Triangle();
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
