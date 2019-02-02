/*******************************
 * Purpose: Binary Tree Class
 * Author: Aaron Gangemi
 * Date Modified: 18/09/2018
 *******************************/

import java.io.*;
import java.util.*;
public class BinarySearchTree<E> implements Iterable, Serializable
{
	private class TreeNode<E> implements Serializable    //tree node class
	{
		private String key;         //Initialise fields
		private E value;
		private TreeNode<E> leftChild;
		private TreeNode<E> rightChild;

		public TreeNode(String inKey, E inVal)   //constructor
		{
			if (inKey == null)
			{
				throw new IllegalArgumentException("key cannot be null");
			}
			key = inKey;
			value = inVal;
			rightChild = null;
			leftChild = null;
		}

		/************************
		 * Purpose: get key
		 * Import: void
		 * @return: key
		 *************************/
		public String getKey()  //get key
		{
			return key;
		}

		/************************
		 * Purpose: get value
		 * Import: none
		 * @return value
		 ***********************/
		public E getValue()    //get value
		{
			return value;
		}

		/*************************
		 * Purpose: get leftChild
		 * Import: none
		 * @return leftchild
		 **********************/
		public TreeNode<E> getLeft()   //get left
		{
			return leftChild;
		}

		/*************************
		 * Purpose: set left
		 * Import new left
		 * @param newLeft
		 ***********************/
		public void setLeft(TreeNode<E> newLeft)   //set left
		{
			leftChild = newLeft;
		}

		/*****************************
		 * Purpose: get right
		 * Import: NONE
		 * @return rightchild
		 */

		public TreeNode<E> getRight()     //get right
		{
			return rightChild;
		}

		/**************************
		 * Purpose: set right
		 * Import: newRight
		 * @param newRight
		 */
		public void setRight(TreeNode<E> newRight)    //set right
		{
			rightChild = newRight;
		}
	}

	private TreeNode<E> root;     //initialise root

	public BinarySearchTree()    //constructor
	{
		root = null;
	}

	/************************
	 * Purpose: find a node by calling recursive
	 * @param key
	 * @return findRecursive result
	 ************************/
	public E find(String key)    //find method
	{
		return findRecursive(key, root);
	}

	/**************************
	 * Purpose: Insert node
	 * @param key
	 * @param value
	 */
	public void insert(String key, E value)    //insert method
	{
		root = insertRecursive(key, value, root);
	}

	/*************************
	 * Purpose: delete node
	 * @param key
	 * Export: none
	 */
	public void delete(String key)   //delete node
	{
		root = deleteNode(key, root);
	}

	/************************
	 * Purpose: get height
	 * Import: NONE
	 * @return heightRecursive
	 */
	public int height()    //return height
	{
		return heightRecursive(root);
	}

	/**************************
	 * Purpose: find recursive method
	 * @param key
	 * @param currentNode
	 * @return value
	 */
	private E findRecursive(String key, TreeNode<E> currentNode)    //find Recursive called in find
	{
		E value = null;
		if (currentNode == null)
		{
			throw new NoSuchElementException("Key " + key + " not found");   //if node is null
		}
		else if (key.equals(currentNode.getKey()))   //if key is found
		{
			value = currentNode.getValue();  //set value
		}
		else if (key.compareTo(currentNode.getKey()) < 0)   //if negative value
		{
			value = findRecursive(key, currentNode.getLeft());
		}
		else
		{
			value = findRecursive(key, currentNode.getRight());
		}
		return value;
	}

	/****************************
	 * Purpose: insert recursive
	 * @param key
	 * @param data
	 * @param current
	 * @return updateNode
	 */
	private TreeNode<E> insertRecursive(String key, E data, TreeNode<E> current)
	{
		TreeNode<E> updateNode = current;
		if (current == null)     //check if node = null
		{
			TreeNode<E> newNode = new TreeNode<E>(key, data);
			updateNode = newNode;
		}
		else if (key.equals(current.getKey()))    //check if key is found
		{
			throw new IllegalArgumentException("key exists");
		}
		else if (key.compareTo(current.getKey()) < 0)   //check if key val is negative
		{
			current.setLeft(insertRecursive(key, data, current.getLeft()));   //set left
		}
		else
		{
			current.setRight(insertRecursive(key, data, current.getRight()));
		}
		return updateNode;
	}

	/***************************
	 * Purpose: deletenode
	 * @param key
	 * @param delNode
	 * @return updateNode
	 *************************/
	private TreeNode<E> deleteNode(String key, TreeNode<E> delNode)
	{
		TreeNode<E> updateNode = null;
		if ((delNode.getLeft() == null) && (delNode.getRight() == null))
			//check if nodes are null
		{
			throw new NoSuchElementException("Key " + key + "not found");
		}
		else if ((delNode.getLeft() != null) && (delNode.getRight() != null))
		// check if delete is valid
		{
			updateNode = delNode.getLeft();  //delete node
		}
		else if ((delNode.getLeft() == null) && (delNode.getRight() != null))
		{
			updateNode = delNode.getRight();  //delete node
		}
		else    //update nodes
		{
			updateNode = promoteSuccessor(delNode.getRight());
			if (updateNode != delNode.getRight())
			{
				updateNode.setRight(delNode.getRight());
			}
			updateNode.setLeft(delNode.getLeft());
		}
		return updateNode;
	}

