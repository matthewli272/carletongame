package screens;

import processing.core.PApplet;
import processing.core.PImage;

public class Instructions {
	private PImage back;
	public Instructions() {
		
	}
	
	public void setup(PApplet drawer) {
		back = drawer.loadImage("executable/sprites" + System.getProperty("file.separator") + "arrow.png");

	}
	
	public void draw(PApplet drawer) {
		drawer.textSize(15);
		drawer.textAlign(drawer.CENTER,drawer.CENTER);
		drawer.fill(0);
		drawer.text("Welcome to 'Insert game name here'. Player 1 use wasd to move and 'c' to shoot.\n "
				+ "Player 2 use arrow keys to move and shift to shoot. Kill all bosses to beat the\n "
				+ "level and move on to the next one. There are differnt types of weapons that may\n "
				+ "be acquired by beating bosses. Good Luck!", drawer.width/2, drawer.height/2);
		
		drawer.imageMode(drawer.CENTER);
		drawer.image(back, drawer.width / 2, drawer.height - 100);
		drawer.imageMode(drawer.CORNER);

	}
}
