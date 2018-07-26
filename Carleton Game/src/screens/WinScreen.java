package screens;

import gifAnimation.Gif;
import processing.core.PApplet;

public class WinScreen {
	private Gif myAnimation;
	public WinScreen() {

	}

	public void setup(PApplet drawer) {
		myAnimation = new Gif(drawer,
				"executable/sprites" + System.getProperty("file.separator") + "TestTrump.gif");
			myAnimation.play();
	}

	public void draw(PApplet drawer) {
		drawer.image(myAnimation, 0, 0);
	}
}
