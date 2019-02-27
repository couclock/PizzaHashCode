package org.couclock;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class SliceMatcher {

	private static int currentMax = 0;
	private static HashMap<BitSet, List<BitSet>> cache = new HashMap<>();

	public static List<BitSet> getBestSlices(List<int[]> remainingSlices) {

		BitSet union = BitUtils.getUnion(remainingSlices);
		if (cache.containsKey(union)) {
			// System.out.println("Using cache " + union);

			return cache.get(union);
		}

		List<BitSet> bestChoice = new ArrayList<>();

		int localMax = 0;

		for (int[] oneSelectedSlice : remainingSlices) {
			List<BitSet> selected = new ArrayList<>();
			BitSet oneSelectedSliceBS = BitUtils.getSliceBitSet(oneSelectedSlice);
			selected.add(oneSelectedSliceBS);
			List<int[]> newRemaining = new ArrayList<>(remainingSlices);
			newRemaining.remove(oneSelectedSlice);
			for (Iterator iterator = remainingSlices.iterator(); iterator.hasNext();) {
				int[] bitSet = (int[]) iterator.next();
				if (BitUtils.getSliceBitSet(bitSet).intersects(oneSelectedSliceBS)) {
					newRemaining.remove(bitSet);
				}
			}

			BitSet newUnion = BitUtils.getUnion(newRemaining);
			if ((oneSelectedSliceBS.cardinality() + newUnion.cardinality()) < union.cardinality()) {
				// System.out.println("Optim ?");
				continue;
			}

			List<BitSet> bestSlices = getBestSlices(newRemaining);
			selected.addAll(bestSlices);
			int currentScore = BitUtils.sumCardinality(selected);
			if (currentScore > localMax) {
				localMax = currentScore;
				bestChoice = selected;
				// System.out.println("New Best : " + localMax + " : " + bestChoice);
			}
			if (localMax == union.cardinality()) {
				break;
			}
		}

		cache.put(union, bestChoice);

		return bestChoice;

	}

}
