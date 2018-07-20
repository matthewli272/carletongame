package sprites;
import processing.core.PApplet;
import processing.core.PImage;

public class Obstacle implements Entity {
	// fields
	private int x, y;
	private PImage img;
	// constructor
	public Obstacle(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	@Override
	public void setX(int x) {
		// TODO Auto-generated method stub
		this.x = x;
	}

	@Override
	public void setY(int y) {
		// TODO Auto-generated method stub
		this.y = y;
	}

	public void setup(PApplet drawer) {
		img = drawer.loadImage("executable/sprites" + System.getProperty("file.separator") + "obstacle.png");
	}

	public void draw(PApplet drawer) {
		drawer.image(img,x,y);
	}

}
