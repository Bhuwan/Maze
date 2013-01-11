package com.bhuwan.main;

import com.bhuwan.main.debug.debugMessage;
import com.bhuwan.maze.*;
import com.bhuwan.creature.*;
import com.bhuwan.solver.Solver;

public class Main {
	public static void main(String[] args) {
		debugMessage.output("Begin execution");
		/* Initialize the Maze Reader, Maze object, creature */
		MazeReader mazeRdr = new MazeReader("Maze.txt");;
		Maze m = mazeRdr.readMazeFile();
		
		/* Print the maze */
		m.printMaze();
		
		/* new creature */
		Creature c = new Creature(m); 
		
		/* Init the SOlver */
		Solver solver = new Solver();
		solver.goNorth(m, c);
		m.printMaze();
		debugMessage.output("Execution completed");
	}
}
