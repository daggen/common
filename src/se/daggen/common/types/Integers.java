package se.daggen.common.types;

import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import se.daggen.common.collection.Tuple;

public class Integers {
	private static final String ALL_NUMBERS = "123456789";

	public static List<Integer> getIntegerSplited(int integerToSplit) {
		List<Integer> listOfNumbers = new LinkedList<>();
		while (integerToSplit != 0) {
			listOfNumbers.add(0, integerToSplit % 10);
			integerToSplit /= 10;
		}
		
		return listOfNumbers;
	}

	public static boolean isAMulticanPandigital(int number) {
		List<Tuple<Integer, Integer>> multiplier = getMultiplierFor(number);
		for (Tuple<Integer, Integer> entry : multiplier) {
			String from = "" + number + entry.x + "" + entry.y;
			if (Strings.isAnagram(from, ALL_NUMBERS))
				return true;
		}
		return false;	
	}

	public static List<Tuple<Integer, Integer>> getMultiplierFor(final int number) {
		List<Tuple<Integer, Integer>> mulitpliers = new LinkedList<>();
		for (int i = 1; i <= java.lang.Math.sqrt(number); i++) {
			if (number % i == 0) {
				int multipelcand = number / i;
				Tuple<Integer, Integer> multiplierPair = new Tuple<Integer, Integer>(i, multipelcand);
				mulitpliers.add(multiplierPair);
			}
		}
		return mulitpliers;
	}

	public static boolean isDigitFactorial(final int number) {
		List<Integer> integers = Integers.getIntegerSplited(number);
		int sum = 0;
		for (Integer integer : integers) {
			sum += fraction(integer);
		}
		return sum == number;
	}
	private static int fraction(final int number) {
		int fraction = 1;
		for (int i = 2; i <= number; i++) {
			fraction *= i;
		}
		return fraction;
	}


}
