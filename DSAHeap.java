/*******************************
 * Purpose: This is the heap prac for data structures and algorithms
 * Author: Aaron Gangemi
 * Date modified: 16/10/2018
 */

import javax.security.auth.callback.Callback;
import java.util.*;
public class DSAHeap implements Callback
{
	private DSAHeapEntry[] heapArray;
	private int heapCount;   //Classfields

	public DSAHeap(int maxSize)
	{
		heapArray = new DSAHeapEntry[maxSize];
		heapCount = 0;  //constructor
	}

	/***********************************
	 * Purpose: To add to the heap
	 * @param priority
	 * @param value
	 *********************************/
	public void add(int priority, Object value)
	{
		if(heapCount == heapArray.length)  //check if full
		{
			throw new IllegalArgumentException("Unable to add to heap");
		}
		else
		{
			DSAHeapEntry newNode = new DSAHeapEntry(priority,value);
			heapArray[heapCount] = newNode;  //add to heap
			trickleUp(heapCount++);  //call trickle up
		}
	}

	/*********************************
	 * To remove a value from the heap
	 * @return root
	 */
	public Object remove()
	{
		DSAHeapEntry root = heapArray[0]; //set root
		heapArray[0] = heapArray[--heapCount]; //set first element
		trickleDown(0); //call trickleDown
		return root;
	}

	/********************************
	 * Purpose: To sort the heap
	 * @return
	 */
	public DSAHeapEntry[] heapSort()
	{
		heapify(); //call heapify
		for (int ii = heapCount -1; ii > 1; ii--)
		{
			swap(0, ii);
			trickleDown(ii); //sort heap
		}
		return heapArray;
	}

	/*******************************
	 * Purpose: heapify sorts in heapsorts
	 * @return
	 */
	public DSAHeapEntry[] heapify()
	{
		for(int ii = (heapCount/2)-1; ii > 0; ii--)
		{
			trickleDown(ii);
		}
		return heapArray;
	}

	/**********************************
	 * Purpose: Trickle up method for heapArray
 	 * @param currentIndex
	 * @return heapArray
	 */
	public DSAHeapEntry[] trickleUp(int currentIndex)
	{
		DSAHeapEntry temp;
		int parentIndex;
		parentIndex = (currentIndex-1)/2;  //set parent index
		if(currentIndex > 0)  //check current index is positive
		{
			if(heapArray[currentIndex].getPriority() >
					heapArray[parentIndex].getPriority())
			//check prioritys
			{
				temp = heapArray[parentIndex]; //set temp
				heapArray[parentIndex] = heapArray[currentIndex]; //add to heap
				heapArray[currentIndex] = temp; //add temp to array
				trickleUp(parentIndex); //call trickle up
			}
		}
		return heapArray;
	}

	/*******************************
	 * Purpose: TrickleDown method
	 * @param currentIndex
	 * @return
	 */
	public DSAHeapEntry[] trickleDown(int currentIndex)
	{
		int leftChildIdx, rightChildIdx, largeIdx;
		leftChildIdx = currentIndex * 2 + 1;
		rightChildIdx = leftChildIdx + 1;
		if(leftChildIdx < heapCount) //check left child
		{
			largeIdx = leftChildIdx;
			if(rightChildIdx < heapCount) //check right child
			{
				if(heapArray[leftChildIdx].getPriority() <
						heapArray[rightChildIdx].getPriority())
				//check priority
				{
					largeIdx = rightChildIdx;
				}
			}
			if(heapArray[largeIdx].getPriority() >
					heapArray[currentIndex].getPriority())
			//check prioritys
			{
				swap(largeIdx,currentIndex);
				trickleDown(largeIdx);
				//swap and trickle
			}
		}
		return heapArray;
	}

	/*********************************
	 * Purpose: swap in heap
	 * @param largeIdx
	 * @param currentIdx
	 */
	public void swap(int largeIdx, int currentIdx)
	{
		DSAHeapEntry temp;
		temp = heapArray[currentIdx];
		// set temp
		heapArray[currentIdx] = heapArray[largeIdx];
		//add to heap on current
		heapArray[largeIdx] = temp;
		//add to heap on large
	}

	/**********************************
	 * Purpose: DISPLAY HEAP
	 * REFERENCE: CODE FROM data structures and algorithms in java
	 */
	public void displayHeap()
	{
		boolean boolBreak = false;
		int nBlanks = 32;
		int itemsPerRow = 1;
		int column = 0;
		int j = 0;
		String dots = "...........................................";
		System.out.println("Heap Array: ");
		for(int i = 0; i < heapCount; i++)
		{
			if(heapArray[i] != null)
			{
				System.out.println(heapArray[i].getValue() + " ");
			}
			else
			{
				System.out.println( "--  ");
			}
		}
		System.out.println();
		System.out.println(dots + dots);
		while (heapCount > 0 && boolBreak == false)
		{
			if(column > 0)
			{
				for (int k = 0; k < nBlanks; k++)
				{
					System.out.println(' ');
				}
			}
			System.out.println(heapArray[j].getValue());
			if(++j == heapCount)
			{
				boolBreak = true;
			}
			if(++column == itemsPerRow)
			{
				nBlanks /= 2;
				itemsPerRow *=2;
				column = 0;
				System.out.println();
			}
			else
			{
				for (int k = 0; k < nBlanks*2-2; k++)
				{
					System.out.println(' ');
				}
			}
		}
		System.out.println("\n" + dots + dots);
	}

	/**********************************
	 * Purpose: DSA Heap entry inner class
	 */
	private class DSAHeapEntry
	{
		private int priority;  //classfields
		public Object value;

		//constructor
		public DSAHeapEntry(int inPriority, Object inValue)
		{
			priority = inPriority;
			value = inValue;
		}

		//priority accessor
		public int getPriority()
		{
			return priority;
		}

		//priority mutator
		public void setPriority(int inPriority)
		{
			priority = inPriority;
		}

		//accessor for value
		public Object getValue()
		{
			return value;
		}

		//mutator for value
		public void setValue(Object inValue)
		{
			value = inValue;
		}
	}
}
