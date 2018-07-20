package Characters;

public class Bosses {

	// fields
	private int bossHealth;
	private Weapons currentWeapon;
	private String name;
	private int bossX;
	private int bossY;

	// constructor
	public Bosses(String name, int bossHealth, int bossX, int bossY) {
		this.name = name;
		this.bossHealth= bossHealth;
		this.bossX = bossX;
		this.bossY = bossY;
	}

}
