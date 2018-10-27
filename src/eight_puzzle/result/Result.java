package eight_puzzle.result;

import java.util.List;

import eight_puzzle.state.State;

public interface Result {
	
	public List<State> getPath();
	
	public int getSteps();
	
	public int getProcessedNodes();
	
	public long getProcessingTime();
	
	public int getMaxDepth();
		
}
