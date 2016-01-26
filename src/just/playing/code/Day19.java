package just.playing.code;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Iterables;

public class Day19 {

//	PUZZLE PROVIDED INPUT AND OUTPUT FOR CROSSCHECKING ASSUMPTIONS
//	private static String FIRST_MOLECULE = "HOHOHO";
//	private static Map<String, Integer> MOLECULE_MAP_COUNT = new HashMap<String, Integer>();
//	private static Map<String, Integer> INITIAL_MOLECULE_COUNT = new HashMap<String, Integer>();

	/** This is input for the puzzle, for both parts */
	private static String PUZZLE_INPUT = "ORnPBPMgArCaCaCaSiThCaCaSiThCaCaPBSiRnFArRnFArCaCaSiThCaCaSiThCaCaCaCaCaCaSiRnFYFArSiRnMgArCaSiRnPTiTiBFYPBFArSiRnCaSiRnTiRnFArSiAlArPTiBPTiRnCaSiAlArCaPTiTiBPMgYFArPTiRnFArSiRnCaCaFArRnCaFArCaSiRnSiRnMgArFYCaSiRnMgArCaCaSiThPRnFArPBCaSiRnMgArCaCaSiThCaSiRnTiMgArFArSiThSiThCaCaSiRnMgArCaCaSiRnFArTiBPTiRnCaSiAlArCaPTiRnFArPBPBCaCaSiThCaPBSiThPRnFArSiThCaSiThCaSiThCaPTiBSiRnFYFArCaCaPRnFArPBCaCaPBSiRnTiRnFArCaPRnFArSiRnCaCaCaSiThCaRnCaFArYCaSiRnFArBCaCaCaSiThFArPBFArCaSiRnFArRnCaCaCaFArSiRnFArTiRnPMgArF";
	/** Maps a molecule to all possible molecules it can evolve into */
	private static Map<String, Set<String>> MOLECULE_MAP = new HashMap<String, Set<String>>();
	/** Maps a molecule to the molecule that can generate it */
	private static Map<String, String> REVERSE_MOLECULAR_MAP = new HashMap<String, String>();
	/** Molecules an electron can evolve into */
	private static Set<String> FIRST_GENERATION_MOLECULES = new HashSet<String>();
	private static Set<String> GENERATED_MOLECULES = new HashSet<String>();
	
	static {
		try{
			BufferedReader reader = Day2.getBufferedReader("day19");
			
			putMoleculesFromFileOnAMap(reader);
			
			for(String molecule : MOLECULE_MAP.keySet()) {
				for(String map : MOLECULE_MAP.get(molecule)) {
					if("e".equals(molecule)) {
						FIRST_GENERATION_MOLECULES.add(map);
					}
					else {
						REVERSE_MOLECULAR_MAP.put(map, molecule);
					}
				}
			}
		}
		catch(IOException ioe) {
			System.out.println(ioe);
		}
	}
	
	private static void putMoleculesFromFileOnAMap(BufferedReader reader) throws IOException {
		String line = null;
		while((line = reader.readLine()) != null) {
			String[] moleculeMapper = line.split("=>");
			if(null == MOLECULE_MAP.get(moleculeMapper[0].trim())) {
				MOLECULE_MAP.put(moleculeMapper[0].trim(), new HashSet<String>());
			}
			MOLECULE_MAP.get(moleculeMapper[0].trim()).add(moleculeMapper[1].trim());
		}
	}
	
	public static void main(String[] args) {
		firstPart();
		secondPart();
	}
	
	private static void firstPart() {
		System.out.println(nextGeneration(PUZZLE_INPUT).size());
	}
	
	private static void secondPart() {
		Set<String> initial = previousGeneration(PUZZLE_INPUT);
		int count = 1;
		long start = System.currentTimeMillis();
		while(!FIRST_GENERATION_MOLECULES.containsAll(initial)) {
			count++;
			//500 here is a lucky guess. If I do not limit, it takes forever to run... never reached the limit. But taking subsets I reached the correct
			//result quite quickly.
			initial = ImmutableSet.copyOf(Iterables.limit(previousGeneration(initial), 500));
		}
		System.out.println("it took me: " + (System.currentTimeMillis() - start) + " millis to calculate");
		System.out.println("Count: " + count);

	}
	
	private static void genereateAllReplacements() {
		for(String molecule : MOLECULE_MAP.keySet()) {
			for(String replacement : MOLECULE_MAP.get(molecule)) {
				makeAllReplacements(molecule, replacement);
			}
		}
	}
	
	private static void makeAllReplacements(String molecule, String replacement) {
		int expectedReplacementsCount = StringUtils.countMatches(PUZZLE_INPUT, molecule);
		for(int i = 1; i <= expectedReplacementsCount; i++) {
			GENERATED_MOLECULES.add(replace(molecule, replacement, i, PUZZLE_INPUT));
		}
	}
	
	private static String replace(String molecule, String replacement, int count, String initial) {
		int splitPosition = getCountMatchPosition(initial, molecule, count);
		String head = initial.substring(0, splitPosition);
		String tail = initial.substring(splitPosition);
		tail = tail.replaceFirst(molecule, replacement);
		return head + tail;
	}
	
	private static int getCountMatchPosition(String stack, String needle, int count) {
		int index = 0;
		String temp;
		for(int i = 0; i < count; i++) {
			temp = stack;
			index += stack.indexOf(needle);
			stack = stack.substring(stack.indexOf(needle));
			stack = stack.substring(needle.length());
			if(!stack.equals(temp) && 1 < count - i) {
				index += needle.length();
			}
		}
		return index;
	}
	
