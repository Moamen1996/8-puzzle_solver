package eight_puzzle.solver;

import eight_puzzle.result.Result;
import eight_puzzle.state.State;

public interface Solver {
	
	Result solve(State initialState);
}
