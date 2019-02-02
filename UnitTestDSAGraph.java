import java.util.*;
public class UnitTestDSAGraph
{
	public static void testDSAGraph()
	{
		DSAGraph theGraph = new DSAGraph();
		Calendar newCal = Calendar.getInstance();
		Calendar tempCal = Calendar.getInstance();
		newCal.set(Calendar.HOUR,12);
		newCal.add(Calendar.MINUTE, 10);
		DSALinkedList theList = new DSALinkedList();
		theList = theGraph.addVertex("A","B",1,newCal, tempCal, theList, "CAR");
		if(theGraph.getVertexCount() == 2)
		{
			System.out.println("Vertex's added");
			System.out.println("Edge added");
		}
		//Test by display
		//if the data being printed in the list is the data in the graph
		//then graph insertion was successful
		theList.displayFoward();
	}

	//The DSAGraph was not done correctly, therefore testing the
	//graph cannnot be done adequately
}