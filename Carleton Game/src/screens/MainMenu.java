package screens;

import java.lang.Math;
import gifAnimation.*;
import java.awt.Image;

import processing.core.PApplet;
import processing.core.PImage;

public class MainMenu {
	Gif myAnimation;
	PImage startButton;

	private int frames, totalFrames;

	public MainMenu() {
		frames = 0;
		totalFrames = 120;

	}
	public void settings() {
		//size(400,400);
	}
	public void setup(PApplet drawer) {
		drawer.smooth();
		myAnimation = new Gif(drawer,
				"executable/sprites" + System.getProperty("file.separator") + "nidhogg_blood.gif");
		myAnimation.play();

		startButton = drawer.loadImage("executable/sprites" + System.getProperty("file.separator") + "start_button.png");
		// gifExport = new GifMaker(drawer,
		// "executable/sprites" + System.getProperty("file.separator") +
		// "nidhogg_blood.gif", 100);
		// gifExport.setRepeat(0); // make it an "endless" animation
		//
		// drawer.noFill();
		// drawer.stroke(0);
		// drawer.strokeWeight(20);


	}

	public void draw(PApplet drawer) {
		drawer.background(255);
		drawer.image(myAnimation, 0, 0);

		drawer.image(startButton,75,200);

	}


}



