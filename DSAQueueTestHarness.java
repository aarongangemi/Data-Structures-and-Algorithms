/***************************************
 * Purpose: DSAQueue test harness
 * Author: Aaron Gangemi
 * Date modified: 23/08/2018
 * Reference: Lafore, R. (2003). Data structures and algorithms in Java
 * 			  Indianapolis, Ind. Indianapolis, Ind. : Sams
 * Reference: Also based on Data Structures and Algorithms lecture slides
 *************************************/
import java.util.*;
public class DSAQueueTestHarness
{
	public static void main(String[] args)
	{
		System.out.println("Test 1 - adding items to queue - count = 4");
		System.out.println("==========================================");
		DSAQueue myQueue = new DSAQueue(5);    //set queue capacity
		myQueue.enqueue(12);
		myQueue.enqueue(24);
		myQueue.enqueue(36);
		myQueue.enqueue(48);       //add items to queue
		System.out.println("The count is: " + myQueue.getCount());
		//Test items are added by testing the count

		System.out.println("Test 2 - removing items from queue - count = 1");
		System.out.println("===============================================");
		myQueue.dequeue();
		myQueue.dequeue();
		myQueue.dequeue();
		//Should remove 12,24,36
		System.out.println("The count is: " + myQueue.getCount()); //should print 1

		//Test if the queue is empty now
		System.out.println("Test 3 - testing isEmpty - count = 0");
		System.out.println("====================================");
		myQueue.dequeue();     //remove final item
 		if(myQueue.isEmpty())
		{
			System.out.println("Queue is empty");   //remove last item
		}
		else
		{
			System.out.println("Error");   //last item wasn't removed
		}
		System.out.println("Test 4 - testing isFull - count = 2");
		System.out.println("==========================================");
		DSAQueue secondQueue = new DSAQueue(2);
		secondQueue.enqueue(2);
		secondQueue.enqueue(4);
		if(secondQueue.isFull())
		{
			System.out.println("Queue is full");
		}
	}
}
