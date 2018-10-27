package eight_puzzle.comparator;

import java.util.Comparator;

import eight_puzzle.state.State;

public interface DistanceComparator extends Comparator<State> {
	
	public double calculateDistance(State state);
}
