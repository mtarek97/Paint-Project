package paint.eg.edu.alexu.csd.oop.draw.cs62_67;
import java.awt.Color;
import java.awt.Point;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import paint.eg.edu.alexu.csd.oop.draw.Shape;



public class XML {

	
	public static void save(String path, ArrayList<Shape> shapes){
		 DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
		 try {
			DocumentBuilder builder = builderFactory.newDocumentBuilder();
			Document dom = builder.newDocument();
			Element root = dom.createElement("shapes");
			for(Shape i : shapes){
				Element e = dom.createElement(i.getClass().getSimpleName());
				e.setAttribute("Color",String.valueOf(i.getColor().getRGB()));
				e.setAttribute("FillColor",String.valueOf(i.getFillColor().getRGB()));
				e.setAttribute("PositionX",String.valueOf(i.getPosition().getX()));
				e.setAttribute("PositionY",String.valueOf(i.getPosition().getY()));
				for(Map.Entry<String, Double> prop : i.getProperties().entrySet()){
					Element propNode = dom.createElement(prop.getKey());
					propNode.appendChild(dom.createTextNode(prop.getValue().toString()));
					e.appendChild(propNode);
				}
				root.appendChild(e);
			}
			dom.appendChild(root);
			
			Transformer tr = TransformerFactory.newInstance().newTransformer();
			tr.transform(new DOMSource(dom),new StreamResult(new FileOutputStream(path)));
			
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TransformerConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TransformerFactoryConfigurationError e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
		 
		public static void load(String path, ArrayList<Shape> shapes){
			Document dom;
			DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
			try {
				DocumentBuilder builder = builderFactory.newDocumentBuilder();
				dom = builder.parse(path);
				
				Element root = dom.getDocumentElement();
				NodeList shapesNodes = root.getChildNodes();
				ShapeFactory factory = new ShapeFactory();
				for(int i=0;i<shapesNodes.getLength();i++){
					Node shapeNode = shapesNodes.item(i);
					Shape shape = factory.createShape(shapeNode.getNodeName());
					NodeList propNodes = shapeNode.getChildNodes();
					Map<String,Double> prop = shape.getProperties();
					for(int j=0;j<propNodes.getLength();j++){
						Node probNode = propNodes.item(i);
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
					shapes.add(shape);
				}
				
				
			} catch (ParserConfigurationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SAXException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
	}
}
