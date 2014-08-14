package se.daggen.common.compare;

import java.util.Comparator;

import se.daggen.common.compare.Compare.OnGoingCompareComparator.OnGoingCompare;

public class Compare {

	private Compare() {
		/* Empty */
	}

	@SafeVarargs
	public static <T extends Comparable<? super T>> T max(T first, T... rest) {
		T maxSoFar = first;
		for (T element : rest) {
			if (is(element).greaterThan(maxSoFar)) {
				maxSoFar = element;
			}
		}

		return maxSoFar;

	}

	@SafeVarargs
	public static <T extends Comparable<? super T>> T min(T first, T... rest) {
		T smallestSoFar = first;
		for (T element : rest) {
			if (is(element).lessThan(smallestSoFar)) {
				smallestSoFar = element;
			}
		}

		return smallestSoFar;

	}

	public static <E extends Comparable<? super E>> OnGoingCompare<E> is(E first) {
		final Comparator<E> comp = new Comparator<E>() {
			@Override
			public int compare(E o1, E o2) {
				return o1.compareTo(o2);
			}

		};

		return new OnGoingCompare<E>(first, comp);
	}

	public static <T> OnGoingCompareComparator<T> withComparator(
			Comparator<T> comp) {
		return new OnGoingCompareComparator<T>(comp);
	}

	static class OnGoingCompareComparator<T> {
		private final Comparator<T> comp;

		private OnGoingCompareComparator(Comparator<T> comp) {
			this.comp = comp;
		}

		public OnGoingCompare<?> is(T first) {
			return new OnGoingCompare<T>(first, comp);
		}

		static class OnGoingCompare<T> {
			private final T first;
			private final Comparator<T> comp;

			private OnGoingCompare(T first, Comparator<T> comparator) {
				this.first = first;
				this.comp = comparator;
			}

			public boolean greaterThan(T second) {
				return comp.compare(first, second) > 0;
			}

			public boolean greaterOrEqualTo(T second) {
				return comp.compare(first, second) >= 0;
			}

			public boolean lessThan(T second) {
				return comp.compare(first, second) < 0;
			}

			public boolean lessOrEqualTo(T second) {
				return comp.compare(first, second) <= 0;
			}

			public boolean equalSizeAs(T second) {
				return comp.compare(first, second) == 0;
			}
		}
	}

}
