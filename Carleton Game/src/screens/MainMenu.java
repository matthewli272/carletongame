package screens;

import java.lang.Math;
import gifAnimation.*;
import java.awt.Image;

//import processing.
import processing.core.PApplet;
import processing.core.PImage;

public class MainMenu {
	Gif myAnimation;
	PImage startButton,sunbeam,instructions;

	private int frames, totalFrames;

	public MainMenu() {
		frames = 0;
		totalFrames = 120;

	}
	public void settings() {
	
	}
	public void setup(PApplet drawer) {
		drawer.smooth();
		myAnimation = new Gif(drawer,
				"executable/sprites" + System.getProperty("file.separator") + "nidhogg_blood.gif");
		myAnimation.play();

		startButton = drawer.loadImage("executable/sprites" + System.getProperty("file.separator") + "start_button.png");
		sunbeam = drawer.loadImage("executable/sprites" + System.getProperty("file.separator") + "SunBeam.png");
		instructions = drawer.loadImage("executable/sprites" + System.getProperty("file.separator") + "Instructions.png");



	}

	public void draw(PApplet drawer) {
		drawer.background(255);
		drawer.image(myAnimation, 0, 0);
		drawer.imageMode(drawer.CENTER);
		drawer.image(instructions, drawer.width/2, drawer.height/2+85);
		drawer.image(sunbeam, drawer.width/2, drawer.height/6);
		drawer.imageMode(drawer.CORNER);
		drawer.image(startButton,75,200);

	}


}