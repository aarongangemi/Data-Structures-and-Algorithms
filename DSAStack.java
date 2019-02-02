import java.util.*;
public class DSAStack
{
	private Object[] stackArray;          //variable declarations
	private int count;
	private static final int defaultCapacity = 100;


	public DSAStack()          //Default Constructor
	{
		stackArray = new Object[defaultCapacity];
		count = 0;
	}

	public DSAStack(int maxCapacity)    //Alternate constructor
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
		return count == 0;
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
			throw new IllegalArgumentException("Stack is full");
		}
		else
		{
			stackArray[count] = value;      //add value to stack
			count++;      //increment stack count
		}
	}
	public Object pop()       //remove value from stack
	{
		Object topVal = stackArray[count-1];     //remove value
		stackArray[count-1] = null;
		count--;    //decrement count
		return topVal;
	}
}
