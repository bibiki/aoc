
package just.playing.code;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Day2 {

	public static int getAreaWithoutExces(int l, int w, int h) {
		return 2 * (l*w + l*h + w*h);
	}
	
	public static int getSlack(int l, int w, int h) {
		int one = l*w;
		
		if(one > l*h) {
			one = l*h;
		}
		if(one > w*h) {
			one = w*h;
		}
		return one;
	}
	
	public static int getNecessaryArea(int l, int w, int h) {
		return getAreaWithoutExces(l, w, h) + getSlack(l, w, h);
	}
	
	public static int getRibbon(int l, int w, int h) {
		int one = l + w;
		if(one > l + h) one = l + h;
		if(one > w + h) one = w + h;
		return 2*one;
	}
	
	public static void main(String[] args) {
		System.out.println(getRibbon(2, 3, 4));
		System.out.println(getRibbon(3, 4, 2));
		System.out.println(getRibbon(4, 3, 2));
		try{
			BufferedReader br = getBufferedReader("day2");
			String line = null;
			int result = 0;
			while((line = br.readLine()) != null) {
				String[] lwh = line.split("x");
				result += Integer.valueOf(lwh[0])*Integer.valueOf(lwh[1])*Integer.valueOf(lwh[2]) + getRibbon(Integer.valueOf(lwh[0]), Integer.valueOf(lwh[1]), Integer.valueOf(lwh[2]));
			}
			System.out.println(result);
		}
		catch(IOException ioe) {
			System.out.println(ioe);
		}
	}
	
	public static BufferedReader getBufferedReader(String fileName) throws IOException {
		Path path = Paths.get("", "files/" + fileName);
		InputStream is = Files.newInputStream(path);
		BufferedReader result = new BufferedReader(new InputStreamReader(is));
		return result;
	}
}
