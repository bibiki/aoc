package just.playing.code;

import java.io.BufferedReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Day23 {

	private static Map<String, Long> registers = new HashMap<String, Long>();
	
	private static List<String> commands = new LinkedList<String>();
	
	static {
		registers.put("a", 1l);
		registers.put("b", 0l);
		try {
			BufferedReader br = Day2.getBufferedReader("day23");
			String line = null;
			while((line = br.readLine()) != null) {
				commands.add(line.trim());
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private static void process() {
		
		try {
			for(int i = 0; i < commands.size(); i++) {
				String[] command = commands.get(i).split(" ");
				if("inc".equals(command[0].trim())) {
					registers.put(command[1], (registers.get(command[1].trim()) + 1));
				}
				else if("tpl".equals(command[0].trim())) {
					registers.put(command[1], (registers.get(command[1].trim())*3));
				}
				else if("hlf".equals(command[0].trim())) {
					registers.put(command[1], (registers.get(command[1].trim())/2));
				}
				else if("jio".equals(command[0].trim())) {
					String[] arguments = command[1].split(",");
					if(1 == registers.get(arguments[0])) {
						i = i + new Integer(command[2].trim()) - 1;
					}
				}
				else if("jie".equals(command[0].trim())) {
					String[] arguments = command[1].split(",");
					if(registers.get(arguments[0])%2 == 0) {
						i = i + new Integer(command[2].trim()) - 1;
					}
				}
				else if("jmp".equals(command[0].trim())) {
					i = i + new Integer(command[1].trim()) - 1;
				}
			}
			System.out.println(registers.get("a") + " " + registers.get("b"));
		}
		catch(ArrayIndexOutOfBoundsException aioobe) {
			aioobe.printStackTrace();
			System.out.println(registers.get("b"));
		}
	}
	
	
	public static void main(String[] args) {
		Day23.process();
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				try {
					while(true) {
						Thread.sleep(10000);
						System.out.println("Thread: " + registers.get("b") + " " + registers.get("a"));
					}
				}
				catch(InterruptedException ie) {
					ie.printStackTrace();
				}
			}
		}).start();
	}
}
