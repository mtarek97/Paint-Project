package paint.eg.edu.alexu.csd.oop.draw.cs62_67.controller;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.geom.Rectangle2D;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

import javax.swing.JColorChooser;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import paint.eg.edu.alexu.csd.oop.draw.DrawingEngine;
import paint.eg.edu.alexu.csd.oop.draw.DrawingEngine2;
import paint.eg.edu.alexu.csd.oop.draw.Shape;
import paint.eg.edu.alexu.csd.oop.draw.cs62_67.startProgram;
import paint.eg.edu.alexu.csd.oop.draw.cs62_67.model.Circle;
import paint.eg.edu.alexu.csd.oop.draw.cs62_67.model.Ellipse;
import paint.eg.edu.alexu.csd.oop.draw.cs62_67.model.LineSegment;
import paint.eg.edu.alexu.csd.oop.draw.cs62_67.model.Rectangle;
import paint.eg.edu.alexu.csd.oop.draw.cs62_67.model.ShapeFactory;
import paint.eg.edu.alexu.csd.oop.draw.cs62_67.model.Square;
import paint.eg.edu.alexu.csd.oop.draw.cs62_67.model.Triangle;
import paint.eg.edu.alexu.csd.oop.draw.cs62_67.view.GUI;
import paint.eg.edu.alexu.csd.oop.draw.cs62_67.view.ShapeCreationBtn;
import paint.eg.edu.alexu.csd.oop.draw.cs62_67.view.ShapeCreationButtonsPanel;
import paint.eg.edu.alexu.csd.oop.draw.cs62_67.view.ShapeNameList;

public class MainController {

	private DrawingEngine engine;
	private ShapeFactory factory;
	private GUI Paint;

	private String selectedShapeName;
	private Shape selectedShape;
	private String dragedShapeName;
	private Shape dragedShape;
	private PaintSurface surface = new PaintSurface();
	private ShapeCreationButtonsPanel shapesCreationPanel;
	private ShapeNameList namesList;
	private startProgram newPrgram;

	public MainController(DrawingEngine engine,
	ShapeFactory factory, GUI Paint) {
		this.engine = engine;
		this.factory = factory;
		this.Paint = Paint;
		this.Paint
		.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		shapesCreationPanel = new ShapeCreationButtonsPanel(
		engine.getSupportedShapes());
		shapesCreationPanel
		.addButtonsListeners(new ShapeCreationBtnListner());
		namesList = new ShapeNameList(engine.getShapes());
		namesList
		.addSelectShapeListner(new SelectShapeListener());
		this.Paint.getContentPane()
		.add(new JScrollPane(namesList), BorderLayout.EAST);
		this.Paint.getContentPane().add(shapesCreationPanel,
		BorderLayout.WEST);

		this.Paint.getContentPane().add(surface,
		BorderLayout.CENTER);
		this.Paint.addExitListener(new ExitListener());
		this.Paint.addUndoListener(new UndoListener());
		this.Paint.addRedoListener(new RedoListener());
		this.Paint.addNewListener(new NewListener());
		this.Paint.addDeleteListener(new DeleteListener());
		this.Paint.saveJsonListener(new jsonSaveListener());
		this.Paint.saveXmlListener(new xmlSaveListener());
		this.Paint.loadListener(new loadListener());
		this.Paint.colorListener(new colorLestener());
		this.Paint.fillColorListener(new fillColorLestener());
	}

	public void orderShape(String type) {
		this.dragedShape = factory.createShape(type);
	}

	private class PaintSurface extends JComponent {
		
		private Point startDrag, endDrag;
		private Point[] Coordinates = new Point[3];
		private int counter = 0;

		public PaintSurface() {
			this.addMouseListener(new MouseAdapter() {
				@Override
				public void mousePressed(MouseEvent e) {
					if (!(dragedShapeName
					.equals("Triangle"))) {
						orderShape(dragedShapeName);
						startDrag =
						new Point(e.getX(), e.getY());
						endDrag = startDrag;
					} else {
						Coordinates[counter] = e.getPoint();
						counter++;
						if (counter % 3 == 0) {
							orderShape(dragedShapeName);
							setProperties(dragedShape,
							Coordinates[0], endDrag);
							engine.addShape(dragedShape);
							namesList.updateShapeNameList(
							engine.getShapes());
							counter = 0;
						}
					}
					repaint();
				}

				@Override
				public void mouseReleased(MouseEvent e) {
					if (!(dragedShapeName
					.equals("Triangle"))) {
						setProperties(dragedShape,
						startDrag, e.getPoint());
						engine.addShape(dragedShape);
						namesList.updateShapeNameList(
						engine.getShapes());
						startDrag = null;
						endDrag = null;
						repaint();
					}
				}
			});

			this.addMouseMotionListener(
			new MouseMotionAdapter() {
				@Override
				public void mouseDragged(MouseEvent e) {

					endDrag = new Point(e.getX(), e.getY());
					repaint();
				}
			});
		}

		@Override
		public void paint(Graphics g) {
			Graphics2D g2 = (Graphics2D) g;
			engine.refresh(g2);
			if (startDrag != null && endDrag != null) {
				g2.setPaint(Color.LIGHT_GRAY);
				setProperties(dragedShape, startDrag,
				endDrag);
				dragedShape.draw(g2);
			}
		}

