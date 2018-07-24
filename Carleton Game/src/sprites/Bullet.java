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
	public Bullet(int x, int y, String type, Direction direction) {
		this.x = x;
		this.y = y;
		this.type = type;
		this.direction = direction;
		counter = 0;
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
		if (counter % 3 == 0) {
			if (direction == Direction.LEFT) {
				x--;
			} else if (direction == Direction.UP) {
				y--;
			} else if (direction == Direction.RIGHT) {
				x++;
			} else {
				y++;
			}
		}
		counter++;
	}

	public Entity collisions(Entity[][] map, Players[] player, Bosses[] boss, Obstacle[] obstacles) {
		for (Players p : player) {
			if (map[x][y] == p)
				return p;
		}
		for (Bosses b : boss) {
			if (map[x][y] == b) {
				return b;
			}
		}
		for (Obstacle o : obstacles) {
			if (map[x][y] == o)
				return o;
		}
		return null;
	}

	public void setup(PApplet drawer) {
		basicBullet = drawer.loadImage("executable/sprites" + System.getProperty("file.separator") + "smallcircle.jpg");
	}

	public void draw(PApplet drawer) {
		drawer.image(basicBullet, x, y);
	}
}
