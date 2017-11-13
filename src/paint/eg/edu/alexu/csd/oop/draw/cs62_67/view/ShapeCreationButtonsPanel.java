package paint.eg.edu.alexu.csd.oop.draw.cs62_67.view;

import java.awt.Color;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.ParallelGroup;
import javax.swing.GroupLayout.SequentialGroup;
import javax.swing.JPanel;

import paint.eg.edu.alexu.csd.oop.draw.Shape;

public class ShapeCreationButtonsPanel extends JPanel{
	
	private ArrayList<ShapeCreationBtn> buttonsList = new ArrayList();
	
	public ShapeCreationButtonsPanel(List<Class<? extends Shape>> shapeslist){
		super();
		this.setName("ShapeCreationButtonsPanel");
		this.setBackground(new Color(227, 242, 253));
		GroupLayout layout = new GroupLayout(this);
		this.setLayout(layout);
		//layout.setAutoCreateGaps(true);
	    //layout.setAutoCreateContainerGaps(true);
	    ParallelGroup parallel = layout.createParallelGroup();
	    layout.setHorizontalGroup(parallel);
	    SequentialGroup sequential = layout.createSequentialGroup();
	    layout.setVerticalGroup(sequential);
	    
		for(Class i : shapeslist){
			ShapeCreationBtn creationBtn = new ShapeCreationBtn(i.getSimpleName());
			parallel.addComponent(creationBtn, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE);
			 sequential.addComponent(creationBtn);
			
			buttonsList.add(creationBtn);
			this.add(creationBtn);
		}
		
	}
	
	public void updateShapeCreationButtonsPanel(List<Class<? extends Shape>> shapeslist){
		this.removeAll();
		this.revalidate();
		this.repaint();
		buttonsList.clear();
		GroupLayout layout = new GroupLayout(this);
		this.setLayout(layout);
		//layout.setAutoCreateGaps(true);
	    //layout.setAutoCreateContainerGaps(true);
	    ParallelGroup parallel = layout.createParallelGroup();
	    layout.setHorizontalGroup(parallel);
	    SequentialGroup sequential = layout.createSequentialGroup();
	    layout.setVerticalGroup(sequential);
	    
		for(Class i : shapeslist){
			ShapeCreationBtn creationBtn = new ShapeCreationBtn(i.getSimpleName());
			parallel.addComponent(creationBtn, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE);
			 sequential.addComponent(creationBtn);
			
			buttonsList.add(creationBtn);
			this.add(creationBtn);
		}
		
	}
	
	
	public void addButtonsListeners(ActionListener listener){
		for(ShapeCreationBtn i : buttonsList){
			i.addActionListener(listener);
		}
	}
	
}
