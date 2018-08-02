package sprites;

import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PImage;

import java.util.ArrayList;

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
	private int movementCount = 0;
	private int area = 1;
	private int testX;
	private int testY;
	private ArrayList<Bullet> playerBullets = new ArrayList<>(10);
	private ArrayList<String> playerMovement = new ArrayList<>(30);
	
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
	public void addBullet(Bullet bullet){
		playerBullets.add(bullet);
	}
	public ArrayList<Bullet> getBullets(){
		return playerBullets;
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
		return playerHealth <= 0;
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

	public void setDirection(Direction direct){
		this.direction = direct;
	}

	public ArrayList<String> getPlayerMovement() {
		return playerMovement;
	}

	public void addMovement(String movement){
		playerMovement.add(movement);
	}

	public void removeMovement(String movement){
		playerMovement.remove(movement);
	}

	@Override
	public void setX(int x) {
		this.playerX = x;
	}
	public int getHealth() {
		return playerHealth;
	}
	public void setup(PApplet drawer){
    }

	public void draw(PApplet drawer, float cellHeight, float cellWidth, ArrayList<Obstacle> obstacles) {
        knight = drawer.loadImage("executable/sprites" + System.getProperty("file.separator") + "test.png");
		placeHolder = drawer.loadImage("executable/sprites" + System.getProperty("file.separator") + "runningsprite.gif");
		drawer.textSize(20);
		drawer.fill(0,0,0);
		if(playerType == 1){
			drawer.textAlign(drawer.LEFT);
			drawer.text(name, 10, 630);
			drawer.text(playerHealth + "%",10,660);
			drawer.fill(255,0,0);
			drawer.rect(drawer.textWidth(name) + 20,610, playerHealth,20);
		} else if (playerType == 2){
			drawer.textAlign(drawer.RIGHT);
			drawer.text(name, 580, 660);
			drawer.text(playerHealth + "%",580,630);
			drawer.fill(255,0,0);
			drawer.rect(480 - (drawer.textWidth(name) + 20),640, playerHealth,20);
		}
		int change1 = 0;
		int change2 = 0;
		if(playerHealth < 0)
			playerHealth = 0;

		if (movementCount == 7) {
			if (playerMovement.contains("w") && (int) (playerY - cellHeight /* / 2 */) > -3) {
				playerY = ((int) (playerY - cellHeight));
				change1 = -20;
				direction = Direction.UP;
			}
			if (playerMovement.contains("a") && (int) (playerX - cellWidth /* / 2 */) > -3) {
				playerX = ((int) (playerX - cellWidth));
				change2 = -20;
				direction = Direction.LEFT;
			}
			if (playerMovement.contains("s") && (int) (playerY - cellHeight /* / 2 */) < 560) {
				playerY = ((int) (playerY + cellHeight));
				change1 = 20;
				direction = Direction.DOWN;
			}
			if (playerMovement.contains("d") && (int) (playerX - cellWidth /* / 2 */) < 560) {
				playerX = ((int) (playerX + cellWidth));
				change2 = 20;
				direction = Direction.RIGHT;
			}

			for(Obstacle obs : obstacles){
				//System.out.println(obs);
				if(obs != null) {
					if (obs.getX() == playerX && obs.getY() == playerY) {
						playerY -= change1;
						playerX -= change2;
					}
				}
			}

			movementCount = 0;
		}

		movementCount++;
		drawer.fill(0, 0, 0);
		placeHolder.resize(0,20);
		knight.resize(0,(int) cellWidth);
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
		drawer.image(placeHolder,playerX,playerY);
		drawer.fill(237, 24, 245);
		drawer.textSize(10);
		drawer.textAlign(drawer.LEFT);
		drawer.text(name, playerX, playerY);
		drawer.fill(0,0,0);
		drawer.textSize(fontHeight);
		while(drawer.textWidth(name) > drawer.textWidth("PlayerTwoE")) {
			fontHeight--;
			drawer.textSize(fontHeight);
		}

		for(int i = 0; i < playerBullets.size(); i++){
			if(playerBullets.get(i).getX() < -10 || playerBullets.get(i).getY() < -10 || playerBullets.get(i).getX() > 590 || playerBullets.get(i).getY() > 590){
				playerBullets.remove(i);
			} else {
				playerBullets.get(i).draw(drawer);
			}
		}

		drawer.fill(255);
		count++;
		testX = playerX;
		testY = playerY;


	}
 	public void takeDamage(/*Bullet b*/) {
		playerHealth -= 5;
	}
	
	public void shoot(PApplet drawer) {
		Bullet bullet = new Bullet(playerX, playerY, "sun", direction, drawer);
		playerBullets.add(bullet);
	}
}
