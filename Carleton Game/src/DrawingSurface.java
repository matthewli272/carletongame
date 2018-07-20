import java.util.ArrayList;

import Characters.Players;
import processing.core.PApplet;
import screens.MainMenu;

import screens.Levels;

public class DrawingSurface extends PApplet /* implements MouseListener, ActionListener, KeyListener */ {
	private ArrayList<String> player1movement = new ArrayList<>(30);
	private ArrayList<String> player2movement = new ArrayList<>(30);
	private Obstacle obstacle;
	private MainMenu mainMenu;
	private Levels level1;
	private int x;
	private int y;
	// private boolean startMenu = true;
	private boolean player1ChangeName = false;
	private boolean player2ChangeName = false;

	private enum State {
		PAUSED, MENU, GAME, INSTRUCTIONS, WIN, LOSE, STARTUP
	};

	private State state;
	private float cellWidth;
	private float cellHeight;

	public DrawingSurface() {
		mainMenu = new MainMenu();
		state = State.MENU;
		runSketch();
		level1 = new Levels();
		obstacle = new Obstacle(0, 0);
	}

	// The statements in the setup() function
	// execute once when the program begins
	public void setup() {
		mainMenu.setup(this);
		obstacle.setup(this);

	}

	// The statements in draw() are executed until the
	// program is stopped. Each statement is executed in
	// sequence and after the last line is read, the first
	// line is executed again.
	public void draw() {
		background(255); // Clear the screen with a white background
		fill(255);
		textAlign(CENTER);
		textSize(12);
		// obstacle.draw(this);

		// rect(x,y,20,20);
		// System.out.println("im here");
		if (player1movement.contains("w")) {
			fill(0, 102, 153);
			textSize(32);
			text("Hello There!", 100, 100);
			fill(255);
			// System.out.println("hello");
		}

		if (state == State.MENU) {
			mainMenu.draw(this);
			rect(20, 150, 250, 30);
			rect(300, 150, 250, 30);
			fill(0, 102, 153);
			textAlign(LEFT);
			textSize(12);
			text(level1.getPlayer1().getName(), 25, 172);
			text(level1.getPlayer2().getName(), 305, 172);

		} else {// this can be added to players class later

			level1.draw(this, 0, 0, 600, 500);
			cellHeight = level1.getCellHeight();
			cellWidth = level1.getCellWidth();

			if (player1movement.contains("w") && (int)(level1.getPlayer1().getY() - cellHeight / 2) > -3) {
				level1.getPlayer1().setY((int) (level1.getPlayer1().getY() - cellHeight / 2));
			}
			if (player1movement.contains("a") && (int)(level1.getPlayer1().getX() - cellWidth / 2) > 0) {
				level1.getPlayer1().setX((int) (level1.getPlayer1().getX() - cellWidth / 2));
			}
			if (player1movement.contains("s") && (int)(level1.getPlayer1().getY() - cellHeight / 2) < 480) {
				level1.getPlayer1().setY((int) (level1.getPlayer1().getY() + cellHeight / 2));
			}
			if (player1movement.contains("d") && (int)(level1.getPlayer1().getX() - cellWidth / 2) < 580) {
				level1.getPlayer1().setX((int) (level1.getPlayer1().getX() + cellWidth / 2));
			}
			/// *
			if (player2movement.contains("w") && (int)(level1.getPlayer2().getY() - cellHeight / 2) > -3) {
				level1.getPlayer2().setY((int) (level1.getPlayer2().getY() - cellHeight / 2));
			}
			if (player2movement.contains("a") && (int)(level1.getPlayer2().getX() - cellWidth / 2) > 0) {
				level1.getPlayer2().setX((int) (level1.getPlayer2().getX() - cellWidth / 2));
			}
			if (player2movement.contains("s") && (int)(level1.getPlayer2().getY() - cellHeight / 2) < 480) {
				level1.getPlayer2().setY((int) (level1.getPlayer2().getY() + cellHeight / 2));
			}
			if (player2movement.contains("d") && (int)(level1.getPlayer2().getX() - cellWidth / 2) < 580) {
				level1.getPlayer2().setX((int) (level1.getPlayer2().getX() + cellWidth / 2));
			}

			// */
			level1.getPlayer1().draw(this, cellHeight, cellWidth);
			level1.getPlayer2().draw(this, cellHeight, cellWidth);
		}
		obstacle.draw(this);

	}

