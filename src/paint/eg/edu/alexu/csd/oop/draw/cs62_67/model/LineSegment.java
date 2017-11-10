package paint.eg.edu.alexu.csd.oop.draw.cs62_67.model;

import java.awt.Graphics;
import java.util.HashMap;
import java.util.Map;

import paint.eg.edu.alexu.csd.oop.draw.Shape;

public class LineSegment extends MyShape {
	// why this ?
	private Map<String, Double> properties = getProperties();
	
	public static final String X1_KEY = "x1";
	public static final String Y1_KEY = "y1";
	public static final String X2_KEY = "x2";
	public static final String Y2_KEY = "y2";
	
	//constructor
	public LineSegment() {
		setName(this.getClass().getSimpleName()+getNumOfShape(this.getClass().getSimpleName()));
		increaseNumOfShape(this.getClass().getSimpleName());
		setColor(this.getColor());
		setPosition(this.getPosition());
		this.properties.put(X1_KEY, 0.0);
		this.properties.put(Y1_KEY, 0.0);
		this.properties.put(X2_KEY, 0.0);
		this.properties.put(Y2_KEY, 0.0);
		setProperties(this.properties);
		setFillColor(this.getFillColor());
		System.out.println(this.getName());
	}
	
	@Override
	public void draw(Graphics canvas) {
		Map<String, Double> properties = getProperties();
		// I got it
		canvas.drawLine( properties.get(X1_KEY).intValue(),properties.get(Y1_KEY).intValue()
				, properties.get(X2_KEY).intValue(), properties.get(Y2_KEY).intValue());
	}
	@Override
	public Object clone() throws CloneNotSupportedException{
		Shape clonedShape = new LineSegment();
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
