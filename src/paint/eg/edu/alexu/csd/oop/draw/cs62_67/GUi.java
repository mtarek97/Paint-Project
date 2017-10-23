package paint.eg.edu.alexu.csd.oop.draw.cs62_67;

import javax.swing.JFrame;

public class GUi {

	private JFrame frame;
	
	/**
	 * Create the application.
	 */
	public GUi() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

}
