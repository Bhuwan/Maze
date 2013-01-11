package com.bhuwan.creature;

import java.util.*;
import com.bhuwan.maze.*;

public class CreaturePath {
	private List<MazeCoord> squaresVisited;
	private List<MazeCoord> squaresOnPath;

	public CreaturePath() {
		squaresVisited = new ArrayList<MazeCoord>();
		squaresOnPath = new ArrayList<MazeCoord>();
	}

	public void setSquaresVisited(MazeCoord c) {
		squaresVisited.add(c);
	}

	public void setSquaresOnPath(MazeCoord c) {
		squaresOnPath.add(c);
	}

	public List<MazeCoord> getSquaresOnPath() {
		return squaresOnPath;
	}

	public List<MazeCoord> getSquaresVisited() {
		return squaresVisited;
	}
	public boolean isSquareVisited(MazeCoord mazeCoord) {
		for (int x = 0; x < squaresVisited.size(); x++) {
			MazeCoord tmpCoord = squaresVisited.get(x);
			if ((tmpCoord.getX() == mazeCoord.getX())
					&& (tmpCoord.getY() == mazeCoord.getY())) {
				return true;
			}
		}
		return false;
	}
}
