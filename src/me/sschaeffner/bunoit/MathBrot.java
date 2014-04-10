package me.sschaeffner.bunoit;

public class MathBrot {
	
	public static NutSet getNutSet (double shiftX, double shiftY, double zoom, int width, int height, int maxIterations) {
		NutSet set = new NutSet(width, height);
		long start = System.currentTimeMillis();
		for (int xc = 0; xc < width; xc++) {
			for (int yc = 0; yc < height; yc++) {
				double a = (xc - (width * 0.5)) / ((width * 0.5) * zoom) + (shiftX / zoom);
				double b = (yc - (height * 0.5)) / ((height * 0.5) * zoom) + (shiftY / zoom);
				set.setPixel(xc, yc, isElement(a, b, maxIterations));
			}
		}
		long time = System.currentTimeMillis() - start;
		System.out.println("calculation time: " + time);
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
