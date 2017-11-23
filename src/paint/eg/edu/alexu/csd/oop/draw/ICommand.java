package paint.eg.edu.alexu.csd.oop.draw;

public interface ICommand {
	
	public void execute();
	public Shape getNewShape();
	public Shape getOldShape();
	public void unexecute();

}
