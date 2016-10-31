/********************************
 * COMP90041 Project C			*
 * File : PlayerManager.java	*
 * Author : Renyi Hou			*
 * Student ID : renyih			*
 * Student Number : 764696		*
 * Date : 22/5/2016				*
*********************************/

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.StringTokenizer;

public class PlayerManager {
	private static final int SORT_BY_RANKING = 1;
	private static final int SORT_BY_USERNAME = 2;
	private static final int MAX_PLAYER = 100;
	private Player[] playerData;
	private int currentPlayerNumber = 0;
	
	//Default constructor for player manager
	public PlayerManager()
	{
		playerData = new Player[MAX_PLAYER];
		getData();
	}
	
	//Retrieve data from players.dat to program
	public void getData()
	{
		try
		{
			ObjectInputStream inputStream = 
					new ObjectInputStream(new FileInputStream("players.dat"));
			
			try
			{
				int i = 0;
				//Will keep retrieve data until last line
				while(true)
				{
					String playerInfo = inputStream.readUTF();
					StringTokenizer playerDetailsToken = new StringTokenizer(playerInfo, ",");
					
					//Initialize difference class based on class type
					String classType = playerDetailsToken.nextToken();
					if(classType.equals("human"))
						playerData[i] = new HumanPlayer();
					else if(classType.equals("ai"))
						playerData[i] = new AIPlayer();
					
					playerData[i].setUsername(playerDetailsToken.nextToken());
					playerData[i].setFamilyName(playerDetailsToken.nextToken());
					playerData[i].setGivenName(playerDetailsToken.nextToken());
					
					//Retrieve integer from string and store into player data
					String numGame = playerDetailsToken.nextToken();
					int numberGame = Integer.parseInt(numGame.replaceAll("[\\D]", ""));
					playerData[i].setNumGamesPlayed(numberGame);
					
					String winGame = playerDetailsToken.nextToken();
					int winOfGame = Integer.parseInt(winGame.replaceAll("[\\D]", ""));
					playerData[i].setNumGamesWon(winOfGame);
					
					String drawGame = playerDetailsToken.nextToken();
					int drawOfGame = Integer.parseInt(drawGame.replaceAll("[\\D]", ""));
					playerData[i].setNumGamesDrawn(drawOfGame);
					
					i++;
					currentPlayerNumber++;
				}
			}
			catch(EOFException e)
			{
				inputStream.close();
			}
		}
		catch(FileNotFoundException e)
		{
		}
		catch(IOException e)
		{
		}
	}
	//Store data to players.dat after exit
	public void storeData()
	{
		try
		{
			ObjectOutputStream outputStream = 
					new ObjectOutputStream(new FileOutputStream("players.dat"));
			
			sort(playerData, currentPlayerNumber, SORT_BY_USERNAME);
			
			for (int i = 0; i < currentPlayerNumber ; i++)
			{
				//Store extra information for player class type
				if(playerData[i].getClass().getName().equals("HumanPlayer"))
				{
					outputStream.writeUTF("human," + playerData[i].toString());
				}
				else if(playerData[i].getClass().getName().equals("AIPlayer"))
				{
					outputStream.writeUTF("ai," + playerData[i].toString());
				}
			}
			outputStream.close( );
		}
		catch(IOException e)
		{
			System.out.println("check3");
		}	
	}
	
	//Add player method
	public void addPlayer(String information)
	{	
		try
		{
			if(information != null)
			{	
				StringTokenizer playerDetailsToken = new StringTokenizer(information,",");
				
				if(playerDetailsToken.countTokens() < 3)
					throw new InvalidArgumentException();
				
				String username = playerDetailsToken.nextToken();
				String familyName = playerDetailsToken.nextToken();
				String givenName = playerDetailsToken.nextToken();
				
				//Using checkPlayer to detect if player already assist
				if(findPlayer(username) != -1)
				{
					System.out.println("The username has been used already.");
				}
				else
				{
					playerData[currentPlayerNumber] = new 
								HumanPlayer(username, familyName, givenName);
					currentPlayerNumber = currentPlayerNumber + 1;
				}
			}
			else
			{
				throw new InvalidArgumentException();
			}
		}
		catch(InvalidArgumentException e)
		{
			System.out.println(e.getMessage());
		}
	}
	
