package eight_puzzle.state;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class EightPuzzleState implements State {
	
	public static final int LENGTH = 3, NUMBERS_COUNT = 9;
	private int[][] board;
	private Cell zeroCell;
	private List<State> neighbors;
	private int hash;
	private State parentState;
	private int depth;
	
	public EightPuzzleState(int[][] board, int depth) throws IllegalArgumentException {
		if(board == null || board.length != LENGTH || board[0].length != LENGTH) {
			throw new IllegalArgumentException();
		}
		zeroCell = null;
		Set<Integer> containedNumbers = new HashSet<>();
		for(int i = 0; i < board.length; i++) {
			for(int j = 0; j < board[0].length; j++) {
				if(board[i][j] == 0) {
					zeroCell = new EightPuzzleCell(i, j);
				}
				if(board[i][j] >= NUMBERS_COUNT || board[i][j] < 0) {
					throw new IllegalArgumentException();
				}
				containedNumbers.add(board[i][j]);
			}
		}
		if(containedNumbers.size() != NUMBERS_COUNT) {
			throw new IllegalArgumentException();
		}
		this.board = board;
		neighbors = null;
		hash = -1;
		this.depth = depth;
	}
	
	/**
	 * Build the neighbor states to the current state.
	 * @return the neighbors to the current state.
	 */
	private List<State> generateneighbors() {
		List<State> newneighbors = new ArrayList<>();
		int[] dx = new int[] {0, 1, 0, -1};
		int[] dy = new int[] {-1, 0, 1, 0};
		for(int i = 0; i < dx.length; i++) {
			int newRow = zeroCell.getRow() + dy[i];
			int newColumn = zeroCell.getColumn() + dx[i];
			if(isValidTransition(newRow, newColumn)) {
				newneighbors.add(new EightPuzzleState(createNewBoard(new EightPuzzleCell(newRow, newColumn)), depth + 1));
			}
		}
		return newneighbors;
	}
	
	private int[][] createNewBoard(Cell newZeroCell) {
		int[][] newBoard = new int[LENGTH][LENGTH];
		for(int i = 0; i < LENGTH; i++) {
			for(int j = 0; j < LENGTH; j++) {
				newBoard[i][j] = board[i][j];
			}
		}
		newBoard[newZeroCell.getRow()][newZeroCell.getColumn()] = 0;
		newBoard[zeroCell.getRow()][zeroCell.getColumn()] = board[newZeroCell.getRow()][newZeroCell.getColumn()];
		return newBoard;
	}
	
	private boolean isValidTransition(int newRow, int newColumn) {
		return newRow < LENGTH && newRow >= 0 && newColumn < LENGTH && newColumn >= 0;
	}
	
	
	@Override
	public List<State> neighbors() {
		if(neighbors == null) {
			neighbors = generateneighbors();
		}
		return new ArrayList<>(neighbors);
	}
	
	/**
	 * Overriding the equals() method from the Object 
	 * class in order to use it in a hash set
	 */
	@Override
	public boolean equals(Object anotherState) {
		if(anotherState == this) {
			return true;
		}
		if(!(anotherState instanceof EightPuzzleState)) {
			return false;
		}
		for(int i = 0; i < LENGTH; i++) {
			for(int j = 0; j < LENGTH; j++) {
				if(board[i][j] != ((EightPuzzleState)anotherState).getCell(new EightPuzzleCell(i, j))) {
					return false;
				}
			}
		}
		return true;
	}
	
	/**
	 * Overriding the hashcode() of the Object class 
	 * in order to use this class in hash set.
	 */
	@Override
	public int hashCode() {
		if(hash == -1) {
			hash = calculateHash();
		}
		return hash;
	}
	
	private int calculateHash() {
		StringBuilder newHash = new StringBuilder();
		for(int i = 0; i < LENGTH; i++) {
			for(int j = 0; j < LENGTH; j++) {
				newHash.append((char)(board[i][j] + '0'));
			}
		}
		return Integer.valueOf(newHash.toString());
	}

	@Override
	public int getCell(Cell cell) throws IllegalArgumentException {
		if(cell == null || !isValidTransition(cell.getRow(), cell.getColumn())) {
			throw new IllegalArgumentException();
		}
		return board[cell.getRow()][cell.getColumn()];
	}

	@Override
	public void setParentState(State state) {
		this.parentState = state;	
	}

	@Override
	public State getParentState() {
		return this.parentState;
	}

	@Override
	public int getDepth() {
		return this.depth;
	}

	@Override
	public boolean isGoalState() {
		int pre = -1;
		for(int i = 0; i < LENGTH; i++) {
			for(int j = 0; j < LENGTH; j++) {
				if(board[i][j] <= pre) {
					return false;
				}
				pre = board[i][j];
			}
		}
		return true;
	}
	
	/**
	 * Checks if the current state is solvable or not.
	 * It depends on the inversion count.
	 * If the inversion count is odd, it will be unsolvable
	 * as every transition decreases the inversion count by an 
	 * even quantity.
	 */
	@Override
	public boolean isSolvable() {
		int inversions = 0;
		boolean[] appeared = new boolean[LENGTH * LENGTH];
		for(int i = 0; i < LENGTH; i++) {
			for(int j = 0; j < LENGTH; j++) {
				int count = 0;
				for(int k = board[i][j] + 1; k < LENGTH * LENGTH && board[i][j] != 0; k++) {
					if(appeared[k]) {
						count++;
					}
				}
				inversions += count;
				appeared[board[i][j]] = true;
			}
		}
		return inversions % 2 == 0;
	}
	
	@Override
	public String toString() {
		StringBuilder resultantString = new StringBuilder();
		for(int[] row : board) {
			resultantString.append(Arrays.toString(row)).append('\n');
		}
		return resultantString.toString();
	}
}
