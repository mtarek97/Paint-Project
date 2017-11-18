package paint.eg.edu.alexu.csd.oop.draw.cs62_67;

import paint.eg.edu.alexu.csd.oop.draw.cs62_67.controller.MainController;
import paint.eg.edu.alexu.csd.oop.draw.cs62_67.model.MyDrawingEngine;
import paint.eg.edu.alexu.csd.oop.draw.cs62_67.model.ShapeFactory;
import paint.eg.edu.alexu.csd.oop.draw.cs62_67.view.GUI;

public class startProgram {
	public static void main(String[] args){
		MyDrawingEngine engine = new MyDrawingEngine();
		ShapeFactory factory = new ShapeFactory(engine);
		GUI Paint = new GUI();
		MainController main = new MainController(engine, factory, Paint);
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
			public void run() {
                Paint.setVisible(true);;
            }
        });
		
	}

}
