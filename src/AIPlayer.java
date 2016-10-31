/********************************
 * COMP90041 Project C			*
 * File : AIPlayer.java			*
 * Author : Renyi Hou			*
 * Student ID : renyih			*
 * Student Number : 764696		*
 * Date : 22/5/2016				*
*********************************/

//Subclass from player for AI player class
public class AIPlayer extends Player 
{	
	private int firstRow = 0, thirdRow = 2;
	private int firstCol = 0, thirdCol = 2;
	
	//Abstract method from player class, decided where to move for AI
	public Move makeMove(char[][] gameBoard) 
	{		
		Move newMove = new Move();
		//Check Full
		System.out.println(this.getGivenName()+"'s move:");
		boolean innerCheck = false;
		for(int row = firstRow; row <= thirdRow; row++)
		{	
			for(int col = firstCol; col <= thirdCol ; col++)
			{
				if(gameBoard[row][col] == ' ')
				{
					newMove.setColumn(col);
					newMove.setRow(row);
					innerCheck = true;
					break;
				}
			}
			if(innerCheck) 
				break;
		}
		return newMove;
	}
		
	//Default Constructor
	public AIPlayer()
	{
		super();
	}
		
	//Overloading Constructor
	public AIPlayer(String username, String familyName, String givenName)
	{
		super(username, familyName, givenName);
	}
}