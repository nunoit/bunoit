package me.sschaeffner.bunoit;

import java.awt.Color;

public class AppleColor {
	public static Color getColorForN (int n, int maxIterations) {
		int r = 0, g = 0, b = 255;
		
		double part = ((double) n / maxIterations) * (double) (maxIterations / 4);
		
		if (part > 1) {
			part = 1;
		}
		
		//mehr iterationen (part näher 1) -> mehr rot
		r = (int) Math.round(255 * part);
		b = 255 - r;
		
		if (r != 0) {
			System.out.println("n=" + n + "; part=" + part + "; r=" + r + "; b=" + b + ";");
		}
		
		return new Color(r, g, b);
	}
}
