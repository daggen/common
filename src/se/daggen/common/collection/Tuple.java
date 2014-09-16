package se.daggen.common.collection;

public final class Tuple<S1, S2> {
	public final S1 x;
	public final S2 y;
	
	public Tuple(S1 x, S2 y) {
		this.x = x;
		this.y = y;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null || obj.getClass() != this.getClass()) {
			return false;
		}
		
		if (obj == this)
			return true;
		
		@SuppressWarnings("unchecked")
		Tuple<S1, S2> other = (Tuple<S1, S2>) obj;
		
		return x.equals(other.x) && y.equals(other.y);
	}
	
	@Override
	public int hashCode() {
		return x.hashCode() + y.hashCode();
	}
	
	
}
