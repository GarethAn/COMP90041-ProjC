/********************************
 * COMP90041 Project C			*
 * File : AIPlayer.java			*
 * Author : Renyi Hou			*
 * Student ID : renyih			*
 * Student Number : 764696		*
 * Date : 22/5/2016				*
*********************************/

public class InvalidCommandException extends Exception
{
	//Default constructor for invalid command exception class
	public InvalidCommandException()
	{
		super("Invalid Command!");
	}
	
	//Overloading constructor that pass in message
	public InvalidCommandException(String message)
	{
		super(message);
	}
}
