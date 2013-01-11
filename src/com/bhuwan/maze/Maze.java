package com.bhuwan.maze;

import com.bhuwan.main.debug.debugMessage;
import com.bhuwan.maze.errors.MazeErrors;

public class Maze {
	/* Static */
	private static final int CLEAR = -1;
	private static final int EXIT_ENTRANCE = 0;
	private static final int WALL = 1;
	private static final int PATH = 10;
	private static final int VISITED = 100;
	private static final int CREATURE_POSITION = -100;
	private static final boolean DEBUG_ON = true;

	/* Local variables */
	private int maze_height;
	private int maze_width;
	private MazeCoord MAZE_EXIT;
	private MazeCoord MAZE_ENTRANCE;
	private int maze[][];
	private boolean mazeComplete = false;

	public Maze(int width, int height, MazeCoord exit, MazeCoord entrance) {
		MAZE_EXIT = exit;
		MAZE_ENTRANCE = entrance;
		maze_height = height;
		maze_width = width;
		maze = new int[maze_height][maze_width];
		initMaze();
	}

	public MazeCoord getMazeEntrance() {
		return (new MazeCoord(MAZE_ENTRANCE));
	}

	public boolean isCoordExit(MazeCoord c) {
		return ((index(c.getX()) == MAZE_EXIT.getX()) && (index(c.getY()) == MAZE_EXIT.getY()));
	}

	public boolean validCoord(MazeCoord c) {
		return ((index(c.getX()) >= 0 && index(c.getX()) < maze_height) && ((index(c
				.getY()) >= 0) && (index(c.getY()) < maze_width)));
	}

	public boolean isCoordClear(MazeCoord c) {
		return (getMazeVal(index(c.getX()), index(c.getY())) == CLEAR);
	}

	public boolean isCoordPath(MazeCoord c) {
		return (getMazeVal(index(c.getX()), index(c.getY())) == PATH);
	}

	public boolean isCoordWall(MazeCoord c) {
		return (getMazeVal(index(c.getX()), index(c.getY())) == WALL);
	}

	public boolean isCoordVisited(MazeCoord c) {
		return (getMazeVal(index(c.getX()), index(c.getY())) == VISITED);
	}

	private boolean isMazeComplete() {
		boolean mazeComp = false;

		if (MAZE_EXIT == null || MAZE_ENTRANCE == null) {
			return false;
		}

		int totalEmpty = 0;
		for (int row = 1; row <= maze_height; row++) {
			for (int col = 1; col <= maze_width; col++) {

				if (getMazeVal(index(row), index(col)) == EXIT_ENTRANCE) {
					totalEmpty++;
				}
				if (totalEmpty == 2)
					break;
			}
		}

		if ((getMazeVal(MAZE_EXIT.getX(), MAZE_EXIT.getY()) == EXIT_ENTRANCE)
				&& (getMazeVal(MAZE_ENTRANCE.getX(), MAZE_ENTRANCE.getY()) == EXIT_ENTRANCE)
				&& (totalEmpty == 2)) {
			mazeComp = true;
		}

		return mazeComp;
	}

	public boolean addData(String tmp, int row) {
		if (mazeComplete)
			return false;

		if ((tmp.length() != 0) && (tmp.length() != maze_width)) {
			debugMessage("Exit due to error: " + MazeErrors.FILE_MAZE_MISMATCH
					+ "," + maze_width);
			System.exit(MazeErrors.FILE_MAZE_MISMATCH);
		}

		for (int x = 1; x <= maze_width; x++) {
			addMaze(tmp.charAt(index(x)), index(row), index(x));
		}

		return (isMazeComplete());
	}

	private int getMazeVal(int row, int col) {
		return maze[row][col];
	}

	public void printMaze() {
		for (int row = 1; row <= maze_height; row++) {
			for (int col = 1; col <= maze_width; col++) {
				if (getMazeVal(index(row), index(col)) == WALL) {
					System.out.print("X");
				} else if (getMazeVal(index(row), index(col)) == EXIT_ENTRANCE) {
					System.out.print("O");
				} else if (getMazeVal(index(row), index(col)) == CLEAR) {

					System.out.print(".");
				} else if (getMazeVal(index(row), index(col)) == CREATURE_POSITION) {
					System.out.print("C");
				} else {
					debugMessage
							.output("Catastrphoic error in printing due to row="
									+ row + ", col=" + col);
				}
			}
			System.out.println("");
		}
		System.out.println("");
	}

	public void setCreaturePosition(MazeCoord m) {

		maze[index(m.getX())][index(m.getY())] = CREATURE_POSITION;
	}

	private void addMaze(char c, int row, int col) {
		int val = CLEAR;
		if (c == 'X' || c == 'x') {
			val = WALL;
		}
		if ((MAZE_ENTRANCE.getX() == col && MAZE_ENTRANCE.getY() == row)
				|| (MAZE_EXIT.getX() == col && MAZE_EXIT.getY() == row)) {
			val = EXIT_ENTRANCE;
		}
		maze[row][col] = val;
	}

	private void initMaze() {
		if (maze == null) {
			debugMessage("No instance of maze");
			return;
		}

		for (int row = 1; row <= maze_height; row++) {
			for (int col = 1; col <= maze_width; col++) {
				maze[index(row)][index(col)] = CLEAR;
			}
		}
		setCreaturePosition(MAZE_ENTRANCE);

	}

	private void debugMessage(String str) {
		if (DEBUG_ON) {
			debugMessage.output(str);
		}
	}

	public boolean removeCreaturePath(MazeCoord mc) {
		if (maze[index(mc.getX())][index(mc.getY())] == CREATURE_POSITION) {
			maze[index(mc.getX())][index(mc.getY())] = CLEAR;
			return true;
		} else {
			return false;

		}
	}

	private int index(int val) {
		return val - 1;
	}
}
