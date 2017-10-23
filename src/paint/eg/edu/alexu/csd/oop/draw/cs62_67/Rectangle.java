package paint.eg.edu.alexu.csd.oop.draw.cs62_67;

import java.awt.Graphics;
import java.awt.Point;
import java.util.Map;

public class Rectangle extends MyShape{
	private Map<String, Double> properties = getProperties();
	public static final String LENGTH_KEY = "xAxis";
	public static final String WIDTH_KEY = "yAxis";
	
	public Rectangle(double xAxis, double yAxis) {
		setColor(this.getColor());
		setPosition(this.getPosition());
		this.properties.put(LENGTH_KEY, xAxis);
		this.properties.put(WIDTH_KEY, yAxis);
		setProperties(this.properties);
		setFillColor(this.getFillColor());
	}
	
	@Override
	public void draw(Graphics canvas) {
		Point position = getPosition();
		double length = properties.get(LENGTH_KEY);
		double width = properties.get(WIDTH_KEY);
		canvas.drawOval(position.x, position.y, (int)width, (int)length);
	}

}
