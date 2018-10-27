import java.util.Scanner;

import ds.container.QueueContainer;
import ds.container.StackContainer;
import eight_puzzle.comparator.EuclideanDistanceComparator;
import eight_puzzle.comparator.ManhattanDistanceComparator;
import eight_puzzle.result.Result;
import eight_puzzle.solver.EightPuzzleSolverInformed;
import eight_puzzle.solver.EightPuzzleSolverUninformed;
import eight_puzzle.solver.Solver;
import eight_puzzle.state.EightPuzzleState;
import eight_puzzle.state.State;

public class EightPuzzleManager {

	public EightPuzzleManager() {

	}

	public void run() {
		try {
			Scanner sc = new Scanner(System.in);
			int[][] board = getInitialState(sc);
			Solver solver = getSolver(sc);
			State initialState = new EightPuzzleState(board, 0);
			if (solver == null || !initialState.isSolvable()) {
				throw new IllegalArgumentException();
			}
			Result result = solver.solve(initialState);
			System.out.println(result);
		} catch (IllegalArgumentException e) {
				System.out.println("NOT SOLVABLE");
		}
	}

	private Solver getSolver(Scanner sc) {
		System.out.print("Enter 0 for dfs, 1 for bfs or 2 for A*: ");
		int type = sc.nextInt();
		switch (type) {
		case 0:
			return new EightPuzzleSolverUninformed(new StackContainer<>());
		case 1:
			return new EightPuzzleSolverUninformed(new QueueContainer<>());
		case 2:
			System.out.print("Enter 0 for Manhattan method or 1 for Ecluidean meathod: ");
			int aStarType = sc.nextInt();
			switch (aStarType) {
			case 0:
				return new EightPuzzleSolverInformed(new QueueContainer<>(new ManhattanDistanceComparator()));
			case 1:
				return new EightPuzzleSolverInformed(new QueueContainer<>(new EuclideanDistanceComparator()));
			default:
				return null;
			}
		default:
			return null;
		}
	}

	private int[][] getInitialState(Scanner sc) {
		System.out.print("Enter the initial in state space separated format:");
		int[][] board = new int[EightPuzzleState.LENGTH][EightPuzzleState.LENGTH];
		for (int i = 0; i < EightPuzzleState.LENGTH; i++) {
			for (int j = 0; j < EightPuzzleState.LENGTH; j++) {
				board[i][j] = sc.nextInt();
			}
		}
		return board;
	}
}
