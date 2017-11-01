package paint.eg.edu.alexu.csd.oop.draw.cs62_67;

public class JavaClassLoader extends ClassLoader{

	public Class<?> loadExtraClass(String classBinName){
		
		ClassLoader classLoader = this.getClass().getClassLoader();
				
		Class<?> loadedClass = null;
		try {
			loadedClass = classLoader.loadClass(classBinName);
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		//System.out.println("load succeeded!");
		return loadedClass;		
	}
}
