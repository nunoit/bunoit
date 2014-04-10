package me.sschaeffner.bunoit;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class MandelDisplay {
	
	//MATH VARS
	double cShiftX, cShiftY, cZoom;
	int maxIterations;
	int width, height;
	
	//DISPLAY VARS
	JFrame frame;
	MandelPanel panel;
	BrotSetter setterPanel;
	
	MandelDisplay () {
		//init MATH
		cShiftX = 0;
		cShiftY = 0;
		cZoom = 1;
		maxIterations = 400;
		
		width = 800;
		height = 800;
		
		//init DISPLAY
		frame = new JFrame("bonuit");
		//frame.setResizable(false);
		
		panel = new MandelPanel();
		setterPanel = new BrotSetter();		
		
		frame.add(BorderLayout.CENTER, panel);
		frame.add(BorderLayout.EAST, setterPanel);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
	
	@SuppressWarnings("serial")
	class MandelPanel extends JPanel {
		
		public MandelPanel() {
			this.setPreferredSize(new Dimension(width, height));
		}
		
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
						g.setColor(AppleColor.getColorForN(nuts[x][y], maxIterations, cZoom));
						g.fillRect(x, y, 1, 1);
					}
				}
			}
		}
	}
	
	@SuppressWarnings("serial")
	class BrotSetter extends JPanel {
		
		GridLayout gridL;
		
		JLabel shiftXLabel, shiftYLabel, zoomLabel, maxIterationsLabel;
		JTextField shiftXTF, shiftYTF, zoomTF, maxIterationsTF;
		
		public BrotSetter() {
			gridL = new GridLayout(0, 2);
			this.setLayout(gridL);
			this.setPreferredSize(new Dimension(300, 300));
			
			shiftXLabel = new JLabel("shift x");
			shiftXTF = new JTextField("" + cShiftX, 50);
			this.add(shiftXLabel);
			this.add(shiftXTF);
			
			shiftYLabel = new JLabel("shift y");
			shiftYTF = new JTextField("" + cShiftY, 50);
			this.add(shiftYLabel);
			this.add(shiftYTF);
			
			zoomLabel = new JLabel("zoom");
			zoomTF = new JTextField("" + cZoom, 50);
			this.add(zoomLabel);
			this.add(zoomTF);
			
			maxIterationsLabel = new JLabel("max Iterations");
			maxIterationsTF = new JTextField("" + maxIterations, 50);
			this.add(maxIterationsLabel);
			this.add(maxIterationsTF);
		}
	}
}
