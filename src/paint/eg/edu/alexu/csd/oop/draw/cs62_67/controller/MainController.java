package paint.eg.edu.alexu.csd.oop.draw.cs62_67.controller;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Map;
import java.util.jar.JarEntry;
import java.util.jar.JarInputStream;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.Timer;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import paint.eg.edu.alexu.csd.oop.draw.Shape;
import paint.eg.edu.alexu.csd.oop.draw.cs62_67.startProgram;
import paint.eg.edu.alexu.csd.oop.draw.cs62_67.model.MyDrawingEngine;
import paint.eg.edu.alexu.csd.oop.draw.cs62_67.model.ShapeFactory;
import paint.eg.edu.alexu.csd.oop.draw.cs62_67.model.shapes.Circle;
import paint.eg.edu.alexu.csd.oop.draw.cs62_67.model.shapes.Ellipse;
import paint.eg.edu.alexu.csd.oop.draw.cs62_67.model.shapes.LineSegment;
import paint.eg.edu.alexu.csd.oop.draw.cs62_67.model.shapes.Rectangle;
import paint.eg.edu.alexu.csd.oop.draw.cs62_67.model.shapes.Square;
import paint.eg.edu.alexu.csd.oop.draw.cs62_67.model.shapes.Triangle;
import paint.eg.edu.alexu.csd.oop.draw.cs62_67.view.GUI;
import paint.eg.edu.alexu.csd.oop.draw.cs62_67.view.ShapeCreationBtn;
import paint.eg.edu.alexu.csd.oop.draw.cs62_67.view.ShapeCreationButtonsPanel;
import paint.eg.edu.alexu.csd.oop.draw.cs62_67.view.ShapeNameList;
import paint.eg.edu.alexu.csd.oop.draw.cs62_67.view.ShapePropertiesPanel;

public class MainController {
	
	private Shape updatedShape;
	private Shape movingShape;
	private int copyFlag = 0;
	private Shape copiedShape;
	private MyDrawingEngine engine;
	private ShapeFactory factory;
	private GUI Paint;
	private String selectedShapeName;
	private Shape selectedShape;
	private String dragedShapeName;
	private Shape dragedShape;
	private PaintSurface surface = new PaintSurface();
	private ShapeCreationButtonsPanel shapesCreationPanel;
	private ShapePropertiesPanel shapePropertiesPanel;
	private JPanel shapesPanel;
	final public static  Color backgroundColor = new Color(245, 246, 247);
	private Color color = Color.black;
	private Color fillColor = Color.WHITE;
	private ShapeNameList namesList;
	private startProgram newPrgram;
	private int createModeFlage = 0;
	private int movingModeFlag = 0;
	private MouseAdapter createAdapter;
	private MouseMotionAdapter createMotion;
	private MouseAdapter moveAdapter;
	private MouseMotionAdapter moveMotion;

	public MainController(MyDrawingEngine engine, ShapeFactory factory, GUI Paint) {
		this.engine = engine;
		this.factory = factory;
		this.Paint = Paint;
		this.Paint.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		shapesPanel = new JPanel(new BorderLayout(0, 0));
		shapePropertiesPanel = new ShapePropertiesPanel();
		namesList = new ShapeNameList(engine.getShapes());

		shapesPanel.add(new JScrollPane(namesList), BorderLayout.CENTER);
		shapesPanel.add(shapePropertiesPanel, BorderLayout.SOUTH);

		shapesCreationPanel = new ShapeCreationButtonsPanel(engine.getSupportedShapes());

		this.Paint.getContentPane().add(shapesPanel, BorderLayout.EAST);
		this.Paint.getContentPane().add(shapesCreationPanel, BorderLayout.WEST);
		this.Paint.getContentPane().add(surface, BorderLayout.CENTER);
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
		this.Paint.copyListener(new copyListener());
		this.Paint.pasteListener(new pasteListener());
		this.Paint.moveListener(new moveLestener());
		this.Paint.addAddPluginListener(new addPluginListener());
		this.Paint.addSaveAsPngListener(new SaveAsPngListener());

		shapesCreationPanel.addButtonsListeners(new ShapeCreationBtnListner());
		namesList.addSelectShapeListner(new SelectShapeListener());
	}

