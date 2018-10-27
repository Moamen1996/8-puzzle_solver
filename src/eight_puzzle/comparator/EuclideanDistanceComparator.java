package eight_puzzle.comparator;

import eight_puzzle.state.EightPuzzleCell;
import eight_puzzle.state.EightPuzzleState;
import eight_puzzle.state.State;

public class EuclideanDistanceComparator implements DistanceComparator {

	@Override
	public int compare(State arg0, State arg1) {
		double v1 = calculateDistance(arg0);
		double v2 = calculateDistance(arg1);
		return v1 < v2 ? -1 : v1 == v2 ? 0 : 1;
	}

	/**
	 * Calculates the eucluidean distance depending on the following formula
	 * SUM(SQRT((current.x - goal.x)^2 + (current.y - goal.y)^2))
	 */
	@Override
	public double calculateDistance(State state) {
		double distance = 0;
		for(int i = 0; i < EightPuzzleState.LENGTH; i++) {
			for(int j = 0; j < EightPuzzleState.LENGTH; j++) {
				int currentNum = state.getCell(new EightPuzzleCell(i, j));
				int actualRow = currentNum / EightPuzzleState.LENGTH;
				int actualColumn = currentNum % EightPuzzleState.LENGTH;
				int deltaY = Math.abs(actualRow - i);
				int deltaX = Math.abs(actualColumn - j);
				distance += Math.sqrt(deltaX * deltaX + deltaY * deltaY);
			}
		}
		return distance + state.getDepth();
	}

}
