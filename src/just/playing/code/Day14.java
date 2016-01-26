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

public class Day14 {

	private static int RACE_DURATION = 1000;
	private static Map<String, Integer> points = new HashMap<String, Integer>();
	private static Map<String, int[]> sfr = new HashMap<String, int[]>();
	private static Map<String, Integer> currentDistance = new HashMap<String, Integer>();
	
	public static void main(String[] args) {
		System.out.println(calculate(14, 10, 127));
		System.out.println(calculate(16, 11, 162));
		
		try{
			Path path = Paths.get("", "files/day14");
			InputStream is = Files.newInputStream(path);
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			String line = null;
			int max = -1;
			String name = "";
			while((line = br.readLine()) != null) {
				String[] lineBrokenDown = line.split(" ");
				points.put(lineBrokenDown[0], 0);
				sfr.put(lineBrokenDown[0], new int[]{Integer.valueOf(lineBrokenDown[3]), Integer.valueOf(lineBrokenDown[6]), Integer.valueOf(lineBrokenDown[13])});
				currentDistance.put(lineBrokenDown[0], 0);
			}
			
			for(int i = 1; i <= 2503; i++) {
				RACE_DURATION = i;
				calculateCurrentDistances(i);
				incrementPoints();
			}
			for(String namee : points.keySet()) {
				System.out.println(namee + ": " + points.get(namee));
				System.out.println("\t" + namee + ": " + currentDistance.get(namee));
			}
		}
		catch(IOException ioe) {
			System.out.println(ioe);
		}

	}
	
	private static void incrementPoints() {
		int max = currentLead();
		for(String name : points.keySet()) {
			if(currentDistance.get(name).equals(max)) {
				points.put(name, points.get(name) + 1);
			}
		}
	}
	
	private static int currentLead() {
		int result = 0;
		for(int distance : currentDistance.values()) {
			if(result < distance) result = distance;
		}
		return result;
	}
	
	private static void calculateCurrentDistances(int currentSecond) {
		int[] deerParams;
		for(String name : sfr.keySet()) {
			deerParams = sfr.get(name);
//			if(isRunning(name, currentSecond)) {
//				currentDistance.put(name, currentDistance.get(name) + deerParams[0]);
//				currentDistance.put(name, calculate(deerParams[0], deerParams[1], deerParams[2]));
				currentDistance.put(name, calculate(deerParams[0], deerParams[1], deerParams[2]));
//			}
		}
	}
	
	private static boolean isRunning(String name, int currentSecond) {
		int[] deerParams = sfr.get(name);
		return currentSecond%(deerParams[1] + deerParams[2]) <= deerParams[1];
	}
	
	private static int calculate(int speed, int flight, int rest) {
		int rez = (RACE_DURATION/(flight+rest))*(speed*flight);
		int temp = flight;
		if(RACE_DURATION%(flight+rest) < flight){
			temp = RACE_DURATION%(flight+rest);
		}
		rez += temp*speed;
		return rez;
	}
}
