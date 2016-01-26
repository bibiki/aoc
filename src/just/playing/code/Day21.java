package just.playing.code;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Day21 {

	private static List<Map<String, Integer>> configurations = new ArrayList<Map<String,Integer>>();
	private static Map<String, Integer> OPPONENT = new HashMap<String, Integer>();

	private static Map<Integer, Integer> weapons = new HashMap<Integer, Integer>();
	private static Map<Integer, Integer> armor = new HashMap<Integer, Integer>();
	private static Map<Integer, Integer> armorRings = new HashMap<Integer, Integer>();
	private static Map<Integer, Integer> damageRings = new HashMap<Integer, Integer>();
	private static Map<Integer, Integer> twoArmorRings = new HashMap<Integer, Integer>();
	private static Map<Integer, Integer> twoDamageRings = new HashMap<Integer, Integer>();
	
	
	
	static {
		
	}
	
	private static void initOpponent() {
		OPPONENT.put("damage", 8);
		OPPONENT.put("armor", 2);
		OPPONENT.put("points", 109);
	}
	
	private static void fight(Map<String, Integer> player, Map<String, Integer> opponent) {
		int count = 0;
		while(player.get("points") > 0 && opponent.get("points") > 0) {
			if(count%2 == 0) {
				dealBlow(player, opponent);
			}
			else {
				dealBlow(opponent, player);
			}
			count++;
		}
	}
	
	private static void dealBlow(Map<String, Integer> attacker, Map<String, Integer> defender) {
		int calculatedDamage = attacker.get("damage") - defender.get("armor");
		if(calculatedDamage < 1) calculatedDamage = 1;
		defender.put("points", defender.get("points") - calculatedDamage);
	}
	
	public static void main(String[] args) {
		Map<String, Integer> myself = new HashMap<String, Integer>();
		Map<String, Integer> opponent = new HashMap<String, Integer>();
		
		opponent.put("damage", 7);
		opponent.put("armor", 2);
		opponent.put("points", 12);

		myself.put("damage", 5);
		myself.put("armor", 5);
		myself.put("points", 8);
		
		fight(myself, opponent);
		System.out.println(myself.get("points"));
		System.out.println(opponent.get("points"));
		initConfigurations();
		System.out.println("Get ready to rumble...");
		for(Map<String, Integer> config : configurations) {
			initOpponent();
			fight(config, OPPONENT);
		}
		int cost = -10000000;
		for(Map<String, Integer> config : configurations) {
			if(config.get("points") < 1 && config.get("cost") > cost) {
				cost = config.get("cost");
			}
//			System.out.println(config.get("points") + " " + config.get("cost"));
		}
		System.out.println("cost: " + cost);
	}
	
	private static void initConfigurations() {//This is missing a bunch of possible configurations
		weapons.put(4, 8);
		weapons.put(5, 10);
		weapons.put(6, 25);
		weapons.put(7, 40);
		weapons.put(8, 74);
		
		armor.put(1, 13);
		armor.put(2, 31);
		armor.put(3, 53);
		armor.put(4, 75);
		armor.put(5, 102);
		
		damageRings.put(1, 25);
		damageRings.put(2, 50);
		damageRings.put(3, 100);

		armorRings.put(1, 20);
		armorRings.put(2, 40);
		armorRings.put(3, 80);
		
		twoArmorRings.put(3, 75);
		twoArmorRings.put(4, 125);
		twoArmorRings.put(5, 150);
		
		twoDamageRings.put(3, 60);
		twoDamageRings.put(4, 100);
		twoDamageRings.put(5, 120);
		
		int i = 0;
		
		for(Integer weapon : weapons.keySet()) {//weapons only
			Map<String, Integer> c = new HashMap<String, Integer>();
			c.put("points", 100);c.put("damage", weapon);c.put("armor", 0);c.put("cost", weapons.get(weapon));
			configurations.add(c);
			System.out.println(++i);
		}
		System.out.println("size, must be 5: "  + configurations.size());
		for(Integer weapon : weapons.keySet()) {//weapons and one armor
			for(Integer a : armor.keySet()) {
				Map<String, Integer> c = new HashMap<String, Integer>();
				c.put("points", 100);c.put("damage", weapon);c.put("armor", a);c.put("cost", weapons.get(weapon) + armor.get(a));
				configurations.add(c);
				System.out.println(++i);
			}
		}
		
		System.out.println("size, must be 30: "  + configurations.size());
		for(Integer weapon : weapons.keySet()) {//weapons and two damage rings
			for(Integer armorD : twoDamageRings.keySet()) {
				Map<String, Integer> c = new HashMap<String, Integer>();
				c.put("points", 100);c.put("damage", weapon + armorD);c.put("armor", 0);c.put("cost", weapons.get(weapon) + twoDamageRings.get(armorD));
				configurations.add(c);
				System.out.println(++i);
			}
		}
		System.out.println("size, must be 45: "  + configurations.size());
		
		for(Integer weapon : weapons.keySet()) {//weapons and two armor rings
			for(Integer armorR : twoArmorRings.keySet()) {
				Map<String, Integer> c = new HashMap<String, Integer>();
				c.put("points", 100);c.put("damage", weapon);c.put("armor", armorR);c.put("cost", weapons.get(weapon) + twoArmorRings.get(armorR));
				configurations.add(c);
				System.out.println(++i);
			}
		}
		System.out.println("size, must be 60: "  + configurations.size());

		
		for(Integer weapon : weapons.keySet()) {//weapons, armor, and one armor ring
			for(Integer a : armor.keySet()) {
				for(Integer armorR : armorRings.keySet()) {
					Map<String, Integer> c = new HashMap<String, Integer>();
					c.put("points", 100);c.put("damage", weapon);c.put("armor", a + armorR);c.put("cost", weapons.get(weapon) + armorRings.get(armorR) + armor.get(a));
					configurations.add(c);
					System.out.println(++i);
				}
			}
		}
		System.out.println("size, must be 135: "  + configurations.size());
		for(Integer weapon : weapons.keySet()) {
			for(Integer a : armor.keySet()) {
				for(Integer armorD : damageRings.keySet()) {//weapons, armor, and one damage ring
					Map<String, Integer> c = new HashMap<String, Integer>();
					c.put("points", 100);c.put("damage", weapon + armorD);c.put("armor", a);c.put("cost", weapons.get(weapon) + damageRings.get(armorD) + armor.get(a));
					configurations.add(c);
					System.out.println(++i);
				}
			}
		}
		System.out.println("size, must be 210: "  + configurations.size());
		for(Integer weapon : weapons.keySet()) {
			for(Integer a : armor.keySet()) {
				for(Integer armorD : damageRings.keySet()) {
					for(Integer armorR : armorRings.keySet()) {//weapons, armor, one damage ring, one armor ring
						Map<String, Integer> c = new HashMap<String, Integer>();
						c.put("points", 100);c.put("damage", weapon + armorD);c.put("armor", a + armorR);c.put("cost", weapons.get(weapon) + damageRings.get(armorD) + armor.get(a) + armorRings.get(armorR));
						configurations.add(c);
						System.out.println(++i);
					}
				}
			}
		}
		System.out.println("size, must be 480: "  + configurations.size());
		
		for(Integer weapon : weapons.keySet()) {
			for(Integer armorD : damageRings.keySet()) {
				for(Integer armorR : armorRings.keySet()) {//weapons, armor, one damage ring, one armor ring
					Map<String, Integer> c = new HashMap<String, Integer>();
					c.put("points", 100);c.put("damage", weapon + armorD);c.put("armor", armorR);c.put("cost", weapons.get(weapon) + damageRings.get(armorD) + armorRings.get(armorR));
					configurations.add(c);
					System.out.println(++i);
				}
			}
		}
		System.out.println("size, must be 525: "  + configurations.size());

		
		for(Integer weapon : weapons.keySet()) {//weapon and one armor ring
			for(Integer armorR : armorRings.keySet()) {
				Map<String, Integer> c = new HashMap<String, Integer>();
				c.put("points", 100);c.put("damage", weapon);c.put("armor", armorR);c.put("cost", weapons.get(weapon) + armorRings.get(armorR));
				configurations.add(c);
				System.out.println(++i);
			}
		}
		System.out.println("size, must be 540: "  + configurations.size());

		for(Integer weapon : weapons.keySet()) {//weapon and one damage ring
			for(Integer armorD : damageRings.keySet()) {
				Map<String, Integer> c = new HashMap<String, Integer>();
				c.put("points", 100);c.put("damage", weapon + armorD);c.put("armor", 0);c.put("cost", weapons.get(weapon) + damageRings.get(armorD));
				configurations.add(c);
				System.out.println(++i);
			}
		}
		System.out.println("size, must be 555: "  + configurations.size());

		
		for(Integer weapon : weapons.keySet()) {//one weapon, one armor, two damage rings
			for(Integer a : armor.keySet()) {
				for(Integer armorD : twoDamageRings.keySet()) {
					Map<String, Integer> c = new HashMap<String, Integer>();
					c.put("points", 100);c.put("damage", weapon + armorD);c.put("armor", a);c.put("cost", weapons.get(weapon) + twoDamageRings.get(armorD) + armor.get(a));
					configurations.add(c);
					System.out.println(++i);
				}
			}
		}
		System.out.println("size, must be 630: "  + configurations.size());

		for(Integer weapon : weapons.keySet()) {//one weapon, one armor, two armor rings
			for(Integer a : armor.keySet()) {
				for(Integer armorR : twoArmorRings.keySet()) {
					Map<String, Integer> c = new HashMap<String, Integer>();
					c.put("points", 100);c.put("damage", weapon);c.put("armor", a + armorR);c.put("cost", weapons.get(weapon) + twoArmorRings.get(armorR) + armor.get(a));
					configurations.add(c);
					System.out.println(++i);
				}
			}
		}
		System.out.println("size, must be 705: "  + configurations.size());

		System.out.println("size, must be 700: "  + configurations.size());
		System.out.println("iterations: " + i);
	}
}
