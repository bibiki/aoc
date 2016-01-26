package just.playing.code;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Day13 {

	private static String[] persons;
	private static Map<String, Integer> happiness = new HashMap<String, Integer>();
	
	public static void main(String[] args) {
		init();
		int[][] permutations = Day9.permutations(persons.length);
		int maxHappines = calculateHappines(permutations[0]);
		System.out.println(calculateHappines(permutations[374]));
		System.out.println(calculateHappines(permutations[123]));
		System.out.println(calculateHappines(permutations[576]));
		for(int i = 1; i < permutations.length; i++) {
			int calculatedHappiness = calculateHappines(permutations[i]);
			if(maxHappines < calculatedHappiness) {
				maxHappines = calculatedHappiness;
			}
			System.out.println(i + ": " + calculatedHappiness);
		}
		System.out.println(maxHappines);
	}
	
	private static int calculateHappines(int[] order) {
		int result = 0;
		for(int i = 0; i < order.length; i++) {
			result += happiness.get(persons[order[i]] + persons[order[(i + 1)%persons.length]]);
			result += happiness.get(persons[order[(i + 1)%persons.length]] + persons[order[i]]);
		}
		return result;
	}
	
	private static void init() {
		try{
			Path path = Paths.get("", "files/day13");
			InputStream is = Files.newInputStream(path);
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			String line = null;
			int sign;
			Set<String> names = new HashSet<String>();
			names.add("Gagi");
			while((line = br.readLine()) != null) {
				line = line.substring(0, line.length() - 1);
				if(line.contains("lose")) sign = -1;
				else sign = 1;
				
				String[] lineBrokenDown = line.split(" ");
				String first = lineBrokenDown[0];
				String second = lineBrokenDown[lineBrokenDown.length - 1];
				names.add(first);
				names.add(second);
				happiness.put(first.trim() + second.trim(), sign*Integer.valueOf(lineBrokenDown[3]));
			}
			persons = names.toArray(new String[names.size()]);
			for(String s : persons) {
				happiness.put(s + "Gagi", 0);
				happiness.put("Gagi" + s, 0);
			}
		}
		catch(IOException ioe) {
			System.out.println(ioe);
		}
	}
}
