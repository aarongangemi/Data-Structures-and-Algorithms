/***************************************
 * Purpose: create a stack
 * Author: Aaron Gangemi
 * Date modified: 23/08/2018
 * Reference: Lafore, R. (2003). Data structures and algorithms in Java
 * 			  Indianapolis, Ind. Indianapolis, Ind. : Sams
 * Reference: Also based on Data Structures and Algorithms lecture slides
 *************************************/
import java.util.*;
public class EquationSolverStack
{
	private Object[] stackArray;          //variable declarations
	private int count;
	private static final int defaultCapacity = 100;


	public EquationSolverStack()          //Default Constructor
	{
		stackArray = new Object[defaultCapacity];
		count = 0;
	}

	public EquationSolverStack(int maxCapacity)    //Alternate constructor
	{
		stackArray = new Object[maxCapacity];
		count = 0;
	}

	public int getCount()        //count accessor
	{
		return count;
	}

	public boolean isEmpty()      //check if stack is empty
	{
		boolean empty = false;
		if(getCount() == 0)
		{
			empty = true;
		}
		return empty;
	}

	public boolean isFull()     //check if stack is full
	{
		return count == stackArray.length;
	}

	public int getArrayLength()
	{
		return stackArray.length;
	}

	public void push(Object value)       //add value to stack
	{
		if(isFull())     //throw exception if stack is full
		{
			throw new StackOverflowError("FULL");
		}
		else
		{
			stackArray[count] = value;      //add value to stack
			count++;      //increment stack count
		}
	}
	public Object pop()       //remove value from stack
	{
		if(isEmpty())
		{
			throw new EmptyStackException();
		}
		Object topVal = stackArray[count-1];     //remove value
		stackArray[count-1] = null;
		count--;    //decrement count
		return topVal;
	}

	public Object top()
	{
		Object topVal;
		if(isEmpty())
		{
			throw new EmptyStackException();
		}
		else
		{
			topVal = stackArray[count - 1];
			return topVal;
		}
	}

}
