/*******************************
 * Purpose: Create a graph with verticies and edges
 * Author: Aaron Gangemi
 * Date modified: 02/10/2018
 */
import java.util.*;
public class DSAGraph<E>
{
	private DSALinkedList<DSAVertex<E>> graphList;      //classfields
	private int adjMatrix[][];
	public DSAGraph()     //constructor
	{
		graphList = new DSALinkedList<DSAVertex<E>>();
		adjMatrix = new int[100][100];
		for (int i = 0; i < 10; i++)     //create new matrix with all 0
		{
			for (int j = 0; j < 10; j++)
			{
				adjMatrix[i][j] = 0;
			}
		}
	}

	/*********************************
	 * Purpose: add vertex to graph
	 * @param vertex1
	 * @param vertex2
	 ******************************/
	public void addVertex(E vertex1, E vertex2)
	{
		DSAVertex<E> vertex3 = new DSAVertex<E>(vertex1);
		DSAVertex<E> vertex4 = new DSAVertex<E>(vertex2);    //create vertex's
		if (checkVertexIsUnique(vertex1) == null)
		//check vertex doesn't already exist
		{
			graphList.insertLast(vertex3); //add to graph
			vertex3.setVertexCounter(getVertexCount());
			//add count for matrix position
		}
		if (checkVertexIsUnique(vertex2) == null)
		{
			graphList.insertLast(vertex4); //add to graph
			vertex4.setVertexCounter(getVertexCount());
			//add count for matrix position
		}
		DSAVertex<E> v1 = checkVertexIsUnique(vertex1);
		DSAVertex<E> v2 = checkVertexIsUnique(vertex2);
		addEdge(v1, v2);
		//add edge

	}

	/****************************
	 * Purpose: add edge to graph
	 * @param vertex1
	 * @param vertex2
	 */
	public void addEdge(DSAVertex<E> vertex1, DSAVertex<E> vertex2)
	{
		vertex1.addVertex(vertex2);
		vertex2.addVertex(vertex1);    //add verticies and connect them
		setAdjMatrix(vertex1.getVertexCounter(),vertex2.getVertexCounter());
		setAdjMatrix(vertex2.getVertexCounter(), vertex1.getVertexCounter());
		//set edges in matrix
	}

	/******************************
	 * Purpose: check vertex doesn't already exist and is unique
	 * @param vertex1
	 * @return vertex
	 */
	public DSAVertex<E> checkVertexIsUnique(E vertex1)
	{
		DSAVertex<E> vertex = null;
		for (DSAVertex<E> v3 : graphList)    //loop through all verticies
		{
			if (v3.value.equals(vertex1))    //if exists
			{
				vertex = v3;  //make them same vertex
			}
		}
		return vertex;
	}

	/***************************
	 * Purpose: get vertex count
	 * @return count
	 */
	public int getVertexCount()
	{
		Iterator x;
		Object y;
		int count = 0;
		if (graphList == null)
		{
			count = 0;
		}
		else
		{
			x = graphList.iterator();
			while (x.hasNext())   //loop through graph
			{
				count++;   //increment count
				y = x.next(); //next node
			}
		}
		return count;
	}

	/**************************
	 * Purpose: getEdgeCount
	 * @return count
	 *************************/
	public int getEdgeCount()
	{
		Iterator x;
		Object y;
		int noOfEdges = -2;   //v - 2 = E
		x = graphList.iterator();
		while (x.hasNext())   //if more verticies exist
		{
			noOfEdges++;   //increment edges
			y = x.next();   //go to next
		}
		return noOfEdges;
	}

	/*****************************
	 * Purpose:create the toString for adjacency list
	 * @return adjOutput
	 */
	public String adjacencyList()
	{
		String adjOutput = " ";
		int count = 0;
		for (DSAVertex<E> vertex : graphList)
		{
			adjOutput = adjOutput + vertex.getValue().toString() + "- > " + vertex.vertexList.toString() + "\n";
			//ToString for list
			//Loop through nodes
		}
		return adjOutput;
	}

	/******************************
	 * Purpose: get adjacenct/iterator
	 * @param vertex
	 * @return found
	 */
	public Iterator<DSAVertex<E>> getAdjacent(DSAVertex<E> vertex)
	{
		return vertex.getAdjacent().iterator(); //return iterator
	}

	/*******************************
	 * Purpose: check verticies are adjacent
	 * @param vertex1
	 * @param vertex2
	 * @return found
	 */
	public boolean isAdjacent(DSAVertex<E> vertex1, DSAVertex<E> vertex2)
	{
		Object y;
		boolean found = false;
		while (getAdjacent(vertex1).hasNext() && getAdjacent(vertex2).hasNext())
		//check more nodes in list
		{
			if (vertex1.getVisited() && vertex2.getVisited())
			{
				//check if verticies are visited
				found = true;
				y = vertex1.getAdjacent().iterator().next();
				//go to next
			}
		}
		return found;
	}

	/******************************
	 * Purpose: get adjacency Matrix
	 * @return
	 */
	public int[][] getAdjMatrix()
	{
		return adjMatrix;
	}

	/*****************************
	 * Purpose: set adjMatrix to 1
	 * @param XPosition
	 * @param YPosition
	 */
	public void setAdjMatrix(int XPosition, int YPosition)
	{
		adjMatrix[XPosition][YPosition] = 1;    //set matrix position to 1
	}

