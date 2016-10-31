/********************************
 * COMP90041 Project C			*
 * File : AIPlayer.java			*
 * Author : Renyi Hou			*
 * Student ID : renyih			*
 * Student Number : 764696		*
 * Date : 22/5/2016				*
*********************************/

public class Move 
{
	private int row, col;
	
	//Mutator method for setting row
	public void setRow(int row)
	{
		this.row = row;
	}
	
	//Assessor method for getting row 
	public int getRow()
	{
		return row;
	}
	
	//Mutator method for setting column
	public void setColumn(int col)
	{
		this.col = col;
	}
	
	//Assessor method for getting column 
	public int getColumn()
	{
		return col;
	}
}