		public void setProperties(Shape shape, Point start,
		Point end) {
			shape.setPosition(start);
			Map<String, Double> prep =
			shape.getProperties();
			if (shape instanceof Circle) {
				prep.put("xAxis",
				end.getX() - start.getX());
				prep.put("yAxis",
				end.getX() - start.getX());
			} else if (shape instanceof Triangle) {
				prep.put("x1", Coordinates[0].getX());
				prep.put("y1", Coordinates[0].getY());
				prep.put("x2", Coordinates[1].getX());
				prep.put("y2", Coordinates[1].getY());
				prep.put("x3", Coordinates[2].getX());
				prep.put("y3", Coordinates[2].getY());
			} else if (shape instanceof Square) {
				prep.put("xAxis",
				end.getX() - start.getX());
				prep.put("yAxis",
				end.getX() - start.getX());
			} else if (shape instanceof Rectangle) {
				prep.put("xAxis",
				end.getY() - start.getY());
				prep.put("yAxis",
				end.getX() - start.getX());
			} else if (shape instanceof Ellipse) {
				prep.put("xAxis",
				end.getX() - start.getX());
				prep.put("yAxis",
				end.getY() - start.getY());
			} else if (shape instanceof LineSegment) {
				prep.put("x1", end.getX());
				prep.put("y1", end.getY());
				prep.put("x2", start.getX());
				prep.put("y2", start.getY());
			}
			shape.setProperties(prep);
		}
	}

	public class SelectShapeListener
	implements ListSelectionListener {

		@Override
		public void valueChanged(ListSelectionEvent e) {
			// TODO Auto-generated method stub
			if (!e.getValueIsAdjusting()) {
				JList source = (JList) e.getSource();
				selectedShapeName =
				(String) source.getSelectedValue();
				DrawingEngine2 engine2 =
				(DrawingEngine2) engine;
				selectedShape =
				engine2.getShapeByName(selectedShapeName);
			}

		}

	}

	public class ShapeCreationBtnListner
	implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			ShapeCreationBtn source =
			(ShapeCreationBtn) e.getSource();
			dragedShapeName = source.getText();
		}

	}

	class ExitListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			System.exit(0);

		}

	}

	class UndoListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				engine.undo();
				namesList
				.updateShapeNameList(engine.getShapes());
			} catch (Exception e1) {
				// TODO: handle exception
			}
			surface.repaint();
		}

	}

	class RedoListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				engine.redo();
				namesList
				.updateShapeNameList(engine.getShapes());
			} catch (Exception e1) {
				// TODO: handle exception
			}
			surface.repaint();

		}

	}

	class NewListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			newPrgram = new startProgram();
			newPrgram.main(null);
		}

	}

	class DeleteListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				engine.removeShape(selectedShape);
				namesList
				.updateShapeNameList(engine.getShapes());
			} catch (Exception e1) {
				// TODO: handle exception
			}
			surface.repaint();

		}

	}

	class xmlSaveListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			JFileChooser chooser = new JFileChooser();
			int retrival = chooser.showSaveDialog(null);
			if (retrival == JFileChooser.APPROVE_OPTION) {
				Path path = Paths
				.get(chooser.getCurrentDirectory() + "/"
				+ chooser.getSelectedFile().getName());

				engine.save(path.toString().concat(".Xml"));
			}
		}

	}

	class jsonSaveListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			JFileChooser chooser = new JFileChooser();
			int retrival = chooser.showSaveDialog(null);
			if (retrival == JFileChooser.APPROVE_OPTION) {
				Path path = Paths
				.get(chooser.getCurrentDirectory() + "/"
				+ chooser.getSelectedFile().getName());

				engine
				.save(path.toString().concat(".JsOn"));
			}
		}

	}

	class loadListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			String filename = File.separator + "tmp";
			JFileChooser fc =
			new JFileChooser(new File(filename));
			int result = fc.showOpenDialog(null);
			String selectedFilePath =
			fc.getSelectedFile().getPath().toString();
			if (result == JFileChooser.APPROVE_OPTION) {
				engine.load(selectedFilePath);
				surface.repaint();
			}
		}
	}
	class colorLestener implements ActionListener{
		Shape updatedShape;
		@Override
		public void actionPerformed(ActionEvent e) {
			Color c = JColorChooser.showDialog(null, "Choose a Color", selectedShape.getColor());
		      if (c != null || selectedShape != null)
				try {
					updatedShape = (Shape) selectedShape.clone();
				} catch (CloneNotSupportedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		      	updatedShape.setColor(c);
		      	engine.updateShape(selectedShape, updatedShape);
		      	namesList
				.updateShapeNameList(engine.getShapes());
		      	surface.repaint();
		}
			
	}
	class fillColorLestener implements ActionListener{
		Shape updatedShape;
		@Override
		public void actionPerformed(ActionEvent e) {
			Color c = JColorChooser.showDialog(null, "Choose a Color", selectedShape.getFillColor());
		      if (c != null || selectedShape != null)
				try {
					updatedShape = (Shape) selectedShape.clone();
				} catch (CloneNotSupportedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		      	updatedShape.setFillColor(c);
		      	engine.updateShape(selectedShape, updatedShape);
		      	namesList
				.updateShapeNameList(engine.getShapes());
		      	surface.repaint();
		}
			
	}
		
}


