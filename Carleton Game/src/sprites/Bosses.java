package sprites;

import java.lang.*;
import gifAnimation.*;
import processing.core.PApplet;
import processing.core.PImage;

public class Bosses implements Entity {

	// fields
	//Gif myAnimation;
	private int bossHealth;
	private Weapons currentWeapon;
	private String name;
	private int bossX;
	private int bossY;
	//private int bossSize;
	private PImage Boss;


	// constructor
	public Bosses(String name, int bossHealth, int bossX, int bossY) {
		this.name = name;
		this.bossHealth = bossHealth;
		this.bossX = bossX;
		this.bossY = bossY;
		//this.bossSize = bossSize;

	}

	@Override
	public int getX() {
		// TODO Auto-generated method stub
		return bossX;
	}

	@Override
	public int getY() {
		return bossY;
	}

	@Override
	public void setX(int x) {
		// TODO Auto-generated method stub
		this.bossX = x;
	}

	@Override
	public void setY(int y) {
		// TODO Auto-generated method stub
		this.bossY = y;
	}

	// I think this works bc it only checks once square next to it?
	/**
	 * 
	 * @param player
	 *            array that stores players in game
	 * @param map
	 *            of the grid
	 * @return the direction that the boss should shoot: 0 if no shooting, 1 for
	 *         left, 2 for up, 3 for right, 4 for down
	 */
	public int shootDir(Players[] player, Entity map[][]) {
		int count = 0;
		for (Players z : player) {
			int startIndexX = bossX - 1, startIndexY = bossY - 1;
			int endIndexX = bossX + 1, endIndexY = bossY + 1;
			if (bossX - 1 < 0) {
				startIndexX = bossX;
				// System.out.println("first, " + startIndexX);
			}
			if (bossX + 1 > map.length - 1) {
				endIndexX = bossX;
				// System.out.println("second, " + endIndexX);
			}
			if (bossY - 1 < 0) {
				startIndexY = bossY;
				// System.out.println("third, " + startIndexY);
			}
			if (bossY + 1 > map[0].length - 1) {
				endIndexY = bossY;
				// System.out.println("fourth, " + endIndexY);
			}
			System.out.println("startIndexX, " + startIndexX);
			System.out.println("endIndexX, " + endIndexX);
			System.out.println("startIndexY, " + startIndexY);
			System.out.println("endIndexY, " + endIndexY);


			for (int k = startIndexX; k <= endIndexX; k++) {
				for (int l = startIndexY; l <= endIndexY; l++) {
					if (map[k][l] == z) {
						
						direction(k, l, map);
					}
				}
			}
		}
		return 0;
	}

	/**
	 * 
	 * @param x
	 *            pos of player
	 * @param y
	 *            pos of player
	 * @param map
	 *            of the grid
	 * @return direction boss should shoot
	 * 
	 *         only is called if the the player is within range of the boss
	 */
	private int direction(int x, int y, Entity[][] map) {
		if (bossY - y > 0) {
			return 2;
		} else if (bossY - y < 0)
			return 4;
		else if (bossX - x > 0)
			return 1;
		else
			return 3;
	}

	public void setup(PApplet drawer,int bossSize) {
		//System.out.println("im in bosses");
		drawer.smooth();
		Boss = drawer.loadImage("executable/sprites" + System.getProperty("file.separator") + "unturned.jpg");
		Boss.resize(0,bossSize);
//		myAnimation = new Gif(drawer,
//				"executable/sprites" + System.getProperty("file.separator") + "TestTrump.gif");
//		myAnimation.resize(0,bossSize);
//		myAnimation.play();
	}
	//eventually determine damage taken by bullet type
	public void takeDamage(Bullet b) {
		bossHealth -= 15;
	}
	public void draw(PApplet drawer) {

		drawer.image(Boss, bossX, bossY);
		//System.out.println(bossX + " " + bossY);
		//drawer.image(myAnimation, bossX, bossY);
		drawer.fill(255,0,0);
		drawer.rect(bossX - 2, bossY - 4, bossHealth / 2, 2);
		drawer.fill(237, 24, 245);
		drawer.textSize(10);
		drawer.text(name, bossX, bossY);

	}




}
