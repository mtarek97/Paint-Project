package paint.eg.edu.alexu.csd.oop.draw.cs62_67;

import java.awt.Graphics;
import java.awt.Point;
import java.util.Map;

public class Ellipse extends MyShape{
	private Map<String, Double> properties = getProperties();
	public static final String X_KEY = "xAxis";
	public static final String Y_KEY = "yAxis";
	
	public Ellipse(double xAxis, double yAxis) {
		setColor(this.getColor());
		setPosition(this.getPosition());
		this.properties.put(X_KEY, xAxis);
		this.properties.put(Y_KEY, yAxis);
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
}
