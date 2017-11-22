package paint.eg.edu.alexu.csd.oop.draw.cs62_67.view;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Map;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import paint.eg.edu.alexu.csd.oop.draw.Shape;

public class ShapePropertiesPanel extends JPanel {

	private ArrayList<JTextField> textFields = new ArrayList();
	private ArrayList<JButton> SettersButtonsList = new ArrayList();
	private JButton nameButton;
	private JButton position;

	public ShapePropertiesPanel() {
		super();
		this.setName("ShapePropertiesPanel");
		this.setBackground(new Color(245, 246, 247));
		this.setBorder(new LineBorder(new Color(0, 0, 0)));
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
	}

	public void updateShapePropertiesPanel(Shape shape) {
		this.removeAll();
		this.revalidate();
		this.repaint();
		textFields.clear();
		SettersButtonsList.clear();

		JLabel typeLabel = new JLabel("type :");
		JLabel typeName = new JLabel(shape.getClass().getSimpleName());
		typeName.setName("type");
		typeLabel.setLabelFor(typeName);
		JPanel type = new JPanel(new FlowLayout(FlowLayout.LEFT));
		type.setBackground(new Color(245, 246, 247));
		type.add(typeLabel);
		type.add(typeName);
		this.add(type);

		JLabel positionLable = new JLabel("position ");
		JLabel positionXLable = new JLabel("x :");
		JTextField positionXField = new JTextField(String.valueOf(shape.getPosition().x));
		positionXField.setName("positionX");
		positionXLable.setLabelFor(positionXField);
		JLabel positionYLable = new JLabel("y :");
		JTextField positionYField = new JTextField(String.valueOf(shape.getPosition().y));
		positionYField.setName("positionY");
		positionYLable.setLabelFor(positionYField);
		position = new JButton("set");
		JPanel pos = new JPanel(new FlowLayout(FlowLayout.LEFT));
		pos.setBackground(new Color(245, 246, 247));
		pos.add(positionLable);
		pos.add(positionXLable);
		pos.add(positionXField);
		pos.add(positionYLable);
		pos.add(positionYField);
		pos.add(position);
		this.textFields.add(positionXField);
		this.textFields.add(positionYField);
		this.add(pos);

		for (Map.Entry<String, Double> property : shape.getProperties().entrySet()) {
			if (!property.getKey().equals("stroke")) {
				JLabel propLabel = new JLabel(property.getKey() + " :");
				JTextField propField = new JTextField(property.getValue().toString());
				propField.setName(property.getKey());
				propLabel.setLabelFor(propField);
				JButton propButton = new JButton("set");
				propButton.setName(property.getKey());
				JPanel propPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
				propPanel.setBackground(new Color(245, 246, 247));
				propPanel.add(propLabel);
				propPanel.add(propField);
				propPanel.add(propButton);
				this.textFields.add(propField);
				this.SettersButtonsList.add(propButton);
				this.add(propPanel);
			}
		}

	}

	public void addPositionSetterButtonListener(ActionListener listener) {
		this.position.addActionListener(listener);
	}

	public void addPropSetterButtonListeners(ActionListener listener) {
		for (JButton i : this.SettersButtonsList) {
			i.addActionListener(listener);
		}
	}

	public String getTextFieldValue(String textFiledName) {
		for (JTextField i : textFields) {
			if (i.getName().toString().equals(textFiledName)) {
				return i.getText().toString();
			}
		}
		return null;
	}

}
