/*****************************
 * Purpose: DSALinked List test harness
 * Author: Aaron Gangemi
 * Date modified: 07/09/2018
 * Reference: Lafore, R. (2003). Data structures and algorithms in Java
 * Indianapolis, Ind. Indianapolis, Ind. : Sams
 * Reference: Also based on Data Structures and Algorithms lecture slides
 *******************************/

import java.io.*;
import java.util.*;

public class DSALinkedListTestHarness
{
	public static void main(String args[])
	{
		//////////////////Variable declarations/////////////////////////
		int numTests = 0;
		int numPassed = 0;
		DSALinkedList myList = null;
		Object newValue;
		//////////////////////Testing Constructor////////////////////////
		try
		{
			numTests++;
			myList = new DSALinkedList();
			System.out.println("Testing constructor creation");
			if (myList.isEmpty() == false)
			{
				throw new IllegalArgumentException("Head must be null");
			}
			numPassed++;
			System.out.println("Constructor: Passed");
		}
		catch (IllegalArgumentException e)
		{
			System.out.println("Constructor: Failed");
		}

		//////////////////////////Testing insertFirst////////////////////////
		try
		{
			numTests++;
			myList.insertFirst("Hello World");
			System.out.println("Insert first: Passed");
			numPassed++;
		}
		catch (Exception e)
		{
			System.out.println("Insert first: Failed");
		}
		/////////////////////////Testing insertLast////////////////////////////
		try
		{
			numTests++;
			myList.insertLast("Back of list");
			myList.insertLast("I love DSA");
			System.out.println("Insert last passed");
			numPassed++;
		}
		catch (Exception e)
		{
			System.out.println("Insert last failed");
		}
		///////////////////Testing isEmpty/////////////////////////////
		try
		{
			//IsEmpty not working
			numTests++;
			myList.removeFirst();
			myList.removeFirst();
			myList.removeFirst();
			myList.displayFoward();
			System.out.println("============================");
			if (myList.isEmpty())
			{
				System.out.println("list is empty");
				System.out.println("passed");
				numPassed++;
			}
			else
			{
				System.out.println("isEmpty: failed");
			}
		}
		catch (Exception e)
		{
			System.out.println("isEmpty failed - exception");
		}
		///////////////////Testing peekFirst//////////////////////////
		try
		{
			numTests++;
			myList.insertFirst("First item in list");
			if (myList.peekFirst().toString().equals("First item in list"))
			{
				System.out.println("Passed");
				numPassed++;
				myList.removeFirst();
			}
			else
			{
				System.out.println("Failed");
			}
		}
		catch (Exception e)
		{
			System.out.println("Failed peekFirst");
		}

		////////////////////////Testing peekLast/////////////////////
		try
		{
			numTests++;
			myList.insertLast("peekLast value");
			if (myList.peekLast().toString().equals("peekLast value"))
			{
				numPassed++;
				System.out.println("PeekLast: Passed");
			}
			else
			{
				System.out.println("PeekLast failed");
			}
		}
		catch (Exception e)
		{
			System.out.println("Peek last threw exception");
		}

		///////////////////////testing removeFirst and Last//////////////
		try
		{
			numTests++;
			myList.insertFirst("If i am displayed, you passed remove first");
			myList.insertFirst("If i am displayed, you failed");
			myList.removeFirst();
			myList.removeLast();
			myList.removeLast();
			myList.displayFoward();
			numTests++;
		}
		catch(Exception e)
		{
			System.out.println("Remove first failed");
		}


	}
}