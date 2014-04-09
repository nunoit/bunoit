package me.sschaeffner.bunoit;

public class MathBrot {
	
	public static NutSet getNutSet (double shiftX, double shiftY, double zoom, int width, int height, int maxIterations) {
		NutSet set = new NutSet(width, height);
		for (int xc = 0; xc < width; xc++) {
			for (int yc = 0; yc < height; yc++) {
				double a = xc - (width / 2);
				double b = yc - (height / 2);
				set.setPixel(xc, yc, isElement(a, b, maxIterations));
			}
		}
		
		return set;
	}
	
	//returns -1 when point is element of the mandelbrot quantity
	public static int isElement(double a, double b, int maxIterations) {
	    double x = 0, x2, y = 0;
	    for (int n = 0; n < maxIterations; n++) {
	        x2 = x * x - y * y + a;
	        y = 2 * x * y + b;
	        x = x2;
	        if (x * x + y * y > 4) {
	            return n;
	        }
	    }
	    return -1;
	}
}
