package Characters;

import processing.core.PApplet;

public class Players extends PApplet{
    private int playerHealth = 100;
    private Weapons currentWeapon;
    private String name;
    private int playerType;//player 1 or player 2
    private int playerX = 0;
    private int playerY = 0;

    public Players(String name, int playerType ){
        this.name = name;
        this.playerType = playerType;
    }

    public int[] meleeAtk(){
        int[] x = {0,2};
        return x;
    }

    public int[] rangedAtk(){
        int[] x = {0,2};
        return x;
    }

    public boolean isDead(){
        return playerHealth == 0;
    }

    public void draw(PApplet drawer,float x, float y){
        drawer.fill(0,0,0);
        //System.out.println(x + " " + y);

        drawer.rect(playerX,playerY,x,y);
        drawer.fill(237,24,245);
        drawer.textSize(10);
        drawer.text(name,playerX,playerY);

        drawer.fill(255);
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

    public int getPlayerHealth() {
        return playerHealth;
    }

    public void setPlayerHealth(int playerHealth) {
        this.playerHealth = playerHealth;
    }
}
