package screens;

import java.awt.Point;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import processing.core.PApplet;
import processing.core.PImage;
import sprites.*;

/*
	Represents a level in the game

	Coded by: Matthew Li, Jeffrey Chi
	Modified on:
*/
public class Levels {



	private ArrayList<Bosses> mobs = new ArrayList<>(30);
	private ArrayList<Bosses> allThings = new ArrayList<>(30);
	private ArrayList<Obstacle> obstacle;
	private PImage pause;
	private PImage floorTile;
	private Entity[][] map;
	private Players player1;
	private Players player2;
	private Bullet bullets;
	private Bosses boss;
	private float cellWidth;
	private float cellHeight;
	private int count = 0;
	private Bullet[][] bullet;
	private ArrayList<Integer> counter;
	private Players[] allP = new Players[2];

	private enum Weapon {
		SWORD, THROWINGSWORD, KNIFE, PISTOL, RIFLE
	};

	private Weapon weapon;
	// Somehow add shields or smt like that

	// Constructs an empty grid
	public Levels(Players player1, Players player2, Bosses boss, ArrayList<Obstacle> obstacle, float width,
			float height) {
		counter = new ArrayList<Integer>();
		this.player1 = player1;
		this.player2 = player2;
		allP[0] = player1;
		allP[1] = player2;
		this.boss = boss;
		this.obstacle = obstacle;
		map = new Entity[31][31];
		for (Obstacle o : obstacle) {
			int i = o.getX() / 20;
			int j = o.getY() / 20;
			if (i < 0)
				i = 0;
			if (j < 0)
				j = 0;
			if (map[i][j] != null) {
				counter.add(obstacle.indexOf(o));
				continue;
			}
			map[i][j] = o;
		}
		for (Integer i : counter) {
			obstacle.set(i, null);
		}
		weapon = Weapon.SWORD;
		map[player1.getX()][player1.getY()] = player1;
		map[player2.getX()][player2.getY()] = player2;
		map[boss.getX()][boss.getY()] = boss;
		bullets = new Bullet(0, 0, "pistol");
		bullet = new Bullet[31][31];
		cellWidth = width / (map[0].length - 1);
		cellHeight = height / (map.length - 1);
		// System.out.println("im in constructor " + (int) cellHeight);
		allThings.add(this.boss);
		for (int i = 0; i < 5; i++) {
			// System.out.println("wat");
			mobs.add(new Bosses("Reaper", 20, i * 4, 20, 1));
		}
		allThings.addAll(mobs);


	}

	public Bosses getBoss() {
		return boss;
	}

	public Players getPlayer1() {
		return player1;
	}

	public Players getPlayer2() {
		return player2;
	}

	public ArrayList<Obstacle> getObstacle() {
		return obstacle;
	}

	public void update() {

	}

	public void setup(PApplet drawer) {
		// System.out.println("im in setup " + cellHeight);
		boss.setup(drawer, (int) cellHeight);
		for (Bosses mob : mobs)
			mob.setup(drawer, (int) cellHeight);
		for (Obstacle o : obstacle) {
			if (o != null)
				o.setup(drawer);
		}

		pause = drawer.loadImage("executable/sprites" + System.getProperty("file.separator") + "pause.png");
		floorTile = drawer.loadImage("executable/sprites" + System.getProperty("file.separator") + "floor_tile.jpg");
		floorTile.resize(0,20);

	}

