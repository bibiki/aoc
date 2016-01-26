package just.playing.code;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Day7 {

	private static Map<String, String> theMap = new HashMap<String, String>();
	private static Map<String, String> cache = new HashMap<String, String>();
	
	public static void main(String[] args) {
		putThingsOnMap("files/day7");
		/*
		 * ls: 1024
		 * jn: 14977
		 * bv: 503
		 * hc: 21519
		 * eu: 10271
		 * by: 307
		 * iu: 29955
		 * o: 16348
		 * gg: 21
		 * c: 0
		 * t: 0
		 * a: 2797
		 * it took me: 0 to calculate that!
		 * 
		 */
		System.out.println("ls: " + process("ls"));
		System.out.println("jn: " + process("jn"));
			System.out.println("bv: " + process("bv"));
		System.out.println("hc: " + process("hc"));
		System.out.println("eu: " + process("eu"));
			System.out.println("by: " + process("by"));
		System.out.println("iu: " + process("iu"));
			System.out.println("o: " + process("o"));
		System.out.println("gg: " + process("gg"));

//		System.out.println(theMap.size());
		long startTime = new Date().getTime();
		System.out.println("c: " + process("c"));
		System.out.println("t: " + process("t"));
		System.out.println("a: " + process("a"));
		long endTime = new Date().getTime();
		System.out.println("it took me: " + (endTime - startTime) + " to calculate that!");
	}
	
	private static String process(String key) {
		if(null != cache.get(key)) return cache.get(key);
		if(isInt(key)) {//because of cases when process calls itself
			return key;
		}
		String value = theMap.get(key);
		if(null == value) {
			return value;
		}
		if(isInt(value.trim())) {
			cache.put(key, value.trim());
			return value;
		}
		else if (value.contains("NOT")) {
			String[] temp = value.split(" ");
			String rez = "" + OP.toOp(value).operation(process(temp[1].trim()));
			if(isInt(rez.trim())) {
				cache.put(key, rez);
			}
			return rez;
		}
		else if (isBinaryOperation(value)) {
			String[] temp = value.split(" ");
			String rez = OP.toOp(value).operation(process(temp[0]), process(temp[2]));
			if(isInt(rez.trim())) {
				cache.put(key, rez);
			}
			return rez;
		}
		else {
			return process(value.trim());
		}
	}
	
	private static boolean isBinaryOperation(String key) {
		return key.contains("AND") || key.contains("OR")|| key.contains("RSHI") || key.contains("LSHI");
	}
	
	private static boolean isInt(String value) {
		try {
			Integer.parseInt(value);
			return true;
		}
		catch(NumberFormatException nfe) {
			return false;
		}
	}
	
	private static void putThingsOnMap(String fileName) {
		try{
			Path path = Paths.get("", fileName);
			InputStream is = Files.newInputStream(path);
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			String line = null;
			while((line = br.readLine()) != null) {
				String[] theLine = line.split("->");
				theMap.put(theLine[1].trim(), theLine[0].trim());
			}
		}
		catch(IOException ioe) {
			System.out.println(ioe);
		}
	}
	private static String binaryNegation(String k) {
		String zeroes = "0000000000000000";
		int temp = Integer.parseInt(k);
		temp = ~temp;
		String binary = Integer.toBinaryString(temp);
		zeroes = zeroes + binary;
		zeroes = zeroes.substring(binary.length());
		return zeroes;
	}

	private enum OP {
		NOT {
			@Override
			public String operation(String... s) {
				// TODO Auto-generated method stub
				String zeroes = "0000000000000000";
				int temp = Integer.parseInt(s[0]);
				temp = ~temp;
				String binary = Integer.toBinaryString(temp);
				zeroes = zeroes + binary;
				zeroes = zeroes.substring(binary.length());
				return Integer.parseInt(zeroes, 2) + "";			}
		},
		AND {
			@Override
			public String operation(String... s) {
				// TODO Auto-generated method stub
				int a = Integer.parseInt(s[0]);
				int b = Integer.parseInt(s[1]);
				int rez = a&b;
				return Integer.toString(rez);
			}
		},
		OR {
			@Override
			public String operation(String... s) {
				// TODO Auto-generated method stub
				int a = Integer.parseInt(s[0]);
				int b = Integer.parseInt(s[1]);
				int rez = a|b;
				return Integer.toString(rez);
			}
		},
		LSHIFT {
			@Override
			public String operation(String... s) {
				// TODO Auto-generated method stub
				return "" + Integer.toString((Integer.parseInt(s[0]) << Integer.parseInt(s[1])));
			}
		},
		RSHIFT {
			@Override
			public String operation(String... s) {
				// TODO Auto-generated method stub
				return "" + Integer.toString((Integer.parseInt(s[0]) >> Integer.parseInt(s[1])));
			}
		};
		
		public abstract String operation(String... s);
		
		public static OP toOp(String s) {
			if(s.contains("NOT")) return NOT;
			else if(s.contains("LSHIFT")){
				return LSHIFT;
			}
			else if (s.contains("RSHI")) return RSHIFT;
			else if (s.contains("OR")) return OR;
			else return AND;
		}
	}
}
