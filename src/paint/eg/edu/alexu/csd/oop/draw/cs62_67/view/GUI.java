package paint.eg.edu.alexu.csd.oop.draw.cs62_67.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.LayoutStyle.ComponentPlacement;

public class GUI extends JFrame {

	private final JMenuBar menuBar = new JMenuBar();
	private final JMenu mnFile = new JMenu("File");
	private final JMenuItem mntmNew = new JMenuItem("New");
	private final JMenuItem mntmLoad =
	new JMenuItem("Load");
	private final JMenu mnEdit = new JMenu("Edit");
	private final JMenuItem mntmUndo =
	new JMenuItem("Undo");
	private final JMenuItem mntmRedo =
	new JMenuItem("Redo");
	private final JMenuItem mntmCut = new JMenuItem("Cut");
	private final JMenuItem mntmCopy =
	new JMenuItem("Copy");
	private final JMenuItem mntmPaste =
	new JMenuItem("Paste");
	private final JMenuItem mntmDelete =
	new JMenuItem("Delete");
	private final JMenuItem mntmExit =
	new JMenuItem("Exit");
	private final JPanel ToolsPanel = new JPanel();
	private final JButton DeleteShapeBtn =
	new JButton("Delete");
	private final JPanel statusPanel = new JPanel();
	private final JLabel mouseXlbl = new JLabel("X:");
	private final JMenu mnSave = new JMenu("Save");
	private final JMenuItem mntmSaveXml =
	new JMenuItem("save xml");
	private final JMenuItem mntmSaveJson =
	new JMenuItem("save json");

	public GUI() {
		setTitle("Paint");
		getContentPane().setBackground(Color.WHITE);
		this.setSize(950, 650);
		getContentPane().setLayout(new BorderLayout(0, 0));
		ToolsPanel.setBorder(null);
		ToolsPanel.setBackground(Color.LIGHT_GRAY);

		getContentPane().add(ToolsPanel,
		BorderLayout.NORTH);
		GroupLayout gl_ToolsPanel =
		new GroupLayout(ToolsPanel);
		gl_ToolsPanel.setHorizontalGroup(
		gl_ToolsPanel.createParallelGroup(Alignment.LEADING)
		.addGroup(Alignment.TRAILING,
		gl_ToolsPanel.createSequentialGroup()
		.addContainerGap(851, Short.MAX_VALUE)
		.addComponent(DeleteShapeBtn,
		GroupLayout.PREFERRED_SIZE, 73,
		GroupLayout.PREFERRED_SIZE).addContainerGap()));
		gl_ToolsPanel.setVerticalGroup(gl_ToolsPanel
		.createParallelGroup(Alignment.TRAILING)
		.addGroup(Alignment.LEADING,
		gl_ToolsPanel.createSequentialGroup()
		.addContainerGap().addComponent(DeleteShapeBtn)
		.addContainerGap(35, Short.MAX_VALUE)));
		DeleteShapeBtn
		.setBackground(SystemColor.activeCaption);
		ToolsPanel.setLayout(gl_ToolsPanel);

		getContentPane().add(statusPanel,
		BorderLayout.SOUTH);

		JLabel mouseYlbl = new JLabel("Y:");
		GroupLayout gl_statusPanel =
		new GroupLayout(statusPanel);
		gl_statusPanel.setHorizontalGroup(gl_statusPanel
		.createParallelGroup(Alignment.LEADING)
		.addGroup(gl_statusPanel.createSequentialGroup()
		.addComponent(mouseXlbl, GroupLayout.PREFERRED_SIZE,
		55, GroupLayout.PREFERRED_SIZE)
		.addPreferredGap(ComponentPlacement.RELATED)
		.addComponent(mouseYlbl).addContainerGap(827,
		Short.MAX_VALUE)));
		gl_statusPanel.setVerticalGroup(gl_statusPanel
		.createParallelGroup(Alignment.TRAILING)
		.addGroup(gl_statusPanel
		.createParallelGroup(Alignment.BASELINE)
		.addComponent(mouseXlbl, GroupLayout.DEFAULT_SIZE,
		25, Short.MAX_VALUE).addComponent(mouseYlbl)));
		statusPanel.setLayout(gl_statusPanel);
		// Color newColor = JColorChooser.showDialog(null,
		// "Choose a color", Color.RED);
		mntmNew
		.setAccelerator(KeyStroke.getKeyStroke('N', Toolkit
		.getDefaultToolkit().getMenuShortcutKeyMask()));
		mntmLoad
		.setAccelerator(KeyStroke.getKeyStroke('O', Toolkit
		.getDefaultToolkit().getMenuShortcutKeyMask()));
		mntmUndo
		.setAccelerator(KeyStroke.getKeyStroke('Z', Toolkit
		.getDefaultToolkit().getMenuShortcutKeyMask()));
		mntmRedo
		.setAccelerator(KeyStroke.getKeyStroke('Y', Toolkit
		.getDefaultToolkit().getMenuShortcutKeyMask()));
		mntmCut
		.setAccelerator(KeyStroke.getKeyStroke('X', Toolkit
		.getDefaultToolkit().getMenuShortcutKeyMask()));
		mntmCopy
		.setAccelerator(KeyStroke.getKeyStroke('C', Toolkit
		.getDefaultToolkit().getMenuShortcutKeyMask()));
		mntmPaste
		.setAccelerator(KeyStroke.getKeyStroke('V', Toolkit
		.getDefaultToolkit().getMenuShortcutKeyMask()));

		setJMenuBar(menuBar);

		menuBar.add(mnFile);

		mnFile.add(mntmNew);

		mnFile.add(mntmLoad);

		mnFile.add(mnSave);

		mnSave.add(mntmSaveXml);

		mnSave.add(mntmSaveJson);

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

	public void
	addExitListener(ActionListener listenforExit) {
		mntmExit.addActionListener(listenforExit);
	}

	public void
	addUndoListener(ActionListener listenforUndo) {
		mntmUndo.addActionListener(listenforUndo);
	}

	public void
	addRedoListener(ActionListener listenforRedo) {
		mntmRedo.addActionListener(listenforRedo);
	}

	public void
	addNewListener(ActionListener listenforNew) {
		mntmNew.addActionListener(listenforNew);
	}

	public void
	addDeleteListener(ActionListener listenforDelete) {
		DeleteShapeBtn.addActionListener(listenforDelete);
	}

	public void
	saveXmlListener(ActionListener listenforSaveXml) {
		mntmSaveXml.addActionListener(listenforSaveXml);
	}

	public void
	saveJsonListener(ActionListener listenforSaveJson) {
		mntmSaveJson.addActionListener(listenforSaveJson);
	}

	public void loadListener(ActionListener listenforLoad) {
		mntmLoad.addActionListener(listenforLoad);
	}
}