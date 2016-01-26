package just.playing.code;

public class Day20 {

	private static final int PUZZLE_INPUT = 33100000;
	
	public static void main(String[] args) {
		System.out.println(firstPart());
		boolean found = false;
		int i = 50;
		while(!found) {
			i++;
			found = findSumOfFactors(i) >= PUZZLE_INPUT;
		}
		System.out.println(i);
	}
	
	private static int firstPart() {
		boolean found = false;
		int num = 1;
		while(!found) {
			found = findSumOfFactors(num, true) >= PUZZLE_INPUT;
			num++;
		}
		return num;
	}
	
	private static int findSumOfFactors(int num, boolean b) {
		int rez = 0;
		for(int i = 1; i <= num; i++) {
			if(0 == num%i) {
				rez += i;
			}
		}
		return rez;
	}
	
	private static int findSumOfFactors(int num) {
		int rez = 0;
		for(int i = num/50; i <= num; i++) {
			if(0 == num%i) {
				rez += i;
			}
		}
		return rez;
	}
}
