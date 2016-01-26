package just.playing.code;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Day8 {

	static String[] needles = new String[]{"\\x", "\\\\", "\""};
	
	public static void main(String[] args) {
		try{
			Path path = Paths.get("", "files/day8");
			InputStream is = Files.newInputStream(path);
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			String allLines = "";
			String line = null;
			int difference = 0;
			int lineCount = 0;
			int subtractionValue = 0;
			while((line = br.readLine()) != null) {
				difference += (3*countOccurences(line, needles[0]) + countOccurences(line, needles[1]) + countOccurences(line, needles[2]));
				allLines += line;
				lineCount++;
				int hexCount = 3*countOccurences(line, needles[0]);
				int slashCount = countOccurences(line, needles[1]);
				int quoteCount = countOccurences(line, needles[2]);
//				System.out.println(hexCount + " " + slashCount + " " + quoteCount + " " + lineCount);
				subtractionValue = subtractionValue + hexCount + slashCount + quoteCount + 2;
				System.out.println(line + ": " + line.length() + " " + (3*countOccurences(line, needles[0]) + countOccurences(line, needles[1]) + countOccurences(line, needles[2])) + " [" + 3*countOccurences(line, needles[0]) + " " + countOccurences(line, needles[1]) + " " + countOccurences(line, needles[2]) + "]");
			}
			System.out.println("difference: " + difference);
			// allChars - 2*numberOfLines[for the quotes] - 3*occurencesOf"\x" - occurencesOf\\ - occurencesOf\"
			System.out.println("allLines.length(): " + allLines.length());
			System.out.println("path.toFile().length(): " + path.toFile().length());
			System.out.println(subtractionValue);
			System.out.println(allLines.length() - subtractionValue);
			System.out.println(allLines.length() - (countOccurences(allLines, needles[0]) + countOccurences(allLines, needles[1]) + countOccurences(allLines, needles[2])));
			System.out.println(allLines);
		}
		catch(IOException ioe) {
			System.out.println(ioe);
		}
	}
	
	protected int count(String line)  {
		int hexCount = 3*countOccurences(line, needles[0]);
		int slashCount = countOccurences(line, needles[1]);
		int quoteCount = countOccurences(line, needles[2]);
		return hexCount + slashCount + quoteCount + 2;
	}
	
	private static int countOccurences(String stack, String needle) {
		int lastIndex = 0;
		int count = 0;

		while(lastIndex != -1){

		    lastIndex = stack.indexOf(needle,lastIndex);

		    if(lastIndex != -1){
		        count ++;
		        lastIndex += needle.length();
		    }
		}
		return count;
	}
}
