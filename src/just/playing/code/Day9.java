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

public class Day9 {

	private static int[][] permutations;
	private static String[] countries;
	private static Map<String, Integer> distances = new HashMap<String, Integer>();

	static {
		try {
			Path path = Paths.get("", "files/day9");
			InputStream is = Files.newInputStream(path);
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			String line = null;
			Set<String> destinations = new HashSet<String>();
			while ((line = br.readLine()) != null) {
				String[] brokenLine = line.split("=");
				String[] cc = brokenLine[0].split("to");
				destinations.add(cc[0].trim());
				destinations.add(cc[1].trim());
				distances.put(brokenLine[0].trim(),
						Integer.valueOf(brokenLine[1].trim()));
			}
			countries = destinations.toArray(new String[destinations.size()]);
			permutations = permutations(countries.length);

		} catch (IOException ioe) {
			System.out.println(ioe);
		}
	}

	public static int[][] permutations(int k) {
		String s = "";
		for (int i = 0; i < k; i++)s+=i;
		String[] temp = permute(s);
		int[][] rez = new int[factorial(k)][k];

		for (int i = 0; i < rez.length; i++) {
			for (int j = 0; j < rez[0].length; j++) {
//				rez[i][j] = (i / factorial(k - j)) % k;
				rez[i][j] = Integer.valueOf(temp[i].split("")[j]);
			}
		}

		return rez;
	}

	private static int factorial(int k) {
		int rez = 1;
		for (int i = 1; i <= k; i++)
			rez *= i;
		return rez;
	}

	public static void main(String[] args) {
		int dist = calculateDistance(permutations[4]);

		for(int j = 0; j < 40320; j++) {
			for(int i = 0; i < permutations[4].length; i++) {
				System.out.print(countries[permutations[j][i]] + "->");
			}
			System.out.println(calculateDistance(permutations[j]));
		}
		for(int i = 1; i < permutations.length; i++) {
			if(dist < calculateDistance(permutations[i])) {
				dist = calculateDistance(permutations[i]);
			}
		}
		System.out.println(dist);
	}

	private static int calculateDistance(int[] cities) {
		int calc = 0;
		for(int i = 0; i < cities.length - 1; i++) {
			if(distances.get(countries[cities[i]] + " to " + countries [cities[i + 1]]) != null)
				calc += distances.get(countries[cities[i]] + " to " + countries [cities[i + 1]]);
			else if(distances.get(countries[cities[i + 1]] + " to " + countries [cities[i]]) != null)
				calc += distances.get(countries[cities[i + 1]] + " to " + countries [cities[i]]);
		}
		return calc;
	}
	public static String[] permute(String s) {
		String[] rez = new String[factorial(s.length())];
		for(int i = 0; i < s.length(); i++) {
			String[] temp = new String[factorial(i + 1)];
			for(int j = 0; j < factorial(i + 1); j++) {
				if(i == 0) {
					rez[0] = "" + s.charAt(i);
					temp[0] = "" + s.charAt(i);
				}
				if (i > 0) {
					temp[j] = rez[j%factorial(i)];
					temp[j] = expandString(temp[j], "" + s.charAt(i), j/factorial(i));
				}
			}
			for(int k = 0; k < factorial(i + 1); k++) {
				rez[k] = temp[k];
			}
		}
		return rez;
	}

	public static String expandString(String x, String y, int z) {
		String rez = "";
		rez = x.substring(0, z) + y + x.substring(z);
		return rez;
	}
}
