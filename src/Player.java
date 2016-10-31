/********************************
 * COMP90041 Project C			*
 * File : Player.java			*
 * Author : Renyi Hou			*
 * Student ID : renyih			*
 * Student Number : 764696		*
 * Date : 22/5/2016				*
*********************************/

public abstract class Player
{
	private String username, familyName, givenName;
	private int numGamesPlayed, numGamesWon, numGamesDrawn;
	private double winrate, drawrate;
	
	public abstract Move makeMove(char[][] gameBoard);
	
	//Default Constructor
	public Player()
	{}
	
	//Overloading Constructor
	public Player(String username, String familyName, String givenName)
	{
		this.username = username;
		this.familyName = familyName;
		this.givenName= givenName;
		this.numGamesPlayed = 0;
		this.numGamesWon = 0;
		this.numGamesDrawn = 0;
		this.winrate = 0.0;
		this.drawrate = 0.0;
	}
	
	//User name Mutator
	public String getUsername()
	{
		return this.username;
	}
	
	//User name Assessor
	public void setUsername(String username)
	{
		this.username = username;
	}
	
	//Family Name Mutator
	public String getFamilyName()
	{
		return this.familyName;
	}
	
	//Family Name Assessor
	public void setFamilyName(String familyName)
	{
		this.familyName = familyName;
	}
	
	//Given Name Mutator
	public String getGivenName()
	{
		return this.givenName;
	}
	
	//Given Name Assessor
	public void setGivenName(String givenName)
	{
		this.givenName = givenName;
	}
	
	//Num Games Played Mutator
	public int getNumGamesPlayed()
	{
		return this.numGamesPlayed;
	}
	
	//Num Games Played Assessor
	public void setNumGamesPlayed(int numGamesPlayed)
	{
		this.numGamesPlayed = numGamesPlayed;
	}
	
	//Increment the number of games wons by 1
	public void numGamesPlayedInc()
	{
		this.numGamesPlayed = this.numGamesPlayed + 1;
	}
	
	//Num Games Won Mutator
	public int getNumGamesWon()
	{
		return this.numGamesWon;
	}
	
	//Num Games Won Assessor
	public void setNumGamesWon(int numGamesWon)
	{
		this.numGamesWon = numGamesWon;
	}
	
	//Increment the number of games wons by 1
	public void numGamesWonInc()
	{
		this.numGamesWon = this.numGamesWon + 1;
	}
	
	//Number Games Drawn Mutator
	public int getNumGamesDrawn()
	{
		return this.numGamesDrawn;
	}
	
	//Number Games Drawn Assessor
	public void setNumGamesDrawn(int numGamesDrawn)
	{
		this.numGamesDrawn = numGamesDrawn;
	}
	
	//Increment the number of games drawn by 1
	public void numGamesDrawnInc()
	{
		this.numGamesDrawn = this.numGamesDrawn + 1;
	}
	
	//Winrate Assessor
	public double getWinrate()
	{
		return this.winrate;
	}
	
	//Winrate Mutator
	public void setWinrate(double winrate)
	{
		this.winrate = winrate;
	}
	
	//Drawrate Assessor
	public double getDrawrate()
	{
		return this.drawrate;
	}
	
	//Drawrate Mutator
	public void setDrawrate(double drawrate)
	{
		this.drawrate = drawrate;
	}
	
	//Overloading class printing function
	public String toString()
	{
		return (username+","+familyName+","+givenName+","
				+numGamesPlayed+" games"+","+numGamesWon+" wins"
				+","+numGamesDrawn+" draws");
	}
	
	//Counting player winrate and drawrate based on num games played, won and draw
	public void countRatio()
	{
		if(numGamesPlayed != 0)
		{
			winrate = ((double)numGamesWon / (double)numGamesPlayed) * 100;
			drawrate = ((double)numGamesDrawn / (double)numGamesPlayed) * 100;
		}
		else
		{
			winrate = 0;
			drawrate = 0;
		}
	}
	
	//Design for reset player score
	public void resetScore()
	{
		this.numGamesPlayed = 0;
		this.numGamesWon = 0;
		this.numGamesDrawn = 0;
		this.winrate = 0.0;
		this.drawrate = 0.0;
	}
	
	//Design for clear information when remove player
	public void clearInformation()
	{
		this.username = "";
		this.familyName = "";
		this.givenName= "";
		this.numGamesPlayed = 0;
		this.numGamesWon = 0;
		this.numGamesDrawn = 0;
		this.winrate = 0.0;
		this.drawrate = 0.0;
	}
	
	//Method for duplicate one information to another
	public void duplicateInformation(Player p)
	{
		this.username = p.username;
		this.familyName = p.familyName;
		this.givenName= p.givenName;
		this.numGamesPlayed = p.numGamesPlayed;
		this.numGamesWon = p.numGamesWon;
		this.numGamesDrawn = p.numGamesDrawn;
		this.winrate = p.winrate;
		this.drawrate = p.drawrate;
	}
}
