package paint.eg.edu.alexu.csd.oop.draw.cs62_67;

import java.awt.Graphics;
import java.util.Map;

public class LineSegment extends MyShape {
	// why this ?
	private Map<String, Double> properties = getProperties();
	
	public static final String X1_KEY = "x1";
	public static final String Y1_KEY = "y1";
	public static final String X2_KEY = "x2";
	public static final String Y2_KEY = "y2";
	
	//constructor
	public LineSegment(double x1 , double y1, double x2, double y2) {
		setColor(this.getColor());
		setPosition(this.getPosition());
		this.properties.put(X1_KEY, x1);
		this.properties.put(Y1_KEY, y1);
		this.properties.put(X2_KEY, x2);
		this.properties.put(Y2_KEY, y2);
		setProperties(this.properties);
		setFillColor(this.getFillColor());
	}
	
	@Override
	public void draw(Graphics canvas) {
		Map<String, Double> properties = getProperties();
		// I got it
		canvas.drawLine( properties.get(X1_KEY).intValue(),properties.get(Y1_KEY).intValue()
				, properties.get(X2_KEY).intValue(), properties.get(Y2_KEY).intValue());
	}
	
}
