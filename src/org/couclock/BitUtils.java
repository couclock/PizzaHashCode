package org.couclock;

import java.util.BitSet;
import java.util.Iterator;
import java.util.List;

public class BitUtils {

	public static BitSet getSliceBitSet(int row, int col, int minRow, int maxRow, int minCol, int maxCol) {

		BitSet result = new BitSet(row * col);

		for (int i = minRow; i < maxRow + 1; i++) {
			for (int j = minCol; j < maxCol + 1; j++) {
				result.set(j + i * col);
			}
		}

		return result;
	}

	public static BitSet getSliceBitSet(int[] slice) {

		return getSliceBitSet(slice[0], slice[1], slice[2], slice[3], slice[4], slice[5]);
	}

	public static String getSliceCoordinate(BitSet slice, int rows, int cols) {

		int first = slice.nextSetBit(0);

		int minRow = first / cols;
		int minCol = first % cols;

		int previous = 0, current = slice.nextSetBit(previous);
		while (current != -1) {

			previous = current;
			current = slice.nextSetBit(previous + 1);
		}

		long maxRow = previous / cols;
		long maxCol = previous % cols;

		return minRow + " " + minCol + " " + maxRow + " " + maxCol;
	}

	public static BitSet getUnion(List<int[]> list) {

		BitSet union = new BitSet();

		for (Iterator iterator = list.iterator(); iterator.hasNext();) {
			int[] bitSet = (int[]) iterator.next();
			union.or(getSliceBitSet(bitSet[0], bitSet[1], bitSet[2], bitSet[3], bitSet[4], bitSet[5]));
		}

		return union;
	}

	public static int sumCardinality(List<BitSet> slices) {
		int counter = 0;
		for (Iterator iterator = slices.iterator(); iterator.hasNext();) {
			BitSet bitSet = (BitSet) iterator.next();
			counter += bitSet.cardinality();
		}
		return counter;
	}

}
