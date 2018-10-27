package eight_puzzle.solver;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import ds.container.Container;
import eight_puzzle.result.EightPuzzleResult;
import eight_puzzle.result.Result;
import eight_puzzle.state.State;

public class EightPuzzleSolverUninformed implements Solver {

	private Container<State> frontier;

	public EightPuzzleSolverUninformed(Container<State> frontier) {
		this.frontier = frontier;
	}

	/**
	 * Solves the 8-puzzle using either dfs, bfs or A* depending 
	 * on container injected.
	 */
	@Override
	public Result solve(State initialState) {
		long startTime = System.currentTimeMillis();
		Set<State> visitedStates = new HashSet<>();
		frontier.clear();
		frontier.add(initialState);
		visitedStates.add(initialState);
		State goalState = null;
		int processedNodes = 0;
		int maxDepth = 0;
		while (frontier.size() > 0 && goalState == null) {
			State currentState = frontier.get();
			processedNodes++;
			maxDepth = Math.max(maxDepth, currentState.getDepth());
			if (currentState.isGoalState()) {
				goalState = currentState;
			}
			List<State> neighbors = currentState.neighbors();
			for (State neighborState : neighbors) {
				if (!visitedStates.contains(neighborState)) {
					frontier.add(neighborState);
					neighborState.setParentState(currentState);
					visitedStates.add(neighborState);
				}
			}
		}
		long totalTime = System.currentTimeMillis() - startTime;
		return goalState != null ? new EightPuzzleResult(goalState.getDepth(), goalState, processedNodes, totalTime, maxDepth)
				: new EightPuzzleResult(-1, null, 0, 0, 0);
	}

}
