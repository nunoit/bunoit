package me.sschaeffner.bunoit;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class MandelDisplay {
	
	//MATH VARS
	double cShiftX, cShiftY, cZoom;
	int maxIterations;
	int width, height;
	
	//DISPLAY VARS
	JFrame frame;
	MandelPanel panel;
	
	MandelDisplay () {
		//init MATH
		cShiftX = 0;
		cShiftY = 0;
		cZoom = 0.45;
		maxIterations = 400;
		
		width = 800;
		height = 800;
		
		//init DISPLAY
		frame = new JFrame("bonuit");
		frame.setSize(width, height);
		
		panel = new MandelPanel();
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		frame.add(panel);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
	
	class MandelPanel extends JPanel {
		@Override
		public void paint(Graphics g) {
			super.paint(g);
			
			NutSet set = MathBrot.getNutSet(cShiftX, cShiftY, cZoom, width, height, maxIterations);
			int[][] nuts = set.getNuts();
			for (int x = 0; x < width; x++) {
				for (int y = 0; y < height; y++) {
					if (nuts[x][y] == -1) {
						g.setColor(Color.BLACK);
						g.fillRect(x, y, 1, 1);
					} else {
						g.setColor(AppleColor.getColorForN(nuts[x][y], maxIterations));
						g.fillRect(x, y, 1, 1);
					}
				}
			}
		}
	}
}
