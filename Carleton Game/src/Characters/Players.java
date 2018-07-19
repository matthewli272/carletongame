package Characters;

public class Players {
    private int playerHealth;
    private Weapons currentWeapon;
    private String name;
    private int playerType;
    private int playerX;
    private int playerY;

    public Players(String name, int playerType ){
        this.name = name;
        this.playerType = playerType;
    }



    //Getters & Setters

    public String getName() {
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public int getPlayerX() {
        return playerX;
    }

    public void setPlayerX(int playerX) {
        this.playerX = playerX;
    }

    public int getPlayerY() {
        return playerY;
    }

    public void setPlayerY(int playerY) {
        this.playerY = playerY;
    }
}
