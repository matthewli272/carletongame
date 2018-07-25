package screens;

import gifAnimation.Gif;
import processing.core.PApplet;
import processing.core.PImage;

public class LoseScreen {
	private Gif trump;
	public LoseScreen() {
		
	}
	public void setup(PApplet drawer) {
		trump = new Gif(drawer,
			"executable/sprites" + System.getProperty("file.separator") + "TestTrump.gif");
		trump.play();

	}
	public void draw(PApplet drawer) {
		
		drawer.image(trump, 0, 0);
		drawer.text("YOU'RE FIRED", 100, 100);
	}
}
