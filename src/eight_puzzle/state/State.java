package eight_puzzle.state;

import java.util.List;

public interface State {
	
	public List<State> neighbors();
	
	public int getCell(Cell cell) throws IllegalArgumentException;
	
	public void setParentState(State state);
	
	public State getParentState();
	
	public int getDepth();
	
	public boolean isGoalState();
	
	public boolean isSolvable();
	
}
