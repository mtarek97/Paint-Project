package paint.eg.edu.alexu.csd.oop.draw;

public interface DrawingEngine2 {

	public Shape getShapeByName(String name);
	
	public String pasrePathtoBinName(String path);
	
	public void addPlugin(Class<? extends Shape> myClass);
}
