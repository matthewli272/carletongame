


import javax.swing.*;
import javax.swing.JFrame;

import processing.awt.PSurfaceAWT;
import processing.core.PApplet;

import java.awt.*;

/**
 * 
 * @author Matthew Li, Jeffrey Chi
 *
 */
public class Main extends JFrame {
 
	public static void main(String args[]) {
		DrawingSurface drawing = new DrawingSurface();

		PSurfaceAWT surf = (PSurfaceAWT) drawing.getSurface();
		PSurfaceAWT.SmoothCanvas canvas = (PSurfaceAWT.SmoothCanvas) surf.getNative();
		JFrame window = (JFrame) canvas.getFrame();
		surf.setResizable(false);
		window.setTitle("SunBeam");


		window.setSize(620,700);
		window.setMinimumSize(new Dimension(100, 100));
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(true);

		window.setVisible(true);



	}



}

