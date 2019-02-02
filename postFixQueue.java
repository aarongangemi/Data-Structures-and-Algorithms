/***********************************
 * Purpose: postFix stack to store result
 * Author: Aaron Gangemi
 * Date modified: 30/08/2018
 ***********************************/

import java.util.*;
public class postFixQueue
{
	private Object[] postFixArray;         //initialise variables from lafore
	private int count;
	private int rear;
	private int front;
	private static final int defaultCapacity = 100;

	public postFixQueue()      //default constructor
	{
		postFixArray = new Object[defaultCapacity];
		front = 0;
		rear = -1;
		count = 0;
	}

	public postFixQueue(int maxCapacity)    //alternate constructor
	{
		postFixArray = new Object[maxCapacity];
		rear = -1;
		front = 0;
		count = 0;
	}

	public Object[] getPostFixArray()
	{
		return postFixArray;
	}

	public int getCount()       //count accessor
	{
		return count;
	}

	public boolean isEmpty()     //return true if queue is empty
	{
		return count == 0;
	}

	public boolean isFull()      //return true if queue is full
	{
		return count == postFixArray.length;
	}

	public void enqueue(Object value)       //add item to queue
	{
		if(rear == postFixArray.length - 1)  //check if room in queue
		{
			rear = -1;
		}
		postFixArray[++rear] = value;     //add item to queue
		count++;      //increment count
	}

	public Object dequeue()       //removes item from queue
	{
		Object temp = postFixArray[front++];     //set temp to queue item 1
		if(front == postFixArray.length) 		   //check if length = 1
		{
			front = 0;                       //set front to 0
		}
		count--;           //decrement count
		return temp;        //export queue item 1
	}

	public Object peek()
	{
		return postFixArray[front];
	}

	public void displayQueue()
	{
		String x = "";
		for(int i =0; i < count; i++)
		{
			x = postFixArray[i].toString();
			System.out.println(x);
		}
	}
}
