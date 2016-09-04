package just.playing.code;

import static org.fest.assertions.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import just.playing.code.day22.Game;
import just.playing.code.day22.MagicSpell;

import org.junit.Test;


public class Day22Test {

	Day22 day22 = new Day22();
	
	
	
	@Test
	public void should() {
		Game game = new Game(10, 14, 8, 250, 0, new HashSet<MagicSpell>());
		game.buySpell(new MagicSpell("R", 5, 229, 0, 0, 101, 0));
		assertThat(21).isEqualTo(game.getStartingMana());
		assertThat(10).isEqualTo(game.getPlayerHitPoints());
		
		game.bossPlay();
		game.removeInactiveSpells();
		assertThat(122).isEqualTo(game.getStartingMana());
		assertThat(2).isEqualTo(game.getPlayerHitPoints());
		
		game.playerPlay();
		game.removeInactiveSpells();
		game.buySpell(new MagicSpell("S", 6, 113, 0, 7, 0, 0));
		assertThat(110).isEqualTo(game.getStartingMana());
		assertThat(2).isEqualTo(game.getPlayerHitPoints());
		
		game.bossPlay();
		assertThat(211).isEqualTo(game.getStartingMana());
		assertThat(1).isEqualTo(game.getPlayerHitPoints());
		game.removeInactiveSpells();
		
		game.playerPlay();
		game.removeInactiveSpells();
		game.buySpell(new MagicSpell("D", 1, 73, 2, 0, 0, 2));
		assertThat(239).isEqualTo(game.getStartingMana());
		assertThat(1).isEqualTo(game.getPlayerHitPoints());
		
		game.bossPlay();
		assertThat(340).isEqualTo(game.getStartingMana());
		assertThat(2).isEqualTo(game.getPlayerHitPoints());
		game.removeInactiveSpells();
		
		game.playerPlay();
		game.buySpell(new MagicSpell("P", 6, 173, 3, 0, 0, 0));
		assertThat(167).isEqualTo(game.getStartingMana());
		assertThat(2).isEqualTo(game.getPlayerHitPoints());
		game.removeInactiveSpells();
		
		game.bossPlay();
		assertThat(167).isEqualTo(game.getStartingMana());
		assertThat(1).isEqualTo(game.getPlayerHitPoints());
		game.removeInactiveSpells();
		
		game.playerPlay();
		game.removeInactiveSpells();
		game.buySpell(new MagicSpell("MM", 1, 53, 4, 0, 0, 0));
		assertThat(114).isEqualTo(game.getStartingMana());
		assertThat(1).isEqualTo(game.getPlayerHitPoints());
		
		game.bossPlay();
		game.removeInactiveSpells();
		
		assertThat(1).isEqualTo(game.getPlayerHitPoints());
		assertThat(114).isEqualTo(game.getStartingMana());
		assertThat(-1).isEqualTo(game.getBossHitPoints());
//		System.out.println(game.getBossHitPoints());
	}
	
	@Test
	public void testBuyMagicSpell() {
		Game game = new Game(18, 5, 8, 1000, 0, new HashSet<MagicSpell>());
		game.buySpell(new MagicSpell("MM", 1, 53, 4, 0, 0, 0));
		assertThat(53).isEqualTo(game.getCostSoFar());
		assertThat(947).isEqualTo(game.getStartingMana());
		game.buySpell(new MagicSpell("S", 6, 113, 0, 7, 0, 2));
		assertThat(166).isEqualTo(game.getCostSoFar());
		assertThat(834).isEqualTo(game.getStartingMana());
		game.buySpell(new MagicSpell("D", 1, 73, 2, 0, 0, 2));
		assertThat(239).isEqualTo(game.getCostSoFar());
		assertThat(761).isEqualTo(game.getStartingMana());
		game.buySpell(new MagicSpell("P", 6, 173, 3, 0, 0, 0));
		assertThat(412).isEqualTo(game.getCostSoFar());
		assertThat(588).isEqualTo(game.getStartingMana());
		game.buySpell(new MagicSpell("R", 5, 229, 0, 0, 101, 0));
		assertThat(641).isEqualTo(game.getCostSoFar());
		assertThat(359).isEqualTo(game.getStartingMana());
	}

	@Test
	public void testPlayMethod() {
		
	}
	
	@Test
	public void testGetNextGames() {
		List<Game> currentGames = new ArrayList<Game>();
		Set<MagicSpell> magicSpells = new HashSet<MagicSpell>(Arrays.asList(
				new MagicSpell("MM", 1, 53, 4, 0, 0, 0),
				new MagicSpell("S", 6, 113, 0, 7, 0, 2),
				new MagicSpell("P", 6, 173, 3, 0, 0, 0),
				new MagicSpell("D", 1, 73, 2, 0, 0, 2),
				new MagicSpell("R", 5, 229, 0, 0, 101, 0)
			));
		currentGames.add(new Game(50, 55, 8, 500, 0, new HashSet<MagicSpell>()));
		List<Game> nextGames = day22.getNextGames(currentGames, magicSpells);
//		assertThat(nextGames).containsExactly(getAllPossibleGamesWithOneMove());
		
		List<Game> secondGeneration = day22.getNextGames(nextGames, magicSpells);
	}
	
	private List<Game> getAllPossibleThirdGenerationGames() {
		return null;
	}
	
	private List<Game> getAllPossibleGamesWithOneMove() {
		HashSet<MagicSpell> magicSpell1 = new HashSet<MagicSpell>();
		magicSpell1.add(new MagicSpell("MM", 1, 53, 4, 0, 0, 0));
		Game game1 = new Game(50, 55, 8, 447, 53, magicSpell1);
		
		magicSpell1 = new HashSet<MagicSpell>();
		magicSpell1.add(new MagicSpell("S", 6, 113, 0, 7, 0, 2));
		Game game2 = new Game(50, 55, 8, 387, 113, magicSpell1);
		
		magicSpell1 = new HashSet<MagicSpell>();
		magicSpell1.add(new MagicSpell("P", 6, 173, 3, 0, 0, 0));
		Game game3 = new Game(50, 55, 8, 327, 173, magicSpell1);
		
		magicSpell1 = new HashSet<MagicSpell>();
		magicSpell1.add(new MagicSpell("D", 1, 73, 2, 0, 0, 2));
		Game game4 = new Game(50, 55, 8, 427, 73, magicSpell1);
		
		magicSpell1 = new HashSet<MagicSpell>();
		magicSpell1.add(new MagicSpell("R", 5, 229, 0, 0, 101, 0));
		Game game5 = new Game(50, 55, 8, 271, 229, magicSpell1);
		return Arrays.asList(game1, game2, game3, game4, game5);
	}
}