	public void orderShape(String type) {
		this.dragedShape = factory.createShape(type);
	}

	private class PaintSurface extends JComponent {
		private Point startDrag, endDrag;
		private Point[] Coordinates = new Point[3];
		private int counter = 0;

		public PaintSurface() {
			createAdapter = new MouseAdapter() {
				@Override
				public void mousePressed(MouseEvent e) {

					if (!(dragedShapeName.equals("Triangle"))) {
						orderShape(dragedShapeName);
						startDrag = new Point(e.getX(), e.getY());
						endDrag = startDrag;
					} else {
						Coordinates[counter] = e.getPoint();
						counter++;
						if (counter % 3 == 0) {
							orderShape(dragedShapeName);
							setProperties(dragedShape, Coordinates[0], endDrag);
							engine.addShape(dragedShape);
							namesList.updateShapeNameList(engine.getShapes());
							counter = 0;
						}
					}
					repaint();
				}

				@Override
				public void mouseReleased(MouseEvent e) {
					if (!(dragedShapeName.equals("Triangle"))) {
						setProperties(dragedShape, startDrag, e.getPoint());
						engine.addShape(dragedShape);
						namesList.updateShapeNameList(engine.getShapes());
						startDrag = null;
						endDrag = null;
						System.out.println(selectedShapeName);

					}
					repaint();
				}

			};
			createMotion = new MouseMotionAdapter() {

				@Override
				public void mouseDragged(MouseEvent e) {
					Paint.mouseXlbl.setText("X: ".concat(String.valueOf(e.getX())));
					Paint.mouseYlbl.setText("Y: ".concat(String.valueOf(e.getY())));
					endDrag = new Point(e.getX(), e.getY());
					repaint();
				}

				@Override
				public void mouseMoved(MouseEvent e) {
					Paint.mouseXlbl.setText("X: ".concat(String.valueOf(e.getX())));
					Paint.mouseYlbl.setText("Y: ".concat(String.valueOf(e.getY())));
				}

			};
			moveAdapter = new MouseAdapter() {
				@Override
				public void mousePressed(MouseEvent e) {
					if (selectedShape != null) {
						try {
							movingShape = (Shape) selectedShape.clone();
						} catch (CloneNotSupportedException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				}

				@Override
				public void mouseReleased(MouseEvent e) {
					if (movingShape != null) {
						if (copyFlag == 0) {
							engine.updateShape(selectedShape, movingShape);
							// namesList.updateShapeNameList(engine.getShapes());
							movingModeFlag = 0;
							selectedShape = movingShape;
							movingShape = null;
						} else {
							Point point = e.getPoint();
							movingShape.setPosition(new Point(point.x, point.y));
							engine.addShape(movingShape);
							namesList.updateShapeNameList(engine.getShapes());
							movingShape = null;
							copyFlag = 0;
						}
					}
					repaint();
				}
			};
			moveMotion = new MouseMotionAdapter() {
				@Override
				public void mouseDragged(MouseEvent e) {
					Paint.mouseXlbl.setText("X: ".concat(String.valueOf(e.getX())));
					Paint.mouseYlbl.setText("Y: ".concat(String.valueOf(e.getY())));
					endDrag = new Point(e.getX(), e.getY());
					if (movingShape != null) {
						draging(movingShape);
					}
				}

				@Override
				public void mouseMoved(MouseEvent e) {
					Paint.mouseXlbl.setText("X: ".concat(String.valueOf(e.getX())));
					Paint.mouseYlbl.setText("Y: ".concat(String.valueOf(e.getY())));

				}

			};
			this.addMouseListener(createAdapter);
			this.addMouseMotionListener(createMotion);

		}

		private int diff = 0;
		int speed = 15;
		{

			final Timer timer = new Timer(1000 / (10 * speed), null);
			timer.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					if (diff < 20) {
						diff++;
					} else {
						diff = 0;
					}
					repaint();
					timer.setDelay(1000 / (10 * speed));
				}
			});
			timer.start();

		}

