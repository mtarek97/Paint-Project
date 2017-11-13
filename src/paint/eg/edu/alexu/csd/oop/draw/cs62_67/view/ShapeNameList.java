package paint.eg.edu.alexu.csd.oop.draw.cs62_67.view;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ListCellRenderer;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionListener;

import paint.eg.edu.alexu.csd.oop.draw.Shape;
import paint.eg.edu.alexu.csd.oop.draw.Shape2;

public class ShapeNameList extends JList{
	
	private DefaultListModel listModel = new DefaultListModel();

	public ShapeNameList(Shape[] shapes){

		super();
		this.setModel(listModel);
		this.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		for(Shape i : shapes){
			Shape2 i2 = (Shape2) i;
			listModel.addElement(i2.getName());
		}
		 this.setAutoscrolls(getAutoscrolls());
		 this.setLayoutOrientation(JList.VERTICAL);
		 this.setDragEnabled(false);
		 this.setCellRenderer(getRenderer());
		 this.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
		 this.setBackground(new Color(227, 242, 253));
		
	}
	
	public void updateShapeNameList(Shape[] shapes){
		listModel.clear();
		for(Shape i : shapes){
			Shape2 i2 = (Shape2) i;
			listModel.addElement(i2.getName());
		}
		System.out.println("updated");
	}
	
	private ListCellRenderer<? super String> getRenderer() {
        return new DefaultListCellRenderer(){
            @Override
            public Component getListCellRendererComponent(JList<?> list,
                    Object value, int index, boolean isSelected,
                    boolean cellHasFocus) {
                JLabel listCellRendererComponent = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected,cellHasFocus);
                listCellRendererComponent.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0,Color.BLACK));
                return listCellRendererComponent;
            }
        };
    }
	

	public void addSelectShapeListner(ListSelectionListener listenForSelectShape){
		this.addListSelectionListener(listenForSelectShape);
	}
	
}
