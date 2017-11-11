package paint.eg.edu.alexu.csd.oop.draw.cs62_67.model;

import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.HashMap;
import java.util.Map;

import paint.eg.edu.alexu.csd.oop.draw.Shape;

public class Triangle extends MyShape{
	private Map<String, Double> properties = new HashMap<>();
	public static final String X1_KEY = "x1";
	public static final String Y1_KEY = "y1";
	public static final String X2_KEY = "x2";
	public static final String Y2_KEY = "y2";
	public static final String X3_KEY = "x3";
	public static final String Y3_KEY = "y3";
	
	public Triangle() {
		setName(this.getClass().getSimpleName()+getNumOfShape(this.getClass().getSimpleName()));
		increaseNumOfShape(this.getClass().getSimpleName());
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
		System.out.println(this.getName());
	}
	public void setProperties(Map<String, Double> properties) {
		this.properties = properties;
	}

	@Override
	//common
	public Map<String, Double> getProperties() {
		return this.properties;
	}

	@Override
	public void draw(Graphics canvas) {
		Graphics2D g2 = (Graphics2D) canvas;
		g2.setStroke(new BasicStroke(6.0f));
		int[] x = new int[3];
		int[] y = new int[3];
		x[0] = getProperties().get(X1_KEY).intValue();
		y[0] = getProperties().get(Y1_KEY).intValue();
		x[1] = getProperties().get(X2_KEY).intValue();
		y[1] = getProperties().get(Y2_KEY).intValue();
		x[2] = getProperties().get(X3_KEY).intValue();
		y[2] = getProperties().get(Y3_KEY).intValue();
		g2.setColor(getColor());
		g2.drawPolygon(x, y, 3);
		g2.setColor(getFillColor());
		g2.fillPolygon(x, y, 3);
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
