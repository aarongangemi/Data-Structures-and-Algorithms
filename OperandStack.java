/***********************************
 * Purpose: Operand stack for equation solver
 * Author: Aaron Gangemi
 * Date modified: 30/08/2018
 ********************************/

import java.util.*;
public class OperandStack
{
	private Object[] operandArray;
	private int count;
	private static final int defaultCapacity = 100;

	public OperandStack()        //constructor
	{
		operandArray = new Object[defaultCapacity];
		count = 0;
	}

	public OperandStack(int maxCapacity)     //alt constructor
	{
		operandArray = new Object[maxCapacity];
		count = 0;
	}

	public int getCount()   //get count
	{
		return count;
	}

	public boolean isEmpty()   //check is empty
	{
		return count == 0;
	}

	public boolean isFull()   //check is full
	{
		return count == operandArray.length;
	}

	public int getArrayLength()  //get length
	{
		return operandArray.length;
	}

	public void push(Object value)    //add to stack
	{
		if(isFull())
		{
			throw new IllegalArgumentException("Stack is full");
		}
		else
		{
			operandArray[count] = value;
			count++;
		}
	}

	public Object pop()    //remove from stack
	{
		if(isEmpty())
		{
			throw new EmptyStackException();
		}
		Object topVal = operandArray[count - 1];
		operandArray[count-1] = null;
		count--;
		return topVal;
	}

	public Object top()     //top of stack
	{
		Object topVal;
		if(isEmpty())
		{
			throw new EmptyStackException();
		}
		else
		{
			topVal = operandArray[count - 1];
		}
		return topVal;
	}
}