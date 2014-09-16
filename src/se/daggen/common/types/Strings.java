package se.daggen.common.types;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import se.daggen.common.collection.counter.Counter;
import se.daggen.common.collection.counter.MapCounter;

public class Strings {
	
	/**
	 * Concatenate a number of strings to one using a delimiantor
	 * @param strings will pick them in same order as the iterator for the collection
	 * @param delimitater will be inserted between each pair.  
	 * @return The new concatenated string with deliminator
	 */
	public static String concatenate(Collection<String> strings, String delimitater) {
		if (strings.size() > 0) {
			return concatenateAllStrings(strings, delimitater);
		}
		return "";
	}
	private static String concatenateAllStrings(Collection<String> strings,
			String delimitater) {
		StringBuilder result = new StringBuilder();
		Iterator<String> iterator = strings.iterator();
		
		appendHeadOfStringsToResult(iterator, result);
		appendTailOfStringsToResult(iterator, result, delimitater);
		
		return result.toString();
	}
	private static void appendTailOfStringsToResult(Iterator<String> iterator,
			StringBuilder result, String delimitater) {
		while (iterator.hasNext()) {
			String stringToUse = iterator.next();
			result.append(delimitater.concat(stringToUse));
		}
	}
	private static void appendHeadOfStringsToResult(Iterator<String> iterator,
			StringBuilder result) {
		result.append(iterator.next());
	}

	/**
	 * Will replicate a string fixed number of time and seperate them with deliminator
	 * @param string to be replicated
	 * @param numberOfTimes the string should be replicated. Cannot be negative.
	 * @param delimitater To use between the pairs.
	 * @return
	 */
	public static String repeatString(String string, int numberOfTimes, String delimitater) {
		validateArguments(numberOfTimes);
		String[] strings = new String[numberOfTimes];
		Arrays.fill(strings, string);
		return concatenate(Arrays.asList(strings), delimitater);
	}
	private static void validateArguments(int numberOfTimes) {
		if (numberOfTimes < 0)
			throw new IllegalArgumentException("NumberOfTimes must be positive");
	}
	
	/**
	 * Will get the largest part of text which is replication from a string
	 * @param text to find the replication from
	 * @return the smallest set of chars which can describe the replication
	 */
	public static String getLargestRepativeStringIn(String text) {
		List<String> substrings = getAllSubstringsTo(text);
		String largestRepitativStringSoFar = "";
		for (String substring : substrings) {
			String repitativString = getRepitativStringFrom(substring);
			if (repitativString.length() > largestRepitativStringSoFar.length()) {
				largestRepitativStringSoFar = repitativString;
			}
		}
		return largestRepitativStringSoFar;
	}	

	/**
	 * Gets the repitative chars a string is made from. The string must be complete made of this replicate string
	 * @param text to check
	 * @return smallest amount of chars which descripte the repitativ pattern
	 */
	public static String getRepitativStringFrom(final String text) {
		for (int numberOfLettersToUse = 1; numberOfLettersToUse <= text.length()/2; numberOfLettersToUse++) {
			String firstPart = text.substring(0, numberOfLettersToUse);
			String secondPart = text.substring(numberOfLettersToUse);
			
			if(isFirstPartRepitativInSecondPart(firstPart, secondPart)) {
				return firstPart;
			}
		}
		return "";
	}
	private static boolean isFirstPartRepitativInSecondPart(String firstPart,
			String secondPart) {
		if(!isSecondPartDividableByFirstpart(firstPart, secondPart)) {
			return false;
		}
		
		int sectionsInSecondPart = secondPart.length() / firstPart.length();
		for (int section = 0; section < sectionsInSecondPart; section++) {
			int startOfSection = section * firstPart.length();
			int endOfSection = startOfSection + firstPart.length();
			String sectionOfSecondPart = secondPart.substring(startOfSection, endOfSection);
			
			if (!firstPart.equals(sectionOfSecondPart)) {
				return false;
			}
		}
		return true;
	}
	private static boolean isSecondPartDividableByFirstpart(String firstPart,
			String secondPart) {
		return (secondPart.length() % firstPart.length()) == 0;
	}
	
	/**
	 * All substrings of a string. 
	 * @param text to generate the substrings
	 * @return All substrings can be found as they are in the original text
	 */
	public static List<String> getAllSubstringsTo(String text) {
		int numberOfSubstrings = getNumberOfSubstringsFor(text);
		List<String> allSubstrings = new ArrayList<>(numberOfSubstrings);
		for (int start = 0; start < text.length(); start++) {
			for (int end = start +1; end <= text.length(); end++) {
				String substring = text.substring(start, end);
				allSubstrings.add(substring);
			}
		}
		return allSubstrings;
	}
	private static int getNumberOfSubstringsFor(String text) {
		int textLength = text.length();
		return textLength * (textLength + 1) / 2;
	}

	public static boolean isAnagram(final String from,
			final String to) {
		if (from.length() != to.length())
			return false;
		
		Map<Character, Integer> countLettersInTo = countLetters(to);
		Map<Character, Integer> countLettersInFrom = countLetters(from);
		
		return countLettersInFrom.equals(countLettersInTo);
	}
	
	public static Map<Character, Integer> countLetters(final String string) {
		Counter<Character> counter = new MapCounter<>();
		for(char c : string.toCharArray()) {
			counter.add(c);
		}
		return counter.toMap();
	}
}
