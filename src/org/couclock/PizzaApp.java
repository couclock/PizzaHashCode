package org.couclock;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class PizzaApp {

	public static void main(String[] args) throws FileNotFoundException {

		System.setIn(new FileInputStream("./data/example.in"));

		Scanner in = new Scanner(System.in);

		String firstLine = in.nextLine();
		String[] firstLineArr = firstLine.split(" ");

		int R = Integer.parseInt(firstLineArr[0]);
		int C = Integer.parseInt(firstLineArr[1]);
		int L = Integer.parseInt(firstLineArr[2]);
		int H = Integer.parseInt(firstLineArr[3]);

		System.err.println("R : " + R + " C : " + C + " L : " + L + " H : " + H);

		for (int i = 0; i < R; i++) {
			String row = in.nextLine();
			System.err.println("row : " + row);
		}

	}

}