	public void keyPressed() {
		if (state != State.MENU) {
			switch (key) {
			case 'w': // this can be added to players class later
				if (!player1movement.contains("w")) {
					player1movement.clear();
					player1movement.add("w");
				}
				break;
			case 'a':
				if (!player1movement.contains("a")) {
					player1movement.clear();
					player1movement.add("a");
				}
				break;
			case 's':
				if (!player1movement.contains("s")) {
					player1movement.clear();
					player1movement.add("s");
				}
				break;
			case 'd':
				// System.out.println("y");
				if (!player1movement.contains("d")) {
					player1movement.clear();
					player1movement.add("d");
				}
				break;
			}
			if (key == CODED)
				switch (keyCode) {
				case UP:
					// System.out.println("x");
					if (!player2movement.contains("w")) {
						player2movement.clear();
						player2movement.add("w");
					}
					break;
				case DOWN:
					if (!player2movement.contains("s")) {
						player2movement.clear();
						player2movement.add("s");
					}
					break;
				case LEFT:
					if (!player2movement.contains("a")) {
						player2movement.clear();
						player2movement.add("a");
					}
					break;
				case RIGHT:
					if (!player2movement.contains("d")) {
						player2movement.clear();
						player2movement.add("d");
					}
					break;
				}

		} else {
			if (player1ChangeName) {
				if (((int) key >= 65 && (int) key <= 90) || ((int) key >= 97 && (int) key <= 122))
					level1.getPlayer1().setName(level1.getPlayer1().getName() + key);
				else if (key == 8)
					level1.getPlayer1().setName(
							level1.getPlayer1().getName().substring(0, level1.getPlayer1().getName().length() - 1));
			} else if (player2ChangeName) {
				if (((int) key >= 65 && (int) key <= 90) || ((int) key >= 97 && (int) key <= 122))
					level1.getPlayer2().setName(level1.getPlayer2().getName() + key);
				else if (key == 8)
					level1.getPlayer2().setName(
							level1.getPlayer2().getName().substring(0, level1.getPlayer2().getName().length() - 1));
			}
		}
	}

	public void keyReleased() {// this can be added to players class later
		switch (key) {
		case 'w':
			player1movement.remove("w");
			break;

		case 'a':
			player1movement.remove("a");
			break;

		case 's':
			player1movement.remove("s");
			break;

		case 'd':
			player1movement.remove("d");
			break;
		}

		if (key == CODED)
			switch (keyCode) {
			case UP:
				player2movement.remove("w");
				break;
			case DOWN:
				player2movement.remove("s");
				break;
			case RIGHT:
				player2movement.remove("d");
				break;
			case LEFT:
				player2movement.remove("a");
				break;
			}

	}

	public void mousePressed() {
		System.out.println(mouseX + " " + mouseY);

		if (state == State.MENU) {
			if (mouseX >= 20 && mouseX <= 270 && mouseY >= 150 && mouseY <= 180) {
				player2ChangeName = false;
				player1ChangeName = true;
				System.out.println("p1");
			} else if (mouseX >= 300 && mouseX <= 550 && mouseY >= 150 && mouseY <= 180) {
				player1ChangeName = false;
				player2ChangeName = true;
				System.out.println("p2");
			} else if (mouseX >= 75 && mouseX <= 525 && mouseY >= 200 && mouseY <= 370) {
				state = State.GAME;
				System.out.println("startGame");
				player1ChangeName = false;
				player2ChangeName = false;
			} else {
				player1ChangeName = false;
				player2ChangeName = false;
			}
		}
	}

	/*
	 * @Override public void actionPerformed(ActionEvent e) { }
	 * 
	 * @Override public void keyTyped(KeyEvent e) { }
	 * 
	 * @Override public void keyPressed(KeyEvent e) { System.out.println("no");
	 * switch (e.getKeyCode()) { case KeyEvent.VK_W:
	 * if(!player1movement.contains("w")) player1movement.add("w"); break; } }
	 * 
	 * @Override public void keyReleased(KeyEvent e) { System.out.println("yes");
	 * switch (e.getKeyCode()) { case KeyEvent.VK_W: player1movement.remove("w");
	 * break; } }
	 * 
	 * @Override public void mouseClicked(MouseEvent e) { }
	 * 
	 * @Override public void mousePressed(MouseEvent e) { }
	 * 
	 * @Override public void mouseReleased(MouseEvent e) { }
	 * 
	 * @Override public void mouseEntered(MouseEvent e) { }
	 * 
	 * @Override public void mouseExited(MouseEvent e) { }
	 */
}