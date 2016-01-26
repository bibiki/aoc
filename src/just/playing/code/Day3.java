package just.playing.code;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Day3 {

	public static int xCoordinate = 0;
	public static int yCoordinate = 0;
	public static int xRCoordinate = 0;
	public static int yRCoordinate = 0;
	
	static Map<String, Integer> housesToPresents = new HashMap<String, Integer>();
	
	public static void main(String[] args) {
		try {
			BufferedReader br = Day2.getBufferedReader("day3");
			
			String line = br.readLine();
			String houseAddress;
			housesToPresents.put("0x0", 2);
			for(int i = 0; i < line.length(); i++) {
				if(i%2 == 0) {
					if (line.charAt(i) == '>') {
						xCoordinate++;
					}
					else if(line.charAt(i) == 'v') {
						yCoordinate--;
					}
					else if(line.charAt(i) == '<') {
						xCoordinate--;
					}
					else if(line.charAt(i) == '^') {
						yCoordinate++;
					}
					houseAddress = xCoordinate + "x" + yCoordinate;
				}
				else {
					if (line.charAt(i) == '>') {
						xRCoordinate++;
					}
					else if(line.charAt(i) == 'v') {
						yRCoordinate--;
					}
					else if(line.charAt(i) == '<') {
						xRCoordinate--;
					}
					else if(line.charAt(i) == '^') {
						yRCoordinate++;
					}
					houseAddress = xRCoordinate + "x" + yRCoordinate;
				}
				if(null == housesToPresents.get(houseAddress)) {
					housesToPresents.put(houseAddress, new Integer(1));
				}
				else {
					housesToPresents.put(houseAddress, housesToPresents.get(houseAddress) + 1);
				}
			}
			int count = 0;
			for(Integer i : housesToPresents.values()) {
				if(i > 0) {
					count++;
				}
			}
			System.out.println(count);
		}
		catch (IOException ioe) {
			ioe.printStackTrace();
		}
	}
}
