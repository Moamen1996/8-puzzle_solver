package eight_puzzle.solver;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import ds.container.Container;
import eight_puzzle.comparator.DistanceComparator;
import eight_puzzle.result.EightPuzzleResult;
import eight_puzzle.result.Result;
import eight_puzzle.state.State;

public class EightPuzzleSolverInformed implements Solver{
	
	private Container<State> frontier;
	
	public EightPuzzleSolverInformed(Container<State> frontier) {
		this.frontier = frontier;
	}

	@Override
	public Result solve(State initialState) {
		long startTime = System.currentTimeMillis();
		frontier.clear();
		frontier.add(initialState);
		Map<String, Integer> depth = new HashMap<>();
		depth.put(initialState.toString(), 0);
		State goalState = null;
		int processedNodes = 0;
		int maxDepth = 0;
		while (frontier.size() > 0 && goalState == null) {
			State currentState = frontier.get();
			if(currentState.getDepth() > depth.get(currentState.toString())) {
				continue;
			}
			processedNodes++;
			maxDepth = Math.max(maxDepth, currentState.getDepth());
			if (currentState.isGoalState()) {
				goalState = currentState;
			}
			List<State> neighbors = currentState.neighbors();
			for (State neighborState : neighbors) {
				if (!depth.containsKey(neighborState.toString()) || neighborState.getDepth() < depth.get(neighborState.toString())) {
					frontier.add(neighborState);
					neighborState.setParentState(currentState);
					depth.put(neighborState.toString(), neighborState.getDepth());
				}
			}
		}
		long totalTime = System.currentTimeMillis() - startTime;
		return goalState != null ? new EightPuzzleResult(goalState.getDepth(), goalState, processedNodes, totalTime, maxDepth)
				: new EightPuzzleResult(-1, null, 0, 0, 0);
	}

	
}
