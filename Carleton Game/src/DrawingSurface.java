import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import processing.core.PApplet;
import screens.*;
import sprites.Bosses;
import sprites.Obstacle;
import sprites.Players;
import screens.Levels;
import java.net.URI;
import java.math.*;
import javax.sound.sampled.*;

public class DrawingSurface extends PApplet /* implements MouseListener, ActionListener, KeyListener */ {

	private ArrayList<String> player1movement = new ArrayList<>(30);
	private ArrayList<String> player2movement = new ArrayList<>(30);
	private ArrayList<Obstacle> obstacles;
	private MainMenu mainMenu;
	private PauseMenu pauseMenu;
	private Instructions instructions;
	private Levels level1;
	private LoseScreen lost;
	private WinScreen won;
	private long time1, time2;
	private boolean player1ChangeName = false;
	private boolean player2ChangeName = false;
	private int count;

	private enum State {
		PAUSED, MENU, GAME, INSTRUCTIONS, WIN, LOSE, STARTUP
	};

	private State state, prevState;
	private float cellWidth;
	private float cellHeight;
	private Clip clip1;
	private Clip clip2;
	private SourceDataLine line = null;
	private byte[] audioBytes;
	private int numBytes;

	public DrawingSurface() {
		mainMenu = new MainMenu();
		lost = new LoseScreen();
		instructions = new Instructions();
		state = prevState = State.MENU;
		won = new WinScreen();
		obstacles = new ArrayList<Obstacle>();
		for (int i = 0; i < 100; i++) {
			int x = (int) (Math.random() * 25) + 4;
			int y = (int) (Math.random() * 25) + 4;
			obstacles.add(new Obstacle(x, y, x * 20, y * 20));
		}
		level1 = new Levels(new Players("Player One", 1, 0, 0), new Players("Player Two", 2, 0, 20),
				new Bosses("Zambie", 100, 20, 20, 0), obstacles, 600, 600);
		time1 = time2 = count = 0;
		pauseMenu = new PauseMenu();

		// File soundFile1 = new File("executable/sound/seinfield.mp3");
		File soundFile1 = new File("executable/sound/microsoft.wav");
		// File soundFile2 = new File("Visager-DarkSanctumBossLoop.wav");
		AudioInputStream audioInputStream1 = null;
		AudioInputStream audioInputStream2;
		try {
			audioInputStream1 = AudioSystem.getAudioInputStream(soundFile1);
			clip1 = AudioSystem.getClip();

			// audioInputStream2 = AudioSystem.getAudioInputStream(soundFile2);
			// clip2 = AudioSystem.getClip();

			clip1.open(audioInputStream1);

		} catch (Exception ex) {
			// clip2.open(audioInputStream2);
			// clip1.loop(Clip.LOOP_CONTINUOUSLY);
			System.out.println("*** Cannot find audio files ***");
			System.exit(1);
		}

		AudioFormat audioFormat = audioInputStream1.getFormat();
		DataLine.Info info = new DataLine.Info(SourceDataLine.class, audioFormat);
		try {
			line = (SourceDataLine) AudioSystem.getLine(info);
			line.open(audioFormat);
		} catch (LineUnavailableException ex) {
			System.out.println("*** Audio line unavailable ***");
			System.exit(1);
		}

		line.start();

		audioBytes = new byte[(int) soundFile1.length()];

		try {
			numBytes = audioInputStream1.read(audioBytes, 0, audioBytes.length);
		} catch (IOException ex) {
			System.out.println("*** Cannot read " + "Rolemusic-TheWhite.mp3" + " ***");
			System.exit(1);
		}
		runSketch();
	}

	// The statements in the setup() function
	// execute once when the program begins
	public void setup() {
		mainMenu.setup(this);
		level1.setup(this);
		pauseMenu.setup(this);
		lost.setup(this);
		won.setup(this);
		instructions.setup(this);

	}