	//Add ai player method
	public void addAIPlayer(String information)
	{	
		try
		{
			if(information != null)
			{	
				StringTokenizer playerDetailsToken = new StringTokenizer(information,",");
				
				if(playerDetailsToken.countTokens() < 3)
					throw new InvalidArgumentException();
				
				String username = playerDetailsToken.nextToken();
				String familyName = playerDetailsToken.nextToken();
				String givenName = playerDetailsToken.nextToken();
				
				//Using checkPlayer to detect if player already assist
				if(findPlayer(username) != -1)
				{
					System.out.println("The username has been used already.");
				}
				else
				{
					playerData[currentPlayerNumber] = new 
								AIPlayer(username, familyName, givenName);
					currentPlayerNumber = currentPlayerNumber + 1;
				}
			}
			else
			{
				throw new InvalidArgumentException();
			}
		}
		catch(InvalidArgumentException e)
		{
			System.out.println(e.getMessage());
		}
	}
	
	//Remove player method
	public void removePlayer(String username)
	{
		if(username != null)
		{
			int userID = findPlayer(username);
			if(userID == -1)
			{
				System.out.println("The player does not exist.");
			}
			else
			{
				if(userID == (currentPlayerNumber - 1))
					playerData[userID].clearInformation();
				else
				{
					for(int i = userID ; i < currentPlayerNumber - 1 ; i ++)
					{
						System.out.println(i);
						if(playerData[i+1].getClass().getName().equals("HumanPlayer"))
							playerData[i] = new HumanPlayer();
						else if(playerData[i+1].getClass().getName().equals("AIPlayer"))
							playerData[i] = new AIPlayer();
						
						playerData[i].duplicateInformation(playerData[i + 1]);
					}
					playerData[currentPlayerNumber - 1].clearInformation();
				}
				currentPlayerNumber--;
			}
		}
		else
		{
			System.out.println("Are you sure you want to remove all players? (y/n)");
			String respond = TicTacToe.keyboard.nextLine();
			if(respond.equals("y"))
			{
				for (int i = 0; i < currentPlayerNumber ; i++)
				{
					playerData[i].clearInformation();
				}
				currentPlayerNumber = 0;
			}
		}
	}
	
	//Edit player method
	public void editPlayer(String information)
	{
		try
		{
			if(information != null)
			{
				throw new InvalidArgumentException();
			}
			else
			{
				StringTokenizer playerDetailsToken = new StringTokenizer(information,",");
				
				if(playerDetailsToken.countTokens() < 3)
					throw new InvalidArgumentException();
				
				String username = playerDetailsToken.nextToken();
				String familyName = playerDetailsToken.nextToken();
				String givenName = playerDetailsToken.nextToken();
				
				int userID = findPlayer(username);
				if(userID == -1)
				{
					System.out.println("The player does not exist.");
				}
				else
				{
					playerData[userID].setFamilyName(familyName);
					playerData[userID].setGivenName(givenName);
				}
			}
		}
		catch(InvalidArgumentException e)
		{
			System.out.println(e.getMessage());
		}
	}
	
	//Reset statistics method
	public void resetStats(String username)
	{
		if(username != null)
		{
			int userID = findPlayer(username);
			if(userID == -1)
			{
				System.out.println("The player does not exist.");
			}
			else
			{
				playerData[userID].resetScore();
			}
		}
		else
		{
			System.out.println("Are you sure you want to reset all player statistics? (y/n)");
			String respond = TicTacToe.keyboard.nextLine();
			if(respond.equals("y"))
			{
				for (int i = 0; i < currentPlayerNumber ; i++)
				{
						playerData[i].resetScore();
				}
			}
		}
	}
	
