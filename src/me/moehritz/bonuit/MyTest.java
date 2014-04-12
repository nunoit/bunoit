package me.moehritz.bonuit;

import java.awt.Color;

public class MyTest {

	public static void main(String[] args) {
		Color c = MyAppleColor.getColorForN(50, 1000);
		System.out.println("r" + c.getRed() + " g" + c.getGreen() + " b"
				+ c.getBlue());
	}
}
