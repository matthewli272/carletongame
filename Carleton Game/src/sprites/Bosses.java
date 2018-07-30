package sprites;

import java.lang.*;
import java.util.ArrayList;

import gifAnimation.*;
import processing.core.PApplet;
import processing.core.PImage;

public class Bosses implements Entity {

	// fields
	//Gif myAnimation;
	private int bossHealth;
	private ArrayList<Bullet>bossBullets = new ArrayList<>(10);
	private Weapons currentWeapon;
	private String name;
	private int bossX;
	private int bossY;
	//private int bossSize;
	private PImage Boss;
	private Direction direction;
	private int type;
	private String typeName;
	private int cellHeight;


	// constructor
	public Bosses(String name, int bossHealth, int bossX, int bossY, int type) {
		this.name = name;
		this.bossHealth = bossHealth;
		this.bossX = bossX;
		this.bossY = bossY;
		//this.bossSize = bossSize;
		this.type = type;

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

	public int getType(){
		return type;
	}

	public void addBullets(Bullet bullet){
		bossBullets.add(bullet);
	}
	public ArrayList<Bullet> getBullets(){
		return bossBullets;
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

	public void move(int player1X, int player1Y, int player2X, int player2Y, ArrayList<Bosses> mobs){
		if ((bossX - player1X) * (bossX - player1X) + (bossY - player1Y)
				* (bossY - player1Y) < (bossX - player2X) * (bossX - player2X)
				+ (bossY - player2Y) * (bossY - player2Y)) {
			if (Math.abs((bossX - player1X)) > Math.abs((bossY - player1Y))) {
				if (bossX > player1X) {
					bossX = (bossX - Math.round(cellHeight));
					for(int i = 0; i < mobs.size();i++)
						if((bossX == mobs.get(i).getX()) && bossY == mobs.get(i).getY()) {
							bossX = bossX + Math.round(cellHeight);
							break;
						}
				} else {
					bossX = (bossX + Math.round(cellHeight));
					for(int i = 0; i < mobs.size();i++)
						if((bossX == mobs.get(i).getX()) && bossY == mobs.get(i).getY()) {
							bossX = bossX - Math.round(cellHeight);
							break;
						}
				}
			} else {
				if (bossY > player1Y) {
					bossY = (bossY - Math.round(cellHeight));
					for(int i = 0; i < mobs.size();i++)
						if((bossY == mobs.get(i).getY()) && bossX == mobs.get(i).getX()) {
							bossY = bossY + Math.round(cellHeight);
							break;
						}
				} else {
					bossY = (bossY + Math.round(cellHeight));
					for(int i = 0; i < mobs.size();i++)
						if((bossY == mobs.get(i).getY()) && bossX == mobs.get(i).getX()) {
							bossY = bossY - Math.round(cellHeight);
							break;
						}
				}
			}
		} else {
			if (Math.abs((bossX - player2X)) > Math.abs((bossY - player2Y))) {
				if (bossX > player2X) {
					bossX = (bossX - Math.round(cellHeight));
					for(int i = 0; i < mobs.size();i++)
						if((bossX == mobs.get(i).getX()) && bossY == mobs.get(i).getY()) {
							bossX = bossX + Math.round(cellHeight);
							break;
						}
				} else {
					bossX = (bossX + Math.round(cellHeight));
					for(int i = 0; i < mobs.size();i++)
						if((bossX == mobs.get(i).getX()) && bossY == mobs.get(i).getY()) {
							bossX = bossX - Math.round(cellHeight);
							break;
						}
				}
			} else {
				if (bossY > player2Y) {
					bossY = (bossY - Math.round(cellHeight));
					for(int i = 0; i < mobs.size();i++)
						if((bossY == mobs.get(i).getY()) && bossX == mobs.get(i).getX()) {
							bossY = bossY + Math.round(cellHeight);
							break;
						}
				} else {
					//if (boss.getY() < player2.getY()){
					bossY = (bossY + Math.round(cellHeight));
					for(int i = 0; i < mobs.size();i++)
						if((bossY == mobs.get(i).getY()) && bossX == mobs.get(i).getX()) {
							bossY = bossY - Math.round(cellHeight);
							break;
						}
				}
			}
		}

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
		cellHeight = bossSize;
		drawer.smooth();
		if(type == 0)
			typeName = "unturned.jpg";
		else if(type == 1)
			typeName = "reaper.png";
		Boss = drawer.loadImage("executable/sprites" + System.getProperty("file.separator") + typeName);
		Boss.resize(0,bossSize);
		bossX = bossX * 20;
		bossY = bossY * 20;
	}
	public void takeDamage(Bullet b) {
		bossHealth -= 15;
	}
	public void draw(PApplet drawer) {
		drawer.image(Boss, bossX, bossY);
		drawer.fill(255,0,0);
		drawer.rect(bossX - 2, bossY - 4, bossHealth / 2, 2);
		drawer.fill(237, 24, 245);
		drawer.textSize(10);
		drawer.text(name, bossX, bossY);
		for(int i = 0; i < bossBullets.size();i++){
            if(bossBullets.get(i).getX() < -10 || bossBullets.get(i).getY() < -10 || bossBullets.get(i).getX() > 600 || bossBullets.get(i).getY() > 600){
                bossBullets.remove(i);
            } else {
                bossBullets.get(i).draw(drawer);
            }
        }
	}
	public int getHealth() {
		return bossHealth;
	}
	public boolean isDead(){
		return bossHealth <= 0;
	}

	public void shoot(int direct, PApplet drawer) {
	    if(direct == 1)
	        direction = Direction.UP;
	    else if(direct == 2)
            direction = Direction.DOWN;
        else if(direct == 3)
            direction = Direction.LEFT;
        else if(direct == 4)
            direction = Direction.RIGHT;

		Bullet bullet = new Bullet(bossX, bossY, "", direction,drawer);
		bossBullets.add(bullet);

	}


}
