package just.playing.code.day22;

public class MagicSpell {

	private String name;
	
	private int turns;
	
	private int cost;
	
	private int damage;
	
	private int armor;
	
	private int recharge;

	private int heal;
	
	public MagicSpell(MagicSpell magicSpell) {
		this.name = magicSpell.name;
		this.turns = magicSpell.turns;
		this.cost = magicSpell.cost;
		this.damage = magicSpell.damage;
		this.armor = magicSpell.armor;
		this.recharge = magicSpell.recharge;
		this.heal = magicSpell.heal;
	}
	
	public MagicSpell(String name, int turns, int cost, int damage, int armor,
			int recharge, int heal) {
		this.name = name;
		this.turns = turns;
		this.cost = cost;
		this.damage = damage;
		this.armor = armor;
		this.recharge = recharge;
		this.heal = heal;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getTurns() {
		return turns;
	}

	public void setTurns(int turns) {
		this.turns = turns;
	}

	public int getCost() {
		return cost;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}

	public int getDamage() {
		return damage;
	}

	public void setDamage(int damage) {
		this.damage = damage;
	}

	public int getArmor() {
		return armor;
	}

	public void setArmor(int armor) {
		this.armor = armor;
	}

	public int getRecharge() {
		return recharge;
	}

	public void setRecharge(int recharge) {
		this.recharge = recharge;
	}
	
	public boolean isActive() {
		return this.turns > 0;
	}

	public int getHeal() {
		return heal;
	}

	public void setHeal(int heal) {
		this.heal = heal;
	}
	
	public void useTurn() {
		this.turns--;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + armor;
		result = prime * result + cost;
		result = prime * result + damage;
		result = prime * result + heal;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + recharge;
		result = prime * result + turns;
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
		MagicSpell other = (MagicSpell) obj;
		if (armor != other.armor)
			return false;
		if (cost != other.cost)
			return false;
		if (damage != other.damage)
			return false;
		if (heal != other.heal)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (recharge != other.recharge)
			return false;
		if (turns != other.turns)
			return false;
		return true;
	}
}
