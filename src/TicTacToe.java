/********************************
 * COMP90041 Project C			*
 * File : TicTacToe.java		*
 * Author : Renyi Hou			*
 * Student ID : renyih			*
 * Student Number : 764696		*
 * Date : 22/5/2016				*
*********************************/

import java.util.Scanner;
import java.util.StringTokenizer;

public class TicTacToe {
	private PlayerManager pm;					//Instance variable for player manager class
	private GameManager gm;						//Instance variable for game manager class
	public static Scanner keyboard;				//Main scanner for handling input
	private boolean systemLoop = true;			//system looping checker, default true
	
	//Default constructor for TicTacToe
	TicTacToe()
	{
		keyboard = new Scanner(System.in);
		pm = new PlayerManager();
		gm = new GameManager();
	}
	
	//Main input switching function, determine which sub function to be called
	public void getInput()
	{
		String option = null, information = null;
		
		//First part, take in the whole string and split using tokenizer
		String keyboardInput = keyboard.nextLine();
		
		//check if the string have two part split with space
		if(keyboardInput.contains(" "))
		{
			StringTokenizer userInput = new StringTokenizer(keyboardInput," ");
			option = userInput.nextToken();
			information = userInput.nextToken();
		}
		else
		{
			option = keyboardInput;
		}
	
		try{
			//Second part, switch to different case based on first string token
					//Add player case
			switch (option){
				case "addplayer" : pm.addPlayer(information); break;			//Add player
				case "addaiplayer" : pm.addAIPlayer(information); break;		//Add AI Player
				case "removeplayer" : pm.removePlayer(information); break;		//Remove player
				case "editplayer" : pm.editPlayer(information); break;			//Edit player
				case "resetstats" : pm.resetStats(information); break;			//Reset stat
				case "displayplayer" : pm.displayPlayer(information); break;	//Display player
				case "rankings" : pm.displayRanking(); break;					//Display rankings
				case "playgame" : 									//Start playing game case
					try
					{
						if(information == null)
							throw new InvalidArgumentException();
						
						//Split the string using tokenizer with comma delimiter
						StringTokenizer playerDetailsToken = new StringTokenizer(information,",");
						
						//Getting the user name of first player and second player
						if(playerDetailsToken.countTokens() < 2)
							throw new InvalidArgumentException();
						
						String firstPlayer = playerDetailsToken.nextToken();
						String secondPlayer = playerDetailsToken.nextToken();
						
						int fPAssist = pm.findPlayer(firstPlayer);
						int sPAssist = pm.findPlayer(secondPlayer);
						
						//if both player assist, start the game, else back to main menu
						//-1 indicate player not found
						if(fPAssist != -1 && sPAssist != -1){
							Player playerOne = pm.returnPlayer(fPAssist);
							Player playerTwo = pm.returnPlayer(sPAssist);
							
							//Game start through here
							gm.playGame(playerOne, playerTwo);
						}
						else
						{
							System.out.println("Player does not exist.");
						}
					}
					catch(InvalidArgumentException e)
					{
						System.out.println(e.getMessage());
					}
					break;
				case "exit" : systemLoop = false; break;		//Exit game case
				default : throw new InvalidCommandException("'" + option + "'" + 
													" is not a valid command.");
			}
		}
		catch(InvalidCommandException e)
		{
			System.out.println(e.getMessage());
		}
	}
	
	//run function for the TicTacToe class, handle the games loop
	public void run()
	{	
		System.out.println("Welcome to Tic Tac Toe!");
		
		//Game loop for keep getting user respond until exit
		do {
			System.out.print("\n>");
			getInput();
		} while(systemLoop);
		
		pm.storeData();
		keyboard.close();			//close the keyboard function
		
		System.out.println();
		System.exit(0);				//quit the system
	}
	
	//main execute function 
	public static void main(String[] args)
	{
		TicTacToe gameSystem = new TicTacToe();
		gameSystem.run();	
	}
}