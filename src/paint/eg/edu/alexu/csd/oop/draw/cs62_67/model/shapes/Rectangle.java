package paint.eg.edu.alexu.csd.oop.draw.cs62_67.model.shapes;

import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.HashMap;
import java.util.Map;

import paint.eg.edu.alexu.csd.oop.draw.Shape;
import paint.eg.edu.alexu.csd.oop.draw.cs62_67.model.MyShape;

public class Rectangle extends MyShape {
	private Map<String, Double> properties = new HashMap<>();
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
		Point position = getPosition();
		double length = getProperties().get(LENGTH_KEY);
		double width = getProperties().get(WIDTH_KEY);
		g2.setColor(getColor());
		g2.drawRect(position.x, position.y, (int)width, (int)length);
		g2.setColor(getFillColor());
		g2.fillRect(position.x, position.y, (int)width, (int)length);
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
