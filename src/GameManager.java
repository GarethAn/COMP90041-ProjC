/********************************
 * COMP90041 Project C			*
 * File : AIPlayer.java			*
 * Author : Renyi Hou			*
 * Student ID : renyih			*
 * Student Number : 764696		*
 * Date : 22/5/2016				*
*********************************/

public class GameManager 
{
	private static final int PLAYER_O_WIN = 1;
	private static final int PLAYER_X_WIN = 2;
	private static final int GAME_DRAW = 3;
	private static final int GAME_CONTINUE = 4;
	private static final int PLAYER_ONE = 1;
	private static final int PLAYER_TWO = 2;
	private static final char SYMBOL_O = 'O';
	private static final char SYMBOL_X = 'X';
	private char[][] gridCells;
	private int firstRow = 0, secRow = 1, thirdRow = 2;
	private int firstCol = 0, secCol = 1, thirdCol = 2;
	private Move playerMove = new Move();
	
	//Default constructor for game manager class
	public GameManager()
	{							
		createGrid();
	}
	
	//Create new game board
	private void createGrid()
	{
		gridCells = new char[3][3];
		for(int row = firstRow; row <= thirdRow; row++)
		{	
			for(int col = firstCol; col <= thirdCol ; col++)
			{
				gridCells[row][col] = ' ';
			}
		}
	}
	
	//Grid Cell Printing function
	private void printGrid()
	{					
		for(int row = firstRow; row <= thirdRow; row++)
		{	
			for(int col = firstCol; col <= thirdCol ; col++)
			{
				System.out.print(gridCells[row][col]);
				
				//Multiple case after each slot printed
				if(( row == firstRow && col == thirdCol) || ( row == secRow && col == thirdCol))
				{
					System.out.println("\n-----");
				} 
				else if (row == thirdRow && col == thirdCol)
				{
					System.out.println("");
				}
				else
				{ 
					System.out.print("|");	
				}
			}
		}
	}
	
	private int getGameState()
	{				
		//Game Checking state
		//Check Column
		for(int col = firstCol; col <= thirdCol ; col++)
		{
			if(gridCells[firstRow][col] == gridCells[secRow][col] 
					&& gridCells[secRow][col] == gridCells[thirdRow][col])
			{
				if(gridCells[firstRow][col] == SYMBOL_O)
				{
					return PLAYER_O_WIN;
				}
				else if(gridCells[firstRow][col] == SYMBOL_X)
				{
					return PLAYER_X_WIN;	
				}
			}
		}
		
		//Check Row
		for(int row = firstRow; row <= thirdRow ; row++)
		{
			if(gridCells[row][firstCol] == gridCells[row][secCol] 
					&& gridCells[row][secCol] == gridCells[row][thirdCol])
			{
				if(gridCells[row][firstCol] == SYMBOL_O)
				{
					return PLAYER_O_WIN;
				}
				else if(gridCells[row][firstCol] == SYMBOL_X)
				{
					return PLAYER_X_WIN;	
				}
			}
		}
		
		//Check diagonal
		if(gridCells[firstRow][firstCol] == gridCells[secRow][secCol] 
				&& gridCells[secRow][secCol] == gridCells[thirdRow][thirdCol] ||
				gridCells[firstRow][thirdCol] == gridCells[secRow][secCol] 
				&& gridCells[secRow][secCol] == gridCells[thirdRow][firstCol])
		{
				if(gridCells[secRow][secCol] == SYMBOL_O)
				{
					return PLAYER_O_WIN;
				}
				else if(gridCells[secRow][secCol] == SYMBOL_X)
				{
					return PLAYER_X_WIN;
				}
		}
		
		//Check Full
		int cellUsed = 0;
		for(int row = firstRow; row <= thirdRow; row++)
		{	
			for(int col = firstCol; col <= thirdCol ; col++)
			{
				if(gridCells[row][col] != ' ')
				{
					cellUsed += 1; // cellUsed = cellUsed + 1;
				}
			}
		}
		
		if (cellUsed == 9)
		{				
			//all nine cells used
			return GAME_DRAW;
		}
		
		//Game Continue
		return GAME_CONTINUE;
	}
	
	//Update score of player after game end
	private void updateScore(Player playerOne, Player playerTwo)
	{
		playerOne.numGamesPlayedInc();
		playerTwo.numGamesPlayedInc();
		playerOne.countRatio();
		playerTwo.countRatio();
	}
	
	//main game mechanic function
	public void playGame(Player playerOne, Player playerTwo)
	{
		createGrid();				//recreate new grid when game start
		printGrid();				//start by printing grid cell
		
		//Game Start
		int gameState = GAME_CONTINUE, playerOrder = PLAYER_ONE;
		
		//main game looping
		do{
			gameState = getGameState();
			
			switch(gameState){
				case GAME_CONTINUE:									//Game Continue
					if(playerOrder == PLAYER_ONE)
					{					//Player O input
						//check user input, if false will keep ask for reinput
						playerMove = playerOne.makeMove(gridCells);
						gridCells[playerMove.getRow()][playerMove.getColumn()] = SYMBOL_O;
						playerOrder = PLAYER_TWO;
					} 
					else if (playerOrder == PLAYER_TWO)
					{				
						//Player X Input
						//check user input, if false will keep ask for reinput
						playerMove = playerTwo.makeMove(gridCells);
						gridCells[playerMove.getRow()][playerMove.getColumn()] = SYMBOL_X;
						playerOrder = PLAYER_ONE;
					}
					printGrid();			//after input, update grid cell
					break;
				case PLAYER_O_WIN:			//Player O Win
					System.out.println("Game over. "+ playerOne.getGivenName() +" won!");
					playerOne.numGamesWonInc();	
					updateScore(playerOne, playerTwo);
					break;
				case PLAYER_X_WIN:			//Player X Win
					System.out.println("Game over. "+ playerTwo.getGivenName() +" won!");
					playerTwo.numGamesWonInc();	
					updateScore(playerOne, playerTwo);
					break;
				case GAME_DRAW: 
					System.out.println("Game over. It was a draw!");			//Draw game
					playerOne.numGamesDrawnInc();
					playerTwo.numGamesDrawnInc();
					updateScore(playerOne, playerTwo);
					break;
			}
			
		}
		while(gameState == GAME_CONTINUE);
		String garbage = TicTacToe.keyboard.nextLine();		//clear the input garbage before back to main 
	}
}
