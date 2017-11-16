package paint.eg.edu.alexu.csd.oop.draw.cs62_67.model.shapes;

import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.HashMap;
import java.util.Map;

import paint.eg.edu.alexu.csd.oop.draw.Shape;
import paint.eg.edu.alexu.csd.oop.draw.cs62_67.model.MyShape;

public class LineSegment extends MyShape {
	// why this ?
	private Map<String, Double> properties = new HashMap<>();
	
	public static final String X1_KEY = "x1";
	public static final String Y1_KEY = "y1";
	public static final String X2_KEY = "x2";
	public static final String Y2_KEY = "y2";
	
	//constructor
	public LineSegment() {
		setColor(this.getColor());
		setPosition(this.getPosition());
		this.properties.put(X1_KEY, 0.0);
		this.properties.put(Y1_KEY, 0.0);
		this.properties.put(X2_KEY, 0.0);
		this.properties.put(Y2_KEY, 0.0);
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
		Map<String, Double> properties = getProperties();
		g2.setStroke(new BasicStroke(5.0f));
		// I got it
		canvas.setColor(getColor());
		canvas.drawLine( getPosition().x,getPosition().y
				, getProperties().get(X1_KEY).intValue(), getProperties().get(Y1_KEY).intValue());
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
