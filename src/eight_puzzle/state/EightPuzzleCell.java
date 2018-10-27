package eight_puzzle.state;

public class EightPuzzleCell implements Cell {
	
	private int row, column;
	
	public EightPuzzleCell(int row, int column) {
		this.row = row;
		this.column = column;
	}
	
	public int getRow() {
		return this.row;
	}
	
	public int getColumn() {
		return this.column;
	}
	
}
