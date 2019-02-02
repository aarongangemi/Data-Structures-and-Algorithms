/*******************************
 * Purpose: DSA stack test harness
 * Author: Aaron Gangemi
 * Date modified: 07/09/2018
 ******************************/

import java.util.*;
public class DSAStackTestHarness
{
	public static void main(String[] args)
	{
		DSAStackList newStack = null;
		DSALinkedList newList = null;
		int numTests = 0;
		//////////////////////////Testing constructor//////////////
		try
		{
			numTests++;
			newStack = new DSAStackList();
			if (newStack.isEmpty() == false)
			{
				throw new IllegalArgumentException("invalid");
			}
			System.out.println("Constructor Passed");
		}
		catch (IllegalArgumentException e)
		{
			System.out.println("Constructor failed");
		}

		////////////////////////testing isempty///////////////////
		try
		{
			numTests++;
			if (newStack.isEmpty())
			{
				System.out.println("Stack is empty - passed");
			}
			else
			{
				System.out.println("Failed");
			}
		}
		catch(Exception e)
		{
			System.out.println("Is empty threw a nice exception");
		}
		/////////////////////////testing push//////////////////////
		try
		{
			numTests++;
			newStack.push("1st item on stack");
			newStack.displayStack();
			System.out.println("Push - Passed");
		}
		catch(Exception e)
		{
			System.out.println("push failed");
		}

		///////////////////////testing pop//////////////////////////
		try
		{
			numTests++;
			newStack.pop();
			if (newStack.isEmpty())
			{
				System.out.println("pop - passed");
			}
			else
			{
				System.out.println("pop failed");
			}
		}
		catch(Exception e)
		{
			System.out.println("pop - failed");
		}

		//////////////////////////testing top
		try
		{
			newStack.push("Top of stack");
			if (newStack.top().toString().equals("Top of stack"))
			{
				System.out.println("Top passed");
			}
			else
			{
				System.out.println("top failed");
			}
		}
		catch (Exception e)
		{
			System.out.println("top threw exception");
		}

	}
}