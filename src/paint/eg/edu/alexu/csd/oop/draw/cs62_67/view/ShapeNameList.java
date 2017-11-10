package paint.eg.edu.alexu.csd.oop.draw.cs62_67.view;

import java.awt.Color;

import javax.swing.DefaultListModel;
import javax.swing.JList;

import paint.eg.edu.alexu.csd.oop.draw.Shape;
import paint.eg.edu.alexu.csd.oop.draw.Shape2;

public class ShapeNameList extends JList{
	
	private DefaultListModel listModel = new DefaultListModel();
	
	public ShapeNameList(Shape[] shapes){

		super();
		this.setModel(listModel);
		for(Shape i : shapes){
			Shape2 i2 = (Shape2) i;
			listModel.addElement(i2.getName());
		}
		 this.setAutoscrolls(getAutoscrolls());
		 this.setBackground(Color.LIGHT_GRAY);
		 this.setLayoutOrientation(JList.VERTICAL);
		 this.setDragEnabled(false);
	}
	
	public void updateShapeNameList(Shape[] shapes){
		listModel.clear();
		for(Shape i : shapes){
			Shape2 i2 = (Shape2) i;
			listModel.addElement(i2.getName());
		}
		System.out.println("updated");
	}
	
}
