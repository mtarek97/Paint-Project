package paint.eg.edu.alexu.csd.oop.draw.cs62_67.model;
import java.awt.Color;
import java.awt.Point;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import paint.eg.edu.alexu.csd.oop.draw.DrawingEngine;
import paint.eg.edu.alexu.csd.oop.draw.Shape;



public class XML {

	DrawingEngine engine;
	
	public XML(DrawingEngine engine){
		this.engine = engine;
	}
	
	public void save(String path, ArrayList<Shape> shapes) throws Exception{
		 DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
		 
			DocumentBuilder builder = builderFactory.newDocumentBuilder();
			Document dom = builder.newDocument();
			
			Element root = dom.createElement("shapes");
			for(Shape i : shapes){
				Element e = dom.createElement(i.getClass().getSimpleName());
				try{
					e.setAttribute("Color",String.valueOf(i.getColor().getRGB()));
				}catch (Exception e1) {
					i.setColor(Color.BLACK);
					e.setAttribute("Color",String.valueOf(i.getColor().getRGB()));
				}
				try{
					e.setAttribute("FillColor",String.valueOf(i.getFillColor().getRGB()));
				}catch (Exception e1) {
					i.setFillColor(Color.white);
					e.setAttribute("FillColor",String.valueOf(i.getFillColor().getRGB()));
				}
				try{
					e.setAttribute("PositionX",String.valueOf(i.getPosition().getX()));
				}catch (Exception e1) {
					i.setPosition(new Point(0, 0));
					e.setAttribute("PositionX",String.valueOf(i.getPosition().getX()));
				}
				e.setAttribute("PositionY",String.valueOf(i.getPosition().getY()));
				Map<String, Double> properties = i.getProperties();
				if(properties != null){
					for(Map.Entry<String, Double> prop : i.getProperties().entrySet()){
						Element propNode = dom.createElement(prop.getKey());
						propNode.appendChild(dom.createTextNode(prop.getValue().toString()));
						e.appendChild(propNode);
					}
				}
				root.appendChild(e);
			}
			dom.appendChild(root);
			
			Transformer tr = TransformerFactory.newInstance().newTransformer();
			tr.setOutputProperty(OutputKeys.ENCODING,"ISO-8859-1" );
			tr.transform(new DOMSource(dom),new StreamResult(new FileOutputStream(path)));
			
		
	}
		 
		public void load(String path, ArrayList<Shape> shapes){
			Document dom;
			File file = new File(path);
			InputStream inputStream;
			Reader reader = null;
			try {
				inputStream = new FileInputStream(file);
				try {
					reader = new InputStreamReader(inputStream, "ISO-8859-1");
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			InputSource is = new InputSource(reader);
			is.setEncoding("ISO-8859-1");
			
			DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
			try {
				DocumentBuilder builder = builderFactory.newDocumentBuilder();
				dom = builder.parse(is);
				
				Element root = dom.getDocumentElement();
				NodeList shapesNodes = root.getChildNodes();
				ShapeFactory factory = new ShapeFactory(engine);
				for(int i=0;i<shapesNodes.getLength();i++){
					Node shapeNode = shapesNodes.item(i);
					Shape shape = factory.createShape(shapeNode.getNodeName());
					if(shape != null){
						NodeList propNodes = shapeNode.getChildNodes();
						Map<String,Double> prop = shape.getProperties();
						for(int j=0;j<propNodes.getLength();j++){
							Node probNode = propNodes.item(j);
							Element probElement = (Element) probNode;
							 prop.put(probElement.getNodeName(), Double.valueOf(probElement.getTextContent()));
						}
						shape.setProperties(prop);
						Element shapeElement = (Element) shapeNode;
						// load position
						int x = Double.valueOf(shapeElement.getAttribute("PositionX")).intValue();
						int y = Double.valueOf(shapeElement.getAttribute("PositionY")).intValue();
						shape.setPosition(new Point(x,y));
						// load Color
						shape.setColor(new Color(Integer.parseInt(shapeElement.getAttribute("Color"))));
						// load FillColor
						shape.setFillColor(new Color(Integer.parseInt(shapeElement.getAttribute("FillColor"))));
					}
					shapes.add(shape);
				}
				
				
			} catch (ParserConfigurationException e) {
				e.printStackTrace();
			} catch (SAXException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}	
	}
}
