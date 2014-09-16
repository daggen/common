package se.daggen.common.collection.counter;

import java.util.Collection;
import java.util.Map;

public interface Counter<E> {
	
	void add(E itemToCount);
	void addAll(Collection<E> itemsToCount);
	int numberOfoccurancesOf(E item);
	Map<E, Integer> toMap();
}
