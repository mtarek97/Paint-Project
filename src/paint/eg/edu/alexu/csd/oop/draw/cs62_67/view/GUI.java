package paint.eg.edu.alexu.csd.oop.draw.cs62_67.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JSeparator;
import javax.swing.KeyStroke;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

public class GUI extends JFrame {

	final public static Color backgroundColor = new Color(245, 246, 247);
	final public static Color hoverColor = new Color(232, 239, 247);
	final public static Color selectedButtonColor = new Color(201,224,247);
	private final JMenuBar menuBar = new JMenuBar();
	private final JMenu mnFile = new JMenu("File");
	private final JMenuItem mntmLoad = new JMenuItem("Load");
	private final JMenu mnEdit = new JMenu("Edit");
	private final JMenuItem mntmUndo = new JMenuItem("Undo");
	private final JMenuItem mntmRedo = new JMenuItem("Redo");
	private final JMenuItem mntmCopy = new JMenuItem("Copy");
	private final JMenuItem mntmPaste = new JMenuItem("Paste");
	private final JMenuItem mntmDelete = new JMenuItem("Delete");
	private final JMenuItem mntmExit = new JMenuItem("Exit");
	private final JPanel ToolsPanel = new JPanel();
	private final JPanel statusPanel = new JPanel();
	public final JLabel mouseXlbl = new JLabel("X: ");
	public final JLabel mouseYlbl = new JLabel("Y: ");
	private final JMenu mnSave = new JMenu("Save");
	private final JMenuItem mntmSaveXml = new JMenuItem("Save as XML");
	private final JMenuItem mntmSaveJson = new JMenuItem("Save as JSON");

	private final JMenu mnPlugins = new JMenu("Plugins");
	private final JMenuItem mntmAddPlugin = new JMenuItem("Add Plugin..");
	private final JSeparator separator_1 = new JSeparator();
	private final JLabel lblStroke = new JLabel("Stroke");
	private final JLabel lblDelete = new JLabel("Delete");
	private final JSeparator separator_2 = new JSeparator();
	private final JLabel lblSnapshot = new JLabel("Snapshot");
	private final JSeparator separator_3 = new JSeparator();

	public final JButton btnColor = new JButton("");
	public final JButton btnFillColor = new JButton("");
	public final JButton btnMove = new JButton("Move");
	private final JButton btnDelete = new JButton("");
	private final JButton btnSnapshot = new JButton("");
	private final JButton btnColorPurple = new JButton("");
	private final JButton btnColorLavender = new JButton("");
	private final JButton btnStroke = new JButton("");
	private final JButton btnRedo = new JButton("");
	private final JButton btnUndo = new JButton("");
	private final JButton btnCopy = new JButton("Copy");
	private final JButton btnPaste = new JButton("Paste");
	private final JButton btnResize = new JButton("Resize");
	private final JButton btnColorIndiago = new JButton("");
	private final JButton btnColorTurquoise = new JButton("");
	private final JButton btnColorBlueGray = new JButton("");
	private final JButton btnColorLightTurquoise = new JButton("");
	private final JButton btnColorGreen = new JButton("");
	private final JButton btnColorLime = new JButton("");
	private final JButton btnColorYellow = new JButton("");
	private final JButton btnColorLightYellow = new JButton("");
	private final JButton btnColorOrange = new JButton("");
	private final JButton btnColorGold = new JButton("");
	private final JButton btnColorRed = new JButton("");
	private final JButton btnColorRose = new JButton("");
	private final JButton btnColorGray50 = new JButton("");
	private final JButton btnColorDarkRed = new JButton("");
	private final JButton btnColorBlack = new JButton("");
	private final JButton btnColorBrown = new JButton("");
	private final JButton btnColorGray25 = new JButton("");
	private final JButton btnColorWhite = new JButton("");
	private final JButton btnPalette = new JButton("");
	private final JLabel lblPalette = new JLabel("Palette");
	private final JMenu mnHelp = new JMenu("Help");
	private final JMenuItem mntmUserGuide = new JMenuItem("User Guide");
	private final JMenuItem mntmAbout = new JMenuItem("About");