	private static int generateMolecule(Set<String> molecules, String molecule, int iteration) {
		if(molecules.contains(molecule)) return iteration;
		else {
			return generateMolecule(nextGeneration(molecules), molecule, iteration+1);
		}
	}
	
	private static Set<String> nextGeneration(Set<String> molecules) {
		Set<String> result = new HashSet<String>();
		for(String molecule : molecules) {
			result.addAll(nextGeneration(molecule));
		}
		return result;
	}
	
	private static Set<String> nextGeneration(String string) {
		Set<String> result = new HashSet<String>();
		for(String mmm : MOLECULE_MAP.keySet()) {
			for(String replacement : MOLECULE_MAP.get(mmm)) {
				if("e".equals(string)) result.addAll(MOLECULE_MAP.get(string));
				else 
					for(int i = 1; i <= StringUtils.countMatches(string, mmm); i++) {
						String newR = replace(mmm, replacement, i, string);
						if(newR.length() <= PUZZLE_INPUT.length()) {
							result.add(newR);
						}
					}
			}
		}
		return result;
	}
	
	private static int findMoleculeGenerationCount(String molecule) {
		return findMoleculeGenerationCount(previousGeneration(molecule), molecule, 1);
	}
	private static int findMoleculeGenerationCount(Set<String> molecules, String molecule, int count) {
		if(FIRST_GENERATION_MOLECULES.containsAll(molecules)) {
			return count;
		}
		else {
			Set<String> previousGeneration = previousGeneration(molecules);
			return findMoleculeGenerationCount(previousGeneration, molecule, count+1);
		}
	}
	
	private static Set<String> previousGeneration(String molecule) {
		Set<String> previousGeneration = new HashSet<String>();
		for(String reverse : REVERSE_MOLECULAR_MAP.keySet()) {
			for(int i = 1; i <=  StringUtils.countMatches(molecule, reverse); i++)
				previousGeneration.add(replace(reverse, REVERSE_MOLECULAR_MAP.get(reverse), i, molecule));
		}
		return previousGeneration;
	}
	
	private static Set<String> previousGeneration(Set<String> molecules) {
		Set<String> previousGeneration = new HashSet<String>();
		for(String molecule : molecules) {
			previousGeneration.addAll(previousGeneration(molecule));
		}
		return previousGeneration;
	}
}
/*
 * --- Day 19: Medicine for Rudolph ---
 * 
 * Rudolph the Red-Nosed Reindeer is sick! His nose isn't shining very brightly, and he needs medicine.
 * 
 * Red-Nosed Reindeer biology isn't similar to regular reindeer biology; Rudolph is going to need custom-made medicine. Unfortunately, Red-Nosed Reindeer chemistry isn't similar to regular reindeer chemistry, either.
 * 
 * The North Pole is equipped with a Red-Nosed Reindeer nuclear fusion/fission plant, capable of constructing any Red-Nosed Reindeer molecule you need. It works by starting with some input molecule and then doing a series of replacements, one per step, until it has the right molecule.
 * 
 * However, the machine has to be calibrated before it can be used. Calibration involves determining the number of molecules that can be generated in one step from a given starting point.
 * 
 * For example, imagine a simpler machine that supports only the following replacements:
 * 
 * H => HO
 * H => OH
 * O => HH
 * Given the replacements above and starting with HOH, the following molecules could be generated:
 * 
 * HOOH (via H => HO on the first H).
 * HOHO (via H => HO on the second H).
 * OHOH (via H => OH on the first H).
 * HOOH (via H => OH on the second H).
 * HHHH (via O => HH).
 * So, in the example above, there are 4 distinct molecules (not five, because HOOH appears twice) after one replacement from HOH. Santa's favorite molecule, HOHOHO, can become 7 distinct molecules (over nine replacements: six from H, and three from O).
 * 
 * The machine replaces without regard for the surrounding characters. For example, given the string H2O, the transition H => OO would result in OO2O.
 * 
 * Your puzzle input describes all of the possible replacements and, at the bottom, the medicine molecule for which you need to calibrate the machine. How many distinct molecules can be created after all the different ways you can do one replacement on the medicine molecule?
 * 
 * Your puzzle answer was 576.
 * 
 * --- Part Two ---
 * 
 * Now that the machine is calibrated, you're ready to begin molecule fabrication.
 * 
 * Molecule fabrication always begins with just a single electron, e, and applying replacements one at a time, just like the ones during calibration.
 * 
 * For example, suppose you have the following replacements:
 * 
 * e => H
 * e => O
 * H => HO
 * H => OH
 * O => HH
 * If you'd like to make HOH, you start with e, and then make the following replacements:
 * 
 * e => O to get O
 * O => HH to get HH
 * H => OH (on the second H) to get HOH
 * So, you could make HOH after 3 steps. Santa's favorite molecule, HOHOHO, can be made in 6 steps.
 * 
 * How long will it take to make the medicine? Given the available replacements and the medicine molecule in your puzzle input, what is the fewest number of steps to go from e to the medicine molecule?
 * 
 * Your puzzle answer was 207.
 * 
 * Both parts of this puzzle are complete! They provide two gold stars: **
 * 
 * At this point, you should return to your advent calendar and try another puzzle.
 */
