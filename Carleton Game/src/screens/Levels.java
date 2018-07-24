package screens;

import java.awt.Point;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import processing.core.PApplet;

import sprites.*;

/*

	Represents a level in the game

	Coded by: Matthew Li
	Modified on:

*/

public class Levels {
	private ArrayList<Obstacle> obstacle;
	// private boolean[][] grid;
	protected Entity[][] map;
	private Players player1;
	private Players player2;
	private Bullet bullets;
	private Bosses boss;
	private float cellWidth;
	private float cellHeight;
	private int count = 0;

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
		map = new Entity[30][30];
		for (Obstacle o : obstacle) {
			map[o.getX()][o.getY()] = o;
		}
		weapon = Weapon.SWORD;
		map[player1.getX()][player1.getY()] = player1;
		map[player2.getX()][player2.getY()] = player2;
		map[boss.getX()][boss.getY()] = boss;
		bullets = new Bullet(0, 0, "pistol");

		cellWidth = width / map[0].length;
		cellHeight = height / map.length;
		// System.out.println("im in constructor " + (int) cellHeight);

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

		for (int i = 0; i < map[0].length; i++) {
			for (int j = 0; j < map.length; j++) {
				drawer.rect(cellWidth * j + x, cellHeight * i + y, cellWidth, cellHeight);
			}
		}
		if (count == 20) {
			count = 0;
			if(boss.getX() - player1.getX() == 0){
			    //boss.shoot(map parameters)
            }else if(boss.getY() - player1.getY() == 0){
                //
            }else if(boss.getX() - player2.getX() == 0){

            }else if(boss.getY() - player2.getY() == 0){

            }else {
                if ((boss.getX() - player1.getX()) * (boss.getX() - player1.getX()) + (boss.getY() - player1.getY())
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
                }
            }
		}

		// for (int i = 0; i < map[0].length; i++) {
		// for (int j = 0; j < map.length; j++) {
		// drawer.rect(cellWidth * j + x, cellHeight * i + y, cellWidth, cellHeight);
		// }
		// }

		boss.draw(drawer);
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
		Bosses[] b = { boss };
		Entity e = bullets.collisions(map, player, b, obs);
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