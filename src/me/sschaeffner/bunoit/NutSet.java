package me.sschaeffner.bunoit;

public class NutSet {
	private int[][] nuts;
	private int width, height;
	
	public NutSet (int width, int height) {
		this.width = width;
		this.height = height;
		
		nuts = new int[width][height];
	}
	
	public void setPixel (int x, int y, int n) {
		nuts[x][y] = n;
	}
	
	public int[][] getNuts() {
		return nuts;
	}
	
	public int getWidth () {
		return width;
	}
	
	public int getHeight () {
		return height;
	}
}
