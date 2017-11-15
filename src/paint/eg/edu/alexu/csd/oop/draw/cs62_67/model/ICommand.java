package paint.eg.edu.alexu.csd.oop.draw.cs62_67.model;

import paint.eg.edu.alexu.csd.oop.draw.Shape;

public interface ICommand {
	
	public void execute();
	public Shape getNewShape();
	public Shape getOldShape();
	public void unexecute();

}
