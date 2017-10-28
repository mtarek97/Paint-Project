package paint.eg.edu.alexu.csd.oop.draw.cs62_67;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import paint.eg.edu.alexu.csd.oop.draw.DrawingEngine;
import paint.eg.edu.alexu.csd.oop.draw.Shape;

public class MainController{
        
    private DrawingEngine engine;
    ShapeFactory factory;
    private Shape shape;
	
    public void orderShape(String type){
		shape = factory.createShape(type);
	}
	
	
	public static void main(String args[]){
		GUI Paint = new GUI();
	}
        class Listener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }
            
        }
	
}
