package paint.eg.edu.alexu.csd.oop.draw.cs62_67.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

public class ShapeCreationBtn extends JButton{
	
	public ShapeCreationBtn(String shapeName){
		super();
		this.setName(shapeName+"CreationBtn");
		this.setForeground(new Color(0, 0, 0));
	  	this.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
	  	 this.setForeground(Color.BLACK);
	  	  this.setBackground(Color.WHITE);
	  	  Border line = new LineBorder(Color.BLACK);
	  	  Border margin = new EmptyBorder(5, 15, 5, 15);
	  	  Border compound = new CompoundBorder(line, margin);
	  	  this.setBorder(compound);
	  	this.setBackground(new Color(227, 242, 253));
	  	//this.setBorderPainted(false);
	  	//this.setFocusPainted(false);
	  	//this.setContentAreaFilled(false);
	  	try {
	  		String iconPath = "/assets/shapes_32/".concat(shapeName).concat(".png");
		  	this.setIcon(new ImageIcon(GUI.class.getResource(iconPath)));
		} catch (Exception e) {
			this.setText(shapeName);
		}
	  	
	  	this.setToolTipText(shapeName);
	  	this.addMouseListener(new java.awt.event.MouseAdapter() {
		    @Override
			public void mouseEntered(java.awt.event.MouseEvent evt) {
		    	setBackground(new Color(232, 239, 247));
		    }

		    @Override
			public void mouseExited(java.awt.event.MouseEvent evt) {
		    	setBackground(new Color(227, 242, 253));
		    }
		});
	}
	
	public void addShapeCreationListner(ActionListener listenForShapeCreationBtn){
		
		this.addActionListener(listenForShapeCreationBtn);
	
	}
}
