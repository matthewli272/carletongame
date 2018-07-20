package Characters;

import processing.core.PApplet;

public class Weapons extends PApplet{
    private String wpnName;
    private int wpnDmg;

    public Weapons(String wpnName){
        this.wpnName = wpnName;

    }

    public void draw(){

        

    }


    //Getters & Setters
    public int getWpnDmg() {
        return wpnDmg;
    }
}