	public GUI() {
		getContentPane().setForeground(Color.WHITE);
		lblPalette.setHorizontalAlignment(SwingConstants.CENTER);
		lblPalette.setFont(new Font("Tahoma", Font.PLAIN, 10));
		btnPalette.setIcon(new ImageIcon(GUI.class.getResource("/assets/palette.png")));
		btnPalette.setToolTipText("Palette");
		btnPalette.setFocusPainted(false);
		btnPalette.setBorderPainted(false);
		btnPalette.setBackground(backgroundColor);
		addButtonHover(btnPalette, new buttonHover());
		btnRedo.setToolTipText("Redo");
		btnRedo.setIcon(new ImageIcon(GUI.class.getResource("/assets/redo.png")));
		btnRedo.setBackground(backgroundColor);
		btnRedo.setBorderPainted(false);
		btnRedo.setFocusPainted(false);
		addButtonHover(btnRedo, new buttonHover());
		btnUndo.setToolTipText("Undo");
		btnUndo.setIcon(new ImageIcon(GUI.class.getResource("/assets/undo.png")));
		btnUndo.setBackground(backgroundColor);
		btnUndo.setBorderPainted(false);
		btnUndo.setFocusPainted(false);
		addButtonHover(btnUndo, new buttonHover());
		lblStroke.setHorizontalAlignment(SwingConstants.CENTER);
		btnStroke.setIcon(new ImageIcon(GUI.class.getResource("/assets/stroke.png")));
		btnStroke.setBackground(backgroundColor);
		btnStroke.setBorderPainted(false);
		btnStroke.setFocusPainted(false);
		addButtonHover(btnStroke, new buttonHover());
		btnColorLavender.setToolTipText("Lavender");
		btnColorLavender.setBackground(new Color(200, 191, 213));
		btnColorPurple.setToolTipText("Purple");
		btnColorPurple.setBackground(new Color(173, 73, 174));
		separator_3.setOrientation(SwingConstants.VERTICAL);
		separator_3.setForeground(SystemColor.controlShadow);
		separator_3.setBackground(Color.WHITE);
		btnSnapshot.setIcon(new ImageIcon(GUI.class.getResource("/assets/snapshot.png")));
		btnSnapshot.setBackground(backgroundColor);
		btnSnapshot.setBorderPainted(false);
		btnSnapshot.setFocusPainted(false);
		addButtonHover(btnSnapshot, new buttonHover());
		separator_2.setOrientation(SwingConstants.VERTICAL);
		separator_2.setForeground(SystemColor.controlShadow);
		separator_2.setBackground(Color.WHITE);
		lblDelete.setHorizontalAlignment(SwingConstants.CENTER);
		mntmAbout.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				About myAbout = new About();
			}
		});
		btnDelete.setIcon(new ImageIcon(GUI.class.getResource("/assets/trash.png")));
		btnDelete.setBackground(backgroundColor);
		btnDelete.setBorderPainted(false);
		btnDelete.setFocusPainted(false);
		addButtonHover(btnDelete, new buttonHover());

		separator_1.setForeground(new Color(160, 160, 160));
		separator_1.setBackground(new Color(255, 255, 255));
		separator_1.setOrientation(SwingConstants.VERTICAL);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setFont(new Font("Trebuchet MS", Font.PLAIN, 18));
		setIconImage(Toolkit.getDefaultToolkit().getImage(GUI.class.getResource("/assets/logo_only.png")));
		setTitle("RGB");
		getContentPane().setBackground(Color.WHITE);
		this.setSize(995, 650);
		getContentPane().setLayout(new BorderLayout(0, 0));
		ToolsPanel.setBorder(new LineBorder(new Color(0, 0, 0)));
		ToolsPanel.setBackground(new Color(245, 246, 247));
		getContentPane().add(ToolsPanel, BorderLayout.NORTH);

		btnCopy.setHorizontalAlignment(SwingConstants.LEFT);
		btnCopy.setBackground(backgroundColor);
		btnCopy.setIcon(new ImageIcon(GUI.class.getResource("/assets/copy.png")));
		btnCopy.setBorderPainted(false);
		btnCopy.setFocusPainted(false);
		addButtonHover(btnCopy, new buttonHover());

		btnPaste.setBackground(backgroundColor);
		btnPaste.setIcon(new ImageIcon(GUI.class.getResource("/assets/paste.png")));
		btnPaste.setBorderPainted(false);
		btnPaste.setFocusPainted(false);
		addButtonHover(btnPaste, new buttonHover());

		btnMove.setIcon(new ImageIcon(GUI.class.getResource("/assets/move.png")));
		btnMove.setBackground(backgroundColor);
		btnMove.setBorderPainted(false);
		btnMove.setFocusPainted(false);
		addButtonHover(btnMove, new buttonHover());

		btnResize.setBackground(backgroundColor);
		btnResize.setBorderPainted(false);
		btnResize.setFocusPainted(false);
		addButtonHover(btnResize, new buttonHover());
		btnResize.setIcon(new ImageIcon(GUI.class.getResource("/assets/resize.png")));

		btnColorIndiago.setToolTipText("Indiago");
		btnColorIndiago.setBackground(new Color(63, 72, 204));

		btnColorTurquoise.setToolTipText("Turquoise");
		btnColorTurquoise.setBackground(new Color(0, 162, 232));

		btnColorBlueGray.setToolTipText("Blue-Gray");
		btnColorBlueGray.setBackground(new Color(112, 146, 190));

		btnColorLightTurquoise.setToolTipText("Light Turquoise");
		btnColorLightTurquoise.setBackground(new Color(153, 217, 234));

		btnColorGreen.setToolTipText("Green");
		btnColorGreen.setBackground(new Color(34, 177, 76));

		btnColorLime.setToolTipText("Lime");
		btnColorLime.setBackground(new Color(181, 230, 29));

		btnColorYellow.setToolTipText("Yellow");
		btnColorYellow.setBackground(new Color(255, 242, 0));

		btnColorLightYellow.setToolTipText("Light Yellow");
		btnColorLightYellow.setBackground(new Color(239, 228, 176));

		btnColorOrange.setToolTipText("Orange");
		btnColorOrange.setBackground(new Color(255, 201, 14));

		btnColorGold.setToolTipText("Gold");
		btnColorGold.setBackground(new Color(255, 201, 14));

		btnColorRed.setToolTipText("Red");
		btnColorRed.setBackground(new Color(237, 28, 36));

		btnColorRose.setToolTipText("Rose");
		btnColorRose.setBackground(new Color(255, 174, 201));

		btnColorGray50.setToolTipText("Gray-50%");
		btnColorGray50.setBackground(new Color(127, 127, 127));

		btnColorDarkRed.setToolTipText("Dark Red");
		btnColorDarkRed.setBackground(new Color(136, 0, 21));

		btnColorBlack.setToolTipText("Black");
		btnColorBlack.setBackground(Color.black);

		btnColorBrown.setToolTipText("Brown");
		btnColorBrown.setBackground(new Color(185, 122, 87));

		btnColorGray25.setToolTipText("Gray-25%");
		btnColorGray25.setBackground(new Color(195, 195, 195));

		btnColorWhite.setToolTipText("White");
		btnColorWhite.setBackground(Color.white);

		JLabel lblNewLabel = new JLabel("Colors");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);

		JLabel lblNewLabel_1 = new JLabel("Fill Color");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);

		JLabel lblOuterColor = new JLabel("Outer Color");
		lblOuterColor.setHorizontalAlignment(SwingConstants.CENTER);

		JSeparator separator_4 = new JSeparator();
		separator_4.setOrientation(SwingConstants.VERTICAL);
		separator_4.setForeground(SystemColor.controlShadow);
		separator_4.setBackground(Color.WHITE);
		btnFillColor.setToolTipText("Fill Color");
		btnFillColor.setBackground(Color.WHITE);
		btnFillColor.setFocusPainted(false);
		btnColor.setToolTipText("Outer Color");
		btnColor.setBackground(Color.BLACK);
		btnColor.setFocusPainted(false);
		lblSnapshot.setHorizontalAlignment(SwingConstants.CENTER);
		GroupLayout gl_ToolsPanel = new GroupLayout(ToolsPanel);
		gl_ToolsPanel.setHorizontalGroup(
			gl_ToolsPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_ToolsPanel.createSequentialGroup()
					.addGap(10)
					.addGroup(gl_ToolsPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(btnUndo, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnRedo, GroupLayout.PREFERRED_SIZE, 43, GroupLayout.PREFERRED_SIZE))
					.addGap(6)
					.addGroup(gl_ToolsPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(btnCopy, GroupLayout.PREFERRED_SIZE, 93, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnPaste, GroupLayout.PREFERRED_SIZE, 93, GroupLayout.PREFERRED_SIZE))
					.addGap(6)
					.addComponent(separator_1, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(6)
					.addGroup(gl_ToolsPanel.createParallelGroup(Alignment.LEADING, false)
						.addComponent(btnMove, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(btnResize, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
					.addGap(6)
					.addComponent(separator_4, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(6)
					.addGroup(gl_ToolsPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(btnStroke, GroupLayout.PREFERRED_SIZE, 64, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblStroke, GroupLayout.PREFERRED_SIZE, 64, GroupLayout.PREFERRED_SIZE))
					.addGap(6)
					.addGroup(gl_ToolsPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(btnColor, GroupLayout.PREFERRED_SIZE, 58, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblOuterColor))
					.addGap(10)
					.addGroup(gl_ToolsPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(btnFillColor, GroupLayout.PREFERRED_SIZE, 61, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_1, GroupLayout.PREFERRED_SIZE, 61, GroupLayout.PREFERRED_SIZE))
					.addGap(6)
					.addGroup(gl_ToolsPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_ToolsPanel.createSequentialGroup()
							.addGroup(gl_ToolsPanel.createParallelGroup(Alignment.LEADING)
								.addComponent(btnColorBlack, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnColorWhite, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
							.addGap(6)
							.addGroup(gl_ToolsPanel.createParallelGroup(Alignment.LEADING)
								.addComponent(btnColorGray25, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnColorGray50, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
							.addGap(10)
							.addGroup(gl_ToolsPanel.createParallelGroup(Alignment.LEADING)
								.addComponent(btnColorBrown, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnColorDarkRed, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
							.addGap(6)
							.addGroup(gl_ToolsPanel.createParallelGroup(Alignment.LEADING)
								.addComponent(btnColorRed, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnColorRose, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
							.addGap(6)
							.addGroup(gl_ToolsPanel.createParallelGroup(Alignment.LEADING)
								.addComponent(btnColorOrange, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnColorGold, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
							.addGap(6)
							.addGroup(gl_ToolsPanel.createParallelGroup(Alignment.LEADING)
								.addComponent(btnColorYellow, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnColorLightYellow, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
							.addGap(6)
							.addGroup(gl_ToolsPanel.createParallelGroup(Alignment.LEADING)
								.addComponent(btnColorGreen, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnColorLime, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
							.addGap(6)
							.addGroup(gl_ToolsPanel.createParallelGroup(Alignment.LEADING)
								.addComponent(btnColorTurquoise, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnColorLightTurquoise, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
							.addGap(6)
							.addGroup(gl_ToolsPanel.createParallelGroup(Alignment.LEADING)
								.addComponent(btnColorIndiago, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnColorBlueGray, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
							.addGap(6)
							.addGroup(gl_ToolsPanel.createParallelGroup(Alignment.LEADING)
								.addComponent(btnColorPurple, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnColorLavender, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
							.addGap(6)
							.addGroup(gl_ToolsPanel.createParallelGroup(Alignment.LEADING)
								.addComponent(btnPalette, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)
								.addComponent(lblPalette, GroupLayout.PREFERRED_SIZE, 32, GroupLayout.PREFERRED_SIZE)))
						.addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE, 346, GroupLayout.PREFERRED_SIZE))
					.addGap(6)
					.addComponent(separator_3, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(6)
					.addGroup(gl_ToolsPanel.createParallelGroup(Alignment.LEADING, false)
						.addComponent(lblSnapshot, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
						.addComponent(btnSnapshot, GroupLayout.PREFERRED_SIZE, 52, Short.MAX_VALUE))
					.addGap(10)
					.addComponent(separator_2, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addGap(6)
					.addGroup(gl_ToolsPanel.createParallelGroup(Alignment.LEADING)
						.addComponent(btnDelete, GroupLayout.PREFERRED_SIZE, 52, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblDelete, GroupLayout.PREFERRED_SIZE, 52, GroupLayout.PREFERRED_SIZE)))
		);
		gl_ToolsPanel.setVerticalGroup(
			gl_ToolsPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_ToolsPanel.createSequentialGroup()
					.addGap(11)
					.addGroup(gl_ToolsPanel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_ToolsPanel.createSequentialGroup()
							.addComponent(btnUndo, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
							.addGap(6)
							.addComponent(btnRedo))
						.addGroup(gl_ToolsPanel.createSequentialGroup()
							.addComponent(btnCopy, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
							.addGap(6)
							.addComponent(btnPaste, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE))
						.addComponent(separator_1, GroupLayout.PREFERRED_SIZE, 67, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_ToolsPanel.createSequentialGroup()
							.addComponent(btnMove)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(btnResize, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE))
						.addComponent(separator_4, GroupLayout.PREFERRED_SIZE, 67, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_ToolsPanel.createSequentialGroup()
							.addComponent(btnStroke, GroupLayout.PREFERRED_SIZE, 51, GroupLayout.PREFERRED_SIZE)
							.addGap(2)
							.addComponent(lblStroke))
						.addGroup(gl_ToolsPanel.createSequentialGroup()
							.addComponent(btnColor, GroupLayout.PREFERRED_SIZE, 51, GroupLayout.PREFERRED_SIZE)
							.addGap(2)
							.addComponent(lblOuterColor))
						.addGroup(gl_ToolsPanel.createSequentialGroup()
							.addComponent(btnFillColor, GroupLayout.PREFERRED_SIZE, 51, GroupLayout.PREFERRED_SIZE)
							.addGap(2)
							.addComponent(lblNewLabel_1))
						.addGroup(gl_ToolsPanel.createSequentialGroup()
							.addGroup(gl_ToolsPanel.createParallelGroup(Alignment.LEADING)
								.addGroup(gl_ToolsPanel.createSequentialGroup()
									.addComponent(btnColorBlack, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
									.addGap(7)
									.addComponent(btnColorWhite, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_ToolsPanel.createSequentialGroup()
									.addComponent(btnColorGray25, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
									.addGap(6)
									.addComponent(btnColorGray50, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_ToolsPanel.createSequentialGroup()
									.addComponent(btnColorBrown, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
									.addGap(7)
									.addComponent(btnColorDarkRed, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_ToolsPanel.createSequentialGroup()
									.addComponent(btnColorRed, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
									.addGap(6)
									.addComponent(btnColorRose, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_ToolsPanel.createSequentialGroup()
									.addComponent(btnColorOrange, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
									.addGap(6)
									.addComponent(btnColorGold, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_ToolsPanel.createSequentialGroup()
									.addComponent(btnColorYellow, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
									.addGap(6)
									.addComponent(btnColorLightYellow, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_ToolsPanel.createSequentialGroup()
									.addComponent(btnColorGreen, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
									.addGap(6)
									.addComponent(btnColorLime, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_ToolsPanel.createSequentialGroup()
									.addComponent(btnColorTurquoise, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
									.addGap(6)
									.addComponent(btnColorLightTurquoise, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_ToolsPanel.createSequentialGroup()
									.addComponent(btnColorIndiago, GroupLayout.PREFERRED_SIZE, 22, GroupLayout.PREFERRED_SIZE)
									.addGap(6)
									.addComponent(btnColorBlueGray, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_ToolsPanel.createSequentialGroup()
									.addComponent(btnColorPurple, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE)
									.addGap(5)
									.addComponent(btnColorLavender, GroupLayout.PREFERRED_SIZE, 23, GroupLayout.PREFERRED_SIZE))
								.addGroup(gl_ToolsPanel.createSequentialGroup()
									.addComponent(btnPalette, GroupLayout.PREFERRED_SIZE, 31, GroupLayout.PREFERRED_SIZE)
									.addGap(7)
									.addComponent(lblPalette)))
							.addGap(2)
							.addComponent(lblNewLabel))
						.addComponent(separator_3, GroupLayout.PREFERRED_SIZE, 67, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_ToolsPanel.createSequentialGroup()
							.addComponent(btnSnapshot)
							.addGap(9)
							.addComponent(lblSnapshot))
						.addComponent(separator_2, GroupLayout.PREFERRED_SIZE, 67, GroupLayout.PREFERRED_SIZE)
						.addGroup(gl_ToolsPanel.createSequentialGroup()
							.addComponent(btnDelete, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE)
							.addGap(11)
							.addComponent(lblDelete)))
					.addContainerGap())
		);
		gl_ToolsPanel.setAutoCreateGaps(true);
		gl_ToolsPanel.setAutoCreateContainerGaps(true);
		ToolsPanel.setLayout(gl_ToolsPanel);
		statusPanel.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));

		getContentPane().add(statusPanel, BorderLayout.SOUTH);

		GroupLayout gl_statusPanel = new GroupLayout(statusPanel);
		gl_statusPanel.setHorizontalGroup(gl_statusPanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_statusPanel.createSequentialGroup()
						.addComponent(mouseXlbl, GroupLayout.PREFERRED_SIZE, 55, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.RELATED).addComponent(mouseYlbl)
						.addContainerGap(827, Short.MAX_VALUE)));
		gl_statusPanel.setVerticalGroup(gl_statusPanel.createParallelGroup(Alignment.TRAILING)
				.addGroup(gl_statusPanel.createParallelGroup(Alignment.BASELINE)
						.addComponent(mouseXlbl, GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
						.addComponent(mouseYlbl)));
		statusPanel.setLayout(gl_statusPanel);
		mntmLoad.setAccelerator(KeyStroke.getKeyStroke('O', Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
		mntmUndo.setAccelerator(KeyStroke.getKeyStroke('Z', Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
		mntmRedo.setAccelerator(KeyStroke.getKeyStroke('Y', Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
		mntmCopy.setAccelerator(KeyStroke.getKeyStroke('D', Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));
		mntmPaste.setAccelerator(KeyStroke.getKeyStroke('F', Toolkit.getDefaultToolkit().getMenuShortcutKeyMask()));

		setJMenuBar(menuBar);

		menuBar.add(mnFile);

		mnFile.add(mntmLoad);

		mnFile.add(mnSave);

		mnSave.add(mntmSaveXml);

		mnSave.add(mntmSaveJson);

		mnFile.add(mntmExit);

		menuBar.add(mnEdit);

		mnEdit.add(mntmUndo);

		mnEdit.add(mntmRedo);

		mnEdit.add(mntmCopy);

		mnEdit.add(mntmPaste);
		mntmDelete.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_DELETE, 0));

		mnEdit.add(mntmDelete);

		menuBar.add(mnPlugins);

		mnPlugins.add(mntmAddPlugin);

		JSeparator separator = new JSeparator();
		mnPlugins.add(separator);

		JMenuItem mntmAboutPlugins = new JMenuItem("About Plugins");
		mnPlugins.add(mntmAboutPlugins);
		
		menuBar.add(mnHelp);
		
		mnHelp.add(mntmUserGuide);
		
		mnHelp.add(mntmAbout);
		this.setVisible(true);
	}

	public void addExitListener(ActionListener listenforExit) {
		mntmExit.addActionListener(listenforExit);
	}

	public void addUndoListener(ActionListener listenforUndo) {
		mntmUndo.addActionListener(listenforUndo);
		btnUndo.addActionListener(listenforUndo);
	}

	public void addRedoListener(ActionListener listenforRedo) {
		mntmRedo.addActionListener(listenforRedo);
		btnRedo.addActionListener(listenforRedo);
	}


	public void addDeleteListener(ActionListener listenforDelete) {
		mntmDelete.addActionListener(listenforDelete);
		btnDelete.addActionListener(listenforDelete);
	}

	public void saveXmlListener(ActionListener listenforSaveXml) {
		mntmSaveXml.addActionListener(listenforSaveXml);
	}

	public void saveJsonListener(ActionListener listenforSaveJson) {
		mntmSaveJson.addActionListener(listenforSaveJson);
	}

	public void loadListener(ActionListener listenforLoad) {
		mntmLoad.addActionListener(listenforLoad);
	}

	public void colorListener(ActionListener listenForColor) {
		btnColor.addActionListener(listenForColor);
	}
	
	public void addPaletteListener(ActionListener listenForPallete) {
		btnPalette.addActionListener(listenForPallete);
	}

	public void fillColorListener(ActionListener listenForFillColor) {
		btnFillColor.addActionListener(listenForFillColor);
	}
	public void strokeListener(ActionListener listenForStroke){
		btnStroke.addActionListener(listenForStroke);
	}
	
	public void addColorButtonsListener(ActionListener listenForColorButtons) {
		btnColorBlack.addActionListener(listenForColorButtons);
		btnColorBlueGray.addActionListener(listenForColorButtons);
		btnColorBrown.addActionListener(listenForColorButtons);
		btnColorDarkRed.addActionListener(listenForColorButtons);
		btnColorGold.addActionListener(listenForColorButtons);
		btnColorGray25.addActionListener(listenForColorButtons);
		btnColorGray50.addActionListener(listenForColorButtons);
		btnColorGreen.addActionListener(listenForColorButtons);
		btnColorIndiago.addActionListener(listenForColorButtons);
		btnColorLavender.addActionListener(listenForColorButtons);
		btnColorLightTurquoise.addActionListener(listenForColorButtons);
		btnColorLightYellow.addActionListener(listenForColorButtons);
		btnColorLime.addActionListener(listenForColorButtons);
		btnColorOrange.addActionListener(listenForColorButtons);
		btnColorPurple.addActionListener(listenForColorButtons);
		btnColorRed.addActionListener(listenForColorButtons);
		btnColorTurquoise.addActionListener(listenForColorButtons);
		btnColorWhite.addActionListener(listenForColorButtons);
		btnColorYellow.addActionListener(listenForColorButtons);
		btnColorRose.addActionListener(listenForColorButtons);
	}

	public void copyListener(ActionListener listenForCopy) {
		mntmCopy.addActionListener(listenForCopy);
		btnCopy.addActionListener(listenForCopy);
	}

	public void pasteListener(ActionListener listenForPaste) {
		mntmPaste.addActionListener(listenForPaste);
		btnPaste.addActionListener(listenForPaste);
	}

	public void moveListener(ActionListener listenForMove) {
		btnMove.addActionListener(listenForMove);
	}
	
	public void resizeListener(ActionListener listenForResize) {
		btnResize.addActionListener(listenForResize);
	}

	public void addAddPluginListener(ActionListener listenForAddPlugin) {
		mntmAddPlugin.addActionListener(listenForAddPlugin);
	}

	public void addSnapshotListener(ActionListener listenForSnapshot) {
		btnSnapshot.addActionListener(listenForSnapshot);
	}

	public void addButtonHover(JButton myButton, MouseListener listenForbuttonHover) {
		myButton.addMouseListener(listenForbuttonHover);
	}

	public void disableUndo() {
		this.mntmUndo.setEnabled(false);
		this.btnUndo.setIcon(new ImageIcon(GUI.class.getResource("/assets/noundo.png")));
	}

	public void enableUndo() {
		this.mntmUndo.setEnabled(true);
		this.btnUndo.setIcon(new ImageIcon(GUI.class.getResource("/assets/undo.png")));
	}

	public void disableRedo() {
		this.mntmRedo.setEnabled(false);
		this.btnRedo.setIcon(new ImageIcon(GUI.class.getResource("/assets/noredo.png")));
	}

	public void enableRedo() {
		this.mntmRedo.setEnabled(true);
		this.btnRedo.setIcon(new ImageIcon(GUI.class.getResource("/assets/redo.png")));
	}

	class buttonHover implements MouseListener {

		@Override
		public void mouseEntered(MouseEvent evt) {
			((JButton) evt.getSource()).setBackground(hoverColor);
			((JButton) evt.getSource()).setBorderPainted(true);
		}

		@Override
		public void mouseExited(MouseEvent evt) {
			((JButton) evt.getSource()).setBackground(backgroundColor);
			((JButton) evt.getSource()).setBorderPainted(false);
		}

		@Override
		public void mousePressed(MouseEvent e) {

		}

		@Override
		public void mouseReleased(MouseEvent e) {

		}

		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub

		}
	}
	
	
	private static void addPopup(Component component, final JPopupMenu popup) {
		component.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				if (e.isPopupTrigger()) {
					showMenu(e);
				}
			}
			private void showMenu(MouseEvent e) {
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}
}
