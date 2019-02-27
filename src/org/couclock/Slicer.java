package org.couclock;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Slicer {

	public static List<int[]> slicer(List<List<String>> pizza, int rows, int cols, int min, int max) {
		List<int[]> slices = new ArrayList<>();

		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {

				for (int width = 0; width < rows - i; width++) {
					for (int height = 0; height < cols - j; height++) {
						int taille = (width + 1) * (height + 1);
						if (taille > max) {
							continue;
						}
						if (!checkMinCount(pizza, i, i + width, j, j + height, min)) {
							continue;
						}

						// System.out.println("Slice : " + i + " " + j + " " + (i + width) + " " + (j +
						// height));
						int[] slice = { rows, cols, i, i + width, j, j + height };
						// BitSet slice = BitUtils.getSliceBitSet(rows, cols, i, i + width, j, j +
						// height);
						// System.out.println("BitSet : " + slice);
						slices.add(slice);
						// System.out.println("Taille : " + ((width + 1) * (height + 1)));
					}

				}

			}
		}

		return slices;

	}

	private static boolean checkMinCount(List<List<String>> pizza, int minRow, int maxRow, int minCol, int maxCol,
			int min) {

		int tomatoCount = 0;
		int mushroomCount = 0;

		List<List<String>> pizzaRows = pizza.subList(minRow, maxRow + 1);

		for (Iterator iterator = pizzaRows.iterator(); iterator.hasNext();) {
			List<String> oneRow = (List<String>) iterator.next();
			List<String> oneSubRow = oneRow.subList(minCol, maxCol + 1);
			for (Iterator iterator2 = oneSubRow.iterator(); iterator2.hasNext();) {
				String oneCell = (String) iterator2.next();
				tomatoCount = oneCell.equals("T") ? tomatoCount + 1 : tomatoCount;
				mushroomCount = oneCell.equals("M") ? mushroomCount + 1 : mushroomCount;
			}

		}

		return tomatoCount >= min && mushroomCount >= min;

	}

}
