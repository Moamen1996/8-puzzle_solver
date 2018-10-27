package eight_puzzle.result;

import java.util.LinkedList;
import java.util.List;

import eight_puzzle.state.State;

public class EightPuzzleResult implements Result {
	
	private State goalState;
	private List<State> path;
	private int processedNodes, steps, maxDepth;
	private long processingTime;
	
	public EightPuzzleResult(int steps, State goalState, int processedNodes, long processingTime, int maxDepth) {
		this.goalState = goalState;
		this.steps = steps;
		this.processedNodes = processedNodes;
		this.processingTime = processingTime;
		this.maxDepth = maxDepth;
	}
	
	@Override
	public List<State> getPath() {
		if(path == null) {
			path = new LinkedList<>();
			State currentState = goalState;
			while(currentState != null) {
				((LinkedList<State>)path).addFirst(currentState);
				currentState = currentState.getParentState(); 
			}
		}
		return new LinkedList<>(path);
	}

	@Override
	public int getSteps() {
		return this.steps;
	}

	@Override
	public int getProcessedNodes() {
		return this.processedNodes;
	}

	@Override
	public long getProcessingTime() {
		return this.processingTime;
	}
	
	@Override
	public String toString() {
		getPath();
		StringBuilder resultantString = new StringBuilder();
		resultantString.append("Processed Nodes: ").append(String.valueOf(processedNodes)).append('\n');
		resultantString.append("Processing Time: ").append(String.valueOf(processingTime)).append('\n');
		resultantString.append("Maximum Depth: ").append(String.valueOf(maxDepth)).append('\n');
		resultantString.append("Branch Steps: ").append(String.valueOf(steps)).append('\n');
		if(steps <= 50) {
			for(State state : path) {
				resultantString.append(state).append("++++++++++++++\n");
			}
		} else {
			resultantString.append("THE PATH IS TOO LONG");
		}
		return resultantString.toString();
	}

	@Override
	public int getMaxDepth() {
		return this.maxDepth;
	}

}
