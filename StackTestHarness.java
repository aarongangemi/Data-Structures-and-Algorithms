/***************************************
 * Purpose: DSAStack test harness
 * Author: Aaron Gangemi
 * Date modified: 23/08/2018
 * Reference: Lafore, R. (2003). Data structures and algorithms in Java
 * 			  Indianapolis, Ind. Indianapolis, Ind. : Sams
 * Reference: Also based on Data Structures and Algorithms lecture slides
 *************************************/

import java.util.*;
public class StackTestHarness
{
	public static void main(String[] args)
	{
		System.out.println("Testing Stack using the count");
		System.out.println("=============================");

		DSAStack theStack = new DSAStack(5);   //set count capacity
		theStack.push(20);     //add values to stack for testing
		theStack.push(40);
		theStack.push(60);
		theStack.push(80);
		System.out.println("initial count is: " + theStack.getCount());         //check initial count
		while(! theStack.isEmpty())      //continue until stack is empty
		{							     //remove items from stack
			Object value = theStack.pop();
			System.out.println(value);  //display value being removed
			System.out.println(theStack.getCount());   //check count of stack
													   //to confirm removal
			System.out.println(" ");
		}
		System.out.println("End of Stack Test 1");
		System.out.println("===================");

		System.out.println("Stack test 2 - test count is full");
		System.out.println("=================================");
		try
		{
			DSAStack newStack = new DSAStack(2);  //set capacity to 2
			newStack.push(200);
			newStack.push(222);
			newStack.push(223);
			if (newStack.isFull())        //check if stack is full
			{
				System.out.println("Stack count is " + newStack.getCount());
				System.out.println("Failed");    //should fail
			}
		}
		catch(IllegalArgumentException e)
		{
			System.out.println("Passed");  //then pass because stack is full
		}
	}
}
