package just.playing.code.improved;

import java.io.BufferedReader;
import java.util.HashMap;
import java.util.Map;

import just.playing.code.Day2;

public class Day3 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = Day2.getBufferedReader("day3");
		String line = br.readLine();
		System.out.println(firstPart(line));
		System.out.println(secondPart(line));
	}
	
	private static int secondPart(String line) {
		HouseAddress santasCurrentHouse = new HouseAddress(0, 0);
		HouseAddress robotsCurrentHouse = new HouseAddress(0, 0);
		Map<HouseAddress, Integer> housesToPresents = new HashMap<HouseAddress, Integer>();
		housesToPresents.put(santasCurrentHouse, 2);
		for(int i = 0; i < line.length(); i++) {
			if(i%2 == 0) {
				santasCurrentHouse = moveToNextHouse(line.charAt(i), santasCurrentHouse);
				givePresentToHouseAndRemember(santasCurrentHouse, housesToPresents);
			}
			else {
				robotsCurrentHouse = moveToNextHouse(line.charAt(i), robotsCurrentHouse);
				givePresentToHouseAndRemember(robotsCurrentHouse, housesToPresents);
			}
		}
		return countHousesWithMinimumCountPresents(1, housesToPresents);
	}
	
	private static int firstPart(String line) {
		HouseAddress currentHome = new HouseAddress(0, 0);
		Map<HouseAddress, Integer> housesToPresents = new HashMap<HouseAddress, Integer>();
		housesToPresents.put(currentHome, 2);
		for(int i = 0; i < line.length(); i++) {
			currentHome = moveToNextHouse(line.charAt(i), currentHome);
			givePresentToHouseAndRemember(currentHome, housesToPresents);
		}
		return countHousesWithMinimumCountPresents(1, housesToPresents);
	}
	
	private static int countHousesWithMinimumCountPresents(int count, Map<HouseAddress, Integer> cache) {
		int result = 0;
		for(Integer i : cache.values()) {
			if(i >= count) {
				result++;
			}
		}
		return result;
	}
	
	private static void givePresentToHouseAndRemember(HouseAddress houseAddress, Map<HouseAddress, Integer> cache) {
		if(null == cache.get(houseAddress)) {
			cache.put(houseAddress, new Integer(1));
		}
		else {
			cache.put(houseAddress, cache.get(houseAddress) + 1);
		}
	}
	
	private static HouseAddress moveToNextHouse(char direction, HouseAddress current) {
		if (direction == '>') {
			return new HouseAddress(current.xCoordinate + 1, current.yCoordinate);
		}
		else if(direction == 'v') {
			return new HouseAddress(current.xCoordinate, current.yCoordinate - 1);
		}
		else if(direction == '<') {
			return new HouseAddress(current.xCoordinate - 1, current.yCoordinate);
		}
		else if(direction == '^') {
			return new HouseAddress(current.xCoordinate, current.yCoordinate + 1);
		}
		throw new IllegalArgumentException("The direction to move to next address is illegal, direction provided: " + direction + "! Expected one of [>, v, <, ^]");
	}
}
/*
 * 
 * --- Day 3: Perfectly Spherical Houses in a Vacuum ---
 * 
 * Santa is delivering presents to an infinite two-dimensional grid of houses.
 * 
 * He begins by delivering a present to the house at his starting location, and then an elf at the North Pole calls him via radio and tells him where to move next. Moves are always exactly one house to the north (^), south (v), east (>), or west (<). After each move, he delivers another present to the house at his new location.
 * 
 * However, the elf back at the north pole has had a little too much eggnog, and so his directions are a little off, and Santa ends up visiting some houses more than once. How many houses receive at least one present?
 * 
 * For example:
 *
 * > delivers presents to 2 houses: one at the starting location, and one to the east.
 * ^>v< delivers presents to 4 houses in a square, including twice to the house at his starting/ending location.
 * ^v^v^v^v^v delivers a bunch of presents to some very lucky children at only 2 houses.
 * Your puzzle answer was 2565.
 * 
 * --- Part Two ---
 * 
 * The next year, to speed up the process, Santa creates a robot version of himself, Robo-Santa, to deliver presents with him.
 * 
 * Santa and Robo-Santa start at the same location (delivering two presents to the same starting house), then take turns moving based on instructions from the elf, who is eggnoggedly reading from the same script as the previous year.
 * 
 * This year, how many houses receive at least one present?
 * 
 * For example:
 * 
 * ^v delivers presents to 3 houses, because Santa goes north, and then Robo-Santa goes south.
 * ^>v< now delivers presents to 3 houses, and Santa and Robo-Santa end up back where they started.
 * ^v^v^v^v^v now delivers presents to 11 houses, with Santa going one direction and Robo-Santa going the other.
 * Your puzzle answer was 2639.
 */