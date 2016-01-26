package just.playing.code;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Day6 {

	private static int lights[][] = new int[1000][1000];
	
	
	public static void main(String[] args) {
		int i = 0;
		for(int j = 0; j < 1_000; j++) {
			for(int k = 0; k < 1_000; k++) {
				i += lights[j][k];
			}
		}
		System.out.println(i);
		i = 0;
		try{
			Path path = Paths.get("", "files/day6");
			InputStream is = Files.newInputStream(path);
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			String line = null;
			while((line = br.readLine()) != null) {
				OP operation = OP.TOGGLE.fromString(line);
				String[] lll = line.split(" ");
				if(OP.TOGGLE.equals(operation)) {
					String[] first = lll[1].split(",");
					String[] second = lll[3].split(",");
//					toggle(Integer.valueOf(first[0]), Integer.valueOf(first[1]), Integer.valueOf(second[0]), Integer.valueOf(second[1]));
					turn(Integer.valueOf(first[0]), Integer.valueOf(first[1]), Integer.valueOf(second[0]), Integer.valueOf(second[1]), operation.operation());
				}
				else {
					String[] first = lll[2].split(",");
					String[] second = lll[4].split(",");
					turn(Integer.valueOf(first[0]), Integer.valueOf(first[1]), Integer.valueOf(second[0]), Integer.valueOf(second[1]), operation.operation());
				}
			}
		}
		catch(IOException ioe) {
			System.out.println(ioe);
		}
		for(int j = 0; j < 1_000; j++) {
			for(int k = 0; k < 1_000; k++) {
				i += lights[j][k];
			}
		}
		System.out.println(i);

	}
	
	private static void turn(int fromRow, int fromCol, int toRow, int toCol, int turnOnOff) {
		for(int i = fromRow; i <= toRow; i++) {
			for(int j = fromCol; j <= toCol; j++) {
				if(turnOnOff > 0 || lights[i][j] > 0) {
					lights[i][j] += turnOnOff;
				}
			}
		}
	}
	
	private static void toggle(int fromRow, int fromCol, int toRow, int toCol) {
		for(int i = fromRow; i <= toRow; i++) {
			for(int j = fromCol; j <= toCol; j++) {
				lights[i][j] = lights[i][j] + 2;
			}
		}
	}
	
	
	private enum OP {
		TOGGLE {
			@Override
			public int operation() {
				// TODO Auto-generated method stub
				return 2;
			}
		}, TURNON {
			@Override
			public int operation() {
				return 1;
			}
		}, TRUNOFF {
			@Override
			public int operation() {
				return -1;
			}
		};
		
		public abstract int operation();

		public OP fromString(String s) {
			if(s.contains("on")) return OP.TURNON;
			if(s.contains("off")) return OP.TRUNOFF;
			return OP.TOGGLE;
		}
	}
	
}
