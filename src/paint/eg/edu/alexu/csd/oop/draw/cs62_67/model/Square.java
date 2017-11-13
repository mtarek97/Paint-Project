package paint.eg.edu.alexu.csd.oop.draw.cs62_67.model;

import java.util.HashMap;
import java.util.Map;

import paint.eg.edu.alexu.csd.oop.draw.Shape;

public class Square extends Rectangle{
	public static final String LENGTH_KEY = "length";
	
	public Square(){
		super();
	}
	
	@Override
	public Object clone() throws CloneNotSupportedException{
		Shape clonedShape = new Rectangle();
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
