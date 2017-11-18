package paint.eg.edu.alexu.csd.oop.draw.cs62_67.view;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class About extends JFrame {

	public About() {
		setResizable(false);
		setTitle("About");
		setSize(400, 410);
		setBackground(new Color(245, 246, 247));
		JLabel lblLogo = new JLabel("");
		lblLogo.setHorizontalAlignment(SwingConstants.CENTER);
		lblLogo.setIcon(new ImageIcon(About.class.getResource("/assets/logoAbout2.png")));
		getContentPane().add(lblLogo, BorderLayout.NORTH);

		JLabel lblNewLabel = new JLabel("<html>"
        + "<h3> &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;A Vector Based Drawing Application</h3>"
        + "<p>  &nbsp; &nbsp;allows the user to draw different shapes and manipulate them, It<br> &nbsp;   &nbsp;supports many features such ​ ​as ​ ​save, ​ ​load, ​ ​undo, ​ ​redo, ​ ​move, ​ <br>  &nbsp; &nbsp;​resize ​ ​and ​ ​delete ​ ​shapes.</p><br><p> &nbsp; &nbsp;All Credits goes to Mostafa Labib, Mahmoud Tarek.<p></html>");
		lblNewLabel.setVerticalAlignment(SwingConstants.TOP);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		getContentPane().add(lblNewLabel, BorderLayout.CENTER);
		setLocation(300, 100);
		setVisible(true);


	}

}
