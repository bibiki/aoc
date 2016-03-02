package just.playing.code;

import java.util.HashSet;
import java.util.Set;


public class Day17 {

	public static int[] containers = new int[] { 33, 14, 18, 20, 45, 35, 16,
			35, 1, 13, 18, 13, 50, 44, 48, 6, 24, 41, 30, 42 };
	private static int[] oldContainers = new int[] {20, 10, 15, 5, 5};
	
	public static void main(String[] args) {
		int result = new Day17().countValidCombinationsRecursively(150, Day17.containers, new int[Day17.containers.length], 0);
//		int result = new Day17().countValidCombinationsIteratively(150, Day17.containers, new int[Day17.containers.length]);
		System.out.println(result);
	}
	
	public int countValidCombinationsIteratively(int target, int[] containers, int[] combinationsCounter) {
		int result = 0;
		while(true) {
			if(target == this.getCombinationsSum(containers, combinationsCounter)) {
				result++;
			}
			if(isLastCombination(combinationsCounter)) {
				break;
			}
			combinationsCounter = this.getNextCombination(combinationsCounter);
		}
		return result;
	}
	
	public int countValidCombinationsRecursively(int target, int[] containers, int[] combinationsCounter, int currentValue) {
		if(target == this.getCombinationsSum(containers, combinationsCounter) ) {
			currentValue++;
		}
		if(this.isLastCombination(combinationsCounter)) {
			return currentValue;
		}
		else { 
			return countValidCombinationsRecursively(target, containers, getNextCombination(combinationsCounter), currentValue);
		}
	}
	
	public int[] getCombinationsCounter(int elementCount) {
		return new int[elementCount];
	}
	
	public int[] getNextCombination(int[] currentCombination) {
		for(int i = currentCombination.length - 1; i >= 0; i--) {
			currentCombination[i] = (currentCombination[i] + 1)%2;
			if(currentCombination[i] % 2 == 1) {
				return currentCombination;
			}
		}
		return currentCombination;
	}
	
	public int getCombinationsSum(int[] containers, int[] combination) {
		int result = 0;
		for(int i = 0; i < combination.length; i++) {
			result += combination[i]*containers[i];
		}
		return result;
	}
	
	public boolean isLastCombination(int[] combinationsCounter) {
		return combinationsCounter.length == this.sumOfArrayElements(combinationsCounter);
	}
	
	public Set<Integer> getSubset(Set<Integer> nums, int[] combination) {
		Set<Integer> result = new HashSet<Integer>();
		int count = 0;
		for(Integer num : nums) {
			if(combination[count] != 0) {
				result.add(num);
			}
			count++;
		}
		return result;
	}
	
	private int sumOfArrayElements(int[] array) {
		int result = 0;
		for(int a : array) {
			result += a;
		}
		return result;
	}
}
