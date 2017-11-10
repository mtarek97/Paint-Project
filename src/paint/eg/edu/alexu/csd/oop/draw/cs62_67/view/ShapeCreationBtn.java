package paint.eg.edu.alexu.csd.oop.draw.cs62_67.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class ShapeCreationBtn extends JButton{
	
	public ShapeCreationBtn(String shapeName){
		super(shapeName);
		this.setName(shapeName+"CreationBtn");
		this.setForeground(new Color(0, 0, 0));
	  	this.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
	  	this.setBackground(SystemColor.activeCaption);
	}
	
	public void addShapeCreationListner(ActionListener listenForShapeCreationBtn){
		
		this.addActionListener(listenForShapeCreationBtn);
	
	}
}
