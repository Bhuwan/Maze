package com.bhuwan.solver;

import com.bhuwan.main.debug.debugMessage;
import com.bhuwan.maze.*;
import com.bhuwan.creature.*;

public class Solver {
	public boolean goEast(Maze maze, Creature c) {
		boolean success = false;

		/*
		 * IF (the square to the north is clear AND we are inside the maze AND
		 * we haven't visited this position)
		 */
		if ((c.canMoveEast(maze)) && (true)
				&& !(c.isSquareVisited(c.getEastPosition()))) {
			/*
			 * Move to the NORTH (marks the square as part of the PATH)
			 */
			MazeCoord prevPosition = new MazeCoord(c.getCurrentPosition());
			
			c.moveEast(maze, false);
			//maze.printMaze();
			/*
			 * .....IF (at exit) .......................................
			 * ..........return success ................................
			 */
			if (c.isCreatureAtExit(maze)) {
				debugMessage.output("\t\t goEAST(): at the exit");
				success = true;
			}
			/* .....ELSE ................................................ */
			else {
				/*
				 * ...........success = goNorth(maze,creature) ..............
				 */
				success = goNorth(maze, c);
				/*
				 * ...........IF (goNorth() NOT successful) .................
				 */
				if (!success) {

					/*
					 * success = goWest(maze, creature)
					 */
					success = goEast(maze, c);
					/*
					 * IF (!goWest() NOT successful)
					 */
					if (!success) {
						/*
						 * success = goEast(maze,creature)
						 */
						success = goSouth(maze, c);
						/*
						 * IF (!goEast() NOT successful)
						 */
						if (!success) {
							/*
							 * Mark Square visited AND BACKTRACK WEST
							 */
							debugMessage.output("\t\t goEast(): BackTrack");
							c.markSquareVisited(c.getCurrentPosition());
							if (!maze.removeCreaturePath(c.getCurrentPosition())) {
								debugMessage.output("\t\t goEast(): Serious error here");
							}
							
							c.moveWest(maze, true);
							/*
							 * END IF
							 */
						}
						/*
						 * .....................END
						 * IF...............................
						 */
					}
					/*
					 * ............END-IF........................................
					 */
				}
				/*
				 * ......END ELSE IF ........................................
				 */
			}
			/*
			 * ELSE .....................................................
			 * ......success = false;
			 */} else {
			success = false;
		}

		/*
		 * return success
		 */
		return success;
	}
	public boolean goSouth(Maze maze, Creature c) {
		boolean success = false;

		/*
		 * IF (the square to the north is clear AND we are inside the maze AND
		 * we haven't visited this position)
		 */
		//debugMessage.output("\tINSIDE goNorth");
		if ((c.canMoveSouth(maze)) && (true)
				&& !(c.isSquareVisited(c.getSouthPosition()))) {

			/*
			 * Move to the NORTH (marks the square as part of the PATH)
			 */
			MazeCoord prevPosition = new MazeCoord(c.getCurrentPosition());
			c.moveSouth(maze, false);
			//maze.printMaze();
			/*
			 * .....IF (at exit) .......................................
			 * ..........return success ................................
			 */
			if (c.isCreatureAtExit(maze)) {
				debugMessage.output("\t\\t goSouth: at the exit");
				success = true;
			}
			/* .....ELSE ................................................ */
			else {
				/*
				 * ...........success = goNorth(maze,creature) ..............
				 */
				success = goSouth(maze, c);
				/*
				 * ...........IF (goNorth() NOT successful) .................
				 */
				if (!success) {

					/*
					 * success = goWest(maze, creature)
					 */
					success = goWest(maze, c);
					/*
					 * IF (!goWest() NOT successful)
					 */
					if (!success) {
						/*
						 * success = goEast(maze,creature)
						 */
						success = goEast(maze, c);
						/*
						 * IF (!goEast() NOT successful)
						 */
						if (!success) {
							/*
							 * Mark Square visited AND BACKTRACK NORTH
							 */
							debugMessage.output("\t\t goSouth(): BackTrack");
							c.markSquareVisited(c.getCurrentPosition());
							if (!maze.removeCreaturePath(c.getCurrentPosition())) {
								debugMessage.output("\t\t goSouth(): Serious error here");
							}
							
							c.moveNorth(maze, true);
							/*
							 * END IF
							 */
						}
						/*
						 * .....................END
						 * IF...............................
						 */
					}
					/*
					 * ............END-IF........................................
					 */
				}
				/*
				 * ......END ELSE IF ........................................
				 */
			}
			/*
			 * ELSE .....................................................
			 * ......success = false;
			 */} else {
			success = false;
		}

		/*
		 * return success
		 */
		//debugMessage.output("\tgoNorth() DONE");
		return success;
	}
	public boolean goWest(Maze maze, Creature c) {
		boolean success = false;

		/*
		 * IF (the square to the north is clear AND we are inside the maze AND
		 * we haven't visited this position)
		 */
		//debugMessage.output("\tINSIDE goWest");
		if ((c.canMoveWest(maze)) && (true)
				&& !(c.isSquareVisited(c.getWestPosition()))) {
			/*
			 * Move to the NORTH (marks the square as part of the PATH)
			 */
			MazeCoord prevPosition = new MazeCoord(c.getCurrentPosition());
			
			c.moveWest(maze, false);
			//maze.printMaze();
			/*
			 * .....IF (at exit) .......................................
			 * ..........return success ................................
			 */
			if (c.isCreatureAtExit(maze)) {
				debugMessage.output("\t\tgoWest: at the exit");
				success = true;
			}
			/* .....ELSE ................................................ */
			else {
				/*
				 * ...........success = goNorth(maze,creature) ..............
				 */
				success = goNorth(maze, c);
				/*
				 * ...........IF (goNorth() NOT successful) .................
				 */
				if (!success) {

					/*
					 * success = goWest(maze, creature)
					 */
					success = goWest(maze, c);
					/*
					 * IF (!goWest() NOT successful)
					 */
					if (!success) {
						/*
						 * success = goEast(maze,creature)
						 */
						success = goSouth(maze, c);
						/*
						 * IF (!goEast() NOT successful)
						 */
						if (!success) {
							/*
							 * Mark Square visited AND BACKTRACK EAST
							 */
							debugMessage.output("\t\t goWest(): BackTrack");
							c.markSquareVisited(c.getCurrentPosition());
							if (!maze.removeCreaturePath(c.getCurrentPosition())) {
								debugMessage.output("\t\t goWest(): Serious error here");
							}
							
							c.moveEast(maze, true);
							/*
							 * END IF
							 */
						}
						/*
						 * .....................END
						 * IF...............................
						 */
					}
					/*
					 * ............END-IF........................................
					 */
				}
				/*
				 * ......END ELSE IF ........................................
				 */
			}
			/*
			 * ELSE .....................................................
			 * ......success = false;
			 */} else {
			success = false;
		}

		/*
		 * return success
		 */
		//debugMessage.output("\tgoWest() DONE");
		return success;
	}

