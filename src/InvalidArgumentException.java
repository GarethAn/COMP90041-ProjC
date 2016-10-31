/********************************
 * COMP90041 Project C			*
 * File : AIPlayer.java			*
 * Author : Renyi Hou			*
 * Student ID : renyih			*
 * Student Number : 764696		*
 * Date : 22/5/2016				*
*********************************/

public class InvalidArgumentException extends Exception
{
	//Default constructor for invalid argument exception class
	public InvalidArgumentException()
	{
		super("Incorrect number of arguments supplied to command.");
	}
	
	//Overloading constructor that pass in message
	public InvalidArgumentException(String message)
	{
		super(message);
	}
}
