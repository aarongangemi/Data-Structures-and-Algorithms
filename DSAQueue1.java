import java.util.*;
import java.io.*;
public class DSAQueue1<DSAVertex>
{
	private Object[] queue;
	private int count;
	public static final int DEFAULT_CAPACITY = 30;

	public DSAQueue1()
	{
		queue = new Object[DEFAULT_CAPACITY];
		count = 0;
	}

	public DSAQueue1(int maxCapacity)
	{
		queue = new Object[maxCapacity];
		count = 0;
	}

	public void enqueue(Object object)
	{
		//sizeManipulator();
		queue[count++] = object;
	}

	public Object dequeue()
	{
		Object item = peek();
		for (int i = 0; i < queue.length - 1; i++)
		{
			queue[i] = queue[i + 1];
		}
		count--;
		return item;
	}

	public Object peek()
	{
		if (isEmpty())
		{
			throw new RuntimeException("nothing to show");
		}
		return queue[0];
	}

	public int getCount()
	{
		return count;
	}

	public boolean isEmpty()
	{
		boolean empty = false;
		if (count == 0)
		{
			empty = true;
		}
		return empty;
	}

	public boolean isFull()
	{
		boolean full = false;
		if (count == queue.length)
		{
			full = true;
		}
		return full;
	}
}

