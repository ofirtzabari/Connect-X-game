import java.util.ArrayList;
import java.util.List;

public class GameBoard {
	
	private StringBuilder board;
	private List<Character> seq = new ArrayList<Character>();
	private int columns;
	private int rows;
	private int connectTarget;
	private boolean isfinis;
	
	
	public GameBoard(int col, int row, int connect) {
		columns = col;
		rows = row;
		connectTarget = connect;
		isfinis=false;
		board = createBoard(row, col);
		for (int i=0 ; i< col*row; i++)
			seq.add(' ');
	}
	
	private static StringBuilder createBoard(int row, int col) {
        StringBuilder board = new StringBuilder();
        for (int j = 0; j < col; j++) {
            board.append("  ").append(j).append(" ");  // Add each column number with spacing
        }
        board.append("\n");
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                board.append("| %c ");
            }
            board.append("|\n");
            
            // Add the bottom line of the current row
            for (int j = 0; j < col; j++) {
                board.append("----");
            }
            board.append("-\n");
        }
        
        return board;
    }


	public void Print() {
		System.out.println("\n" + String.format(this.board.toString(), seq.toArray()));
	}
	
	public void DoMove(int p,char symbol) {
		this.seq.set(p, symbol);
	}
	
	public int getColumns() {
		return columns;
	}
	
	public int getRows() {
		return rows;
	}
	
	public List<Character> GetSeq() {
		return this.seq;
	}
	
	public boolean IsFinish() {
		return this.isfinis;
		}
	
	public void SetFinish(boolean b) {
		this.isfinis=b;
	}
	
	public int getConnectTarget() {
		return connectTarget;
	}
}