	public boolean goNorth(Maze maze, Creature c) {
		boolean success = false;

		/*
		 * IF (the square to the north is clear AND we are inside the maze AND
		 * we haven't visited this position)
		 */
		//debugMessage.output("\tINSIDE goNorth");
		if ((c.canMoveNorth(maze)) && (true)
				&& !(c.isSquareVisited(c.getNorthPosition()))) {

			/*
			 * Move to the NORTH (marks the square as part of the PATH)
			 */
			
			c.moveNorth(maze, false);
			//maze.printMaze();
			/*
			 * .....IF (at exit) .......................................
			 * ..........return success ................................
			 */
			if (c.isCreatureAtExit(maze)) {
				debugMessage.output("\t\tgoNORTH: at the exit");
				success = true;
			}
			/* .....ELSE ................................................ */
			else {
				/*
				 * ...........success = goNorth(maze,creature) ..............
				 */
				success = goNorth(maze, c);
				/*
				 * ...........IF (goNorth() NOT successful) .................
				 */
				if (!success) {

					/*
					 * success = goWest(maze, creature)
					 */
					success = goWest(maze, c);
					/*
					 * IF (!goWest() NOT successful)
					 */
					if (!success) {
						/*
						 * success = goEast(maze,creature)
						 */
						success = goEast(maze, c);
						/*
						 * IF (!goEast() NOT successful)
						 */
						if (!success) {
							/*
							 * Mark Square visited AND BACKTRACK SOUTH
							 */
							
							debugMessage.output("\t\tgoNorth(): BackTrack");
							c.markSquareVisited(c.getCurrentPosition());
							if (!maze.removeCreaturePath(c.getCurrentPosition())) {
								debugMessage.output("\t\t goNorth(): Serious error here");
							}
							
							c.moveSouth(maze, true);
							
							
							/*
							 * END IF
							 */
						}
						/*
						 * .....................END
						 * IF...............................
						 */
					}
					/*
					 * ............END-IF........................................
					 */
				}
				/*
				 * ......END ELSE IF ........................................
				 */
			}
			/*
			 * ELSE .....................................................
			 * ......success = false;
			 */} else {
			success = false;
		}

		/*
		 * return success
		 */
		//debugMessage.output("\tgoNorth() DONE");
		return success;
	}
}