		@Override
		public void paint(Graphics g) {
			Graphics2D g2 = (Graphics2D) g;
			engine.refresh(g2);
			if (startDrag != null && endDrag != null) {
				g2.setPaint(Color.LIGHT_GRAY);
				if (selectedShape == null) {
					setProperties(dragedShape, startDrag, endDrag);
					dragedShape.draw(g2);
				}
			}
			if (selectedShape != null) {

				g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
				g2.setStroke(new BasicStroke(2f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND, 1f,
						new float[] { 10f, 10f }, diff));
				g2.setPaint(Color.BLACK);
				drawHighlightingRectangle(g2, selectedShape);
			}
			if (movingShape != null) {
				movingShape.draw(g2);
			}
		}

		public void setProperties(Shape shape, Point start, Point end) {
			shape.setColor(color);
			shape.setFillColor(fillColor);
			shape.setPosition(start);
			Map<String, Double> prep = shape.getProperties();
			if (shape instanceof Circle) {
				prep.put("xAxis", end.getX() - start.getX());
				prep.put("yAxis", end.getX() - start.getX());
			} else if (shape instanceof Triangle) {
				prep.put("x1", Coordinates[0].getX());
				prep.put("y1", Coordinates[0].getY());
				prep.put("x2", Coordinates[1].getX());
				prep.put("y2", Coordinates[1].getY());
				prep.put("x3", Coordinates[2].getX());
				prep.put("y3", Coordinates[2].getY());
			} else if (shape instanceof Square) {
				prep.put("xAxis", end.getX() - start.getX());

				prep.put("yAxis", end.getX() - start.getX());
			} else if (shape instanceof Rectangle) {
				prep.put("xAxis", end.getY() - start.getY());
				prep.put("yAxis", end.getX() - start.getX());
			} else if (shape instanceof Ellipse) {
				prep.put("xAxis", end.getX() - start.getX());
				prep.put("yAxis", end.getY() - start.getY());
			} else if (shape instanceof LineSegment) {
				prep.put("x1", end.getX());
				prep.put("y1", end.getY());
				prep.put("x2", start.getX());
				prep.put("y2", start.getY());
			}
			shape.setProperties(prep);
		}

		public void drawHighlightingRectangle(Graphics2D g2, Shape selectedShape) {
			if (selectedShape instanceof Square || selectedShape instanceof Ellipse) {
				g2.drawRect(selectedShape.getPosition().x - 5, selectedShape.getPosition().y - 5,
						selectedShape.getProperties().get("xAxis").intValue() + 10,
						selectedShape.getProperties().get("yAxis").intValue() + 10);
			} else if (selectedShape instanceof Rectangle) {
				g2.drawRect(selectedShape.getPosition().x - 5, selectedShape.getPosition().y - 5,
						selectedShape.getProperties().get("yAxis").intValue() + 10,
						selectedShape.getProperties().get("xAxis").intValue() + 10);
			} else if (selectedShape instanceof LineSegment) {
				Map<String, Double> properties = selectedShape.getProperties();
				int minx;
				int miny;
				int maxx;
				int maxy;
				int x1 = properties.get("x1").intValue();
				minx = x1;
				maxx = x1;
				int y1 = properties.get("y1").intValue();
				miny = y1;
				maxy = y1;
				int x2 = selectedShape.getPosition().x;
				if (maxx < x2) {
					maxx = x2;
				}
				if (minx > x2) {
					minx = x2;
				}
				int y2 = selectedShape.getPosition().y;
				if (maxy < y2) {
					maxy = y2;
				}
				if (miny > y2) {
					miny = y2;
				}
				if (maxx - minx == 0) {
					g2.drawRect(minx - 5, miny, 10, maxy - miny);
				} else if (maxy - miny == 0) {
					g2.drawRect(minx, miny - 5, maxx - minx, 10);
				} else {
					g2.drawRect(minx, miny, maxx - minx, maxy - miny);
				}
			} else if (selectedShape instanceof Triangle) {
				int maxX;
				int maxY;
				int minX;
				int minY;
				int[] x = new int[3];
				int[] y = new int[3];
				x[0] = selectedShape.getPosition().x;
				maxX = x[0];
				minX = maxX;
				y[0] = selectedShape.getPosition().y;
				maxY = y[0];
				minY = maxY;
				x[1] = selectedShape.getProperties().get("x2").intValue();
				if (maxX < x[1]) {
					maxX = x[1];
				}
				if (minX > x[1]) {
					minX = x[1];
				}
				y[1] = selectedShape.getProperties().get("y2").intValue();
				if (maxY < y[1]) {
					maxY = y[1];
				}
				if (minY > y[1]) {
					minY = y[1];
				}
				x[2] = selectedShape.getProperties().get("x3").intValue();
				if (maxX < x[2]) {
					maxX = x[2];
				}
				if (minX > x[2]) {
					minX = x[2];
				}
				y[2] = selectedShape.getProperties().get("y3").intValue();
				if (maxY < y[2]) {
					maxY = y[2];
				}
				if (minY > y[2]) {
					minY = y[2];
				}
				g2.drawRect(minX - 2, minY - 1, maxX - minX, maxY - minY + 3);

			}

		}

