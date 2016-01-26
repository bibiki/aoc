package just.playing.code;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class Day15 {

	private static int rows = 176851;
	private static int[][] permutations = new int[rows][4];
	private static Map<String, int[]> ingridients = new HashMap<String, int[]>();
	public static void main(String[] args) {
		try{
			initializePermutations();
			Path path = Paths.get("", "files/day15");
			InputStream is = Files.newInputStream(path);
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			String line = null;
			while((line = br.readLine()) != null) {
				String[] lineBrokenDown = line.split(":");
				ingridients.put(lineBrokenDown[0].trim(), new int[5]);
				String[] properties = lineBrokenDown[1].split(",");
				int prop = 0;
				for(int i = 0; i < properties.length; i++) {
					ingridients.get(lineBrokenDown[0])[prop++] = Integer.valueOf(properties[i].trim().split(" ")[1]);
				}
			}
			System.out.println(getDurability(new int[]{1, 2, 3, 4}));
			System.out.println("ishte nate");
			System.out.println(getMeMaxCookieScore());
		}
		catch(IOException ioe) {
			System.out.println(ioe);
		}

	}
	
	private static int getMeMaxCookieScore() {
		int rez = 0;
		for(int i = 0; i < permutations.length; i++) {
			int capacity = getCapacity(permutations[i]);
			int durability = getDurability(permutations[i]);
			int flavor = getFlavor(permutations[i]);
			int texture = getTexture(permutations[i]);
			int calories = getCalories(permutations[i]);
//			System.out.println(capacity + " " + durability + " " + flavor + " " + texture);
			int count = capacity*durability*flavor*texture;
			if(calories == 500 && count > rez) rez = count;
		}
		return rez;
	}
	
	private static int getCapacity(int[] combo) {
		int rez = 0;
		int count = 0;
		for(int[] props : ingridients.values()) {
			System.out.println(combo[count] + " * " + props[1]);
			rez += (combo[count]*props[0]);
			count++;
		}
		if(rez < 0) return 0;
		return rez;
	}
	
	private static int getDurability(int[] combo) {
		int rez = 0;
		int count = 0;
		for(int[] props : ingridients.values()) {
			rez += (combo[count]*props[1]);
			count++;
		}
		if(rez < 0) return 0;
		return rez;
	}
	
	private static int getFlavor(int[] combo) {
		int rez = 0;
		int count = 0;
		for(int[] props : ingridients.values()) {
			rez += (combo[count]*props[2]);
			count++;
		}
		if(rez < 0) return 0;
		return rez;
	}
	
	private static int getTexture(int[] combo) {
		int rez = 0;
		int count = 0;
		for(int[] props : ingridients.values()) {
			rez += (combo[count]*props[3]);
			count++;
		}
		if(rez < 0) return 0;
		return rez;
	}
	
	private static int getCalories(int[] combo) {
		int rez = 0;
		int count = 0;
		for(int[] props : ingridients.values()) {
			rez += (combo[count]*props[4]);
			count++;
		}
		if(rez < 0) return 0;
		return rez;
	}
	private static void initializePermutations() {
		int r = 0;
		for(int i = 0; i <= 100; i++) {
			for(int j = 0; j <= 100; j++) {
				for(int k = 0; k <= 100; k++) {
					for(int l = 0; l <= 100; l++) {
						if(100 == i + j + k + l) {
							permutations[r][0] = i;
							permutations[r][1] = j;
							permutations[r][2] = k;
							permutations[r][3] = l;
							r++;
						}
					}
				}
			}
		}
		System.out.println("aja: " + r);
	}
}
