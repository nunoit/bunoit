package me.moehritz.bonuit;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JWindow;

import me.sschaeffner.bunoit.AppleColor;
import me.sschaeffner.bunoit.MathBrot;

public class MandelbrotBaecker {

	private List<Nut> nuts;
	
	private double zoom = 400000;
	private double shiftX = 80020000;
	private double shiftY = 0;
	private int maxIterations = 400;
	
	private int sizeX = Toolkit.getDefaultToolkit().getScreenSize().width, sizeY = Toolkit.getDefaultToolkit().getScreenSize().height;
	private double middleX, middleY, zoomX, zoomY;

	public static void main(String[] args) {
		new MandelbrotBaecker().go();
	}

	public void go() {
		JWindow frame = new JWindow();
		MandelPanel panel = new MandelPanel();
		frame.setContentPane(panel);
		frame.setSize(sizeX, sizeY);
		frame.setLocationRelativeTo(null);
		prepare();
		start();
		panel.nuts = this.nuts;
		frame.setVisible(true);
	}

	public void prepare() {
		middleX = sizeX * 0.5;
		middleY = sizeY * 0.5;

		zoomX = middleX * zoom;
		zoomY = middleY * zoom;

		nuts = new ArrayList<>();

		for (int x = 0; x < sizeX; x++) {
			for (int y = 0; y < sizeY; y++) {
				Nut n = new Nut();
				n.x = x;
				n.y = y;
				nuts.add(n);
			}
		}
		System.out.println("Created " + (sizeX * sizeY) + " pixels!");
	}

	public void start() {
		long start = System.currentTimeMillis();
		nuts.parallelStream().forEach(n -> n.calc());
		long time = System.currentTimeMillis() - start;
		System.out.println("Done! It took " + time + "ms.");
	}

	public class Nut {
		public int count = 0, x, y;

		public void calc() {
			double a = (x - middleX + shiftX) / zoomX;
			double b = (y - middleY + shiftY) / zoomY;
			count = MathBrot.isElement(a, b, maxIterations);
		}
	}

	@SuppressWarnings("serial")
	public class MandelPanel extends JPanel {
		private List<Nut> nuts;

		@Override
		public void paint(Graphics g) {
			g.drawRect(1, 1, sizeX - 2, sizeY - 2);
			for (Nut n : nuts) {
				g.setColor(n.count == -1 ? Color.BLACK : AppleColor
						.getColorForN(n.count, maxIterations));
				g.fillRect(n.x, n.y, 1, 1);
			}
		}
	}

}
