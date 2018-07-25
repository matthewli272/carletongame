package screens;

import gifAnimation.Gif;
import processing.core.PApplet;
import processing.core.PImage;

public class LoseScreen {
	private Gif trump;
	private PImage mainMenu;
	public LoseScreen() {
		
	}
	public void setup(PApplet drawer) {
		trump = new Gif(drawer,
			"executable/sprites" + System.getProperty("file.separator") + "TestTrump.gif");
		trump.play();
		mainMenu = drawer.loadImage("executable/sprites" + System.getProperty("file.separator") + "mainMenu.png");

	}
	public void draw(PApplet drawer) {
		drawer.image(trump, 0, 0);
		drawer.imageMode(drawer.CENTER);
		drawer.image(mainMenu, drawer.width/2, drawer.height-50);
		drawer.imageMode(drawer.CORNER);
		drawer.textSize(50);
		drawer.textAlign(drawer.CENTER,drawer.CENTER);
		drawer.fill(0,255,0);
		drawer.text("YOU'RE FIRED!", drawer.width/2,drawer.height/2);
	}
}
