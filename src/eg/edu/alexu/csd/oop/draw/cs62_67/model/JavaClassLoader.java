package eg.edu.alexu.csd.oop.draw.cs62_67.model;

import eg.edu.alexu.csd.oop.draw.Shape;

public class JavaClassLoader extends ClassLoader{

	public Class<? extends Shape> loadExtraClass(String classBinName){
		
		ClassLoader classLoader = this.getClass().getClassLoader();
				
		Class<?> loadedClass = null;
		try {
			loadedClass =  classLoader.loadClass(classBinName);

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		//System.out.println("load succeeded!");
		return (Class<? extends Shape>) loadedClass;		
	}
}
