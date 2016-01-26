package just.playing.code;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class Day22 {

	private static final Map<String, Integer> OPPONENT = new HashMap<String, Integer>();
	private static int initialMana = 500;
	private static int armor = 0;
	private static int points = 50;
	private static int manaSpent = 0;
	private static int minManaSpent = 100000000;
	
	private static final Map<String, Integer> magicMissle = new HashMap<String, Integer>();
	private static final Map<String, Integer> drain = new HashMap<String, Integer>();
	private static final Map<String, Integer> shield = new HashMap<String, Integer>();
	private static final Map<String, Integer> poison = new HashMap<String, Integer>();
	private static final Map<String, Integer> recharge = new HashMap<String, Integer>();
	private static final List<Map<String, Integer>> spells = new LinkedList<Map<String,Integer>>();

	/**
	 * DOES NOT WORK. I SOLVED THE FIRST HALF BY HAND. I STILL DID NOT SOLVE THE SECOND PART.
	 */
	public static void main(String[] args) {
		int count = 0;
		if(points > 0 && OPPONENT.get("points") > 0) {
			fight(count);
		}
		count++;
		spells.add(recharge);
		
		if(points > 0 && OPPONENT.get("points") > 0) {
			fight(count);
		}
		count++;
		spells.add(poison);
		
		if(points > 0 && OPPONENT.get("points") > 0) {
			fight(count);
		}
		count++;
		spells.add(magicMissle);
		
		if(points > 0 && OPPONENT.get("points") > 0) {
			fight(count);
		}
		count++;
		spells.add(shield);
		if(points > 0 && OPPONENT.get("points") > 0) {
			fight(count);
		}
		count++;
		spells.add(drain);
		
		if(points > 0 && OPPONENT.get("points") > 0) {
			fight(count);
		}
		count++;
		spells.add(magicMissle);
		
		if(points > 0 && OPPONENT.get("points") > 0) {
			fight(count);
		}
		count++;
		spells.add(magicMissle);
		
		if(points > 0 && OPPONENT.get("points") > 0) {
			fight(count);
		}
		count++;
		spells.add(magicMissle);
		System.out.println(points + " " + manaSpent);
	}
	
	static {
		spells.add(recharge);
		spells.add(poison);
		spells.add(magicMissle);
		spells.add(magicMissle);
		spells.add(magicMissle);
		spells.add(magicMissle);
		spells.add(shield);
		spells.add(drain);
	}
	
	private static void initOpponent() {
		OPPONENT.put("damage", 8);
		OPPONENT.put("points", 55);
	}
	
	private static void fight(String variation) {
		int count = 0;
		while(true) {
			if(points > 0 && OPPONENT.get("points") > 0) {
				fight(count);
			}
			else {
				return;
			}
			count++;
		}
	}
	
	private static void activateSpell(int spell) {
		Map<String, Integer> ssspell = spells.get(spell);
		if(ssspell.get("active") > ssspell.get("turns")) {
			throw new IllegalArgumentException("Cannot buy the spell");
		}
		manaSpent += ssspell.get("cost");
		initialMana -= ssspell.get("costs");
	}
	
	private static void fight(int count) {
		for(Map<String, Integer> spel : spells) {
			OPPONENT.put("points", OPPONENT.get("points") - spel.get("damage"));
			armor += spel.get("armor");
			points += spel.get("heal");
		}
		if(count % 2 == 1) {
			int damage = 8 - armor;
			if(1 > damage) damage = 1;
			points -= damage;
		}
	}
	
	private static void fight(Map<String, Integer> player, Map<String, Integer> opponent) {
		int count = 0;
		while(player.get("points") > 0 && opponent.get("points") > 0) {
			if(count%2 == 0) {
				playerHit();
			}
			else {
				bossHit();
			}
			count++;
		}
	}
	
	private static void playerHit() {
		if(points < 1) throw new RuntimeException("Player lost, too many blows received!");
		if(initialMana < 53) throw new RuntimeException("Player lost, no money left!");
		
	}
	
	private static void bossHit() {
		if(OPPONENT.get("points") < 1) throw new RuntimeException("You won with money spent: " + manaSpent);
	}
	
	private static void spellsInit() {
		magicMissle.put("cost", 53);magicMissle.put("armor", 0);magicMissle.put("heal", 0);magicMissle.put("damage", 4);magicMissle.put("active", 0);magicMissle.put("turns", 1);magicMissle.put("mana", 0);
		drain.put("cost", 73);drain.put("armor", 0);drain.put("heal", 2);drain.put("damage", 2);drain.put("active", 0);drain.put("turns", 1);drain.put("mana", 0);
		shield.put("cost", 113);shield.put("armor", 7);shield.put("heal", 0);shield.put("damage", 0);shield.put("active", 0);shield.put("turns", 6);shield.put("mana", 0);
		poison.put("cost", 173);poison.put("armor", 0);poison.put("heal", 0);poison.put("damage", 3);shield.put("active", 0);shield.put("turns", 6);shield.put("mana", 0);
		recharge.put("cost", 229);recharge.put("armor", 0);recharge.put("heal", 0);recharge.put("damage", 0);recharge.put("active", 0);recharge.put("turns", 5);recharge.put("mana", 101);
	}
	
	private static String next(String s) {
		if("4".equals(s)) s = "0" + s;
		String last = "" + s.charAt(s.length() - 1);
		int l = Integer.valueOf(last);
		if(4 == l) return next(s.substring(0, s.length() - 1)) + "0";
		else return s.substring(0, s.length() - 1) + (l + 1);
	}
}
