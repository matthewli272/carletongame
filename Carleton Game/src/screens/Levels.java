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

	Coded by: Matthew Li
	Modified on:
*/
public class Levels {

	private ArrayList<Bosses> mobs = new ArrayList<>(30);
	private ArrayList<Bosses> allThings = new ArrayList<>(30);
	private ArrayList<Obstacle> obstacle;
	private PImage pause; 
	private Entity[][] map;
	private Players player1;
	private Players player2;
	private Bullet bullets;
	private Bosses boss;
	private float cellWidth;
	private float cellHeight;
	private int count = 0;
	private Bullet[][] bullet;


	private enum Weapon {
		SWORD, THROWINGSWORD, KNIFE, PISTOL, RIFLE
	};

	private Weapon weapon;
	// Somehow add shields or smt like that

	// Constructs an empty grid
	public Levels(Players player1, Players player2, Bosses boss, ArrayList<Obstacle> obstacle, float width,
			float height) {
		this.player1 = player1;
		this.player2 = player2;
		this.boss = boss;
		this.obstacle = obstacle;
		map = new Entity[31][31];
		for (Obstacle o : obstacle) {
			map[o.getX()][o.getY()] = o;
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
		for(int i = 0; i < 5; i++){
			//System.out.println("wat");
			mobs.add(new Bosses("Reaper",20,i * 4,20,1));
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

	public void update() {

	}

	public void setup(PApplet drawer) {
		// System.out.println("im in setup " + cellHeight);
		boss.setup(drawer, (int) cellHeight);
		for(Bosses mob : mobs)
			mob.setup(drawer,(int) cellHeight);

		//System.out.println(cellHeight + " " + cellWidth);

		pause = drawer.loadImage("executable/sprites" + System.getProperty("file.separator") + "pause.png");
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
		//System.out.println(boss.getHealth());
		for (int i = 0; i < map[0].length - 1; i++) {
			for (int j = 0; j < map.length - 1; j++) {
				drawer.rect(cellWidth * j + x, cellHeight * i + y, cellWidth, cellHeight);
			}
		}
		if (count == 20) {
			count = 0;
			if(boss.getX() - player1.getX() == 0){
				if(boss.getY() - player1.getY() > 0)
			    	boss.shoot(1,drawer);
				else
					boss.shoot(2,drawer);
            }else if(boss.getY() - player1.getY() == 0){
				if(boss.getX() - player1.getX() > 0)
					boss.shoot(3,drawer);
				else
					boss.shoot(4,drawer);
            }else if(boss.getX() - player2.getX() == 0){
				if(boss.getY() - player2.getY() > 0)
					boss.shoot(1,drawer);
				else
					boss.shoot(2,drawer);
            }else if(boss.getY() - player2.getY() == 0){
				if(boss.getX() - player2.getX() > 0)
					boss.shoot(3,drawer);
				else
					boss.shoot(4,drawer);
            }
            /*allThings.remove(boss);
            boss.move(player1.getX(),player1.getY(),player2.getX(),player2.getY(), allThings);
            allThings.add(boss);*/
            for(Bosses mob: mobs) {
            	allThings.remove(mob);
				mob.move(player1.getX(), player1.getY(), player2.getX(), player2.getY(), allThings);
				allThings.add(mob);
			}
/*			if ((boss.getX() - player1.getX()) * (boss.getX() - player1.getX()) + (boss.getY() - player1.getY())
					* (boss.getY() - player1.getY()) < (boss.getX() - player2.getX()) * (boss.getX() - player2.getX())
					+ (boss.getY() - player2.getY()) * (boss.getY() - player2.getY())) {
				// System.out.println("I am in x");
				if (Math.abs((boss.getX() - player1.getX())) > Math.abs((boss.getY() - player1.getY()))) {
					if (boss.getX() > player1.getX()) {
						boss.setX(boss.getX() - Math.round(cellWidth));
					} else {
						//else if (boss.getX() < player1.getX()){
						boss.setX(boss.getX() + Math.round(cellWidth));
					}
				} else {
					if (boss.getY() > player1.getY()) {
						boss.setY(boss.getY() - Math.round(cellHeight));
					} else {
						//if (boss.getY() < player1.getY()){
						boss.setY(boss.getY() + Math.round(cellHeight));
					}
				}
			} else {
				// System.out.println("I am in y");
				if (Math.abs((boss.getX() - player2.getX())) > Math.abs((boss.getY() - player2.getY()))) {
					if (boss.getX() > player2.getX()) {
						boss.setX(boss.getX() - Math.round(cellWidth));
					} else {
						//else if (boss.getX() < player2.getX()){
						boss.setX(boss.getX() + Math.round(cellWidth));
					}
				} else {
					if (boss.getY() > player2.getY()) {
						boss.setY(boss.getY() - Math.round(cellHeight));
					} else {
						//if (boss.getY() < player2.getY()){
						boss.setY(boss.getY() + Math.round(cellHeight));
					}
				}
			}*/

		}

		// for (int i = 0; i < map[0].length; i++) {
		// for (int j = 0; j < map.length; j++) {
		// drawer.rect(cellWidth * j + x, cellHeight * i + y, cellWidth, cellHeight);
		// }
		// }
		drawer.image(pause, 600, 0);
		//boss.draw(drawer);
		for(Bosses mob : allThings) {
			mob.draw(drawer);
			//System.out.println("what");
		}
		for(Bosses thing : allThings){
			int testX = thing.getX()/20;
			int testY = thing.getY()/20;
			if(testX == -1)
				testX = 0;
			if(testY == -1)
				testY = 0;
			map[testX][testY] = thing;
		}

		count++;
	}

	public float getCellWidth() {
		return cellWidth;
	}

	public float getCellHeight() {
		return cellHeight;
	}

	public void takeDmg() {
		Players[] player = { player1, player2 };
		Obstacle[] obs = {};
		Bosses[] b = new Bosses[10];
		for(int i = 0; i < allThings.size();i++){
			b[i] = allThings.get(i);
			//if(allThings.get(i) == boss)
				//System.out.println("hullo");
		}

		ArrayList<Bullet>  player1bullet = player1.getBullets();


		for(Bullet insertBullet : player1bullet){
			int testX = insertBullet.getX()/20;
			int testY = insertBullet.getY()/20;
			if(testX == -1)
				testX = 0;
			if(testY == -1)
				testY = 0;
			bullet[testX][testY] = insertBullet;
		}


		/*Entity e = bullets.collisions(map, bullet, player, b, obs);
		for (Players p : player) {
			if (e == p) {
				p.takeDamage(bullets);
				map[bullets.getX()][bullets.getY()] = null;
			}
		}
		for (Bosses s : b) {
			if (e == s) {
				s.takeDamage(bullets);
				map[bullets.getX()][bullets.getY()] = null;
			}
		}
		for (Obstacle o : obs) {
			if (o == e)
				map[bullets.getX()][bullets.getY()] = null;
		}*/

		for(int i = 0; i < player1bullet.size(); i++) {
			Entity e = player1bullet.get(i).collisions(map, bullet, player, b, obs);
			//System.out.println(e);
			int testX = player1bullet.get(i).getX()/20;
			int testY = player1bullet.get(i).getY()/20;
			if(testX == -1)
				testX = 0;
			if(testY == -1)
				testY = 0;

			if(e != null) {
				for (Players p : player) {
					if (e == p) {
						p.takeDamage(player1bullet.get(i));
						map[testX][testY] = null;
						System.out.println("wat");
					}
				}
				for (Bosses s : b) {
					if (e == s) {
						//System.out.println(e + " ? " + s);
						//System.out.println(s == boss);
						s.takeDamage(player1bullet.get(i));
						map[testX][testY] = null;
						if(s.isDead()) {
							allThings.remove(s);
							if(s.getType() == 1)
								mobs.remove(s);
							//System.out.println("?");
						}
						//System.out.println("the");
					}
				}
				for (Obstacle o : obs) {
					if (o == e) {
						map[testX][testY] = null;
						System.out.println("fuck");
					}

				}
			}
		}
	}
	
	/**
	 * Optionally, complete this method to toggle a cell in the game of life grid
	 * between alive and dead.
	 * 
	 * @param i
	 *            The x coordinate of the cell in the grid.
	 * @param j
	 *            The y coordinate of the cell in the grid.
	 */
	public void toggleCell(int i, int j) {
		// grid[i][j] = !grid[i][j];
	}

}