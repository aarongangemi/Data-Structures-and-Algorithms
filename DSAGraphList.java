/********************************
		* Purpose: DSA Linked List
		* Author: Aaron Gangemi
		* Date modified: 07/09/2018
		* Reference: Lafore, R. (2003). Data structures and algorithms in Java
		* Indianapolis, Ind. Indianapolis, Ind. : Sams
		* Reference: Also based on Data Structures and Algorithms lecture slides
 		* Reference: This class is a linked list class which was submitted in both
 		* my practical 4 and practical 6 of Data Structures and Algorithms
		********************************/

import java.util.*;
public class DSAGraphList<E> implements Iterable<E>
{
	private DSAListNode<E> head;       //classfields
	private DSAListNode<E> tail;


	public DSAGraphList()    //constructor
	{
		head = null;
		tail = null;
	}

	/*******************************
	 * Purpose: to insert at the top of the list
	 * @param newValue
	 * return: none
	 ******************************/
	public void insertFirst(E newValue)
	{
		DSAListNode<E> newNode = new DSAListNode<E>(newValue);
		if (isEmpty())
		{
			tail = newNode;     //if empty then tail is new node
		}
		else
		{
			head.setPrevious(newNode);   //head is previous node
		}
		newNode.setNext(head);    //next node is head
		head = newNode;    //head is new node

	}

	/*********************************
	 * Purpose: To insert from the back of the list
	 * @param newValue
	 * return none
	 *******************************/
	public void insertLast(E newValue)
	{
		DSAListNode<E> newNode = new DSAListNode<E>(newValue);
		if (isEmpty())   //check if empty
		{
			head = newNode;    //head is newnode if empty
		}
		else
		{
			tail.setNext(newNode);     //next node is tail
		}
		newNode.setPrevious(tail);   //tail is previous
		tail = newNode;   //new node is tail
	}

	/*******************************
	 * Purpose: to check if list is empty
	 * @param: none
	 * @return empty or not
	 *******************************/
	public boolean isEmpty()    //check if empty
	{
		return (head == null);    //if count is 0 then empty
	}

	/********************************
	 * Purpose: To peek at top of list
	 * @param: none
	 * @return nodeValue
	 *******************************/
	public E peekFirst()
	{
		E nodeValue;
		if (isEmpty())    //check if empty
		{
			throw new NoSuchElementException("No element found");
		}
		else
		{
			nodeValue = head.getValue();    //get head value
		}
		return nodeValue;
	}

	/*******************************
	 * Purpose: get bottom of list value
	 * @param: none
	 * @return: nodeValue
	 ******************************/
	public E peekLast()
	{
		E nodeValue;
		if (isEmpty())   //check if empty
		{
			throw new NoSuchElementException("No Element found");
		}
		else
		{
			nodeValue = tail.getValue();  //get tail value
		}
		return nodeValue;
	}

	/*******************************
	 * Purpose: To remove from top of list
	 * @param: none
	 * @return nodeValue
	 ********************************/
	public E removeFirst()
	{
		E nodeValue;
		if (isEmpty())     //check if empty
		{
			throw new NoSuchElementException("No element found");
		}
		else if(head.getNext() == null)  //check if only 1 element left
		{
			nodeValue = head.getValue();   //get head value
			head = null;      //set all to null so stack is empty
			tail = null;
		}
		else
		{
			nodeValue = head.getValue();    //get head value
			head.getNext().setPrevious(null);   //set previous to null
			head = head.getNext();   //get next is head
		}
		return nodeValue;
	}

	/*****************************
	 * Purpose: to remove last element of list
	 * @param: None
	 * @return: lastnode
	 *****************************/
	public E removeLast()
	{
		E lastNode;
		if (isEmpty())   //check if empty
		{
			throw new NoSuchElementException("No element found");
		}
		else if(tail.getNext() == null)    //remove item from stack
		{
			lastNode = tail.getValue();    //get tail value
			head = null;     //set all to null
			tail = null;
		}
		else
		{
			lastNode = tail.getValue();     //last node is tail value
			tail.getPrevious().setNext(null);    //next = null
			tail = tail.getPrevious();    //get previous tail value
		}
		return lastNode;
	}

	/*********************************
	 * Purpose: display list
	 * @param: none
	 * @return: none
	 *******************************/
	public void displayFoward()
	{
		System.out.println("List (head --> tail): ");
		DSAListNode<E> currentNode = head;
		while (currentNode != null)
		{
			currentNode.displayLink();
			currentNode = currentNode.getNext();
		}
		System.out.println(" ");
	}

	private class DSAListNode<E>
	{
		private E value;
		private DSAListNode<E> next;
		private DSAListNode<E> previous;      //classfields

		public DSAListNode(E inValue)    //constructor
		{
			value = inValue;
			next = null;
			previous = null;
		}

		public E getValue()
		{
			return value;     //gets value of node
		}

		public void setValue(E inValue)
		{
			value = inValue;    //sets value
		}

		public DSAListNode<E> getNext()
		{
			return next;   //get next node
		}

		public DSAListNode<E> getPrevious()
		{
			return previous;  //get previous node
		}

		public void setNext(DSAListNode<E> newNext)
		{
			next = newNext;   //set next node
		}

		public void setPrevious(DSAListNode<E> inPrevious)
		{
			previous = inPrevious;  //set previous node
		}

		public void displayLink()
		{
			System.out.print(value.toString() + " ");    //display
		}

		public String toString()
		{
			String str = "";
			if(this.value != null)
			{
				str = value.toString();
			}
			else
			{
				str = "null";
			}
			if(next != null)
			{
				str += ", " + next.toString();
			}
			return str;
		}
	}

	public String toString()
	{
		String str = "";
		if(head != null)
		{
			str = "[ "+head.toString()+"]";
		}
		return str;
	}


	/**************************
	 * Purpose: iterate through list
	 * @param: none
	 * @return: DSALinkedListIterator
	 */
	public Iterator iterator()
	{
		return new DSAGraphListIterator(this);
	}

	/*****************************
	 * Purpose: Iterate through list
	 * @param: methods from here are used for iterator
	 * @param: value
	 ******************************/
	private class DSAGraphListIterator<E> implements Iterator
	{
		private DSAGraphList<E>.DSAListNode<E> iterNext;    //classfields

		public DSAGraphListIterator(DSAGraphList<E> theList)
		{
			iterNext = theList.head;
		}

		public boolean hasNext()
		{
			return (iterNext != null);
		}

		public E next()
		{
			E value;
			if (iterNext == null)
			{
				value = null;
			}
			else
			{
				value = iterNext.getValue();
				iterNext = iterNext.getNext();
			}
			return value;
		}

		@Override
		public void remove()
		{
			throw new UnsupportedOperationException("not supported");
		}
	}

}