	public void draw() {
		if (level1 == null) {
			obstacles = new ArrayList<Obstacle>();
			for (int i = 0; i < 100; i++) {
				int x = (int) (Math.random() * 25) + 4;
				int y = (int) (Math.random() * 25) + 4;
				obstacles.add(new Obstacle(x, y, x * 20, y * 20));
			}
			level1 = new Levels(new Players("Player One", 1, 0, 0), new Players("Player Two", 2, 0, 20),
					new Bosses("Zambie", 100, 20, 20, 0), obstacles, 600, 600);
			level1.setup(this);
		}
		background(255); // Clear the screen with a white background
		fill(255);
		textAlign(CENTER);
		textSize(12);
		if (level1.getPlayer1().getHealth() == 0 && level1.getPlayer2().getHealth() == 0) {
			prevState = state;
			state = State.LOSE;
		}
		if (level1.getBoss().getHealth() == 0 && state == State.GAME) {
			prevState = state;
			state = State.WIN;
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

		} else if (state == State.GAME) {
			clip1.stop();
			level1.draw(this, 0, 0);
			cellHeight = level1.getCellHeight();
			cellWidth = level1.getCellWidth();
			level1.takeDmg();

			if (player1movement.contains("c")) {
				if (time1 == 0) {
					time1 = System.currentTimeMillis();
					level1.getPlayer1().shoot(this);
				} else if (System.currentTimeMillis() - time1 > 500) {
					time1 = System.currentTimeMillis();
					level1.getPlayer1().shoot(this);
				}
			}
			if (player2movement.contains("c")) {
				if (time2 == 0) {
					time2 = System.currentTimeMillis();
					level1.getPlayer2().shoot(this);
				} else if (System.currentTimeMillis() - time2 > 500) {
					time2 = System.currentTimeMillis();
					level1.getPlayer2().shoot(this);
				}
			}

			level1.getPlayer1().draw(this, cellHeight, cellWidth, level1.getObstacle());
			level1.getPlayer2().draw(this, cellHeight, cellWidth, level1.getObstacle());
		} else if (state == State.PAUSED) {
			pauseMenu.draw(this);
		} else if (state == State.LOSE) {
			clip1.start();
			lost.draw(this);
		} else if (state == State.WIN) {
			won.draw(this);
		} else if (state == State.INSTRUCTIONS) {
			instructions.draw(this);
		}
	}

	public void keyPressed() {
		if (state != State.MENU) {
			switch (key) {
			case 'w': // this can be added to players class later
				if (!level1.getPlayer1().getPlayerMovement().contains("w")) {
					level1.getPlayer1().getPlayerMovement().clear();
					level1.getPlayer1().getPlayerMovement().add("w");
				}
				break;
			case 'a':
				if (!level1.getPlayer1().getPlayerMovement().contains("a")) {
					level1.getPlayer1().getPlayerMovement().clear();
					level1.getPlayer1().getPlayerMovement().add("a");
				}
				break;
			case 's':
				if (!level1.getPlayer1().getPlayerMovement().contains("s")) {
					level1.getPlayer1().getPlayerMovement().clear();
					level1.getPlayer1().getPlayerMovement().add("s");
				}
				break;
			case 'd':
				// System.out.println("y");
				if (!level1.getPlayer1().getPlayerMovement().contains("d")) {
					level1.getPlayer1().getPlayerMovement().clear();
					level1.getPlayer1().getPlayerMovement().add("d");
				}
				break;

			case 'c':
				player1movement.add("c");
				break;

			}

			if (key == CODED)
				switch (keyCode) {
				case UP:
					// System.out.println("x");
					if (!level1.getPlayer2().getPlayerMovement().contains("w"))
						level1.getPlayer2().getPlayerMovement().add("w");
					break;
				case DOWN:
					if (!level1.getPlayer2().getPlayerMovement().contains("s"))
						level1.getPlayer2().getPlayerMovement().add("s");
					break;
				case LEFT:
					if (!level1.getPlayer2().getPlayerMovement().contains("a"))
						level1.getPlayer2().getPlayerMovement().add("a");
					break;
				case RIGHT:
					if (!level1.getPlayer2().getPlayerMovement().contains("d"))
						level1.getPlayer2().getPlayerMovement().add("d");
					break;
				case SHIFT:
					if (!player2movement.contains("c"))
						player2movement.add("c");
					// System.out.println("HULLO");
					break;
				}

		} else {
			if (player1ChangeName) {
				if (((int) key >= 65 && (int) key <= 90) || ((int) key >= 97 && (int) key <= 122) || (int) key == 32
						|| ((int) key >= 48 && (int) key <= 57))
					level1.getPlayer1().setName(level1.getPlayer1().getName() + key);
				else if (key == 8)
					level1.getPlayer1().setName(
							level1.getPlayer1().getName().substring(0, level1.getPlayer1().getName().length() - 1));
			} else if (player2ChangeName) {
				if (((int) key >= 65 && (int) key <= 90) || ((int) key >= 97 && (int) key <= 122) || (int) key == 32
						|| ((int) key >= 48 && (int) key <= 57))
					level1.getPlayer2().setName(level1.getPlayer2().getName() + key);
				else if (key == 8)
					level1.getPlayer2().setName(
							level1.getPlayer2().getName().substring(0, level1.getPlayer2().getName().length() - 1));
			}
		}
	}

