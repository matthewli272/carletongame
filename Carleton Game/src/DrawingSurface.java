import java.awt.event.*;
import java.awt.geom.Point2D;
import java.util.ArrayList;

import processing.core.PApplet;



public class DrawingSurface extends PApplet /*implements MouseListener, ActionListener, KeyListener*/ {
	private ArrayList<String> test = new ArrayList<>(30);
	private int x;
	private int y;
	
	public DrawingSurface() {
		
		runSketch();
	}
	
	// The statements in the setup() function 
	// execute once when the program begins
	public void setup() {
		
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
		rect(x,y,20,20);
		//System.out.println("im here");
		if(test.contains("w")){
			fill(0, 102, 153);
			textSize(32);
			text("Hello There!", 100, 100);
			fill(255);
			//System.out.println("hello");
		}

		if(x > mouseX)
			x-=1;
		else if (x < mouseX)
			x+=1;

		if(y > mouseY)
			y-=1;
		else if(y < mouseY)
			y+=1;


	
	}
	public void keyPressed(){


		switch (key) {
			case 'w':
				if(!test.contains("w"))
					test.add("w");
				break;
		}
	}
	public void keyReleased(){
		switch (key) {
			case 'w':
				test.remove("w");
				break;
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

