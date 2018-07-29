package sprites;

import processing.core.PApplet;
import processing.core.PImage;

public class Sword {
	private PImage sword;
	private boolean thrown;
	private int x, y;
	private int counter;

	public Sword(int x, int y) {
		thrown = false;
		this.x = x;
		this.y = y;
		counter = 0;
	}

	public void setup(PApplet drawer) {
		sword = drawer.loadImage("executable/sprites" + System.getProperty("file.separator") + ("sword.png"));
	}

	public void draw(PApplet drawer) {
		drawer.image(sword, x, y);
	}

	public boolean thrown() {
		if (thrown)
			return true;
		else
			return false;
	}

	/**
	 * Only moves after thrown
	 */
	public void move(int x, int y, Direction direction) {
		if (thrown) {
			if (counter % 3 == 0) {
				if (direction == Direction.LEFT) {
					this.x -= 20;
				} else if (direction == Direction.UP) {
					this.y -= 20;
				} else if (direction == Direction.RIGHT) {
					this.x += 20;
				} else {
					this.y += 20;
				}
			}
			counter++;
		} else {
			this.x = x;
			this.y = y;
		}
	}
}
