/***************************************
 * Purpose: create a stack
 * Author: Aaron Gangemi
 * Date modified: 23/08/2018
 * Reference: Lafore, R. (2003). Data structures and algorithms in Java
 * Indianapolis, Ind. Indianapolis, Ind. : Sams
 * Reference: Also based on Data Structures and Algorithms lecture slides
 *************************************/
import java.util.*;
public class DSAStackList<E> implements Iterable
{
	private DSALinkedList<E> list;

	public DSAStackList()          //Default Constructor
	{
		list = new DSALinkedList();
	}

	public boolean isEmpty()
	{
		return (list.isEmpty());  //check if empty
	}

	public void push(E item)       //add value to stack
	{
		list.insertFirst(item);
	}
	public E pop()       //remove value from stack
	{
		return list.removeFirst();
	}

	public E top()    //get top value
	{
		return list.peekFirst();
	}

	public void displayStack()   //display stack
	{
		list.displayFoward();
	}
	public Iterator iterator()   //iterator
	{
		return list.iterator();
	}
}
