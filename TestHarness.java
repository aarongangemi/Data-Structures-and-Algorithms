import java.util.*;
public class TestHarness
{
	///////////////////////////////Testing Constructor////////////
	public static void main(String[] args)
	{
		int numTests = 0;
		BinarySearchTree<String> myTree = new BinarySearchTree();
		try
		{
			System.out.println("Testing constructor");
			if (myTree != null)
			{
				System.out.println("Constructor Passed");
			}
			else
			{
				System.out.println("Constructor Failed");
			}
		}
		catch(Exception e)
		{
			System.out.println("Constructor threw exception");
		}
		/////////////////////////////Testing insert////////////////////////
		try
		{
			myTree.insert("1","2");
			myTree.insert("2","3");
			myTree.insert("3","4");
			if (myTree.find("1").equals("2"))
			{
				System.out.println("Insert passed");
			}
		}
		catch(Exception e)
		{
			System.out.println("Insert threw exception");
		}
		//////////////////////////Testing find//////////////////////////
		try
		{
			if (myTree.find("1").equals("2"))
			{
				System.out.println("Find passed");
			}
			else
			{
				System.out.println("Find failed");
			}
		}
		catch(Exception e)
		{
			System.out.println("Find threw exception");
		}
		////////////////////////Testing Delete/////////////////////
		try
		{
			myTree.insert("4", "delete passed");
			myTree.delete("1");
		}
		catch (Exception e)
		{
			System.out.println("Delete threw exception and failed");
		}
		////////////////////Testing height//////////////////////
		try
		{
			if (myTree.height() == 2)
			{
				System.out.println("height passed");
			}
			else
			{
				System.out.println("height failed");
			}
		}
		catch(NumberFormatException e)
		{
			System.out.println("Height threw exception");
		}
	}
}
