package just.playing.code;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Day5 {

	static Character[] ch = new Character[]{'a', 'e', 'i', 'o', 'u'};
	static String[] abc = new String[]{"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"};
	private static Set<Character> vowels = new HashSet<Character>(Arrays.asList(ch));
	private static Set<String> badStrings = new HashSet<String>();
	private static Set<String> doubles = new HashSet<String>();
	private static Set<String> thoseThreeLetterStrings = new HashSet<String>();
	private static Set<String> pairs = new HashSet<String>();

	static {
		initializeThoseThreeLetterStrings();
		initializePairs();
		initializeBadStrings();
		initializeDoubles();
	}
	
	public static void main(String[] args) {
		int i = 0;
		
		try{
			Path path = Paths.get("", "files/day5");
			InputStream is = Files.newInputStream(path);
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			String line = null;
			while((line = br.readLine()) != null) {
//				if(isStringNice(line)) i++;
				if(newDefinitionOfNice(line)) i++;
			}
		}
		catch(IOException ioe) {
			System.out.println(ioe);
		}
		
		System.out.println(i);
	}
	
	private static boolean newDefinitionOfNice(String string) {
		return conditionTwo(string) && conditionOne(string);
	}
	
	private static boolean conditionOne(String string) {
		int count = 0;
		String temp;
		boolean lookedEnough = false;
		for(String pair : pairs) {
			count = 0;
			temp = string;
			 do {
				if(temp.contains(pair)) {
					count ++;
					lookedEnough = false;
					temp = temp.substring(temp.indexOf(pair) + 2);
				}
				else {
					lookedEnough = true;
				}
				if(count > 1) return true;
			} while(!lookedEnough && temp.length() >= 2);
		}
		return false;
	}
	
	private static boolean conditionTwo(String string) {
		 for(String s : thoseThreeLetterStrings) {
			 if(string.contains(s)) return true;
		 }
		return false;
	}
	
	private static boolean isStringNice(String string) {
		return doesNotContainBadStrings(string) && containsAletterTwiceInARow(string) && containsAtLeastThreeVowels(string);
	}
	
	private static boolean containsAtLeastThreeVowels(String string) {
		int count = 0;
		for(int i = 0; i < string.length(); i++) {
			if(vowels.contains(string.charAt(i))) count++;
			if(count > 2) return true;
		}
		return false;
	}
	
	private static boolean containsAletterTwiceInARow(String string) {
		for(String goodToHave : doubles) {
			if(string.contains(goodToHave)) return true;
		}
		return false;
	}
	
	private static boolean doesNotContainBadStrings(String string) {
		for(String badString : badStrings) {
			if(string.contains(badString)) return false;
		}
		return true;
	}
	
	private static void initializeDoubles() {
		doubles.add("aa");
		doubles.add("bb");
		doubles.add("cc");
		doubles.add("dd");
		doubles.add("ee");
		doubles.add("ff");
		doubles.add("gg");
		doubles.add("hh");
		doubles.add("ii");
		doubles.add("jj");
		doubles.add("kk");
		doubles.add("ll");
		doubles.add("mm");
		doubles.add("nn");
		doubles.add("oo");
		doubles.add("pp");
		doubles.add("qq");
		doubles.add("rr");
		doubles.add("ss");
		doubles.add("tt");
		doubles.add("uu");
		doubles.add("vv");
		doubles.add("ww");
		doubles.add("xx");
		doubles.add("yy");
		doubles.add("zz");
	}
	
	private static void initializeBadStrings() {
		badStrings.add("ab");
		badStrings.add("cd");
		badStrings.add("pq");
		badStrings.add("xy");
	}
	
	private static void initializeThoseThreeLetterStrings() {
		for(int i = 0; i < abc.length; i++) {
			for(int j = 0; j < abc.length; j++) {
				thoseThreeLetterStrings.add(abc[i] + abc[j] + abc[i]);
			}
		}
	}
	
	private static void initializePairs() {
		for(int i = 0; i < abc.length; i++) {
			for(int j = 0; j < abc.length; j++) {
				pairs.add(abc[i] + abc[j]);
			}
		}
	}
}
