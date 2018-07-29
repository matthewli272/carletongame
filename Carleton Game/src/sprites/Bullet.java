package sprites;

import processing.core.PApplet;
import processing.core.PImage;

public class Bullet implements Entity {
	// fields
	private int x, y;
	private String type;
	private PImage basicBullet;
	private int counter;

	private Direction direction;

	// constructor
	public Bullet(int x, int y, String type, Direction direction, PApplet drawer) {
		this.x = x;
		this.y = y;
		this.type = type;
		this.direction = direction;
		counter = 0;

		basicBullet = drawer.loadImage("executable/sprites" + System.getProperty("file.separator") + "smallcircle.png");
		basicBullet.resize(0, 20);
	}

	public Bullet(int x, int y, String type) {
		this.x = x;
		this.y = y;
		this.type = type;
	}

	@Override
	public int getX() {
		// TODO Auto-generated method stub
		return x;
	}

	@Override
	public int getY() {
		// TODO Auto-generated method stub
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

	public void move() {
		if (counter % 2 == 0) {
			if (direction == Direction.LEFT) {
				x -= 20;
			} else if (direction == Direction.UP) {
				y -= 20;
			} else if (direction == Direction.RIGHT) {
				x += 20;
			} else {
				y += 20;
			}
		}
		counter++;
	}

	public Entity collisions(Entity map[][], Bullet[][] bullets, Players[] player, Bosses[] boss,
			Obstacle[] obstacles) {
		int testX = x/20;
		int testY = y/20;
		if(testX == -1)
			testX = 0;
		if(testY == -1)
			testY = 0;
		//System.out.println(testX + "," + testY);
		for (Players p : player) {
			//System.out.println("wat");
			//System.out.println(map[testX][testY] + "," + bullets[testX][testY]);
			if (map[testX][testY] == p && bullets[testX][testY] != null)
				return p;
		}

		for (Bosses b : boss) {
			//System.out.println("the");
			//System.out.println(map[testX][testY] + "," + bullets[testX][testY]);
			//System.out.println(map[testX][testY] + "," + b);
			if (map[testX][testY] == b && bullets[testX][testY] != null) {

				return b;
			}
		}
		for (Obstacle o : obstacles) {
			//System.out.println("fak");
			if (map[testX][testY] == o && bullets[testX][testY] != null)
				return o;
		}
		return null;
	}

	/*
	 * public void setup(PApplet drawer) { basicBullet =
	 * drawer.loadImage("executable/sprites" + System.getProperty("file.separator")
	 * + "smallcircle.png"); }
	 */
	public void draw(PApplet drawer) {
		drawer.image(basicBullet, x, y);
		move();
	}
}
