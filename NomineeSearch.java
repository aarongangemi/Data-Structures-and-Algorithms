import java.io.*;
import java.util.*;
public class NomineeSearch
{

	/*********************************
	 * REFERENCE: This method is being reused. It was submitted and taken
	 * 			  from my equation solver task in the Practical 3. In addition,
	 * 			  the File IO is being resused from Practical 1 and based
	 * 			  off of the lecture slides of week 1.
	 * Purpose: To read in each line of the CSV FILE
	 * @return line
	 *******************************/
	public static DSALinkedList readIntoList(String substring,
											 DSALinkedList candidateList)
	{
		Iterator listIter = candidateList.iterator();
		DSALinkedList nomineeList = new DSALinkedList();
		Object currentNode;
		String surname;
		while (listIter.hasNext())
		{
			currentNode = listIter.next();
			surname = ListNominees.getComponents(
							currentNode.toString(), 6);
			//construct surname
			if(surname.startsWith(substring.toUpperCase()))
			{
				//Check surname starts with substring
				nomineeList.insertLast(currentNode.toString());  //add line to list if true
			}
		}
		if(nomineeList.isEmpty())  //if no items in list
		{
			System.out.println("No surnames start with substring");
		}
		return nomineeList;
	}

	/**********************************
	 * Purpose: to get the nominees last name
	 * @return substring
	 ********************************/

	public static String getNomineeLastName()
	{
		String substring = "";
		Scanner sc = new Scanner(System.in);
		boolean isValid = false;
		while(!isValid)  //loop so input will be valid
		{
			try
			{
				System.out.println("Please enter the last name of the nominee: ");
				substring = sc.nextLine();  //enter nominee last name
				isValid = true;
			}
			catch (InputMismatchException e)  //catch invalid data type
			{
				System.out.println("Incorrect datatype for substring");
				sc.nextLine();
			}
		}
		return substring;
	}

	/*******************************
	 * REFERENCE: The code below contains some code which was taken from stack
	 * 			  overflow. SEE BELOW FOR REFERENCE.
	 * Purpose: To filter the data based on either state, party or none.
	 * @param filterOption
	 * @param theList
	 */
	public static String switchFilterOption(int filterOption,
										  DSALinkedList theList)
	{
		Iterator iter = theList.iterator();
		Object currentNode;
		String filter = " ", upperParty;
		if (filterOption == 1)  //check for state
		{
			filter = getStateFilter();  //user enters state
			System.out.println("SEARCH RESULTS FOUND:");
			System.out.println("=====================");
			while(iter.hasNext())
			{
				currentNode = iter.next().toString(); //get next in list
				if(currentNode.toString().startsWith(filter)) //check for state
				{
					System.out.println(currentNode);  //print list node
				}
			}
		}
		else if(filterOption == 2)
		{
			filter = getPartyFilter(); //get party name
			//Obtained from Rekon,
			//Published 11 October 2010
			//https://stackoverflow.com/questions/3904579/
			//how-to-capitalize-the-first-letter-of-a-string-in-java
			//Accessed on the 4th October 2018

			upperParty = filter.substring(0,1).toUpperCase() +
						 filter.substring(1); //convert first character to upper

			//end of Obtained code
			System.out.println("SEARCH RESULTS FOUND:");
			System.out.println("=====================");
			while(iter.hasNext())  //loop through list
			{
				currentNode = iter.next().toString();   //get next in list
				if(currentNode.toString().contains(filter + ","))
				{
					//checks if line contains party
					//comma is on the end due to "Liberal"
					//and "Liberal National"/
					System.out.println(currentNode);
				}
			}
		}
		else if(filterOption == 3)
		{
			System.out.println("SEARCH RESULTS FOUND:");
			System.out.println("=====================");
			theList.displayFoward();
			//Print all in list by traversing through it.
		}
		return filter;
	}

	/*******************************
	 * Purpose: To get the user to enter the state name and filter by this
	 * @return state
	 */
	public static String getStateFilter()
	{
		boolean isValid = false;
		String state = "";
		Scanner sc = new Scanner(System.in);
		while(!isValid) //loop so input is valid
		{
			try
			{
				System.out.println("Please enter a state name to filter by");
				state = sc.nextLine();  //user enters state name
				isValid = true;  //if valid datatype
			}
			catch (InputMismatchException e) //catch invalid data type
			{
				System.out.println("Please enter a valid state as a string");
				sc.nextLine(); //flush invalid data
			}
		}
		return state;
	}

	/*******************************
	 * Purpose: The user enters the party name and filter by this data
	 * @return party
	 ******************************/
	public static String getPartyFilter()
	{
		boolean isValid = false;
		String party = "";
		Scanner sc = new Scanner(System.in);
		while (!isValid) //loop so input will be valid
		{
			try
			{
				System.out.println("Please enter a party to filter by");
				party = sc.nextLine();  //user enters party
				isValid = true;
			}
			catch (InputMismatchException e) //catch invalid datatype
			{
				System.out.println("Please enter a valid party as a string");
				sc.nextLine(); //flush invalid input
			}
		}
		return party;
	}

	public static void writeOutNominees(String fileName, DSALinkedList theList,
										int filterOption, String filter)
	{
		Iterator iter;
		Object currentNode;
		FileOutputStream outStrm;
		PrintWriter pw = null;
		try
		{
			outStrm = new FileOutputStream(fileName);
			pw = new PrintWriter(outStrm);
			iter = theList.iterator();
			while (iter.hasNext())
			{
				currentNode = iter.next().toString();
				if (filterOption == 1)
				{
					if (currentNode.toString().startsWith(filter))
					{
						pw.println(currentNode + " ");
					}
				}
				else if (filterOption == 2)
				{
					if (currentNode.toString().startsWith(filter))
					{
						pw.println(currentNode + " ");
					}
				}
			}
			pw.close();
		}
		catch (IOException e)
		{
			System.out.println("Unable to write to file");
			pw.close();
		}
	}
}
