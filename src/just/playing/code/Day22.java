package just.playing.code;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import just.playing.code.day22.Game;
import just.playing.code.day22.MagicSpell;

public class Day22 {

	private static int count = 0;
	
	public static void main(String[] args) {
		Set<MagicSpell> magicSpells = new HashSet<MagicSpell>(Arrays.asList(
					new MagicSpell("MM", 1, 53, 4, 0, 0, 0),
					new MagicSpell("S", 6, 113, 0, 7, 0, 0),
					new MagicSpell("P", 6, 173, 3, 0, 0, 0),
					new MagicSpell("D", 1, 73, 2, 0, 0, 2),
					new MagicSpell("R", 5, 229, 0, 0, 101, 0)
				));
		List<Game> paths = Arrays.asList(new Game(50, 55, 8, 500, 0, new HashSet<MagicSpell>()));
//		System.out.println(new Day22().findPathRecursive(paths, magicSpells, -1));
		System.out.println(new Day22().iterative(12, paths, magicSpells));
	}

	public int iterative(int iterations, List<Game> paths, Set<MagicSpell> possibleSpells){
		int result = 0;
		List<Game> nextStep = this.getNextGames(paths, possibleSpells);
		for(int i = 0; i < iterations; i++) {
			for(Game path : nextStep) {
				path.bossPlay();
				if(path.isGameEnded()) {
					if(path.hasPlayerWon()) {
						if(result == 0) {
							result = path.getCostSoFar();
						}
						else {
							result = ((result < path.getCostSoFar()) ? result : path.getCostSoFar());
						}
					}
				}
				path.removeInactiveSpells();
				path.playerPlay();
				path.removeInactiveSpells();
				if(path.isGameEnded()) {
					if(path.hasPlayerWon()) {
						if(result == 0) {
							result = path.getCostSoFar();
						}
						else {
							result = ((result < path.getCostSoFar()) ? result : path.getCostSoFar());
						}
//						System.out.println(path.getCostSoFar() + ": " + path.getMoves());
					}
//					System.out.println(path.getPlayerHitPoints() + " " + path.getBossHitPoints());
//					System.out.println("game has ended: " + path.hasPlayerWon());
				}
			}
			nextStep = this.getNextGames(nextStep, possibleSpells);
		}
		return result;
	}
	
	public int findPathRecursive(List<Game> paths, Set<MagicSpell> possibleSpells, int rez) {
		count++;
		if(count > 14) return rez;
		if(rez >= 0 || paths.size() < 1) {
//			System.out.println(paths);
//			return rez;
		}
		int result = rez;
		List<Game> nextStep = this.getNextGames(paths, possibleSpells);
		for(Game path : nextStep) {
			path.bossPlay();
			if(path.isGameEnded()) {
				if(path.hasPlayerWon()) {
					if(result == -1) {
						result = path.getCostSoFar();
					}
					else {
						result = ((result < path.getCostSoFar()) ? result : path.getCostSoFar());
					}
//					System.out.println(path.getCostSoFar() + ": " + path.getMoves());
				}
//				System.out.println("game has ended: " + path.hasPlayerWon());
			}
			path.removeInactiveSpells();
			path.playerPlay();
			path.removeInactiveSpells();
			if(path.isGameEnded()) {
				if(path.hasPlayerWon()) {
					if(result == -1) {
						result = path.getCostSoFar();
					}
					else {
						result = ((result < path.getCostSoFar()) ? result : path.getCostSoFar());
					}
//					System.out.println(path.getCostSoFar() + ": " + path.getMoves());
				}
//				System.out.println(path.getPlayerHitPoints() + " " + path.getBossHitPoints());
//				System.out.println("game has ended: " + path.hasPlayerWon());
			}
		}
		return this.findPathRecursive(nextStep, possibleSpells, result);
	}
	
	public List<Game> getNextGames(List<Game> current, Set<MagicSpell> possibleSpells) {
		List<Game> result = new ArrayList<Game>();
		for(Game game : current) {
//			game.preMoveDamage(0);//for part 1 of the puzzle
			game.preMoveDamage(1);//for part 2 of the puzzle
			for(MagicSpell spell : possibleSpells) {
				if(!game.isGameEnded() && game.isValidGame(spell)) {// && game.getStartingMana() >= spell.getCost()) {
					Game g = new Game(game);
					g.buySpell(new MagicSpell(spell));
					result.add(g);
				}
			}
		}
		return result;
	}
}
