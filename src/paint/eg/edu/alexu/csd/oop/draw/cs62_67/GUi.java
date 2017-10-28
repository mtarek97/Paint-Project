package paint.eg.edu.alexu.csd.oop.draw.cs62_67;

import javax.swing.JFrame;

import java.awt.Dimension;

import javax.swing.JButton;

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
		frame.getContentPane().setLayout(null);
		
		JButton btnNewButton = new JButton("New button");
		btnNewButton.setSize(new Dimension(97, 25));
		frame.getContentPane().add(btnNewButton);
		frame.setVisible(true);
	}
}
