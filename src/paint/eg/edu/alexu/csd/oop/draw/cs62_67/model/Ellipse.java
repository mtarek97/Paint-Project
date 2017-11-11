package paint.eg.edu.alexu.csd.oop.draw.cs62_67.model;

import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.HashMap;
import java.util.Map;

import paint.eg.edu.alexu.csd.oop.draw.Shape;

public class Ellipse extends MyShape{
	private Map<String, Double> properties = new HashMap<>();
	public static final String X_KEY = "xAxis";
	public static final String Y_KEY = "yAxis";
	
	public Ellipse() {
		setName(this.getClass().getSimpleName()+getNumOfShape(this.getClass().getSimpleName()));
		increaseNumOfShape(this.getClass().getSimpleName());
		setColor(this.getColor());
		setPosition(this.getPosition());
		this.properties.put(X_KEY, 0.0);
		this.properties.put(Y_KEY, 0.0);
		setProperties(this.properties);
		setFillColor(this.getFillColor());
		System.out.println(this.getName());
	}
	
	@Override
	public void draw(Graphics canvas) {
		Graphics2D g2 = (Graphics2D) canvas;
		g2.setStroke(new BasicStroke(6.0f));
		Point position = getPosition();

		double x = getProperties().get(X_KEY);
		double y = getProperties().get(Y_KEY);
		g2.setColor(getColor());
		g2.drawOval(position.x, position.y, (int)x, (int)y);
		g2.setColor(getFillColor());
		g2.fillOval(position.x, position.y, (int)x, (int)y);
		
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
