package just.playing.code.day24;

import java.io.BufferedReader;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

import just.playing.code.Day17;
import just.playing.code.Day2;

public class Day24 {

	private static Set<Integer> packages = new HashSet<Integer>();
	/**
	 * 
	 */
	static {
		try {
			BufferedReader br = Day2.getBufferedReader("day24");
			String line = null;
			while((line = br.readLine()) != null) {
				packages.add(new Integer(line.trim()));
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		int desiredGroups = 4;//3 for first part of the puzzle, 4 for the second part of the puzzle
		Day17 util = new Day17();
		Day24 day24 = new Day24();
		Set<Group> firstGroups = day24.getGroups(Day24.packages, desiredGroups, util);
		System.out.println("Group size: " + firstGroups.size());
		Group groupOfInterest = null;
		for(Group g : firstGroups) {
			groupOfInterest = g;
		}
		System.out.println(groupOfInterest.getQuantumEntanglement());
		int[] combination = new int[packages.size()];
	}
	
	private boolean canBeSplitInEqualPieces(Set<Integer> containers, int pieces, Day17 util) {
		int desiredSize = this.sum(containers);
		if(desiredSize%pieces != 0) throw new RuntimeException("Sum of containers is not divisible by " + pieces);
		int[] containersArray = this.unbox(containers.toArray(new Integer[containers.size()]));
		desiredSize /= pieces;
		int[] combination = new int[containers.size()];
		while(!util.isLastCombination(combination)) {
			if(desiredSize == util.getCombinationsSum(containersArray, combination))
				return true;
			combination = util.getNextCombination(combination);
		}
		return false;
	}
	
	private Set<Integer> difference(Set<Integer> universe, Set<Integer> set) {
		Set<Integer> result = new HashSet<Integer>();
		result.addAll(universe);
		result.removeAll(set);
		return result;
	}
	
	public Set<Group> getGroups(Set<Integer> containers, int desiredGroups, Day17 util) {
		Set<Group> result = new TreeSet<Group>();
		int[] containersArray = this.unbox(containers.toArray(new Integer[containers.size()]));
		int[] combination = new int[containers.size()];
		int desiredSize = this.getDesiredSize(containers, desiredGroups);
		while(!util.isLastCombination(combination)) {
			if(desiredSize == util.getCombinationsSum(containersArray, combination)) {
				result.add(new Group(util.getSubset(containers, combination)));
			}
			combination = util.getNextCombination(combination);
		}
		return result;
	}
	
	private int[] unbox(Integer[] nums) {
		int[] result = new int[nums.length];
		for(int i = 0; i < nums.length; i++) {
			result[i] = nums[i];
		}
		return result;
	}
	
	private int getDesiredSize(Set<Integer> containers, int desiredGroups) {
		int result = this.sum(containers);
		if(result%desiredGroups != 0) {
			throw new RuntimeException("Don't punk with my heart! The sum of containers is not a multiple of three.");
		}
		return result/desiredGroups;
	}
	
	private int sum(Set<Integer> nums) {
		int result = 0;
		for(Integer num : nums) {
			result += num;
		}
		return result;
	}
}
