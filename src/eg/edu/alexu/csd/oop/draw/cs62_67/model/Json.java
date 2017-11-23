package eg.edu.alexu.csd.oop.draw.cs62_67.model;

import java.awt.Color;
import java.awt.Point;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import eg.edu.alexu.csd.oop.draw.DrawingEngine;
import eg.edu.alexu.csd.oop.draw.Shape;

public class Json {
	
	private static DrawingEngine engine;
	public Json(DrawingEngine engine){
		this.engine = engine;
	}

	static StringBuilder builder = new StringBuilder();
	private static BufferedWriter bw = null;
	private static BufferedReader br = null;
	
	public static void save(String path, ArrayList<Shape> shapes) throws IOException{
			bw = new BufferedWriter(new FileWriter(path));
			bw.write("{\n");
			bw.write("\"shapes\": [\n");
			for(int i = 0; i < shapes.size(); i++){
				writeProperties(shapes.get(i));
				if(i != shapes.size()-1){
					bw.write("},\n");
				}
				else{
					bw.write("}\n");
				}
			}
			bw.write("]\n");
			bw.write("}");
			bw.close();
		
	}
	
	public static void load(String path, ArrayList<Shape> shapes) throws IOException{
			int counter = 0;
			ShapeFactory factory = new ShapeFactory(engine);

			
			br = new BufferedReader(new FileReader(path));
			String currentLine = new String();
			
			for(int i = 0; i < 2; i++){
				builder.append(br.readLine());
			}
			if(!builder.toString().equals("nullnull")){
				while(!((currentLine = br.readLine()).equals("]"))){
					currentLine = br.readLine();
					Shape shape = factory.createShape(getValue(currentLine));
					if(shape != null){
						currentLine = br.readLine();
						int x = Integer.parseInt(getValue(currentLine));
						Color c = new Color(x);
						try{
							shape.setColor(c);
						}catch (Exception e) {
							
						}
						Point position = new Point();
						currentLine = br.readLine();
						Double positionX = Double.parseDouble(getValue(currentLine)) ;
						currentLine = br.readLine();
						Double positionY = Double.parseDouble(getValue(currentLine));
						currentLine = br.readLine();
						position.setLocation(positionX.intValue(), positionY.intValue());
						shape.setPosition(position);
						shape.setFillColor(new Color(Integer.parseInt(getValue(currentLine))));
						String property = br.readLine();
						Map<String,Double> properties = new HashMap<String,Double>();
						while(true){
							property = br.readLine();
							if((property.equals("}"))) {
								break;
							}
							String key = getKey(property);
							String value = getValue(property);
							properties.put(key, Double.parseDouble(value));
						}
						shape.setProperties(properties);
						shapes.add(shape);
						currentLine = br.readLine();
					}
					else if(shape == null && counter == 0){
						shapes.add(shape);
						counter = 1;
					}
				}
			}
			br.close();
	}
	
	private static void writeProperties(Shape shape) throws IOException{
		bw.write("{\n");
		writeKeyValue("Kind",shape.getClass().getSimpleName());
		bw.write(",\n");
		try{
			writeKeyValue("Color",String.valueOf(shape.getColor().getRGB()));
		}catch (Exception e) {
			shape.setColor(Color.black);
			writeKeyValue("Color",String.valueOf(shape.getColor().getRGB()));
		}
		bw.write(",\n");
		try{
			writeKeyValue("PositionX",String.valueOf(shape.getPosition().getX()));
		}catch (Exception e) {
			shape.setPosition(new Point(0, 0));
			writeKeyValue("PositionX",String.valueOf(shape.getPosition().getX()));
		}
		bw.write(",\n");
		writeKeyValue("PositionY",String.valueOf(shape.getPosition().getY()));
		bw.write(",\n");
		try{
			writeKeyValue("FillColor",String.valueOf(shape.getFillColor().getRGB()));
		}catch (Exception e) {
			shape.setFillColor(Color.WHITE);
			writeKeyValue("FillColor",String.valueOf(shape.getFillColor().getRGB()));
		}
		Map<String,Double> properties = shape.getProperties();
		if(properties != null){
			bw.write(",\n");
			bw.write("\"properties\": {\n");
			Set<String> keys = properties.keySet();
			int counter = 0;
			for(String key : keys){
				counter++;
				writeKeyValue(key, "" + properties.get(key));
				if(counter != keys.size()){
					bw.write(",\n");
				}
			}
			bw.write("\n");
			bw.write("}\n");
		}
		
	}
	
	private static void writeKeyValue(String key,String value){
		try {
			bw.write("\"" + key + "\":" + "\"" + value + "\"");
		} catch (IOException e) {
			
			throw new RuntimeException("error");
		}
	}
	
	private static String getValue(String string){
		String[] splited = string.split(":");
		String value = splited[1];
		if(value.charAt(value.length()-1) == ','){
			value = value.substring(1, value.length()-2);
		}
		else{
			value = value.substring(1, value.length()-1);
		}
		return value;
	}
	
	private static String getKey(String string){
		String[] splited = string.split(":");
		String value = splited[0];
		value = value.substring(1, value.length()-1);
		return value;
	}
	/*private static String fileReader(String path) throws IOException{
		StringBuilder builder = new StringBuilder();
		br = new BufferedReader(new FileReader(path));
		builder.append(br.readLine());
		builder.append("\n");
		return builder.toString();
	}*/
}