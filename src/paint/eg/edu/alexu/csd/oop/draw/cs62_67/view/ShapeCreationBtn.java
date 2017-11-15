package paint.eg.edu.alexu.csd.oop.draw.cs62_67.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class ShapeCreationBtn extends JButton{
	
	public ShapeCreationBtn(String shapeName){
		super();
		this.setName(shapeName+"CreationBtn");
		this.setForeground(new Color(0, 0, 0));
	  	this.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
	  	this.setBackground(new Color(227, 242, 253));
	  	try {
	  		String iconPath = "/assets/shapes_32/".concat(shapeName).concat(".png");
		  	this.setIcon(new ImageIcon(GUI.class.getResource(iconPath)));
		} catch (Exception e) {
			this.setText(shapeName);
		}
	  	
	  	this.setToolTipText(shapeName);
	}
	
	public void addShapeCreationListner(ActionListener listenForShapeCreationBtn){
		
		this.addActionListener(listenForShapeCreationBtn);
	
	}
}
