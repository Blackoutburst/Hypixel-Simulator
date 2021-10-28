package com.blackoutburst.sim.core;

import java.util.Comparator;

public class PlayerComparator implements Comparator<HPlayer> {
	
	public int compare(HPlayer b, HPlayer a) {
		int comparator = Integer.valueOf(a.score).compareTo(b.score);
		return comparator == 0 ? Integer.valueOf(a.score).compareTo(b.score) : comparator;
	}
}