	//Display player information method
	public void displayPlayer(String username)
	{
		if(username != null)
		{
			int userID = findPlayer(username);
			if(userID == -1)
			{
				System.out.println("The player does not exist.");
			}
			else
			{
				System.out.println(playerData[userID]);
			}
		}
		else
		{
			//before display, sort the player according to username
			sort(playerData, currentPlayerNumber, SORT_BY_USERNAME);
			for (int i = 0; i < currentPlayerNumber ; i++)
			{
				System.out.println(playerData[i]);
			}
		}
	}
	
	//Display ranking for top 10 player method, sort by insertion sort
	public void displayRanking()
	{	
		for (int i = 0; i < currentPlayerNumber; i++)
		{
			playerData[i].countRatio();
		}
		
		//before display, will sort according to winrate, drawrate and username
		sort(playerData, currentPlayerNumber, SORT_BY_RANKING);
		
		System.out.println(" WIN  | DRAW | GAME | USERNAME");
		for (int i = 0; i < currentPlayerNumber && i <= 10 ; i++)
		{
			int winrate = (int) Math.round (playerData[i].getWinrate());
			int drawrate = (int) Math.round (playerData[i].getDrawrate());
			int gamePlay = playerData[i].getNumGamesPlayed();
			String username = playerData[i].getUsername();
			System.out.printf(" %3d%% | %3d%% | %2d   | %s%n" 
							, winrate, drawrate, gamePlay, username);
		}
	}
	
	//Method to find is player assist and return index, if not found return -1
	public int findPlayer(String username)
	{
		for (int i = 0; i < currentPlayerNumber ; i++)
		{
			if(playerData[i].getUsername().equals(username))
			{
				return i;
			}
		}
		return -1;
	}
	
	public Player returnPlayer(int index)
	{
		return playerData[index];
	}
	
	//the following sorting algorithm is insertion sort
	private static void sort(Player[] playerData, int numberUsed, int sortCase)
	{
		Player tempPlayerData;		//Store the data in temporary space
		 
		for (int i = 1; i < numberUsed; i++)
		{
			tempPlayerData = playerData[i]; 		//assume the first one as the biggest
		 
			int j;
			for (j = i; j > 0; j--)
			{
				if(sortCase == SORT_BY_RANKING)
				{
					//comparing with compare function
					if (compare(tempPlayerData, playerData[j-1]))
					{
						playerData[j] = playerData[j-1]; 
					}
					else
					{
						break;
					}
				}
				else if(sortCase == SORT_BY_USERNAME)
				{
					//comparing with compare username function
					if (compareUsername(tempPlayerData, playerData[j-1]))
					{
						playerData[j] = playerData[j-1]; 
					}
					else
					{
						break;
					}
				}
			}
			playerData[j] = tempPlayerData;  // j is the position for what we selected.
		}
    }
	
	//Compare function, we compare by winrate first, then drawrate and follow by username
    private static boolean compare(Player playerOne, Player playerTwo)
    {
    	if (playerOne.getWinrate() > playerTwo.getWinrate())
    	{
    		return true;
    	}
    	else if (playerOne.getWinrate() < playerTwo.getWinrate())
    	{
    		return false;
    	}
  	 
    	if (playerOne.getDrawrate() > playerTwo.getDrawrate())
    	{
    		return true;
    	}
    	else if (playerOne.getDrawrate() < playerTwo.getDrawrate())
    	{
      		return false;
    	}
    	
    	return(compareUsername(playerOne, playerTwo));
    }
    
    //Compare by username only
    private static boolean compareUsername(Player playerOne, Player playerTwo)
    {
    	if (playerOne.getUsername().compareTo(playerTwo.getUsername()) < 0)
    	{
    		return true;
    	}
    	else
    	{
    		return false;
    	}
    }
}
