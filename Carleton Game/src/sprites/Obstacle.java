package sprites;
import processing.core.PApplet;
import processing.core.PImage;

public class Obstacle implements Entity {
	// fields
	private int xIndex, yIndex;
	private int x, y;
	private PImage img;
	// constructor
	public Obstacle(int xIndex, int yIndex, int x, int y) {
		this.x = x;
		this.y = y;
		this.xIndex = xIndex;
		this.yIndex = yIndex;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}



	@Override
	public void setX(int x) {
		this.x = x;
	}

	@Override
	public void setY(int y) {
		this.y = y;
	}

	public void setup(PApplet drawer) {
		img = drawer.loadImage("executable/sprites" + System.getProperty("file.separator") + "obstacle.png");
		img.resize(0,20);
	}

	public void draw(PApplet drawer) {
		drawer.image(img,x,y);
		//System.out.println("IN DRAW METHOD");

	}

}
