package paint.eg.edu.alexu.csd.oop.draw.cs62_67.model.shapes;

import java.util.HashMap;
import java.util.Map;

import paint.eg.edu.alexu.csd.oop.draw.Shape;

public class Circle extends Ellipse{
	
	public static final String RADIUS_KEY = "radius";
	//Constructor
	public Circle(){
		 super();
	}
	
	
	@Override
	public Object clone() throws CloneNotSupportedException{
		Shape clonedShape = new Circle();
        clonedShape.setColor(this.getColor());
        clonedShape.setFillColor(this.getFillColor());
        clonedShape.setPosition(this.getPosition());
        Map<String, Double> newprop = new HashMap<String,Double>();
        for (Map.Entry s: this.getProperties().entrySet()){
            String key = (String) s.getKey(); 
            Double value = (Double) s.getValue();
        	newprop.put(key, value);
        }
        clonedShape.setProperties(newprop);
        return clonedShape;	
    }
}