	/**************************
	 * Purpose: heightRecursive
	 * @param currentNode
	 * @return currentHeight
	 */
	private int heightRecursive(TreeNode<E> currentNode)
	{
		int currentHeight, leftHeight, rightHeight;  //variables
		if (currentNode == null)   //check if node is null
		{
			currentHeight = -1;
		}
		else
		{
			leftHeight = heightRecursive(currentNode.getLeft());  //height of left
			rightHeight = heightRecursive(currentNode.getRight());  //height of right
			if (leftHeight > rightHeight)
			{
				currentHeight = leftHeight + 1;   //increment height
			}
			else
			{
				currentHeight = rightHeight + 1;  //increment height
			}
		}
		return currentHeight;
	}

	/**************************
	 * Purpose: PromoteSuccessor
	 * @param current
	 * @return successor
	 ***************************/
	private TreeNode<E> promoteSuccessor(TreeNode<E> current)
	{
		TreeNode<E> successor = current;
		if (current.getLeft() != null)	//check if left node exists
		{
			successor = promoteSuccessor(current.getLeft());    //left is successor
			if (successor == current.getLeft())
			{
				current.setLeft(successor.getRight());    //setLeft to right
			}
		}
		return successor;
	}

	/*****************************
	 * Purpose: inOrder wrapper
	 * @param fileName
	 */
	public void inOrder(String fileName)
	{
		FileOutputStream fileStrm = null;
		PrintWriter pw = null;
		try
		{
			fileStrm = new FileOutputStream(fileName);
			pw = new PrintWriter(fileStrm);    //write to filename
			inOrderRecursive(root, pw);   //recursive method
			pw.close();    //close printwriter
		}
		catch(IOException e)
		{
			System.out.println("Unable to write to file");
		}
	}

	/**************************
	 * Purpose: in order recursive method
	 * @param currentNode
	 * @param pw
	 *************************/
	private void inOrderRecursive(TreeNode<E> currentNode, PrintWriter pw)
	{
		if (currentNode != null)
		{
			inOrderRecursive(currentNode.leftChild, pw);
			pw.print(currentNode.getValue().toString() + "\n");    //call and print recursive node
			inOrderRecursive(currentNode.rightChild, pw);
		}
	}

	/*******************************
	 * Purpose: Preorder wrapper method
	 * @param fileName
	 * Export NONE
	 ****************************/
	public void preOrder(String fileName)
	{
		FileOutputStream fileStrm = null;
		PrintWriter pw = null;
		try
		{
			fileStrm = new FileOutputStream(fileName);
			pw = new PrintWriter(fileStrm);   //print to filename
			preOrderRecursive(root, pw);
			pw.close();  //close printwriter
		}
		catch(IOException e)
		{
			System.out.println("Unable to write to file");
		}
	}

	/****************************
	 * Purpose: To order values in preorder by calling recursive method
	 * @param currentNode
	 * @param pw
	 * @return: NONE
	 */
	private void preOrderRecursive(TreeNode<E> currentNode, PrintWriter pw)
	{
		if (currentNode != null)
		{
			pw.print(currentNode.getValue().toString() + "\n");
			preOrderRecursive(currentNode.leftChild, pw);
			//call recursive method passing in children and printwriter
			preOrderRecursive(currentNode.rightChild, pw);
		}
	}

	/*********************************
	 * Purpose: To order values in postorder - wrapper method
	 * @param fileName
	 * @return NONE
	 */
	public void postOrder(String fileName)
	{
		FileOutputStream fileStrm = null;
		PrintWriter pw = null;
		try
		{
			fileStrm = new FileOutputStream(fileName);    //open filstrm
			pw = new PrintWriter(fileStrm);
			postOrderRecursive(root, pw);   //call private method
			pw.close();     //close printwriter
		}
		catch(IOException e)
		{
			System.out.println("Unable to write to file");
		}
	}

	/******************************
	 * Purpose: To call recursive methods to order values in post order
	 * @param currentNode
	 * @param pw
	 * @return NONE
	 */
	private void postOrderRecursive(TreeNode<E> currentNode, PrintWriter pw)
	{
		if(currentNode != null)
		{
			postOrderRecursive(currentNode.leftChild, pw);    //recurse methods
			postOrderRecursive(currentNode.rightChild, pw);
			pw.print(currentNode.getValue().toString() + "\n"); //print to file
		}
	}

	/******************************
	 * Purpose: print tree to terminal
	 * @param: NONE
	 * @return: void
	 */
	public void printTree()
	{
		printAscending(root);   //wrapper method
	}

