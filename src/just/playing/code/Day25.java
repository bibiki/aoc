package just.playing.code;

public class Day25 {

	long[][] cache = new long[][]{
			{20151125, 18749137, 17289845, 30943339, 10071777, 33511524},
			{31916031, 21629792, 16929656, 7726640, 15514188, 4041754},
			{16080970, 8057251, 1601130, 7981243, 11661866, 16474243},
			{24592653, 32451966, 21345942, 9380097, 10600672, 31527494},
			{77061, 17552253, 28094349, 6899651, 9250759, 31663883},
			{33071741, 6796745, 25397450, 24659492, 1534922, 27995004}
		};
	
	public long getCodeRecursively(int row, int column) {
		if(row < 6 && column < 6) {
			return cache[row][column];
		}
		else {
//			Multiply it by 252533 to get 5088824049625. Then, divide that by 33554393
			int previousValuesRow = row + 1;
			int previousValuesColumn = column - 1;
			if(column == 0) {
				previousValuesColumn = row - 1;
				previousValuesRow = 0;
			}
			return (252533*getCodeRecursively(previousValuesRow, previousValuesColumn))%33554393;
		}
	}
	
	public long getCodeIteratively(int row, int column) {
		long result = 20151125;
		int count = row*column + sumUpTo(row - 2) + sumUpTo(column - 1);
		for(int i = 1; i < count; i++) {
			result = (252533*result)%33554393;
		}
		return result;
	}
	
	private static int sumUpTo(int n) {
		int result = 0;
		for(int i = 1; i <= n; i++) {
			result += i;
		}
		return result;
	}
	
	public static void main(String[] args) {
//		2981, column 3075
//		System.out.println(new Day25().getCodeRecursively(2981, 3075));
		System.out.println(new Day25().getCodeIteratively(1, 1));
		System.out.println(new Day25().getCodeIteratively(2, 1));
		System.out.println(new Day25().getCodeIteratively(1, 2));
		System.out.println(new Day25().getCodeIteratively(2, 2));
		System.out.println(new Day25().getCodeIteratively(2981, 3075));
	}
}
