package me.moehritz.bonuit;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.swing.JPanel;
import javax.swing.JWindow;

import me.sschaeffner.bunoit.MathBrot;

public class MandelbrotBaecker {

	private List<Nut> nuts;

	private double zoom = 0.35;
	private double shiftX = 0;
	private double shiftY = 0;
	private int maxIterations = 400;

	private int sizeX = Toolkit.getDefaultToolkit().getScreenSize().width,
			sizeY = Toolkit.getDefaultToolkit().getScreenSize().height,
			biggest;
	private double middleX, middleY, zoomX, zoomY;
	private JWindow frame;

	public static void main(String[] args) {
		MandelbrotBaecker b = new MandelbrotBaecker();
		b.go();
		Scanner s = new Scanner(System.in);
		all: while (true) {
			try {
				String x = s.nextLine();
				String y = x.substring(0, 1);
				switch (y) {
				case "l":
					b.shiftX -= Double.parseDouble(x.substring(1)) / b.zoom;
					break;
				case "r":
					b.shiftX += Double.parseDouble(x.substring(1)) / b.zoom;
					break;
				case "u":
					b.shiftY -= Double.parseDouble(x.substring(1)) / b.zoom;
					break;
				case "d":
					b.shiftY += Double.parseDouble(x.substring(1)) / b.zoom;
					break;
				case "+":
					b.zoom += Double.parseDouble(x.substring(1));
					break;
				case "-":
					b.zoom -= Double.parseDouble(x.substring(1));
					break;
				case "*":
					b.maxIterations += Double.parseDouble(x.substring(1));
					break;
				case "/":
					b.maxIterations -= Double.parseDouble(x.substring(1));
					break;
				case "exit":
					break all;
				default:
					System.out.println("Hm?");
					break;
				}
				b.prepare(false);
				b.start();
				b.frame.repaint();
				System.out.println("Zoom:" + b.zoom + " X:" + b.shiftX + " Y:"
						+ b.shiftY + " i:" + b.maxIterations);
			} catch (Exception e) {
				System.out.println("Möp.");
				e.printStackTrace();
			}
		}
		s.close();
	}

	public void go() {
		frame = new JWindow();
		MandelPanel panel = new MandelPanel();
		frame.setContentPane(panel);
		frame.setSize(sizeX, sizeY);
		frame.setLocationRelativeTo(null);
		prepare(true);
		start();
		frame.setVisible(true);
	}

	public void prepare(boolean neu) {
		middleX = sizeX * 0.5;
		middleY = sizeY * 0.5;

		zoomX = middleX * zoom;
		zoomY = middleY * zoom;
		if (neu) {
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
			double a = ((x - middleX) / zoomX) + shiftX;
			double b = ((y - middleY) / zoomY) + shiftY;
			count = MathBrot.isElement(a, b, maxIterations);
			if (biggest < count)
				biggest = count;
		}
	}

	@SuppressWarnings("serial")
	public class MandelPanel extends JPanel {

		@Override
		public void paint(Graphics g) {
			for (Nut n : nuts) {
				g.setColor(n.count == -1 ? Color.BLACK : MyAppleColor
						.getColorForN(n.count, biggest));
				g.fillRect(n.x, n.y, 1, 1);
				g.setColor(Color.WHITE);
				g.drawRect((frame.getWidth() / 2) - 5,
						(frame.getHeight() / 2) - 5, 10, 10);
			}
		}
	}

}
