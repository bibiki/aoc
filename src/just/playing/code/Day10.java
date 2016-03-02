package just.playing.code;

public class Day10 {

	public static void main(String[] args) {
		int length = 0;
		String string = "3113322113";
//		int numOfIterationsPuzzlesAsksFor = 40;//for the first part
		int numOfIterationsPuzzlesAsksFor = 50;//for the second part
		for(int i = 0; i < numOfIterationsPuzzlesAsksFor; i++) {
			string = whatYouSeeIsWhatYouGet(string);
		}
		length = string.length();
		System.out.println(length);
	}
	private static String whatYouSeeIsWhatYouGet(String s) {
		String result = "";
		char characterBeingCounted = s.charAt(0);
		int count = 0;
		for(int i = 0; i < s.length(); i++) {
			if(s.charAt(i) != characterBeingCounted) {
				if(count != 0) {
					result += (count + "" + characterBeingCounted);//record how many time character c appeared
				}
				//reset count to zero and c to the new character being counted
				count = 0;
				characterBeingCounted = s.charAt(i);
			}
			count++;
		}
		result += (count + "" + characterBeingCounted);//record information for the last unique character
		return result;
	}
}
