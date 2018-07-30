package screens;

import processing.core.PApplet;
import processing.core.PImage;

public class PauseMenu {
	private PImage mainMenu;
	private PImage instructions;
	private PImage back;
	public PauseMenu() {

	}

	public void setup(PApplet drawer) {
		drawer.background(0);
		mainMenu = drawer.loadImage("executable/sprites" + System.getProperty("file.separator") + "mainMenu.png");
		instructions = drawer.loadImage("executable/sprites" + System.getProperty("file.separator") + "Instructions.png");
		back = drawer.loadImage("executable/sprites" + System.getProperty("file.separator") + "arrow.png");
	}

	public void draw(PApplet drawer) {
		drawer.textSize(90);
		drawer.fill(0);
		drawer.textAlign(drawer.CENTER);
		drawer.text("Take a Break!" ,drawer.width/2, drawer.height/2-200);
		
		drawer.imageMode(drawer.CENTER);
		drawer.image(mainMenu, drawer.width / 2, drawer.height - 70);
		drawer.image(instructions, drawer.width / 2, drawer.height-200);
		drawer.image(back, drawer.width / 2, drawer.height-380);
		drawer.imageMode(drawer.CORNER);
	}
}
