package screens;

import gifAnimation.Gif;
import processing.core.PApplet;
import processing.core.PImage;

public class WinScreen {
	private Gif myAnimation;
	private PImage mainMenu;

	public WinScreen() {

	}

	public void setup(PApplet drawer) {
		myAnimation = new Gif(drawer, "executable/sprites" + System.getProperty("file.separator") + "Tyler1.gif");
		myAnimation.play();
		mainMenu = drawer.loadImage("executable/sprites" + System.getProperty("file.separator") + "mainMenu.png");

	}

	public void draw(PApplet drawer) {
		drawer.image(myAnimation, 0, 0);
		drawer.textSize(50);
		drawer.textAlign(drawer.CENTER, drawer.CENTER);
		drawer.fill(0, 255, 0);
		drawer.text("Congrats! You Won!", drawer.width / 2, drawer.height / 2);
		drawer.imageMode(drawer.CENTER);
		drawer.image(mainMenu, drawer.width/2, drawer.height-50);
		drawer.imageMode(drawer.CORNER);

	}
}
