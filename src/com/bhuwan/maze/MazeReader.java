package com.bhuwan.maze;

import java.io.*;
import java.util.*;

import com.bhuwan.main.debug.debugMessage;
import com.bhuwan.maze.errors.MazeErrors;

public class MazeReader {

	private static final boolean DEBUG_ON = true;
	private int maze_height;
	private int maze_width;
	private MazeCoord MAZE_EXIT;
	private MazeCoord MAZE_ENTRANCE;
	private String fileName = "";

	public MazeReader(String fileName) {
		// readMazeFile(fileName);
		this.fileName = fileName;
	}

	/*
	 * This will attempt to read in fileName
	 */
	public Maze readMazeFile() {

		Maze m = null;
		File file = new File(fileName);

		if (!fileExists(file)) {
			debugMessage(file.getAbsolutePath() + " does not exist "
					+ MazeErrors.FILE_NOT_FOUND);
			System.exit(MazeErrors.FILE_NOT_FOUND);
		}

		/* Read the important details of the maze */
		try {
			Scanner j = new Scanner(file);
			maze_width = j.nextInt();
			maze_height = j.nextInt();

			int tmpX = j.nextInt();
			int tmpY = j.nextInt();
			MAZE_EXIT = new MazeCoord(tmpX, tmpY);

			int tmpX1 = j.nextInt();
			int tmpY1 = j.nextInt();
			MAZE_ENTRANCE = new MazeCoord(tmpX1, tmpY1);

			m = new Maze(maze_width, maze_height, MAZE_EXIT, MAZE_ENTRANCE);
			int row = 0;
			
			while (j.hasNextLine()) {
				String tmp = j.nextLine();
				if ((tmp.length() != 0) && (tmp.length() != maze_width)) {
					debugMessage("Exit due to error: "
							+ MazeErrors.FILE_MAZE_MISMATCH + "," + maze_width);
					System.exit(MazeErrors.FILE_MAZE_MISMATCH);
				}

				if (tmp.length() <= 0) {
					continue;
				} else {
					row++;
				}
				m.addData(tmp, row);
				

			}
		} catch (Exception e) {
			debugMessage.output("An error occured in readMazeFile(): " + e.getMessage());
		}

		return m;
	}

	private boolean fileExists(File file) {
		return file.exists();
	}

	private void debugMessage(String str) {
		if (DEBUG_ON) {
			Date d = new Date();
			System.out.println(d.getTime() + " ("
					+ this.getClass().getSimpleName() + "):\t" + str);
		}
	}
}
