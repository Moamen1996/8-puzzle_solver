package eight_puzzle.comparator;

import eight_puzzle.state.EightPuzzleCell;
import eight_puzzle.state.EightPuzzleState;
import eight_puzzle.state.State;

public class ManhattanDistanceComparator implements DistanceComparator {
	
	@Override
	public int compare(State arg0, State arg1) {
		double v1 = calculateDistance(arg0);
		double v2 = calculateDistance(arg1);
		return v1 < v2 ? -1 : v1 == v2 ? 0 : 1;
	}
	
	/**
	 * Calculates Manhattan distance by the following formula
	 * SUM(abs(current.x - goal.x) + abs(current.y - goal.y))
	 */
	@Override
	public double calculateDistance(State state) {
		double distance = 0;
		for(int i = 0; i < EightPuzzleState.LENGTH; i++) {
			for(int j = 0; j < EightPuzzleState.LENGTH; j++) {
				int goalNumber = state.getCell(new EightPuzzleCell(i, j));
				int goalI = goalNumber / EightPuzzleState.LENGTH;
				int goalJ = goalNumber % EightPuzzleState.LENGTH;
				distance += Math.abs(goalI - i) + Math.abs(goalJ - j);
			}
		}
		return distance + state.getDepth();
	}
}
