package se.daggen.common;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;

public class Primes {

	private static LinkedList<Integer> primeNumbers;
	
	static {
		initilizePrimeNumberList();
	}
	private static void initilizePrimeNumberList() {
		primeNumbers = new LinkedList<>();
		primeNumbers.addAll(Arrays.asList(2, 3, 5, 7, 11));
	}
	
	/**
	 * Check if a number is a prime number
	 * @param numberToCheck
	 * @return true if it is a prime number else false
	 */
	public static boolean isPrime(final int numberToCheck) {
		if (numberToCheck < 2)
			return false;
		if (isPrimeAccordingToList(numberToCheck)) {
			if (!isListBigEnoughToCheck(numberToCheck)) {
				increaseListToCheckNumbersAsBigAs(numberToCheck);
				return isPrimeAccordingToList(numberToCheck);
			}
			return true;
		} else 
			return false;
	}
	private static boolean isPrimeAccordingToList(int numberToCheck) {
		Iterator<Integer> iterator = primeNumbers.iterator();
		while (iterator.hasNext()) {
			int primeNumber = iterator.next();
			if (primeNumber == numberToCheck) {
				return true;
			} else if (numberToCheck % primeNumber == 0) {
				return false;
			} else if (java.lang.Math.sqrt(numberToCheck) <= primeNumber) {
				break;
			}
		}
		return true;
	}
	private static boolean isListBigEnoughToCheck(int numberToCheck) {
		return numberToCheck <= java.lang.Math.sqrt(primeNumbers.getLast());
	}
	private static void increaseListToCheckNumbersAsBigAs(final int upperlimit) {
		int startFrom = primeNumbers.getLast() + 1;
		for (int candidate = startFrom; candidate <= java.lang.Math.sqrt(upperlimit) ; candidate++) {
			if (isPrimeAccordingToList(candidate)) {
				primeNumbers.add(candidate);
			}
		}
	}

	/**
	 * Returns the largest prime number which is equal or less then upperlimit
	 * @param upperLimit upperbound for possible retuned prime number. Must greater or equal to 2. 
	 * @return a prime number
	 */
	public static int getfirstPrimNumberSmallerThen(int upperLimit) {
		checkInput(upperLimit);
		if (!isListBigEnoughToCheck(upperLimit * upperLimit)) {
			increaseListToCheckNumbersAsBigAs(upperLimit * upperLimit);
		}
		
		Iterator<Integer> iterator = primeNumbers.iterator();
		int lastNumber = iterator.next();
		
		while (iterator.hasNext()) {
			int primeNumber = iterator.next();
			if (primeNumber > upperLimit)
				break;
			else 
				lastNumber = primeNumber;				
				
		}
		return lastNumber;
		
	}
	private static void checkInput(int upperLimit) {
		if (upperLimit < 2)
			throw new IllegalArgumentException("UpperLimit must be at least 2");
	}

	public static boolean isPrime(Collection<Integer> integersToCheck) {
		for (int integer : integersToCheck) {
			if (!isPrime(integer)) {
				return false;
			}
		}
		return true;
	}
}
