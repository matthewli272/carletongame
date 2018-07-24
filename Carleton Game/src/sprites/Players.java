package sprites;

import processing.core.PApplet;
import processing.core.PImage;

enum Direction {
	LEFT, UP, RIGHT, DOWN
};

public class Players implements Entity {
	private int playerHealth = 100;
	private Weapons currentWeapon;
	private String name;
	private int playerType;// player 1 or player 2
	private int playerX;
	private int playerY;
	private PImage knight;
	private PImage otherKnight;
	private PImage placeHolder;
	private int count = 0;
	private int area = 1;
	private int testX;
	private int testY;
	
	private Direction direction;
	private int fontHeight = 20;

	public Players(String name, int playerType, int playerX, int playerY) {
		this.name = name;
		this.playerType = playerType;
		this.playerY = playerY;
		this.playerX = playerX;
		testX = playerX;
		testY = playerY;
		direction = Direction.UP;


	}

	// Getters & Setters
	public int[] meleeAtk() {
		int[] x = { 0, 2 };
		return x;
	}

	public int[] rangedAtk() {
		int[] x = { 0, 2 };
		return x;
	}

	public boolean isDead() {
		return playerHealth == 0;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public int getX() {
		return this.playerX;
	}

	@Override
	public int getY() {
		return this.playerY;
	}

	@Override
	public void setY(int y) {
		// TODO Auto-generated method stub
		playerY = y;
	}

	public int getPlayerHealth() {
		return playerHealth;
	}

	public void setPlayerHealth(int playerHealth) {
		this.playerHealth = playerHealth;
	}

	@Override
	public void setX(int x) {
		this.playerX = x;
	}

	public void setup(PApplet drawer){
        //knight = drawer.loadImage("executable/sprites" + System.getProperty("file.separator") + "test.png");

    }

	public void draw(PApplet drawer, float x, float y) {
        knight = drawer.loadImage("executable/sprites" + System.getProperty("file.separator") + "test.png");
		placeHolder = drawer.loadImage("executable/sprites" + System.getProperty("file.separator") + "runningsprite.gif");
		drawer.fill(0, 0, 0);
		// System.out.println(x + " " + y);

		//drawer.rect(playerX, playerY, x, y);
        //knight.resize(0,(int) y);
		placeHolder.resize(0,20);
        //otherKnight = knight.get(0,0,(int)x,(int)y);
        knight.resize(0,(int) y);
        if(count == 10) { //20/55
            area += 7;
            count = 0;
        }
        if(playerX == testX && playerY == testY) {
            if (area > 9)
                area = 1;
        } else {
            if (area > 24)
                area = 16;
        }
        //System.out.println(area);
        //otherKnight = knight.get(area,0,(int) x - 2,(int) y);
        //drawer.image(otherKnight,playerX,playerY);
		drawer.image(placeHolder,playerX,playerY);

        drawer.fill(237, 24, 245);
		drawer.textSize(10);
		drawer.text(name, playerX, playerY);
		drawer.fill(0,0,0);
		drawer.textSize(fontHeight);
		while(drawer.textWidth(name) > drawer.textWidth("PlayerTwoE")) {
			fontHeight--;
			drawer.textSize(fontHeight);
		}
		if(playerType == 1){
			drawer.textAlign(drawer.LEFT);
			drawer.text(name, 10, 630);
			drawer.fill(255,0,0);
			drawer.rect(drawer.textWidth(name) + 20,610, playerHealth,20);
		} else if (playerType == 2){
			drawer.textAlign(drawer.RIGHT);
			drawer.text(name, 580, 660);
			drawer.fill(255,0,0);
			drawer.rect(480 - (drawer.textWidth(name) + 20),640, playerHealth,20);
		}




		drawer.fill(255);
		count++;
		testX = playerX;
		testY = playerY;


	}
	//eventually determine damage taken by bullet type
	public void takeDamage(Bullet b) {
		playerHealth -= 15;
	}
	
	public void shoot() {
		Bullet bullet = new Bullet(playerX, playerY, "", direction);
		
	}
}
