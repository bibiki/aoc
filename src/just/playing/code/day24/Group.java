package just.playing.code.day24;

import java.util.Set;

public class Group implements Comparable<Group>{

	public final Set<Integer> containers;
	
	public Group(Set<Integer> containers) {
		this.containers = containers;
	}
	
	@Override
	public int compareTo(Group o) {
		if(this.containers.size() < o.getCount()) {
			return 1;
		}
		else if(this.containers.size() == o.getCount()) {
			if(this.getQuantumEntanglement() < o.getQuantumEntanglement()) {
				return 1;
			}
			else if(this.getQuantumEntanglement() == o.getQuantumEntanglement()) {
				return 0;
			}
		}
		return -1;
	}
	
	public long getQuantumEntanglement() {
		long rez = 1;
		for(Integer container : containers) {
			rez *= container.longValue();
		}
		return rez;
	}
	
	public int getCount() {
		return containers.size();
	}
}
