package just.playing.code;

import static org.junit.Assert.*;

import org.junit.Test;

public class Day8Test {

	Day8 day8 = new Day8();

	@Test
	public void firstTest() {
		String line = "";
		assertEquals(0, 2 + line.length() - day8.count(line));
		line = "aqttwnsohbzian\"evtllfxwkog\"cunzw";
		byte[] bytes = line.getBytes();
		System.out.println("bytes: " + bytes.length);
//		assertEquals(32, 2 + line.length() - day8.count(line));
	}
}
