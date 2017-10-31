package paint.eg.edu.alexu.csd.oop.draw.cs62_67;

import java.awt.BorderLayout;



import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import javax.swing.JPanel;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

public class GUI extends JFrame {
	
	JButton circleButton = new JButton("Circle");
	JButton lineButton = new JButton("Line");
	JButton SquareButton = new JButton("Square");
	JButton RectangleButton = new JButton("Rectangle");
	JButton EllipseButton = new JButton("Ellipse");
	JButton TriangleButton = new JButton("Triangle");
	private final JMenuBar menuBar = new JMenuBar();
	private final JMenu mnFile = new JMenu("File");
	private final JMenuItem mntmNew = new JMenuItem("New");
	private final JMenuItem mntmSave = new JMenuItem("Save");
	private final JMenuItem mntmLoad = new JMenuItem("Load");
	private final JMenu mnEdit = new JMenu("Edit");
	private final JMenuItem mntmUndo = new JMenuItem("Undo");
	private final JMenuItem mntmRedo = new JMenuItem("Redo");
	private final JMenuItem mntmCut = new JMenuItem("Cut");
	private final JMenuItem mntmCopy = new JMenuItem("Copy");
	private final JMenuItem mntmPaste = new JMenuItem("Paste");
	private final JMenuItem mntmDelete = new JMenuItem("Delete");
	public GUI() {
		this.setSize(553, 553);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(new BorderLayout(0, 0));
		JPanel panel = new JPanel();
		
		
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(8)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addComponent(TriangleButton)
						.addComponent(EllipseButton)
						.addComponent(RectangleButton)
						.addComponent(SquareButton)
						.addComponent(lineButton)
						.addComponent(circleButton))
					.addContainerGap())
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(7)
					.addComponent(circleButton)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(lineButton)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(SquareButton)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(RectangleButton)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(EllipseButton)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(TriangleButton)
					.addGap(288))
		);
		panel.setLayout(gl_panel);
		getContentPane().add(panel, BorderLayout.WEST);
		
		setJMenuBar(menuBar);
		
		menuBar.add(mnFile);
		
		mnFile.add(mntmNew);
		
		mnFile.add(mntmSave);
		
		mnFile.add(mntmLoad);
		
		menuBar.add(mnEdit);
		
		mnEdit.add(mntmUndo);
		
		mnEdit.add(mntmRedo);
		
		mnEdit.add(mntmCut);
		
		mnEdit.add(mntmCopy);
		
		mnEdit.add(mntmPaste);
		
		mnEdit.add(mntmDelete);
		this.setVisible(true);
	}
	
	void addCircleListner(ActionListener listenforCircleButton){
		circleButton.addActionListener(listenforCircleButton);
	}
	
	void addLineListner(ActionListener listenforLineButton){
		lineButton.addActionListener(listenforLineButton);
	}
	void addSquareListner(ActionListener listenforSquareButton){
		SquareButton.addActionListener(listenforSquareButton);
	}
	void addRectangleListener(ActionListener listenforRectangleButton){
		RectangleButton.addActionListener(listenforRectangleButton);
	}
	void addEllipseListner(ActionListener listenforEllipseButton){
		EllipseButton.addActionListener(listenforEllipseButton);
	}
}