package paint.eg.edu.alexu.csd.oop.draw.cs62_67;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Toolkit;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
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
	private final JMenuItem mntmExit = new JMenuItem("Exit");
	public GUI() {
		setTitle("Paint");
		getContentPane().setBackground(Color.WHITE);
		this.setSize(553, 553);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(new BorderLayout(0, 0));
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		//Color newColor = JColorChooser.showDialog(null, "Choose a color", Color.RED);
		mntmNew.setAccelerator(KeyStroke.getKeyStroke('N', Toolkit.getDefaultToolkit ().getMenuShortcutKeyMask()));
		mntmSave.setAccelerator(KeyStroke.getKeyStroke('S', Toolkit.getDefaultToolkit ().getMenuShortcutKeyMask()));
		mntmLoad.setAccelerator(KeyStroke.getKeyStroke('O', Toolkit.getDefaultToolkit ().getMenuShortcutKeyMask()));
		mntmUndo.setAccelerator(KeyStroke.getKeyStroke('Z', Toolkit.getDefaultToolkit ().getMenuShortcutKeyMask()));
		mntmRedo.setAccelerator(KeyStroke.getKeyStroke('Y', Toolkit.getDefaultToolkit ().getMenuShortcutKeyMask()));
		mntmCut.setAccelerator(KeyStroke.getKeyStroke('X', Toolkit.getDefaultToolkit ().getMenuShortcutKeyMask()));
		mntmCopy.setAccelerator(KeyStroke.getKeyStroke('C', Toolkit.getDefaultToolkit ().getMenuShortcutKeyMask()));
		mntmPaste.setAccelerator(KeyStroke.getKeyStroke('V', Toolkit.getDefaultToolkit ().getMenuShortcutKeyMask()));
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(8)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING, false)
						.addComponent(RectangleButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(circleButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(lineButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(SquareButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(EllipseButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(TriangleButton, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
						
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
		
		mnFile.add(mntmExit);
		
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
	void addTriangleListner(ActionListener listenforTriangleButton){
		TriangleButton.addActionListener(listenforTriangleButton);
	}
	void addExitListener(ActionListener listenforExit){
		mntmExit.addActionListener(listenforExit);
	}
	void addUndoListener(ActionListener listenforUndo){
		mntmUndo.addActionListener(listenforUndo);
	}
	void addRedoListener(ActionListener listenforRedo){
		mntmRedo.addActionListener(listenforRedo);
	}
	void addNewListener(ActionListener listenforNew){
		mntmNew.addActionListener(listenforNew);
	}
}