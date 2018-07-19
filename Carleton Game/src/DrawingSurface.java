import java.awt.event.*;
import java.awt.geom.Point2D;
import java.util.ArrayList;

import processing.core.PApplet;
import screens.MainMenu;
import screens.levels;



public class DrawingSurface extends PApplet /*implements MouseListener, ActionListener, KeyListener*/ {
	private ArrayList<String> test = new ArrayList<>(30);
	private MainMenu mainMenu;
	private int x;
	private int y;
	private boolean startMenu = true;
	private Players player1;
	private Players player2;
	private boolean player1ChangeName = false;
	private boolean player2ChangeName = false;
	private enum State {
		PAUSED, MENU, GAME, INSTRUCTIONS, WIN, LOSE, STARTUP 
	};
	private State state;
	
	public DrawingSurface() {
		mainMenu = new MainMenu();
		state = State.MENU;
		runSketch();
	}
	
	// The statements in the setup() function 
	// execute once when the program begins
	public void setup() {
		player1 = new Players(" ", 1);
		player2 = new Players(" ", 2);
		mainMenu.setup(this);


	}
	
	// The statements in draw() are executed until the 
	// program is stopped. Each statement is executed in 
	// sequence and after the last line is read, the first 
	// line is executed again.
	public void draw() { 
		background(255);   // Clear the screen with a white background
		fill(255);
		textAlign(CENTER);
		textSize(12);
		mainMenu.draw(this);
		rect(x,y,20,20);
		rect(20,150,250,30);
		rect(300,150,250,30);
		//System.out.println("im here");
		if(test.contains("w")){
			fill(0, 102, 153);
			textSize(32);
			text("Hello There!", 100, 100);
			fill(255);
			//System.out.println("hello");
		}

		if(startMenu){
			fill(0, 102, 153);
			textAlign(LEFT);
			textSize(12);
			text(player1.getName(), 25, 172);
			text(player2.getName(), 305, 172);


		}else {

			if (x > mouseX)
				x -= 1;
			else if (x < mouseX)
				x += 1;

			if (y > mouseY)
				y -= 1;
			else if (y < mouseY)
				y += 1;
		}


	
	}
	public void keyPressed(){

		if(!startMenu) {
			switch (key) {
				case 'w':
					if (!test.contains("w"))
						test.add("w");
					break;
			}
		} else{
			if(player1ChangeName) {
				if (((int) key >= 65 && (int) key <= 90) || ((int) key >= 97 && (int) key <= 122))
					player1.setName(player1.getName() + key);
				else if(key == 8)
					player1.setName(player1.getName().substring(0,player1.getName().length() - 1));
			} else if (player2ChangeName) {
				if (((int) key >= 65 && (int) key <= 90) || ((int) key >= 97 && (int) key <= 122))
					player2.setName(player2.getName() + key);
				else if(key == 8)
					player2.setName(player2.getName().substring(0,player2.getName().length() - 1));
			}
		}
	}
	public void keyReleased(){
		switch (key) {
			case 'w':
				test.remove("w");
				break;
		}
	}
	public void mousePressed(){
		System.out.println(mouseX + " " + mouseY);
		if(startMenu){
			if(mouseX >= 20 && mouseX <= 270 && mouseY >= 150 && mouseY <= 180) {
				player2ChangeName = false;
				player1ChangeName = true;
				System.out.println("p1");
			}else if(mouseX >= 300 && mouseX <= 550 && mouseY >= 150 && mouseY <= 180) {
				player1ChangeName = false;
				player2ChangeName = true;
				System.out.println("p2");
			}else{
				player1ChangeName = false;
				player2ChangeName = false;
			}
		}
	}

/*
	@Override
	public void actionPerformed(ActionEvent e) {

	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

	@Override
	public void keyPressed(KeyEvent e) {
		System.out.println("no");
		switch (e.getKeyCode()) {
			case KeyEvent.VK_W:
				if(!test.contains("w"))
					test.add("w");
				break;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		System.out.println("yes");
		switch (e.getKeyCode()) {
			case KeyEvent.VK_W:
				test.remove("w");
				break;
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {

	}

	@Override
	public void mousePressed(MouseEvent e) {

	}

	@Override
	public void mouseReleased(MouseEvent e) {

	}

	@Override
	public void mouseEntered(MouseEvent e) {

	}

	@Override
	public void mouseExited(MouseEvent e) {

	}*/
}

