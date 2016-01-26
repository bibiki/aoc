package just.playing.code;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class Day11 {

	private static Set<String> pairs = new TreeSet<String>();
	private static Set<String> triples = new TreeSet<String>();
	static String[] abc = new String[]{"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"};
	private static Map<String, Integer> indices = new HashMap<String, Integer>();
	
	static {
		initializePairs();
		initializeTriples();
		initiateIndices();
	}
	
	public static void main(String[] args) {
		System.out.println(getNext("a"));
		System.out.println(getNext("z"));
		System.out.println(getNext("zz"));
		System.out.println(getNext("zaz"));
		String currentPassword = "hxbxwxba";
		System.out.println(nextPassword(currentPassword));
		System.out.println(nextPassword("abcdefgh"));
		System.out.println(nextPassword("ghijklmn"));
		System.out.println(nextPassword("hxbxxyzz"));
	}
	
	private static String nextPassword(String currentPassword) {
		boolean found = false;
		while(!found) {
			currentPassword = getNext(currentPassword);
			found = isValid(currentPassword);
		}
		return currentPassword;
	}
	
	private static boolean isValid(String s) {
		return conditionOne(s) && conditionTwo(s) && conditionThree(s);
	}
	
	private static boolean conditionOne(String s) {
		return !(s.contains("i") || s.contains("o") || s.contains("l"));
	}
	
	private static boolean conditionTwo(String s) {
		for(String triple : triples) {
			if(s.contains(triple)) return true;
		}
		return false;
	}
	
	private static boolean conditionThree(String s) {
		int count = 0;
		for(String pair : pairs) {
			if(s.contains(pair)) count++;
		}
		return count > 1;
	}
	
	private static String getNext(String s) {
		if("".equals(s)) return "a";
		String lastChar = "" + s.charAt(s.length() - 1);
		if("z".equals(lastChar)) {
			return getNext(s.substring(0, s.length() - 1)) + 'a';
		}
		else {
			int nextIndice = (indices.get(lastChar) + 1)%26;
			return s.substring(0, s.length() - 1) + abc[nextIndice];
		}
	}
	
	
	
	private static void initializePairs() {
		for(String s : abc) {
			pairs.add(s + s);
		}
	}
	
	private static void initializeTriples() {
		for(int i = 0; i < abc.length - 2; i++) {
			triples.add(abc[i] + abc[i + 1] + abc[i + 2]);
		}
	}
	
	private static void initiateIndices() {
		int index = 0;
		for(String s : abc) {
			indices.put(s, index++);
		}
	}
}
