import java.util.*;
public class DSAQueue
{
	private Object[] queueArray;         //initialise variables from lafore
	private int count;
	private int rear;
	private int front;
	private static final int defaultCapacity = 100;

	public DSAQueue()      //default constructor
	{
		queueArray = new Object[defaultCapacity];
		front = 0;
		rear = -1;
		count = 0;
	}

	public DSAQueue(int maxCapacity)    //alternate constructor
	{
		queueArray = new Object[maxCapacity];
		rear = -1;
		front = 0;
		count = 0;
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
		return count == queueArray.length;
	}

	public void enqueue(int value)       //add item to queue
	{
		if(rear == queueArray.length - 1)  //check if room in queue
		{
			rear = -1;
		}
		queueArray[++rear] = value;     //add item to queue
		count++;      //increment count
	}

	public Object dequeue()       //removes item from queue
	{
		Object temp = queueArray[front++];     //set temp to queue item 1
		if(front == queueArray.length) 		   //check if length = 1
		{
			front = 0;                       //set front to 0
		}
		count--;           //decrement count
		return temp;        //export queue item 1
	}

	public Object peek()
	{
		return queueArray[front];
	}
}