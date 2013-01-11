package com.bhuwan.maze;

public class MazeCoord implements Cloneable {
	private int x;
	private int y;
	public MazeCoord() {
		x = -1;
		y = -1;
	}
	
	public MazeCoord(MazeCoord mc) {
		this.x = mc.getX();
		this.y = mc.getY();
	}
	public MazeCoord(int x, int y) {
		this.setX(x);
		this.setY(y);
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getX() {
		return x;
	}
	public void setY(int y) {
		this.y = y;
	}
	public int getY() {
		return y;
	}
	public void increment(char c) {
		if (c == 'x' || c == 'X') {
			this.x++;
		} else {
			this.y++;
		}
	}
	public void decrement(char c) {
		if (c == 'x' || c == 'X') {
			this.x--;
		} else {
			this.y--;
		}
	}
	protected Object clone() throws CloneNotSupportedException {
		MazeCoord clone = (MazeCoord) super.clone();
		return clone;
	}
	
	
}
