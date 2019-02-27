package org.couclock;

import java.util.BitSet;

public class MyBitSet {

	int minRow;
	int maxRow;
	int minCol;
	int maxCol;

	int rowCount;
	int colCount;

	public MyBitSet(int minRow, int maxRow, int minCol, int maxCol, int rowCount, int colCount) {
		super();
		this.minRow = minRow;
		this.maxRow = maxRow;
		this.minCol = minCol;
		this.maxCol = maxCol;
		this.rowCount = rowCount;
		this.colCount = colCount;
	}

	public BitSet getBitSet() {
		return BitUtils.getSliceBitSet(rowCount, colCount, minRow, maxRow, minCol, maxCol);
	}

}
