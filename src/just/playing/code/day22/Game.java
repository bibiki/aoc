package just.playing.code.day22;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

public class Game {

	public static final String[] SPELL_NAMES = new String[]{"MM", "H", "S", "A", "R"};
	
	private int playerHitPoints;
	
	private int bossHitPoints;
	
	private int bossDamage;
	
	private int startingMana;
	
	private int costSoFar;
	
	private Set<MagicSpell> magicSpells = new HashSet<MagicSpell>();
	
	private LinkedList<String> moves = new LinkedList<String>();
	
	public LinkedList<String> getMoves() {
		return this.moves;
	}
	
	public Game(Game game) {
		this.playerHitPoints = game.playerHitPoints;
		this.bossHitPoints = game.bossHitPoints;
		this.bossDamage = game.bossDamage;
		this.startingMana = game.startingMana;
		this.costSoFar = game.costSoFar;
		for(MagicSpell s : game.magicSpells) {
			this.magicSpells.add(new MagicSpell(s));
		}
		for(String s : game.moves) {
			moves.add(s);
		}
	}
	
	public Game(int playerHitPoints, int bossHitPoints, int bossDamage,
			int startingMana, int costSoFar, Set<MagicSpell> magicSpells) {
		this.playerHitPoints = playerHitPoints;
		this.bossHitPoints = bossHitPoints;
		this.bossDamage = bossDamage;
		this.startingMana = startingMana;
		this.costSoFar = costSoFar;
		this.magicSpells = magicSpells;
	}

	public boolean isGameEnded() {
		return (playerHitPoints <= 0) || (bossHitPoints <= 0);
	}
	
	public boolean hasPlayerWon() {
		return this.bossHitPoints <= 0 && playerHitPoints > 0;
	}
	
	public int getPlayerHitPoints() {
		return playerHitPoints;
	}

	public void setPlayerHitPoints(int playerHitPoints) {
		this.playerHitPoints = playerHitPoints;
	}

	public int getBossHitPoints() {
		return bossHitPoints;
	}

	public void setBossHitPoints(int bossHitPoints) {
		this.bossHitPoints = bossHitPoints;
	}

	public int getBossDamage() {
		return bossDamage;
	}

	public void setBossDamage(int bossDamage) {
		this.bossDamage = bossDamage;
	}

	public int getStartingMana() {
		return startingMana;
	}

	public void setStartingMana(int startingMana) {
		this.startingMana = startingMana;
	}

	public int getCostSoFar() {
		return costSoFar;
	}

	public void setCostSoFar(int costSoFar) {
		this.costSoFar = costSoFar;
	}

	public Set<MagicSpell> getMagicSpells() {
		return magicSpells;
	}

	public void setMagicSpells(Set<MagicSpell> magicSpells) {
		this.magicSpells = magicSpells;
	}

	public void playerPlay() {
		for(MagicSpell spell : this.magicSpells) {
			if(spell.isActive()) {
//				System.out.println("PSpell name: " + spell.getName() + " " + spell.getDamage());
				this.bossHitPoints -= spell.getDamage();
				this.playerHitPoints += spell.getHeal();
				this.startingMana += spell.getRecharge();
				spell.useTurn();
			}
		}
	}
	
	public void bossPlay() {
		for(MagicSpell spell : this.magicSpells) {
			if(spell.isActive()) {
//				System.out.println("BSpell name: " + spell.getName() + " " + spell.getDamage());
				this.bossHitPoints -= spell.getDamage();
				this.playerHitPoints += spell.getHeal();
				this.playerHitPoints += spell.getArmor();
				this.startingMana += spell.getRecharge();
				spell.useTurn();
			}
		}
		if(this.bossHitPoints > 0) {
			this.playerHitPoints -= bossDamage;
		}
	}
	
	public void preMoveDamage(int defaultDamage) {
		this.playerHitPoints -= defaultDamage;
	}
	
	public boolean isValidGame(MagicSpell magicSpell) {
		for(MagicSpell s : this.magicSpells) {
			if(s.getName().equals(magicSpell.getName())) {
				return false;
			}
		}
		return this.startingMana >= magicSpell.getCost();
	}
	
	public void removeInactiveSpells() {
		Set<MagicSpell> newSpells = new HashSet<>();
		for(MagicSpell s : this.magicSpells) {
			if(s.getTurns() > 0) {
				newSpells.add(s);
			}
		}
		this.magicSpells.clear();
		this.magicSpells.addAll(newSpells);
	}
	
	public void buySpell(MagicSpell spell) {
		this.magicSpells.add(spell);
		this.startingMana -= spell.getCost();
		this.costSoFar += spell.getCost();
		this.moves.add(spell.getName());
		this.removeInactiveSpells();
	}

	@Override
	public String toString() {
		return "Game [playerHitPoints=" + playerHitPoints + ", bossHitPoints="
				+ bossHitPoints + ", bossDamage=" + bossDamage
				+ ", startingMana=" + startingMana + ", costSoFar=" + costSoFar
				+ "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + bossDamage;
		result = prime * result + bossHitPoints;
		result = prime * result + costSoFar;
		result = prime * result
				+ ((magicSpells == null) ? 0 : magicSpells.hashCode());
		result = prime * result + playerHitPoints;
		result = prime * result + startingMana;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Game other = (Game) obj;
		if (bossDamage != other.bossDamage)
			return false;
		if (bossHitPoints != other.bossHitPoints)
			return false;
		if (costSoFar != other.costSoFar)
			return false;
		if (magicSpells == null) {
			if (other.magicSpells != null)
				return false;
		} else if (!magicSpells.equals(other.magicSpells))
			return false;
		if (playerHitPoints != other.playerHitPoints)
			return false;
		if (startingMana != other.startingMana)
			return false;
		return true;
	}
}
