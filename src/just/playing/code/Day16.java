package just.playing.code;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class Day16 {

	private static Set<String> mustHave = new HashSet<String>();
	private static Set<String> mustNotHave = new HashSet<String>();
	private static Set<String> allOfMy500Aunts = new HashSet<String>();
	private static Set<String> mustHaveMoreThan = new HashSet<String>();
	private static Set<String> mustHaveFewerThan = new HashSet<String>();
	private static Set<String> newMustHave = new HashSet<String>();
	
	static {
		System.out.println(1024*1024);
		putAuntsOnMap();
//		putBeeperInfoOnMap();
		newPutBeeperInfoOnMap();
	}
	public static void main(String[] args) {
		Iterator<String> iterator = allOfMy500Aunts.iterator();
		Set<String> toRemove = new HashSet<String>();
		while(iterator.hasNext()) {
			String aunt = iterator.next();
//			if(!(assertMustHaves(aunt) && assertMustNotHaves(aunt))) {
//			if(!(assertNewMustHaves(aunt) && assertMustNotHaves(aunt) && greaterThan(aunt) && fewerThan(aunt))) {
				if(!(assertNewMustHaves(aunt) && assertMustNotHaves(aunt) && greaterThan(aunt) && fewerThan(aunt))) {
				toRemove.add(aunt);
//				System.out.println(aunt);
			}
		}
		allOfMy500Aunts.removeAll(toRemove);
		System.out.println(allOfMy500Aunts.size());
		for(String aunt : allOfMy500Aunts) System.out.println(aunt);
		System.out.println("----------------------");
//		for(String aunt : toRemove) System.out.println(aunt);
	}

	private static boolean assertMustHaves(String aunt) {
		for(String info : mustHave) {
			if(!aunt.contains(info) && aunt.contains(info.split(":")[0].trim())) return false;
		}
		return true;
	}
	
	private static boolean assertMustNotHaves(String aunt) {
		boolean rez = true;
		for(String info : mustNotHave) {
//			if(!aunt.contains(info.trim())) return true;
			rez &= (aunt.contains(info.trim()) || !aunt.contains(info.split(":")[0].trim()));
		}
		return rez;
	}
	
	private static void putAuntsOnMap() {
		try{
			Path path = Paths.get("", "files/day16");
			InputStream is = Files.newInputStream(path);
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			String line = null;
			while((line = br.readLine()) != null) {
				allOfMy500Aunts.add(line);
			}
		}
		catch(IOException ioe) {
			System.out.println(ioe);
		}
	}
	
	private static void putBeeperInfoOnMap() {
		mustHave.add("children: 3");
		mustHave.add("cats: 7");
		mustHave.add("samoyeds: 2");
		mustHave.add("pomeranians: 3");
		mustHave.add("goldfish: 5");
		mustHave.add("trees: 3");
		mustHave.add("cars: 2");
		mustHave.add("perfumes: 1");
		mustNotHave.add("akitas: 0");
		mustNotHave.add("vizslas: 0");
	}
	
	private static boolean assertNewMustHaves(String aunt) {
		for(String info : newMustHave) {
			if(!aunt.contains(info) && aunt.contains(info.split(":")[0].trim())) return false;
		}
		return true;
	}
	
	private static void newPutBeeperInfoOnMap() {
		newMustHave.add("children: 3");
		newMustHave.add("samoyeds: 2");
		newMustHave.add("cars: 2");
		newMustHave.add("perfumes: 1");
		mustHaveFewerThan.add("pomeranians: 3");
		mustHaveFewerThan.add("goldfish: 5");
		mustHaveMoreThan.add("trees: 3");
		mustHaveMoreThan.add("cats: 7");
		mustNotHave.add("akitas: 0");
		mustNotHave.add("vizslas: 0");
	}
	
	private static boolean greaterThan(String aunt) {
		boolean rez = true;
		for(String info : mustHaveMoreThan) {
			if(aunt.contains(info.split(":")[0].trim()) && !hasMore(aunt, info)) {
//				aunt.contains(info.split(":")[0].trim())) return false;
				rez &= false;
			}
		}
		return rez;
	}
	
	private static boolean fewerThan(String aunt) {
		boolean rez = true;
		for(String info : mustHaveFewerThan) {
			if(aunt.contains(info.split(":")[0].trim()) && !hasLess(aunt, info)) {
				rez &= false;
			}
		}
		return rez;
	}

	private static boolean hasLess(String aunt, String property) {
		String temp = aunt.substring(aunt.indexOf(property.split(":")[0]) + property.split(":")[0].length() + 1);
		String count = temp.split(",")[0].trim();
		String lowerBound = property.split(":")[1].trim();
		return Integer.valueOf(count) < Integer.valueOf(lowerBound);
	}

	
	private static boolean hasMore(String aunt, String property) {
		String temp = aunt.substring(aunt.indexOf(property.split(":")[0]) + property.split(":")[0].length() + 1);
		String count = temp.split(",")[0].trim();
		String lowerBound = property.split(":")[1].trim();
		return Integer.valueOf(count) > Integer.valueOf(lowerBound);
	}
}
