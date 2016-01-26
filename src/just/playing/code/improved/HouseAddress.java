package just.playing.code.improved;

public class HouseAddress {

	public final int xCoordinate;
	public final int yCoordinate;
	
	public HouseAddress(int xCoordinate, int yCoordinate) {
		this.xCoordinate = xCoordinate;
		this.yCoordinate = yCoordinate;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + xCoordinate;
		result = prime * result + yCoordinate;
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
		HouseAddress other = (HouseAddress) obj;
		if (xCoordinate != other.xCoordinate)
			return false;
		if (yCoordinate != other.yCoordinate)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "HouseAddress [xCoordinate=" + xCoordinate + ", yCoordinate="
				+ yCoordinate + "]";
	}
}
