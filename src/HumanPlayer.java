/********************************
 * COMP90041 Project C			*
 * File : AIPlayer.java			*
 * Author : Renyi Hou			*
 * Student ID : renyih			*
 * Student Number : 764696		*
 * Date : 22/5/2016				*
*********************************/

//Subclass from player for human player class
public class HumanPlayer extends Player
{	
	private int firstRow = 0, thirdRow = 2;
	private int firstCol = 0, thirdCol = 2;
	
	//Grid updating and checking if input invalid
	private boolean checkGridCell(int row, int col, char[][] gameBoard)
	{
		if((row < firstRow || row > thirdRow) && (col < firstCol || col > thirdCol))
		{
			System.out.println("Invalid move. You must place at a cell within {0,1,2} {0,1,2}.");
			return false;
		}
		else if(gameBoard[row][col] != ' ')
		{
			System.out.println("Invalid move. The cell has been occupied.");
			return false;
		}
		else
		{
			return true;
		}
	}
		
	public Move makeMove(char[][] gameBoard)
	{	
		boolean inputCheck = false;
		int row, col;
		do
		{
			System.out.println(this.getGivenName()+"'s move:");
			row = TicTacToe.keyboard.nextInt();
			col = TicTacToe.keyboard.nextInt();
			inputCheck = checkGridCell(row, col, gameBoard);
		} while(!inputCheck);
		
		Move newMove = new Move();
		newMove.setRow(row);
		newMove.setColumn(col);
		
		return newMove;
	}
	
	//Default Constructor
	public HumanPlayer()
	{
		super();
	}
	
	//Overloading constructor
	public HumanPlayer(String username, String familyName, String givenName)
	{
		super(username, familyName, givenName);
	}
}