	/**
	 * Optionally, complete this method to draw the grid on a PApplet.
	 * 
	 * @param //marker
	 *            The PApplet used for drawing.
	 * @param x
	 *            The x pixel coordinate of the upper left corner of the grid
	 *            drawing.
	 * @param y
	 *            The y pixel coordinate of the upper left corner of the grid
	 *            drawing.
	 * @param //width
	 *            The pixel width of the grid drawing.
	 * @param //height
	 *            The pixel height of the grid drawing.
	 */
	public void draw(PApplet drawer, float x, float y/* , float width, float height */) {
		/*
		 * cellWidth = width / map[0].length; cellHeight = height / map.length;
		 */
		for (int i = 0; i < map[0].length - 1; i++) {
			for (int j = 0; j < map.length - 1; j++) {
				//drawer.rect(cellWidth * j + x, cellHeight * i + y, cellWidth, cellHeight);
				if((Math.abs((int) (cellWidth * j + x - player1.getX())) > 60 || Math.abs((int) (cellWidth * i + y - player1.getY())) > 60)
						&&(Math.abs((int) (cellWidth * j + x - player2.getX())) > 60 || Math.abs((int) (cellWidth * i + y - player2.getY())) > 60)) {
					drawer.fill(0,0,0);
					drawer.rect(cellWidth * j + x, cellHeight * i + y, cellWidth, cellHeight);
					drawer.fill(255,255,255);
				}else {
					/*if((Math.abs((int) (cellWidth * j + x - player1.getX())) == 60 &&Math.abs((int) (cellWidth * i + y - player1.getY())) != 0)){
						drawer.fill(0,0,0);
						drawer.rect(cellWidth * j + x, cellHeight * i + y, cellWidth, cellHeight);
						drawer.fill(255,255,255);
					}else if((Math.abs((int) (cellWidth * i + y - player1.getY())) == 60 &&Math.abs((int) (cellWidth * j + x - player1.getY())) != 0)){
						drawer.fill(0,0,0);
						drawer.rect(cellWidth * j + x, cellHeight * i + y, cellWidth, cellHeight);
						drawer.fill(255,255,255);
					}else*/
						drawer.image(floorTile, cellWidth * j + x, cellHeight * i + y);
				}
			}
		}
		if (count == 20) {
			count = 0;
			if (boss.getX() - player1.getX() == 0) {
				if (boss.getY() - player1.getY() > 0)
					boss.shoot(1, drawer);
				else
					boss.shoot(2, drawer);
			} else if (boss.getY() - player1.getY() == 0) {
				if (boss.getX() - player1.getX() > 0)
					boss.shoot(3, drawer);
				else
					boss.shoot(4, drawer);
			} else if (boss.getX() - player2.getX() == 0) {
				if (boss.getY() - player2.getY() > 0)
					boss.shoot(1, drawer);
				else
					boss.shoot(2, drawer);
			} else if (boss.getY() - player2.getY() == 0) {
				if (boss.getX() - player2.getX() > 0)
					boss.shoot(3, drawer);
				else
					boss.shoot(4, drawer);
			}
			for (int i = 0; i < allThings.size(); i++) {
				Bosses mob = allThings.get(i);
				allThings.remove(mob);
				mob.move(player1.getX(), player1.getY(), player2.getX(), player2.getY(), allThings, player1, player2);
				allThings.add(mob);
			}
		}
		drawer.image(pause, 600, 0);
		for (Bosses mob : allThings) {
			mob.draw(drawer);
		}
		for (Obstacle o : obstacle) {
			if (o != null)
				o.draw(drawer);
		}
		for (Bosses thing : allThings) {
			int testX = thing.getX() / 20;
			int testY = thing.getY() / 20;
			if (testX == -1)
				testX = 0;
			if (testY == -1)
				testY = 0;
			map[testX][testY] = thing;
		}
		for (Players player : allP){
			int testX = player.getX() / 20;
			int testY = player.getY() / 20;
			if (testX == -1)
				testX = 0;
			if (testY == -1)
				testY = 0;
			map[testX][testY] = player;
		}
		count++;
		int counter = 10000;
		for (Obstacle o : obstacle) {
			if (o != null) {
				if (boss.getX() == o.getX() && boss.getY() == o.getY()) {
					counter = obstacle.indexOf(o);
					break;
				}
			}
		}
		if (counter != 10000)
			obstacle.remove(counter);
	}

	public float getCellWidth() {
		return cellWidth;
	}

	public float getCellHeight() {
		return cellHeight;
	}

	public void takeDmg() {
		Players[] player = { player1, player2 };
		Obstacle[] obs = new Obstacle[110];
		Bosses[] b = new Bosses[10];
		for (int i = 0; i < allThings.size(); i++) {
			b[i] = allThings.get(i);
		}
		for (int i = 0; i < obstacle.size(); i++) {
			obs[i] = obstacle.get(i);
		}
		ArrayList<Bullet> playerBullets = player1.getBullets();
		playerBullets.addAll(player2.getBullets());
		for (Bullet insertBullet : playerBullets) {
			int testX = insertBullet.getX() / 20;
			int testY = insertBullet.getY() / 20;
			if (testX == -1)
				testX = 0;
			if (testY == -1)
				testY = 0;
			bullet[testX][testY] = insertBullet;
		}
		for (int i = 0; i < playerBullets.size(); i++) {
			Entity e = playerBullets.get(i).collisions(map, bullet, player, b, obs);
			int testX = playerBullets.get(i).getX() / 20;
			int testY = playerBullets.get(i).getY() / 20;
			if (testX == -1)
				testX = 0;
			if (testY == -1)
				testY = 0;
			if (e != null) {
	/*			for (Players p : player) {
					if (e == p) {
						p.takeDamage(playerBullets.get(i));
						map[testX][testY] = null;
					}
				}
	*/			for (Bosses s : b) {
					if (e == s) {
						s.takeDamage(playerBullets.get(i));
						map[testX][testY] = null;
						if (s.isDead()) {
							allThings.remove(s);
							if (s.getType() == 1)
								mobs.remove(s);
						}
					}
				}
				for (Obstacle o : obs) {
					if (o == e) {
						map[testX][testY] = null;
					}
				}
			}
		}
		ArrayList<Bullet> bossBullets = boss.getBullets();
		for (Bullet insertBullet : bossBullets) {
			int testX = insertBullet.getX() / 20;
			int testY = insertBullet.getY() / 20;
			if (testX == -1)
				testX = 0;
			if (testY == -1)
				testY = 0;
			bullet[testX][testY] = insertBullet;
		}

		for (int i = 0; i < bossBullets.size(); i++) {
			Entity e = bossBullets.get(i).collisions(map, bullet, player, b, obs);
			int testX = bossBullets.get(i).getX() / 20;
			int testY = bossBullets.get(i).getY() / 20;
			if (testX == -1)
				testX = 0;
			if (testY == -1)
				testY = 0;
			if (e != null) {
				for (Players p : player) {
					if (e == p) {
						p.takeDamage(/*bossBullets.get(i)*/);
						map[testX][testY] = null;
					}
				}
			}

		}

	}
}