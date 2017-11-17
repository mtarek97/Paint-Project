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
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionListener;

import paint.eg.edu.alexu.csd.oop.draw.Shape;

public class ShapeNameList extends JList {

	private DefaultListModel<String> listModel;

	public ShapeNameList(Shape[] shapes) {

		super();
		listModel = new DefaultListModel();
		this.setModel(listModel);
		this.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		for (Shape i : shapes) {
			listModel.addElement(i.getClass().getSimpleName());
		}
		this.setAutoscrolls(getAutoscrolls());
		this.setLayoutOrientation(JList.VERTICAL);
		this.setDragEnabled(false);
		this.setCellRenderer(getRenderer());
		this.setFont(new Font("Comic Sans MS", Font.PLAIN, 14));
		this.setBackground(new Color(245, 246, 247));
		this.setBorder(new LineBorder(new Color(0, 0, 0)));

	}

	public void updateShapeNameList(Shape[] shapes) {
		listModel.clear();
		for (Shape i : shapes) {
			listModel.addElement(i.getClass().getSimpleName().toString());
		}
		System.out.println("updated");
	}

	public int getNum(Shape[] shapes, String type) {
		int cnt = 0;
		for (Shape i : shapes) {
			if (i.getClass().getSimpleName().equals(type)) {
				cnt++;
			}

		}
		return cnt;
	}

	private ListCellRenderer<? super String> getRenderer() {
		return new DefaultListCellRenderer() {
			@Override
			public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected,
					boolean cellHasFocus) {
				JLabel listCellRendererComponent = (JLabel) super.getListCellRendererComponent(list, value, index,
						isSelected, cellHasFocus);
				listCellRendererComponent.setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, Color.BLACK));
				return listCellRendererComponent;
			}
		};
	}

	public void addSelectShapeListner(ListSelectionListener listenForSelectShape) {
		this.addListSelectionListener(listenForSelectShape);
	}

}
