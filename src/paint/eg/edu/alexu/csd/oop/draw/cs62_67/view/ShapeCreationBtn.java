package paint.eg.edu.alexu.csd.oop.draw.cs62_67.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class ShapeCreationBtn extends JButton{
	
	final public static  Color backgroundColor = new Color(245, 246, 247);
	final public static Color hoverColor = new Color(232, 239, 247);
	
	public ShapeCreationBtn(String shapeName){
		super();
		this.setName(shapeName+"CreationBtn");
		this.setForeground(new Color(0, 0, 0));
	  	this.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
	  	 this.setForeground(Color.BLACK);
	  	this.setBackground(backgroundColor);
		this.setBorderPainted(false);
		this.setFocusPainted(false);
	  	try {
	  		String iconPath = "/assets/shapes_32/".concat(shapeName).concat(".png");
		  	this.setIcon(new ImageIcon(GUI.class.getResource(iconPath)));
		} catch (Exception e) {
			String iconPath = "/assets/shapes_32/unknown.png";
			this.setIcon(new ImageIcon(GUI.class.getResource(iconPath)));

		}
	  	
	  	this.setToolTipText(shapeName);
	  	this.addMouseListener(new java.awt.event.MouseAdapter() {
		    @Override
			public void mouseEntered(java.awt.event.MouseEvent evt) {
		    	setBackground(hoverColor);
		    	setBorderPainted(true);
		    }

		    @Override
			public void mouseExited(java.awt.event.MouseEvent evt) {
		    	setBackground(backgroundColor);
		    	setBorderPainted(false);
		    }
		});
	}
	
	public void addShapeCreationListner(ActionListener listenForShapeCreationBtn){
		
		this.addActionListener(listenForShapeCreationBtn);
	
	}
}
