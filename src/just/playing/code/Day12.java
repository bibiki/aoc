package just.playing.code;

import java.io.BufferedReader;
import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Day12 {

	private static int SUM = 0;
	public static void main(String[] args) {
		try{
			BufferedReader br = Day2.getBufferedReader("day12.json");
			String json = "";
			String line = null;
			while((line = br.readLine()) != null) {
				json += line;
			}
			JSONParser parser = new JSONParser();
			JSONArray parsed = (JSONArray)parser.parse(json);
			process(parsed);
			System.out.println(SUM);
		}
		catch(IOException | ParseException ioe) {
			System.out.println(ioe);
		}
	}
	
	private static void process(Object array) {
		if(isNumber(array)) SUM += Integer.valueOf("" + array);
		else {
			if(array instanceof JSONArray)
				for(Object o : ((JSONArray)array)) {
					process(o);
				}
			else if(array instanceof JSONObject) {
				if(!((JSONObject)array).values().contains("red")) {
					for(Object o : ((JSONObject)array).values()) {
						process(o);
					}
				}
			}
		}
	}
	
	private static boolean isNumber(Object o) {
		try {
			Integer.valueOf("" + o);
			return true;
		}
		catch(Exception e) {
			return false;
		}
	}
}