	/****************************
	 * Purpose: To print each node
	 * @param node
	 * @return: NONE
	 */
	private void printAscending(TreeNode<E> node)
	{
		if(node != null)
		{
			printAscending(node.getLeft());   //print left
			System.out.println(node.value);
			printAscending(node.getRight());    //print right

		}
	}

	/********************************
	 * Purpose: Iterator constructor
	 * @param: NONE
	 * @return Iterator for tree
	 *****************************/
	public Iterator iterator()
	{
		return new TreeIterator<E>(this.root);
	}

	private class TreeIterator<E> implements Iterator
	{
		protected TreeNode<E> root = null;
		protected Stack<TreeNode<E>> visiting = new Stack<TreeNode<E>>();
		protected Stack<Boolean> visitingRightChild = new Stack<Boolean>();
		private boolean preOrder = false;
		private boolean inOrder = true;      //class fields
		private boolean postOrder = false;

		public TreeIterator(TreeNode<E> root)
		{
			this.root = root;
			visiting = new Stack<TreeNode<E>>();
			visitingRightChild = new Stack<Boolean>();     //constructor
			preOrder = false;
			inOrder = true;
			postOrder = false;
		}

		public TreeIterator(TreeNode<E> root, boolean inPreOrder)
		{
			this.root = root;
			visiting = new Stack<TreeNode<E>>();
			visitingRightChild = new Stack<Boolean>();      //Alt constructor
			preOrder = inPreOrder;
			inOrder = false;
			postOrder = !preOrder;
		}

		/************************
		 * Purpose: Check if nodes exist
		 * @return: boolean value
		 * @param: NONE
		 */
		public boolean hasNext()
		{
			return (root != null);      //check if more elements
		}

		/***********************
		 * Purpose: return order
		 * @return x
		 */
		public E next()       //loop to next and return order
		{
			E x = null;
			if (!hasNext())
			{
				throw new NoSuchElementException("no more elements");
			}
			if (preOrder)
			{
				x = preorderNext();    //preorder
			}
			else if (inOrder)
			{
				x = inorderNext();     //inorder
			}
			else if (postOrder)
			{
				x = postorderNext();   //postorder
			}
			else
			{
				assert (false);
				x = null;
			}
			return x;
		}

		/******************************
		 * Purpose: Iterate preorder
		 * @param: NONE
		 * @return: node.getValue
		 */
		private E preorderNext()
		{
			if (visiting.empty())
			{
				visiting.push(root);    //push root to stack
			}
			TreeNode<E> node = visiting.pop();
			E result = node.getValue();
			if (node.getRight() != null)      //check right exists
			{
				visiting.push(node.getRight());  //push right to stack
			}
			if (node.getLeft() != null) //check left exists
			{
				visiting.push(node.getLeft());    //push left to stack
			}
			if (visiting.empty())      //check if empty
			{
				root = null;
			}
			return node.getValue();     //return node value
		}

		/*****************************
		 * Purpose: Iterate pre-order
		 * @param: NONE
		 * @return: result
		 */
		private E inorderNext()
		{
			if (visiting.empty())        //check if empty
			{
				pushLeftMostNodeRecord(root);
			}
			TreeNode<E> node = visiting.pop();
			E result = node.getValue();
			if (node.getRight() != null)    //check if right is empty
			{
				TreeNode<E> right = node.getRight();
				pushLeftMostNodeRecord(right);     //iterate right
			}
			if (visiting.empty())
			{
				root = null;
			}
			return result;
		}

		/***************************
		 *Purpose: push left node to stack
		 * @param node
		 * @return: NONE
		 */
		private void pushLeftMostNodeRecord(TreeNode<E> node)
		{
			if (node != null)
			{
				visiting.push(node);
				visitingRightChild.push(false);
				pushLeftMostNodeRecord(node.getLeft());
			}
		}

		/*************************
		 * Purpose: Iterate postorder nodes
		 * @param: NONE
		 * @return: x
		 */
		private E postorderNext()
		{
			E x = null;
			if (visiting.empty())    //check if empty
			{
				pushLeftMostNodeRecord(root);   //push root
			}
			if ((visiting.peek().getRight() == null) || (visitingRightChild.peek()))
			{
				E result = visiting.pop().getValue();
				visitingRightChild.pop();   //pop node
				if (visiting.empty())
				{
					root = null;   //if empty, no root
				}
				x = result;
			}
			else
			{
				if (visitingRightChild.pop())   //pop child
				{
					assert (false);
				}
				visitingRightChild.push(true);
				TreeNode<E> right = visiting.peek().getRight();  //get right
				assert (right != null);
				pushLeftMostNodeRecord(right);
				x = preorderNext();
			}
			return x;
		}

		/************************
		 * Purpose: remove method
		 * @param: NONE
		 * @return: NONE
		 */
		public void remove()
		{
			throw new UnsupportedOperationException("remove");
		}
	}
}