	public void keyReleased() {
		switch (key) {
		case 'w':
			level1.getPlayer1().getPlayerMovement().remove("w");
			break;

		case 'a':
			level1.getPlayer1().getPlayerMovement().remove("a");
			break;

		case 's':
			level1.getPlayer1().getPlayerMovement().remove("s");
			break;

		case 'd':
			level1.getPlayer1().getPlayerMovement().remove("d");
			break;

		case 'c':
			player1movement.remove("c");
			break;
		}

		if (key == CODED)
			switch (keyCode) {
			case UP:
				level1.getPlayer2().getPlayerMovement().remove("w");
				break;
			case DOWN:
				level1.getPlayer2().getPlayerMovement().remove("s");
				break;
			case RIGHT:
				level1.getPlayer2().getPlayerMovement().remove("d");
				break;
			case LEFT:
				level1.getPlayer2().getPlayerMovement().remove("a");
				break;
			case SHIFT:
				player2movement.remove("c");
				break;
			}

	}

	public void mousePressed() {
		//System.out.println(mouseX + ", " + mouseY);
		if (state == State.MENU) {
			if (mouseX >= 20 && mouseX <= 270 && mouseY >= 150 && mouseY <= 180) {
				player2ChangeName = false;
				player1ChangeName = true;
			} else if (mouseX >= 300 && mouseX <= 550 && mouseY >= 150 && mouseY <= 180) {
				player1ChangeName = false;
				player2ChangeName = true;
			} else if (mouseX >= 75 && mouseX <= 525 && mouseY >= 240 && mouseY <= 340) {
				prevState = state;
				state = State.GAME;
				player1ChangeName = false;
				player2ChangeName = false;
			} else if (mouseY >= 360 && mouseY < 475) {
				prevState = state;
				state = State.INSTRUCTIONS;
			} else {
				player1ChangeName = false;
				player2ChangeName = false;
			}
		} else if (state == State.GAME) {
			if (mouseX >= 600 && mouseX <= 620 && mouseY >= 0 && mouseY <= 20) {
				prevState = state;
				state = State.PAUSED;
			}
		} else if (state == State.LOSE || state == State.WIN) {
			if (mouseX >= 110 && mouseX <= 510 && mouseY >= 600 && mouseY <= 700) {
				prevState = state;
				state = State.MENU;
				level1 = null;
			}
		} else if (state == State.PAUSED) {
			if (mouseX >= 0 && mouseX <= 620 && mouseY >= 200 && mouseY <= 400) {
				prevState = state;
				state = State.GAME;
			} else if (mouseX >= 0 && mouseX <= 620 && mouseY > 400 && mouseY <= 530) {
				prevState = state;
				state = State.INSTRUCTIONS;
			} else if (mouseX >= 110 && mouseX <= 510 && mouseY >= 580 && mouseY <= 680) {
				prevState = state;
				state = State.MENU;
				level1 = null;
			}
		} else if (state == State.INSTRUCTIONS) {
			if (mouseX >= 0 && mouseX <= 620 && mouseY >= 500 && mouseY <= 700) {
				state = prevState;
				prevState = State.INSTRUCTIONS;
			}
		}
	}
}