		public void draging(Shape shape) {
			Point prePoint = shape.getPosition();
			shape.setPosition(endDrag);
			if (shape instanceof LineSegment) {
				Map<String, Double> lineMap = shape.getProperties();
				Double x1 = ((lineMap.get("x1") + (endDrag.x - prePoint.x)));
				int y1 = (int) (lineMap.get("y1") + (endDrag.y - prePoint.y));
				lineMap.put("x1", x1);
				lineMap.put("y1", (double) y1);
				shape.setProperties(lineMap);
			}
			if (shape instanceof Triangle) {
				Map<String, Double> lineMap = shape.getProperties();
				Double x2 = ((lineMap.get("x2") + (endDrag.x - prePoint.x)));
				int y2 = (int) (lineMap.get("y2") + (endDrag.y - prePoint.y));
				Double x3 = ((lineMap.get("x3") + (endDrag.x - prePoint.x)));
				int y3 = (int) (lineMap.get("y3") + (endDrag.y - prePoint.y));
				lineMap.put("x2", x2);
				lineMap.put("y2", (double) y2);
				lineMap.put("x3", x3);
				lineMap.put("y3", (double) y3);
				shape.setProperties(lineMap);
			}
		}
	}

	public class SelectShapeListener implements ListSelectionListener {

		@Override
		public void valueChanged(ListSelectionEvent e) {
			if (!e.getValueIsAdjusting()) {
				JList source = (JList) e.getSource();
				if (source != null && source.getSelectedIndex() != -1) {
					selectedShapeName = (String) source.getSelectedValue();
					System.out.println(source);
					selectedShape = engine.getShapes()[source.getSelectedIndex()];
					System.out.println(selectedShape);
					movingModeFlag = 1;
					if (selectedShape != null) {
						// namesList.removeListSelectionListener( this );
						shapePropertiesPanel.updateShapePropertiesPanel(selectedShape);

						namesList.updateShapeNameList(engine.getShapes());
						shapePropertiesPanel.addPositionSetterButtonListener(new positionSetterButtonListner());
						shapePropertiesPanel.addPropSetterButtonListeners(new probSetterButtonListner());
						// namesList.addListSelectionListener( this );
					}
				}

			}
		}
	}

	public class ShapeCreationBtnListner implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			ShapeCreationBtn source = (ShapeCreationBtn) e.getSource();
			dragedShapeName = source.getToolTipText();
			createModeFlage = 1;
			movingModeFlag = 0;
			if (!(surface.getMouseListeners()[0] == createAdapter)) {
				surface.removeMouseListener(moveAdapter);
				surface.removeMouseMotionListener(moveMotion);
				surface.addMouseListener(createAdapter);
				surface.addMouseMotionListener(createMotion);
			}
			selectedShape = null;
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
				selectedShape = engine.redoActions.get(engine.redoActions.size() - 1).getOldShape();
				namesList.updateShapeNameList(engine.getShapes());
				Paint.enableRedo();
				if(!engine.undoActions.isEmpty()){
					Paint.enableUndo();
				}else{
					Paint.disableUndo();
				}
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
				selectedShape = engine.undoActions.get(engine.undoActions.size() - 1).getNewShape();
				namesList.updateShapeNameList(engine.getShapes());
				Paint.enableUndo();
				if(!engine.redoActions.isEmpty()){
					Paint.enableRedo();
				}else{
					Paint.disableRedo();
				}
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
				namesList.updateShapeNameList(engine.getShapes());
				selectedShape = null;
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
				Path path = Paths.get(chooser.getCurrentDirectory() + "/" + chooser.getSelectedFile().getName());

				engine.save(path.toString().concat(".XmL"));
			}
		}

	}

	class jsonSaveListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			JFileChooser chooser = new JFileChooser();
			int retrival = chooser.showSaveDialog(null);
			if (retrival == JFileChooser.APPROVE_OPTION) {
				Path path = Paths.get(chooser.getCurrentDirectory() + "/" + chooser.getSelectedFile().getName());

				engine.save(path.toString().concat(".JsOn"));
			}
		}

	}

	class loadListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			String filename = File.separator + "tmp";
			JFileChooser fc = new JFileChooser(new File(filename));
			int result = fc.showOpenDialog(null);
			String selectedFilePath = fc.getSelectedFile().getPath().toString();
			if (result == JFileChooser.APPROVE_OPTION) {
				System.out.println(engine.getSupportedShapes());
				engine.load(selectedFilePath);
				namesList.updateShapeNameList(engine.getShapes());
				surface.repaint();
			}
		}
	}

	class colorLestener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			color = JColorChooser.showDialog(null, "Choose a Color", color);
			Paint.btnColor.setBackground(color);
			if (selectedShape != null) {
				try {
					updatedShape = (Shape) selectedShape.clone();
					updatedShape.setColor(color);
				} catch (CloneNotSupportedException e1) {
					e1.printStackTrace();
				}
				engine.updateShape(selectedShape, updatedShape);
				namesList.updateShapeNameList(engine.getShapes());
				selectedShape = updatedShape;
				surface.repaint();
			}
		}

	}

	class fillColorLestener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			fillColor = JColorChooser.showDialog(null, "Choose a Color", fillColor);
			Paint.btnFillColor.setBackground(fillColor);
			if (selectedShape != null) {
				try {
					updatedShape = (Shape) selectedShape.clone();
					updatedShape.setFillColor(fillColor);
				} catch (CloneNotSupportedException e1) {
					e1.printStackTrace();
				}
				engine.updateShape(selectedShape, updatedShape);
				namesList.updateShapeNameList(engine.getShapes());
				selectedShape = updatedShape;
				surface.repaint();
			}
		}

	}

	class copyListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			if (selectedShape != null) {
				copyFlag = 1;
				if (!(surface.getMouseListeners()[0] == moveAdapter)) {
					surface.removeMouseListener(createAdapter);
					surface.removeMouseMotionListener(createMotion);
					surface.addMouseListener(moveAdapter);
					surface.addMouseMotionListener(moveMotion);
				}
			}

		}

	}

	class pasteListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (copyFlag == 1) {
				try {
					copiedShape = (Shape) selectedShape.clone();
				} catch (CloneNotSupportedException e1) {
					e1.printStackTrace();
				}
				Point point = copiedShape.getPosition();
				copiedShape.setPosition(new Point(point.x + 20, point.y));
				engine.addShape(copiedShape);
				namesList.updateShapeNameList(engine.getShapes());
				selectedShape = copiedShape;
				surface.repaint();
				copyFlag = 0;
			}

		}

	}

	public class probSetterButtonListner implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (selectedShape != null) {
				JButton source = (JButton) e.getSource();
				String propKey = source.getName();
				Map<String, Double> newProperties = selectedShape.getProperties();
				newProperties.put(propKey, Double.valueOf(shapePropertiesPanel.getTextFieldValue(propKey)));
				try {
					updatedShape = (Shape) selectedShape.clone();
					updatedShape.setProperties(newProperties);
					System.out.println(updatedShape.getProperties().get(propKey));
				} catch (CloneNotSupportedException e1) {
					e1.printStackTrace();
				}
				engine.updateShape(selectedShape, updatedShape);
				selectedShape = updatedShape;
				namesList.updateShapeNameList(engine.getShapes());
				surface.repaint();
			}

		}

	}

	public class positionSetterButtonListner implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (selectedShape != null) {
				try {
					updatedShape = (Shape) selectedShape.clone();
					Point newPosition = new Point();
					newPosition.x = Integer.valueOf(shapePropertiesPanel.getTextFieldValue("positionX"));
					newPosition.y = Integer.valueOf(shapePropertiesPanel.getTextFieldValue("positionY"));
					updatedShape.setPosition(newPosition);
				} catch (CloneNotSupportedException e1) {
					e1.printStackTrace();
				}
				engine.updateShape(selectedShape, updatedShape);
				selectedShape = updatedShape;
				// System.out.println("--------");
				// System.out.println(engine.getShapes());
				// System.out.println("--------");
				namesList.updateShapeNameList(engine.getShapes());
				surface.repaint();
			}
		}

	}

	public class moveLestener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			if (!(surface.getMouseListeners()[0] == moveAdapter)) {
				surface.removeMouseListener(createAdapter);
				surface.removeMouseMotionListener(createMotion);
				surface.addMouseListener(moveAdapter);
				surface.addMouseMotionListener(moveMotion);
			}
		}

	}

	class addPluginListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			String filename = File.separator + "tmp";
			JFileChooser fc = new JFileChooser(new File(filename));
			int result = fc.showOpenDialog(null);
			String selectedFilePath = fc.getSelectedFile().getPath().toString();
			if (result == JFileChooser.APPROVE_OPTION) {
				try {
					JarInputStream jarFile = new JarInputStream(new FileInputStream(selectedFilePath));
					JarEntry jarEntry;
					ArrayList<String> names = new ArrayList<>();
					while (true) {
						jarEntry = jarFile.getNextJarEntry();
						if (jarEntry == null) {
							break;
						}
						if (jarEntry.getName().endsWith(".class")) {
							String classBinName = jarEntry.getName().replaceAll("/", "\\.");
							classBinName = classBinName.substring(0, classBinName.length() - 6);
							names.add(classBinName);
						}
					}
					ClassLoader mainLoader = getClass().getClassLoader();
					ClassLoader loader = URLClassLoader
							.newInstance(new URL[] { new File(selectedFilePath).toURI().toURL() }, mainLoader);
					Class<? extends Shape> cl = (Class<? extends Shape>) loader.getClass().forName(names.get(0), true,
							loader);
					if (cl.newInstance() instanceof Shape) {
						System.out.println(cl.getSimpleName());
						engine.addPlugin(cl);
					}
					jarFile.close();
				} catch (Exception e1) {
					e1.printStackTrace();
				}

			}
			shapesCreationPanel.updateShapeCreationButtonsPanel(engine.getSupportedShapes());
			shapesCreationPanel.addButtonsListeners(new ShapeCreationBtnListner());
		}
	}

	class SaveAsPngListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			BufferedImage image = new BufferedImage(surface.getWidth(), surface.getHeight(),
					BufferedImage.TYPE_INT_RGB);
			Graphics2D g = image.createGraphics();
			g.clearRect(0, 0, surface.getWidth(), surface.getHeight());
			surface.printAll(g);

			g.dispose();
			try {
				ImageIO.write(image, "jpg", new File("Paint.jpg"));
				ImageIO.write(image, "png", new File("Paint.png"));
			} catch (IOException exp) {
				exp.printStackTrace();
			}
		}
	}
	
	

}
