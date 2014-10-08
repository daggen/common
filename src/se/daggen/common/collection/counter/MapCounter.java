package se.daggen.collection.counter;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

import se.daggen.collection.counter.Counter;

public class MapCounter<E> implements Counter<E> {
	
	private final Map<E, AtomicInteger> counter;

	public MapCounter () {
		counter = new ConcurrentHashMap<E, AtomicInteger>();
	}

	@Override
	public void add(E itemToCount) {
		AtomicInteger counter = getCounterFor(itemToCount);
		counter.incrementAndGet();
			
	}

	private AtomicInteger getCounterFor(E itemToCount) {
		if (!counter.containsKey(itemToCount)) {
			synchronized (counter) {
				if (!counter.containsKey(itemToCount)) {
					AtomicInteger counterForItem= new AtomicInteger(0);
					counter.put(itemToCount, counterForItem);
				}
			}
		}
		return counter.get(itemToCount);
	}

	@Override
	public void addAll(Collection<E> itemsToCount) {
		for(E itemToCount : itemsToCount) {
			add(itemToCount);
		}		
	}

	@Override
	public int numberOfoccurancesOf(E item) {
		if (counter.containsKey(item)) {
			return counter.get(item).get();
		}
		return 0;
	}

	@Override
	public Map<E, Integer> toMap() {
		Map<E, Integer> newMap = new HashMap<>();
		for (Map.Entry<E, AtomicInteger> entry : counter.entrySet()) {
			newMap.put(entry.getKey(), entry.getValue().get());
		}
		return newMap;
	}

}
