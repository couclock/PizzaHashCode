package org.couclock;

import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;
import java.util.Scanner;

public class PizzaApp {

	public static void main(String[] args) throws IOException {

		System.setIn(new FileInputStream("./data/medium.in"));
		FileWriter outputFile = new FileWriter("./data/result.out");

		Scanner in = new Scanner(System.in);

		String firstLine = in.nextLine();
		String[] firstLineArr = firstLine.split(" ");

		int R = Integer.parseInt(firstLineArr[0]);
		int C = Integer.parseInt(firstLineArr[1]);
		int L = Integer.parseInt(firstLineArr[2]);
		int H = Integer.parseInt(firstLineArr[3]);

		System.err.println("R : " + R + " C : " + C + " L : " + L + " H : " + H);

		List<List<String>> pizza = new ArrayList<>();

		for (int i = 0; i < R; i++) {
			String row = in.nextLine();
			System.err.println("row : " + row);
			String[] rowD = row.split("");
			List<String> pizzaRow = new ArrayList<>();
			for (int j = 0; j < rowD.length; j++) {
				pizzaRow.add(rowD[j]);
			}
			pizza.add(pizzaRow);
		}
		System.out.println(pizza);

		List<BitSet> slices = Slicer.slicer(pizza, R, C, L, H);
		System.out.println("slices count : " + slices.size());

		List<BitSet> selectedSlices = SliceMatcher.getBestSlices(slices);

		System.out.println(selectedSlices);

		outputFile.write(selectedSlices.size() + "\n");
		for (BitSet bitSet : selectedSlices) {
			String sliceCoordinate = BitUtils.getSliceCoordinate(bitSet, R, C);
			outputFile.write(sliceCoordinate + "\n");
			System.out.println(sliceCoordinate);
		}

		outputFile.close();

	}

}
