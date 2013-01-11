package com.bhuwan.creature;

import com.bhuwan.main.debug.debugMessage;
import com.bhuwan.maze.*;

public class Creature {
	private final static boolean DEBUG_MODE = false;
	private MazeCoord currentPosition;
	private CreaturePath path;

	public Creature(Maze m) {
		currentPosition = new MazeCoord();
		setCurrentPosition(m.getMazeEntrance());
		m.setCreaturePosition(currentPosition);
		path = new CreaturePath();
	}

	public void setCurrentPosition(MazeCoord mc) {
		currentPosition.setX(mc.getX() + 1);
		currentPosition.setY(mc.getY() + 1);
	}

	public boolean isSquareVisited(MazeCoord mazeCoord) {
		return (path.isSquareVisited(mazeCoord));
	}

	public void markSquareVisited(MazeCoord mazeCoord) {
		path.setSquaresVisited(mazeCoord);
	}

	public MazeCoord getCurrentPosition() {
		return currentPosition;
	}

	public boolean canMoveNorth(Maze m) {
		MazeCoord tmp = getNorthPosition();
		return ((m.validCoord(tmp)) && (m.isCoordClear(tmp)));
	}

	public boolean canMoveSouth(Maze m) {
		MazeCoord tmp = getSouthPosition();
		return ((m.validCoord(tmp)) && (m.isCoordClear(tmp)));
	}

	public boolean canMoveWest(Maze m) {
		MazeCoord tmp = getWestPosition();
		return ((m.validCoord(tmp)) && (m.isCoordClear(tmp)));
	}

	public boolean canMoveEast(Maze m) {
		MazeCoord tmp = getEastPosition();
		return ((m.validCoord(tmp)) && (m.isCoordClear(tmp)));

	}

	public boolean moveEast(Maze m, boolean backtrack) {
		if ((backtrack) || (canMoveEast(m))) {
			if (DEBUG_MODE)
				debugMessage.output("Creature: Moved creature EAST");
			MazeCoord tmp = getEastPosition();
			currentPosition = tmp;
			m.setCreaturePosition(currentPosition);
			path.setSquaresOnPath(tmp);
			return true;
		} else {
			if (DEBUG_MODE)
				debugMessage.output("Creature: EAST move failed");
			return false;
		}
	}

	public boolean moveSouth(Maze m, boolean backtrack) {
		if ((backtrack) || (canMoveSouth(m))) {
			if (DEBUG_MODE)
				debugMessage.output("Creature: Moved creature SOUTH");
			MazeCoord tmp = getSouthPosition();
			currentPosition = tmp;
			m.setCreaturePosition(currentPosition);
			path.setSquaresOnPath(tmp);
			return true;
		} else {
			if (DEBUG_MODE)
				debugMessage.output("Creature: SOUTH move failed");
			return false;
		}
	}

	public boolean moveNorth(Maze m, boolean backtrack) {
		if ((backtrack) || (canMoveNorth(m))) {
			if (DEBUG_MODE)
				debugMessage.output("Creature: Moved creature north");
			MazeCoord tmp = getNorthPosition();
			currentPosition = tmp;
			m.setCreaturePosition(currentPosition);
			path.setSquaresOnPath(tmp);
			return true;
		} else {
			if (DEBUG_MODE)
				debugMessage.output("Creature: North move failed");
			return false;
		}
	}

	public boolean moveWest(Maze m, boolean backtrack) {
		if ((backtrack) || (canMoveWest(m))) {
			if (DEBUG_MODE)
				debugMessage.output("Creature: Moved creature WEST");
			MazeCoord tmp = getWestPosition();
			currentPosition = tmp;
			m.setCreaturePosition(currentPosition);
			path.setSquaresOnPath(tmp);
			return true;
		} else {
			if (DEBUG_MODE)
				debugMessage.output("Creature: WEST move failed");
			return false;
		}
	}

	public boolean isCreatureAtExit(Maze m) {
		return (m.isCoordExit(currentPosition));

	}

	public MazeCoord getNorthPosition() {
		MazeCoord tmp = new MazeCoord(currentPosition);
		tmp.decrement('x');
		return tmp;
	}

	public MazeCoord getSouthPosition() {
		MazeCoord tmp = new MazeCoord(currentPosition);
		tmp.increment('x');
		return tmp;
	}

	public MazeCoord getWestPosition() {
		MazeCoord tmp = new MazeCoord(currentPosition);
		tmp.decrement('y');
		return tmp;
	}

	public MazeCoord getEastPosition() {
		MazeCoord tmp = new MazeCoord(currentPosition);
		tmp.increment('y');
		return tmp;
	}

	public void displayCurrentPosition() {
		debugMessage.output("Creature: Current Position is row="
				+ currentPosition.getX() + ", col=" + currentPosition.getY());
	}

}
