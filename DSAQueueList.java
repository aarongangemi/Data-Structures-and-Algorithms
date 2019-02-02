/**************************
 * Purpose: Queue for DSA linked list
 * Author: Aaron Gangemi
 * Date modified: 07/09/2018
 * Reference: Lafore, R. (2003). Data structures and algorithms in Java
 * Indianapolis, Ind. Indianapolis, Ind. : Sams
 * Reference: Also based on Data Structures and Algorithms lecture slides
 ****************************/

import java.util.*;
import java.io.*;
public class DSAQueueList<E> implements Iterable
{
	private DSALinkedList<E> queue;    //classfields

	public DSAQueueList()
	{
		queue = new DSALinkedList<E>();    //constructor
	}

	public void enqueue(E value)
	{
		queue.insertLast(value);    //add item
	}

	public E dequeue()
	{
		return queue.removeFirst();    //remove item from list
	}

	public E peek()
	{
		return queue.peekFirst();   //get first value
	}

	public boolean isEmpty()
	{
		return queue.isEmpty();   //check if empty
	}

	public Iterator iterator()
	{
		return queue.iterator();  //iterator
	}

	public void displayQueue()    //display queue
	{
		queue.displayFoward();
	}
}

