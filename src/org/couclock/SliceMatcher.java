package org.couclock;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class SliceMatcher {

	private static int currentMax = 0;
	private static HashMap<BitSet, List<BitSet>> cache = new HashMap<>();

	public static List<BitSet> getBestSlices(List<BitSet> remainingSlices) {

		BitSet union = BitUtils.getUnion(remainingSlices);
		if (cache.containsKey(union)) {
			// System.out.println("Using cache " + union);

			return cache.get(union);
		}

		List<BitSet> bestChoice = new ArrayList<>();

		int localMax = 0;

		for (BitSet oneSelectedSlice : remainingSlices) {
			List<BitSet> selected = new ArrayList<>();
			selected.add(oneSelectedSlice);
			List<BitSet> newRemaining = new ArrayList<>(remainingSlices);
			newRemaining.remove(oneSelectedSlice);
			for (Iterator iterator = remainingSlices.iterator(); iterator.hasNext();) {
				BitSet bitSet = (BitSet) iterator.next();
				if (bitSet.intersects(oneSelectedSlice)) {
					newRemaining.remove(bitSet);
				}
			}

			BitSet newUnion = BitUtils.getUnion(newRemaining);
			if ((oneSelectedSlice.cardinality() + newUnion.cardinality()) < union.cardinality()) {
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
