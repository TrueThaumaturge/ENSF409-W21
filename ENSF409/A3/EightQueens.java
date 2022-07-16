import java.util.Arrays;

/**
* A class that represents and fills an 8x8 chess board with up to 8 queens
* such that none are in the same row, column, or diagonal using a recursive
* solution.
* @author	 Quentin Jennings <a href="mailto:quentin.jennings@ucalgary.ca">
*	quentin.jennings@ucalgary.ca</a>
* @version	1.9
* @since 	1.0
*/
public class EightQueens {
	private char[][] chessboard = new char[8][8]; //placement of queens (o / Q)
	private int queensPlaced = 0;
	
	/*//testing purposes
	public static void main(String[] args) {
		EightQueens test = new EightQueens();
		test.setQueens(8);
		
		EightQueens testCopy = test.clone();
		testCopy.printBoard();
		testCopy.emptySquare(0,0);
		test.printBoard();
		testCopy.printBoard();
		
	}*/
	
	//____________________
	/**
	 * Constructor that fills the chessboard and threatened board.
	 */
	public EightQueens() {
		for(char[] arr : chessboard) {
			Arrays.fill(arr, 'o');
		}
	}
	
	/**
	 * Clones the object using a deep copy.
	 * @return Object Returns the cloned EightQueens object.
	 */
	public EightQueens clone(){
		EightQueens copy = new EightQueens();
		copy.queensPlaced = queensPlaced;
		copy.chessboard = new char[8][8];
		for(int i = 0; i < 8; i++) {
			for(int j = 0; j < 8; j++) {
				copy.chessboard[i][j] = chessboard[i][j];
			}
		}
		return copy;
	}
	//____________________
	
	//____________________
	/**
	 * Getter for the chessboard.
	 * @return char[][] Returns the chessboard 2D array.
	 */
	public char[][] getBoard() {
		return chessboard;
	}
	/**
	 * Getter for the chessboard.
	 * @return char[][] Returns the chessboard 2D array.
	 */
	public int getQueensPlaced() {
		return queensPlaced;
	}
	
	/**
	 * Prints the chessboard and the threatened board.
	 */
	void printBoard() {
		for(char[] arr : chessboard) {
			for(char ch : arr) {
				System.out.print(ch + " ");
			}
			System.out.println();
		}
	}
	//____________________
	
	//____________________
	/**
	 * Error checks, then calls a recursive member to actually set the queens.
	 * @param queensRemaining The number of queens to be added to the board.
	 */
	public boolean setQueens(int queensRemaining) {
		if(queensRemaining < 0 || queensRemaining > 8) {
			throw new IllegalArgumentException("Input must be between 0-8.");
		}
		if(queensPlaced + queensRemaining > 8) { //if the board is full, can't place more
			System.out.println("Error: Board can not exceed 8 queens.");
			return false;
		}
		if(boardValidity() == false) {
			System.out.println("Error: Invalid board prior to placement.");
			return false;
		}
		return setQueensRecursive(queensRemaining, 0);
	 }
	
	/**
	 * Recursively places a number of queens so that none are in the same
	 * row, column, or diagonal.
	 * @param queensRemaining The number of queens left to be added.
	 * @param row The current row of the recursion.
	 */
	public boolean setQueensRecursive(int queensRemaining, int row) {
		if(queensRemaining == 0) { //exit condition
			return true;
		}
		else { //recursive condition
			for(int col = 0; col < 8; col++) {
				if(chessboard[row][col] == 'Q') {
					if(setQueensRecursive(queensRemaining, row + 1)) { //recursive call
						return true;
					}
				}
				else if(validPlacement(row, col)) {
					setQueen(row, col); //places queen
					if(setQueensRecursive(queensRemaining - 1, row + 1)) { //recursive call
						return true;
					}
					else { //backtracks and removes queen if it doesn't work
						emptySquare(row, col);
					}
				}
			}
		}
		return false; //if a queen can't be placed in any column, needs to backtrack
	}
	//____________________
	
	//____________________
	/**
	 * Checks if a Queen could be placed at a location.
	 * @param row The row of the queen.
	 * @param col The column of the queen.
	 * @return boolean If the placement is valid, returns true.
	 */
	boolean validPlacement(int row, int col) {
		for(int i = 0; i < 8; i++) {
			if(chessboard[i][col] == 'Q' || chessboard[row][i] == 'Q') {
				return false;
			}
			for(int j = 0; j < 8; j++) {
				if(chessboard[i][j] == 'Q') {
					if(row + col == i + j || row - col == i - j) {
						return false;
					}
				}
			}
		}
		return true;
	}
	
	/**
	 * Checks each queen to ensure a valid placement.
	 * @return boolean If a placement is invalid, returns false.
	 */
	boolean boardValidity() {
		for(int i = 0; i < 8; i++) {
			for(int j = 0; j < 8; j++) {
				if(chessboard[i][j] == 'Q') {
					emptySquare(i, j);
					boolean validity = validPlacement(i, j);
					setQueen(i, j);
					if(validity == false) {
						return false;
					}
				}
			}
		}
		return true;
	}
	//____________________
	
	//____________________
	/**
	 * Places a queen on the chessboard, regardless of any checks.
	 * @param row The row of the target.
	 * @param column The column of the target.
	 */
	public void setQueen(int row, int col) {
		if(chessboard[row][col] == 'o') {
			chessboard[row][col] = 'Q';
			queensPlaced++;
		}
	}
	
	/**
	 * Empties a square, removing any queens present in the location.
	 * @param row The row of the target.
	 * @param column The column of the target.
	 */
	public void emptySquare(int row, int col) {
		if(chessboard[row][col] == 'Q') {
			chessboard[row][col] = 'o';
			queensPlaced--;
		}
	}
	//____________________
}