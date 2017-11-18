package paint.eg.edu.alexu.csd.oop.draw.cs62_67.controller;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
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

	final int PROX_DIST = 3;
	private Shape updatedShape;
	private Shape movingShape;
	private Shape resizedShape;
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
	final public static Color backgroundColor = new Color(245, 246, 247);
	private Color color = Color.black;
	private Color fillColor = Color.WHITE;
	private String selectedColorButton = null;
	private ShapeNameList namesList;
	private startProgram newPrgram;
	private int createModeFlage = 0;
	private int movingModeFlag = 0;
	private MouseAdapter createAdapter;
	private MouseMotionAdapter createMotion;
	private MouseAdapter moveAdapter;
	private MouseMotionAdapter moveMotion;
	private MouseAdapter resizeAdapter;
	private MouseMotionAdapter resizeMotion;
	java.awt.Rectangle highlightRect = new java.awt.Rectangle();

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
		this.Paint.addDeleteListener(new DeleteListener());
		this.Paint.saveJsonListener(new jsonSaveListener());
		this.Paint.saveXmlListener(new xmlSaveListener());
		this.Paint.loadListener(new loadListener());
		this.Paint.colorListener(new colorLestener());
		this.Paint.fillColorListener(new fillColorLestener());
		this.Paint.addPaletteListener(new paletteListerner());
		this.Paint.copyListener(new copyListener());
		this.Paint.pasteListener(new pasteListener());
		this.Paint.moveListener(new moveLestener());
		this.Paint.resizeListener(new resizeLestener());
		this.Paint.addAddPluginListener(new addPluginListener());
		this.Paint.addSnapshotListener(new snapshotListener());
		this.Paint.addColorButtonsListener(new colorButtonsListener());

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
							if (movingShape instanceof LineSegment) {
								movingShape.setPosition(new Point(point.x, point.y));
								Map<String, Double> prop = movingShape.getProperties();
								prop.put("x1", prop.get("x1") + (point.x - prop.get("x2")));
								prop.put("y1", prop.get("y1") + (point.y - prop.get("y2")));
								prop.put("x2", (double) movingShape.getPosition().x);
								prop.put("y2", (double) movingShape.getPosition().y);
								movingShape.setProperties(prop);
							} else if (movingShape instanceof Triangle) {
								movingShape.setPosition(new Point(point.x, point.y));
								Map<String, Double> prop = movingShape.getProperties();
								prop.put("x2", prop.get("x2") + (point.x - prop.get("x1")));
								prop.put("y2", prop.get("y2") + (point.y - prop.get("y1")));
								prop.put("x3", prop.get("x3") + (point.x - prop.get("x1")));
								prop.put("y3", prop.get("y3") + (point.y - prop.get("y1")));
								prop.put("x1", movingShape.getPosition().getX());
								prop.put("y1", movingShape.getPosition().getY());
								movingShape.setProperties(prop);

							} else {
								movingShape.setPosition(new Point(point.x, point.y));
							}

							engine.addShape(movingShape);
							namesList.updateShapeNameList(engine.getShapes());
							selectedShape = movingShape;
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

			resizeAdapter = new MouseAdapter() {
				@Override
				public void mousePressed(MouseEvent e) {
					if (selectedShape != null && getCursor() != Cursor.getDefaultCursor()) {
						try {
							resizedShape = (Shape) selectedShape.clone();
						} catch (CloneNotSupportedException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}

				}

				@Override
				public void mouseReleased(MouseEvent e) {
					if (resizedShape != null) {
						engine.updateShape(selectedShape, resizedShape);
						selectedShape = resizedShape;
						resizedShape = null;
					}
					repaint();
				}

			};
			resizeMotion = new MouseMotionAdapter() {
				@Override
				public void mouseDragged(MouseEvent e) {
					Paint.mouseXlbl.setText("X: ".concat(String.valueOf(e.getX())));
					Paint.mouseYlbl.setText("Y: ".concat(String.valueOf(e.getY())));
					if (resizedShape != null) {
						resizing(resizedShape, e.getPoint());
					}
				}

				@Override
				public void mouseMoved(MouseEvent e) {
					Paint.mouseXlbl.setText("X: ".concat(String.valueOf(e.getX())));
					Paint.mouseYlbl.setText("Y: ".concat(String.valueOf(e.getY())));
					Point p = e.getPoint();
					if (!isOverRect(p)) {
						if (getCursor() != Cursor.getDefaultCursor()) {
							// If cursor is not over rect reset it to the
							// default.
							setCursor(Cursor.getDefaultCursor());
						}
						return;
					}
					// Locate cursor relative to center of rect.
					int outcode = getOutcode(p);
					java.awt.Rectangle r = highlightRect;
					switch (outcode) {
					case java.awt.Rectangle.OUT_TOP:
						if (Math.abs(p.y - r.y) < PROX_DIST) {
							setCursor(Cursor.getPredefinedCursor(Cursor.N_RESIZE_CURSOR));
						}
						break;
					case java.awt.Rectangle.OUT_TOP + java.awt.Rectangle.OUT_LEFT:
						if (Math.abs(p.y - r.y) < PROX_DIST && Math.abs(p.x - r.x) < PROX_DIST) {
							setCursor(Cursor.getPredefinedCursor(Cursor.NW_RESIZE_CURSOR));
						}
						break;
					case java.awt.Rectangle.OUT_LEFT:
						if (Math.abs(p.x - r.x) < PROX_DIST) {
							setCursor(Cursor.getPredefinedCursor(Cursor.W_RESIZE_CURSOR));
						}
						break;
					case java.awt.Rectangle.OUT_LEFT + java.awt.Rectangle.OUT_BOTTOM:
						if (Math.abs(p.x - r.x) < PROX_DIST && Math.abs(p.y - (r.y + r.height)) < PROX_DIST) {
							setCursor(Cursor.getPredefinedCursor(Cursor.SW_RESIZE_CURSOR));
						}
						break;
					case java.awt.Rectangle.OUT_BOTTOM:
						if (Math.abs(p.y - (r.y + r.height)) < PROX_DIST) {
							setCursor(Cursor.getPredefinedCursor(Cursor.S_RESIZE_CURSOR));
						}
						break;
					case java.awt.Rectangle.OUT_BOTTOM + java.awt.Rectangle.OUT_RIGHT:
						if (Math.abs(p.x - (r.x + r.width)) < PROX_DIST
								&& Math.abs(p.y - (r.y + r.height)) < PROX_DIST) {
							setCursor(Cursor.getPredefinedCursor(Cursor.SE_RESIZE_CURSOR));
						}
						break;
					case java.awt.Rectangle.OUT_RIGHT:
						if (Math.abs(p.x - (r.x + r.width)) < PROX_DIST) {
							setCursor(Cursor.getPredefinedCursor(Cursor.E_RESIZE_CURSOR));
						}
						break;
					case java.awt.Rectangle.OUT_RIGHT + java.awt.Rectangle.OUT_TOP:
						if (Math.abs(p.x - (r.x + r.width)) < PROX_DIST && Math.abs(p.y - r.y) < PROX_DIST) {
							setCursor(Cursor.getPredefinedCursor(Cursor.NE_RESIZE_CURSOR));
						}
						break;
					default: // center
						setCursor(Cursor.getDefaultCursor());
					}
				}
			};

			this.addMouseListener(createAdapter);
			this.addMouseMotionListener(createMotion);

		}

		private int diff = 0;
		int speed = 8;
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
			updateUndoAndRedoView();
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
			if (resizedShape != null) {
				resizedShape.draw(g2);
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
				highlightRect.x = selectedShape.getPosition().x - 5;
				highlightRect.y = selectedShape.getPosition().y - 5;
				highlightRect.width = selectedShape.getProperties().get("xAxis").intValue() + 10;
				highlightRect.height = selectedShape.getProperties().get("yAxis").intValue() + 10;
				g2.drawRect(highlightRect.x, highlightRect.y, highlightRect.width, highlightRect.height);
			} else if (selectedShape instanceof Rectangle) {
				highlightRect.x = selectedShape.getPosition().x - 5;
				highlightRect.y = selectedShape.getPosition().y - 5;
				highlightRect.width = selectedShape.getProperties().get("yAxis").intValue() + 10;
				highlightRect.height = selectedShape.getProperties().get("xAxis").intValue() + 10;
				g2.drawRect(highlightRect.x, highlightRect.y, highlightRect.width, highlightRect.height);
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
					highlightRect.x = minx - 5;
					highlightRect.y = miny;
					highlightRect.width = 10;
					highlightRect.height = maxy - miny;
					g2.drawRect(highlightRect.x, highlightRect.y, highlightRect.width, highlightRect.height);
				} else if (maxy - miny == 0) {
					highlightRect.x = minx;
					highlightRect.y = miny - 5;
					highlightRect.width = maxx - minx;
					highlightRect.height = 10;
					g2.drawRect(highlightRect.x, highlightRect.y, highlightRect.width, highlightRect.height);
				} else {
					highlightRect.x = minx;
					highlightRect.y = miny;
					highlightRect.width = maxx - minx;
					highlightRect.height = maxy - miny;
					g2.drawRect(highlightRect.x, highlightRect.y, highlightRect.width, highlightRect.height);
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
				highlightRect.x = minX - 2;
				highlightRect.y = minY - 1;
				highlightRect.width = maxX - minX;
				highlightRect.height = maxY - minY + 3;
				g2.drawRect(highlightRect.x, highlightRect.y, highlightRect.width, highlightRect.height);

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
				lineMap.put("x2", shape.getPosition().getX());
				lineMap.put("y2", shape.getPosition().getY());
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
				lineMap.put("x1", shape.getPosition().getX());
				lineMap.put("y1", shape.getPosition().getY());
				shape.setProperties(lineMap);
			}
		}

		public void resizing(Shape shape, Point p) {
			int type = getCursor().getType();
			if (shape instanceof Rectangle) {
				Map<String, Double> RectMap = shape.getProperties();
				Double x = shape.getPosition().getX();
				Double y = shape.getPosition().getY();
				Double height = RectMap.get("xAxis"); // = y1-y2
				Double width = RectMap.get("yAxis"); // = x2-x1
				Double dx = p.x - x;
				Double dy = p.y - y;
				switch (type) {
				case Cursor.N_RESIZE_CURSOR:
					shape.setPosition(new Point(x.intValue(), (int) (y.intValue() + dy)));
					RectMap.put("xAxis", height - dy);
					shape.setProperties(RectMap);
					break;
				case Cursor.NW_RESIZE_CURSOR:
					shape.setPosition(new Point((int) (x.intValue() + dx), (int) (y.intValue() + dy)));
					RectMap.put("xAxis", height - dy);
					RectMap.put("yAxis", width - dx);
					shape.setProperties(RectMap);
					break;
				case Cursor.W_RESIZE_CURSOR:
					shape.setPosition(new Point((int) (x.intValue() + dx), y.intValue()));
					RectMap.put("yAxis", width - dx);
					shape.setProperties(RectMap);
					break;
				case Cursor.SW_RESIZE_CURSOR:
					shape.setPosition(new Point((int) (x.intValue() + dx), y.intValue()));
					RectMap.put("xAxis", dy);
					RectMap.put("yAxis", width - dx);
					shape.setProperties(RectMap);
					break;
				case Cursor.S_RESIZE_CURSOR:
					RectMap.put("xAxis", dy);
					shape.setProperties(RectMap);
					break;
				case Cursor.SE_RESIZE_CURSOR:
					RectMap.put("xAxis", dy);
					RectMap.put("yAxis", dx);
					shape.setProperties(RectMap);
					break;
				case Cursor.E_RESIZE_CURSOR:
					RectMap.put("yAxis", dx);
					shape.setProperties(RectMap);
					break;
				case Cursor.NE_RESIZE_CURSOR:
					shape.setPosition(new Point(x.intValue(), (int) (y.intValue() + dy)));
					RectMap.put("xAxis", height - dy);
					RectMap.put("yAxis", dx);
					shape.setProperties(RectMap);
					break;
				default:
					System.out.println("unexpected type: " + type);

				}
			} else if (shape instanceof Ellipse) {
				Map<String, Double> RectMap = shape.getProperties();
				Double x = shape.getPosition().getX();
				Double y = shape.getPosition().getY();
				Double width = RectMap.get("xAxis"); // = y1-y2
				Double height = RectMap.get("yAxis"); // = x2-x1
				Double dx = p.x - x;
				Double dy = p.y - y;
				switch (type) {
				case Cursor.N_RESIZE_CURSOR:
					shape.setPosition(new Point(x.intValue(), (int) (y.intValue() + dy)));
					RectMap.put("yAxis", height - dy);
					shape.setProperties(RectMap);
					break;
				case Cursor.NW_RESIZE_CURSOR:
					shape.setPosition(new Point((int) (x.intValue() + dx), (int) (y.intValue() + dy)));
					RectMap.put("yAxis", height - dy);
					RectMap.put("xAxis", width - dx);
					shape.setProperties(RectMap);
					break;
				case Cursor.W_RESIZE_CURSOR:
					shape.setPosition(new Point((int) (x.intValue() + dx), y.intValue()));
					RectMap.put("xAxis", width - dx);
					shape.setProperties(RectMap);
					break;
				case Cursor.SW_RESIZE_CURSOR:
					shape.setPosition(new Point((int) (x.intValue() + dx), y.intValue()));
					RectMap.put("yAxis", dy);
					RectMap.put("xAxis", width - dx);
					shape.setProperties(RectMap);
					break;
				case Cursor.S_RESIZE_CURSOR:
					RectMap.put("yAxis", dy);
					shape.setProperties(RectMap);
					break;
				case Cursor.SE_RESIZE_CURSOR:
					RectMap.put("yAxis", dy);
					RectMap.put("xAxis", dx);
					shape.setProperties(RectMap);
					break;
				case Cursor.E_RESIZE_CURSOR:
					RectMap.put("xAxis", dx);
					shape.setProperties(RectMap);
					break;
				case Cursor.NE_RESIZE_CURSOR:
					shape.setPosition(new Point(x.intValue(), (int) (y.intValue() + dy)));
					RectMap.put("yAxis", height - dy);
					RectMap.put("xAxis", dx);
					shape.setProperties(RectMap);
					break;
				default:
					System.out.println("unexpected type: " + type);

				}
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
				if (surface.getMouseListeners().length != 0) {
					if (surface.getMouseListeners()[0] == moveAdapter) {
						surface.removeMouseListener(moveAdapter);
						surface.removeMouseMotionListener(moveAdapter);
					}
				}
				if (surface.getMouseListeners().length != 0) {
					if (surface.getMouseListeners()[0] == resizeAdapter) {
						surface.removeMouseListener(resizeAdapter);
						surface.removeMouseMotionListener(resizeMotion);
					}
				}

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

				selectedShape = null;
				surface.repaint();
			}
		}
	}

	class colorLestener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			selectedColorButton = "OuterColor";
			Paint.clickedSelectedColorButton(selectedColorButton);
		}

	}

	class fillColorLestener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			selectedColorButton = "FillColor";
			Paint.clickedSelectedColorButton(selectedColorButton);
		}

	}

	class copyListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {

			if (selectedShape != null) {
				copyFlag = 1;
				if (!(surface.getMouseListeners()[0] == moveAdapter)) {
					if (surface.getMouseListeners().length != 0) {
						if (surface.getMouseListeners()[0] == createAdapter) {
							surface.removeMouseListener(createAdapter);
							surface.removeMouseMotionListener(createMotion);
						}
					}
					if (surface.getMouseListeners().length != 0) {
						if (surface.getMouseListeners()[0] == resizeAdapter) {
							surface.removeMouseListener(resizeAdapter);
							surface.removeMouseMotionListener(resizeMotion);
						}
					}

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
				if (copiedShape instanceof LineSegment) {
					copiedShape.setPosition(new Point(point.x + 20, point.y));
					Map<String, Double> prop = copiedShape.getProperties();
					prop.put("x1", prop.get("x1") + 20);
					prop.put("y1", prop.get("y1"));
					prop.put("x2", prop.get("x2") + 20);
					prop.put("y2", prop.get("y2"));
					copiedShape.setProperties(prop);

				} else if (copiedShape instanceof Triangle) {
					copiedShape.setPosition(new Point(point.x + 20, point.y));
					Map<String, Double> prop = copiedShape.getProperties();
					prop.put("x1", prop.get("x1") + 20);
					prop.put("y1", prop.get("y1"));
					prop.put("x2", prop.get("x2") + 20);
					prop.put("y2", prop.get("y2"));
					prop.put("x3", prop.get("x3") + 20);
					prop.put("y3", prop.get("y3"));
					copiedShape.setProperties(prop);
				} else {
				copiedShape.setPosition(new Point(point.x + 20, point.y));
			}

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

			try {
				updatedShape = (Shape) selectedShape.clone();
				Map<String, Double> newProperties = updatedShape.getProperties();
				newProperties.put(propKey, Double.valueOf(shapePropertiesPanel.getTextFieldValue(propKey)));
				updatedShape.setProperties(newProperties);
				// System.out.println(updatedShape.getProperties().get(propKey));
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
			Point newPosition = new Point();
			newPosition.x = Integer.valueOf(shapePropertiesPanel.getTextFieldValue("positionX"));
			newPosition.y = Integer.valueOf(shapePropertiesPanel.getTextFieldValue("positionY"));
			try {
				updatedShape = (Shape) selectedShape.clone();
				updatedShape.setPosition(newPosition);
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

public class moveLestener implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		if (!(surface.getMouseListeners()[0] == moveAdapter)) {
			if (surface.getMouseListeners().length != 0) {
				if (surface.getMouseListeners()[0] == createAdapter) {
					surface.removeMouseListener(createAdapter);
					surface.removeMouseMotionListener(createMotion);
				}
			}
			if (surface.getMouseListeners().length != 0) {
				if (surface.getMouseListeners()[0] == resizeAdapter) {
					surface.removeMouseListener(resizeAdapter);
					surface.removeMouseMotionListener(resizeMotion);
				}
			}

			surface.addMouseListener(moveAdapter);
			surface.addMouseMotionListener(moveMotion);
		}
	}

}

public class resizeLestener implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		if (!(surface.getMouseListeners()[0] == resizeAdapter)) {
			if (surface.getMouseListeners().length != 0) {
				if (surface.getMouseListeners()[0] == moveAdapter) {
					surface.removeMouseListener(moveAdapter);
					surface.removeMouseMotionListener(moveMotion);
				}
			}
			if (surface.getMouseListeners().length != 0) {
				if (surface.getMouseListeners()[0] == createAdapter) {
					surface.removeMouseListener(createAdapter);
					surface.removeMouseMotionListener(createMotion);
				}
			}

			surface.addMouseListener(resizeAdapter);
			surface.addMouseMotionListener(resizeMotion);
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

class snapshotListener implements ActionListener {

	@Override
	public void actionPerformed(ActionEvent e) {
		BufferedImage image = new BufferedImage(surface.getWidth(), surface.getHeight(), BufferedImage.TYPE_INT_RGB);
		surface.paint(image.getGraphics());
		Graphics2D g = image.createGraphics();
		g.setBackground(Color.WHITE);
		g.clearRect(0, 0, surface.getWidth(), surface.getHeight());
		surface.printAll(g);
		JFileChooser chooser = new JFileChooser("");
		int retrival = chooser.showSaveDialog(null);
		if (retrival == JFileChooser.APPROVE_OPTION) {
			Path path = Paths.get(chooser.getCurrentDirectory() + "/" + chooser.getSelectedFile().getName());
			try {
				ImageIO.write(image, "png", new File(path.toString().concat(".png")));
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}

	}

	public void updateUndoAndRedoView() {
		if (engine.undoActions.isEmpty()) {
			Paint.disableUndo();
		} else {
			Paint.enableUndo();
		}
		if (engine.redoActions.isEmpty()) {
			Paint.disableRedo();
		} else {
			Paint.enableRedo();
		}
	}

	class paletteListerner implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if (selectedColorButton == "FillColor") {
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
			} else if (selectedColorButton == "OuterColor") {
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

	}

	class colorButtonsListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			JButton source = (JButton) e.getSource();
			Color newColor = source.getBackground();
			if (selectedColorButton == "FillColor") {
				fillColor = newColor;
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

			} else if (selectedColorButton == "OuterColor") {
				color = newColor;
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
	}

	/**
	 * Make a smaller Rectangle and use it to locate the cursor relative to the
	 * Rectangle center.
	 */
	private int getOutcode(Point p) {
		java.awt.Rectangle r = (java.awt.Rectangle) this.highlightRect.clone();
		r.grow(-PROX_DIST, -PROX_DIST);
		return r.outcode(p.x, p.y);
	}

	/**
	 * Make a larger Rectangle and check to see if the cursor is over it.
	 */
	private boolean isOverRect(Point p) {
		java.awt.Rectangle r = (java.awt.Rectangle) this.highlightRect.clone();
		r.grow(PROX_DIST, PROX_DIST);
		return r.contains(p);
	}

}
