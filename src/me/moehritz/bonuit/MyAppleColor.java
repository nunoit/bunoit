package me.moehritz.bonuit;

import java.awt.Color;

public class MyAppleColor {

	private final static double xR = 0.05;
	private final static double sG = 0.5;
	private final static double xB = 0.5;

	private final static int l = 225;

	public static Color getColorForN(int n, int maxIterations) {
		int r = 0, g = 0, b = 0;

		double part = n / (double) maxIterations;

		r = (int) (part < xR ? l - ((1 / xR) * part * l) : 0);
		g = (int) ((part >= sG ? (1 - part) * 2 : part * 2) * l);
		b = (int) (part > 1 - xB ? (1 / xB) * (part - (1 - xB)) * l : 0);

		return new Color(r, g, b);
	}
}
