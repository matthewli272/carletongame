package Characters;

import processing.core.PApplet;

public class Players implements Entity {
	private int playerHealth;
	private Weapons currentWeapon;
	private String name;
	private int playerType;// player 1 or player 2
	private int playerX;
	private int playerY;

	public Players(String name, int playerType, int playerX, int playerY) {
		this.name = name;
		this.playerType = playerType;
		this.playerY = playerY;
		this.playerX = playerX;
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

	public void draw(PApplet drawer, float x, float y) {
		drawer.fill(0, 0, 0);
		// System.out.println(x + " " + y);

		drawer.rect(playerX, playerY, x, y);
		drawer.fill(237, 24, 245);
		drawer.textSize(10);
		drawer.text(name, playerX, playerY);

		drawer.fill(255);
	}

}
