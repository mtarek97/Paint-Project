package paint.eg.edu.alexu.csd.oop.draw.cs62_67.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class ShapeNameBtn extends JButton{
	
	public void ShapeNameBtn(String name){
		this.setToolTipText(name);
	  	this.setForeground(new Color(0, 0, 0));
	  	this.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
	  	this.setBackground(SystemColor.activeCaption);
	  	this.addActionListener(new ActionListener() {
	  		@Override
			public void actionPerformed(ActionEvent arg0) {
	  		}
	  	});
	}
}