	public DSALinkedList<DSAVertex<E>> getVertexList(DSAVertex<E> vertex)
	{
		return vertex.getAdjacent();
	}
	/***************************
	 * Purpose: print matrix by looping through
	 */
	public void displayMatrix()
	{
		for (int i = 1; i <= getVertexCount(); i++) //loop through row
		{
			for (int j = 1; j <= getVertexCount(); j++)   //loop through column
			{
				System.out.print(adjMatrix[i][j]);   //print
				System.out.print(" ");
			}
			System.out.println("");
		}
	}

	private class DSAVertex<E>
	{
		private boolean visited;
		private E value;
		private DSALinkedList<DSAVertex<E>> vertexList;
		private int vertexCounter;
		public DSAVertex(E inValue)
		{
			visited = false;		//vertex constructor
			value = inValue;
			vertexList = new DSALinkedList<DSAVertex<E>>();
		}

		/***********************
		 * Purpose: accessor for value
		 * @return value
		 */
		public E getValue()
		{
			return value;
		}

		/***********************
		 * Purpose: get List
		 * @return vertexList
		 */
		public DSALinkedList<DSAVertex<E>> getAdjacent()
		{
			return vertexList;
		}

		/**********************
		 * Purpose: add vertex to list
		 * @param vertex
		 */
		public void addVertex(DSAVertex<E> vertex)
		{
			vertexList.insertLast(vertex);  //add to Linked list
		}

		/***********************
		 * Purpose: access vertex
		 * @return next iterator
		 */
		public DSAVertex<E> getVertex()
		{
			return (DSAVertex<E>) vertexList.iterator().next();
		}

		/********************
		 * Purpose: add edge
		 * @param vertex
		 */
		public void addEdge(DSAVertex<E> vertex)
		{
			vertexList.insertLast(vertex); //add vertex to list
		}

		/**********************
		 * Purpose: vertex is visited - boolean
		 */
		public void setVisited()
		{
			visited = true;
		}

		/**************************
		 * set node to not visited - boolean
		 */
		public void clearVisitied()
		{
			visited = false;
		}

		/*************************
		 * Used for matrix positioning
		 * @return vertexCounter
		 */
		public int getVertexCounter()
		{
			return vertexCounter;
		}

		/***************************
		 * Purpose: used to set matrix position
		 * @param inCounter
		 */
		public void setVertexCounter(int inCounter)
		{
			vertexCounter = inCounter;
		}

		public boolean getVisited()
		{
			return visited;
		}

		public String toString()
		{
			return (value.toString() + " ");
		}
	}

	/**************************
	 * Purpose: perform a DFS on grapbh
	 */
	public void depthFirstSearch()
	{
		DSALinkedList<DSAVertex<E>> iter;
		Object x;
		System.out.println("Depth First Search: ");
		Stack<DSAVertex<E>> theStack = new Stack<DSAVertex<E>>(); //create stack
		theStack.push(graphList.peekFirst());//push first to stack
		graphList.peekFirst().setVisited();
		while(!theStack.empty())  //loop through stack
		{
			x = theStack.peek();
			DSAVertex<E> v = getAdjUnvisitedVertex(theStack.peek());
			if(v == null)
			{
				theStack.pop();  //pop off if vertex is Null
			}
			else
			{
				v.setVisited();   //visited = true
				System.out.println(x.toString());
				System.out.println(v.toString());
				theStack.push(v);   //push vertex to stack
			}
		}
	}

	/*************************
	 * Purpose: to get unvisited vertex that is adjacent
	 * @param x
	 * @return value
	 */
	public DSAVertex<E> getAdjUnvisitedVertex(DSAVertex<E> x)
	{
		DSAVertex<E> value = null;
		DSAVertex<E> vertex = (DSAVertex<E>) x;
		boolean goToNext = true;
		while(x.vertexList.iterator().hasNext() && goToNext)
		{
			//loop through
			Object temp = x.vertexList.iterator().next();
			DSAVertex<E> inTemp = (DSAVertex<E>) temp;
			if(inTemp.getVisited() == false)
			{
				value = inTemp;  //used for type case
				goToNext = false;
			}
		}
		return value;
	}

	/****************************
	 * Purpose: perform a BFS on graph
	 */
	/*public void breadthFirstSearch()
	{
		Object x;
		Iterator y;
		System.out.println("Breadth First Search: ");
		DSAVertex<E> vertex2 = null;
		DSAVertex<E> vertex1 = null;
		graphList.peekFirst().setVisited();  //set first to true
		System.out.println(graphList.peekFirst().toString());
		DSAQueue1<DSAVertex<E>> theQueue = new DSAQueue1<DSAVertex<E>>();
		//create queue
		theQueue.enqueue(graphList.peekFirst());  //enqueue first
		while(!theQueue.isEmpty() && y.hasNext())
		{
			vertex1 = (DSAVertex<E>) theQueue.dequeue();  //dequeue first
			vertex2 = getAdjUnvisitedVertex(vertex1);
			while (vertex2 != null)
			{
				vertex2.setVisited();
				theQueue.enqueue(vertex2);  //enqueue
				System.out.println(vertex2.toString());
				x = y.next();
			}
		}
	}*/


}