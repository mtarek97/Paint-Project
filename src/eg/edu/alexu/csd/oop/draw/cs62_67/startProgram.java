package eg.edu.alexu.csd.oop.draw.cs62_67;

import eg.edu.alexu.csd.oop.draw.cs62_67.controller.MainController;
import eg.edu.alexu.csd.oop.draw.cs62_67.model.MyDrawingEngine;
import eg.edu.alexu.csd.oop.draw.cs62_67.model.ShapeFactory;
import eg.edu.alexu.csd.oop.draw.cs62_67.view.GUI;

public class startProgram {
	public static void main(String[] args){
		MyDrawingEngine engine = new MyDrawingEngine();
		ShapeFactory factory = new ShapeFactory(engine);
		final GUI Paint = new GUI();
		MainController main = new MainController(engine, factory, Paint);
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
			public void run() {
                Paint.setVisible(true);;
            }
        });
		
	}
}
