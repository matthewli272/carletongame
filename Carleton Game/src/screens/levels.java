package screens;

import java.awt.Point;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

import Characters.Players;
import processing.core.PApplet;

/*

	Represents a Game Of Life grid.

	Coded by: Matthew Li
	Modified on:

*/

public class levels {
	private boolean[][] grid;
	private Players[][] playerPosition;
	//private Bosses[][] bossesPosition;
	//private Obstacles[][] obstaclePosition;
	
	// Constructs an empty grid
	public levels() {
		grid = new boolean[20][20];
		playerPosition = new Players[20][20];
		//bossesPosition = new Bosses[20][20];
		//obstaclePosition = new Obstacles[20][20];
	}


	// Runs a single turn of the Game Of Life
	public void step() {
		int count = 0;
		for (int i = 0; i < grid[0].length; i++) {
			for (int j = 0; j < grid.length; j++) {
				neighbors[i][j] = getNeighbors(i, j);
			}
		}
		for (int i = 0; i < grid[0].length; i++) {
			for (int j = 0; j < grid.length; j++) {
				if ((neighbors[i][j] == 3)) {
					grid[i][j] = true;
				} else if (neighbors[i][j] >= 4 || neighbors[i][j] <= 1) {
					grid[i][j] = false;
				}
			}
		}
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid.length; j++) {
				if (grid[i][j])
					count++;
			}
		}
		System.out.println(count);
		// for (int i = 0; i < grid.length; i++) {
		// for (int j = 0; j < grid[0].length; j++) {
		// System.out.println(grid[i][j]);
		//// if (grid[i][j])
		//// count++;
		// }
		// }
		// System.out.println(count);

	}

//	public int getNeighbors(int i, int j) {
//		int count = 0;
//		int startIndexX = i - 1, startIndexY = j - 1;
//		int endIndexX = i + 1, endIndexY = j + 1;
//		if (i - 1 < 0) {
//			startIndexX = i;
//			// System.out.println("first, " + startIndexX);
//		}
//		if (i + 1 > grid.length - 1) {
//			endIndexX = i;
//			// System.out.println("second, " + endIndexX);
//		}
//		if (j - 1 < 0) {
//			startIndexY = j;
//			// System.out.println("third, " + startIndexY);
//		}
//		if (j + 1 > grid[0].length - 1) {
//			endIndexY = j;
//			// System.out.println("fourth, " + endIndexY);
//		}
//		System.out.println("startIndexX, " + startIndexX);
//		System.out.println("endIndexX, " + endIndexX);
//		System.out.println("startIndexY, " + startIndexY);
//		System.out.println("endIndexY, " + endIndexY);
//
//		for (int k = startIndexX; k <= endIndexX; k++) {
//			for (int l = startIndexY; l <= endIndexY; l++) {
//				if (grid[k][l])
//					count++;
//				System.out.println(grid[k][l]);
//			}
//		}
//		if (grid[i][j])
//			count--;
//		System.out.println(count);
//		return count;
//	}

	// Runs n turns of the Game Of Life
	public void step(int n) {
		for (int i = 0; i < n; i++)
			step();
	}

	// Formats this Life grid as a String to be printed (one call to this method
	// returns the whole multi-line grid)
	public String toString() {
		String temp = "";
		for (int i = 0; i < grid[0].length; i++) {
			for (int j = 0; j < grid.length; j++) {
				if (grid[j][i]) {
					temp += "*";
				} else {
					temp += "-";
				}
			}
			temp += "\n";
		}

		return temp;
	}

	// Reads in array data from a simple text file containing asterisks (*)
	public void readData(String filename, boolean[][] gameData) {
		File dataFile = new File(filename);

		if (dataFile.exists()) {
			int count = 0;

			FileReader reader = null;
			Scanner in = null;
			try {
				reader = new FileReader(dataFile);
				in = new Scanner(reader);

				while (in.hasNext()) {
					String line = in.nextLine();
					for (int i = 0; i < line.length(); i++)
						if (i < gameData.length && count < gameData[i].length && line.charAt(i) == '*')
							gameData[i][count] = true;

					count++;
				}
			} catch (IOException ex) {
				throw new IllegalArgumentException("Data file " + filename + " cannot be read.");
			} finally {
				if (in != null)
					in.close();
			}

		} else {
			throw new IllegalArgumentException("Data file " + filename + " does not exist.");
		}
	}

	/**
	 * Optionally, complete this method to draw the grid on a PApplet.
	 * 
	 * @param marker
	 *            The PApplet used for drawing.
	 * @param x
	 *            The x pixel coordinate of the upper left corner of the grid
	 *            drawing.
	 * @param y
	 *            The y pixel coordinate of the upper left corner of the grid
	 *            drawing.
	 * @param width
	 *            The pixel width of the grid drawing.
	 * @param height
	 *            The pixel height of the grid drawing.
	 */
	public void draw(PApplet marker, float x, float y, float width, float height) {
		float cellWidth = width / grid[0].length;
		float cellHeight = height / grid.length;
		for (int i = 0; i < grid[0].length; i++) {
			for (int j = 0; j < grid.length; j++) {
				if (grid[j][i]) {
					marker.fill(0);
				} else {
					marker.fill(255);
				}
				marker.rect(cellWidth * j + x, cellHeight * i + y, cellWidth, cellHeight);

			}
		}

	}

	/**
	 * Optionally, complete this method to determine which element of the grid
	 * matches with a particular pixel coordinate.
	 * 
	 * @param p
	 *            A Point object representing a graphical pixel coordinate.
	 * @param x
	 *            The x pixel coordinate of the upper left corner of the grid
	 *            drawing.
	 * @param y
	 *            The y pixel coordinate of the upper left corner of the grid
	 *            drawing.
	 * @param width
	 *            The pixel width of the grid drawing.
	 * @param height
	 *            The pixel height of the grid drawing.
	 * @return A Point object representing a coordinate within the game of life
	 *         grid.
	 */
	public Point clickToIndex(Point p, float x, float y, float width, float height) {
		float cellWidth = width / grid.length;
		float cellHeight = height / grid.length;
		int j = (int) ((p.x - x) / cellWidth);
		int i = (int) ((p.y - y) / cellHeight);
		if (j < 0 || j >= grid.length)
			return null;
		if (i < 0 || i >= grid[0].length)
			return null;
		Point answer = new Point(j, i);
		return answer;
	}

	/**
	 * Optionally, complete this method to toggle a cell in the game of life grid
	 * between alive and dead.
	 * 
	 * @param i
	 *            The x coordinate of the cell in the grid.
	 * @param j
	 *            The y coordinate of the cell in the grid.
	 */
	public void toggleCell(int i, int j) {
		grid[i][j] = !grid[i][j];
	}

}