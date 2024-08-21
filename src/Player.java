import java.util.List;

abstract public class Player extends Thread{
	
	private char symbol;
	private GameBoard game;
	private int score;
	protected String name;
	
	public Player(GameBoard g,char symbol,int score) {
		this.score=score;
		this.game=g;
		this.symbol=symbol;
	}
	
	
	public char Getsymbol() {
		return this.symbol;
	}
	
	public void AddPoint() {
		this.score++;
	}
	
	public int GetPoints() {
		return this.score;
	}
	
	public abstract void SetPlace(); 
	
	
	public GameBoard GetGame() {
		return this.game;
	}
	
	public void WhoWon() {
	    GameBoard game = this.GetGame();
	    List<Character> seq = game.GetSeq();
	    int rows = game.getRows();
	    int columns = game.getColumns();
	    int connectTarget = game.getConnectTarget();
	    char symbol = this.Getsymbol();

	    for (int row = 0; row < rows; row++) {
	        for (int col = 0; col < columns; col++) {
	            if (seq.get(row * columns + col) != symbol) continue;

	            // Check horizontal
	            if (col + connectTarget <= columns && checkDirection(seq, row, col, 0, 1, symbol, columns, connectTarget)) {
	                highlightWinningSequence(seq, row, col, 0, 1, symbol, columns, connectTarget);
	                game.Print();
	                this.AddPoint();
	                game.SetFinish(true);
	                System.out.println("Player with symbol " + symbol + " won horizontally!");
	                return;
	            }

	            // Check vertical
	            if (row + connectTarget <= rows && checkDirection(seq, row, col, 1, 0, symbol, columns, connectTarget)) {
	                highlightWinningSequence(seq, row, col, 1, 0, symbol, columns, connectTarget);
	                game.Print();
	                this.AddPoint();
	                game.SetFinish(true);
	                System.out.println("Player with symbol " + symbol + " won vertically!");
	                return;
	            }

	            // Check diagonal down-right
	            if (row + connectTarget <= rows && col + connectTarget <= columns && checkDirection(seq, row, col, 1, 1, symbol, columns, connectTarget)) {
	                highlightWinningSequence(seq, row, col, 1, 1, symbol, columns, connectTarget);
	                game.Print();
	                this.AddPoint();
	                game.SetFinish(true);
	                System.out.println("Player with symbol " + symbol + " won diagonally down-right!");
	                return;
	            }

	            // Check diagonal down-left
	            if (row + connectTarget <= rows && col - connectTarget + 1 >= 0 && checkDirection(seq, row, col, 1, -1, symbol, columns, connectTarget)) {
	                highlightWinningSequence(seq, row, col, 1, -1, symbol, columns, connectTarget);
	                game.Print();
	                this.AddPoint();
	                game.SetFinish(true);
	                System.out.println("Player with symbol " + symbol + " won diagonally down-left!");
	                return;
	            }
	        }
	    }
	}
	
	private boolean checkDirection(List<Character> seq, int row, int col, int rowDir, int colDir, char symbol, int columns, int connectTarget) {
	    for (int i = 1; i < connectTarget; i++) {
	        if (seq.get((row + i * rowDir) * columns + (col + i * colDir)) != symbol) {
	            return false;
	        }
	    }
	    return true;
	}
	
	private void highlightWinningSequence(List<Character> seq, int row, int col, int rowDir, int colDir, char symbol, int columns, int connectTarget) {
	    for (int i = 0; i < connectTarget; i++) {
	        int index = (row + i * rowDir) * columns + (col + i * colDir);
	        seq.set(index, Character.toUpperCase(symbol)); // For example, making the symbol uppercase as "highlight"
	    }
	}
}


