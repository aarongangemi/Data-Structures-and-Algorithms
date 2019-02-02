import java.io.*;
import java.util.*;

/*********************************
 * Purpose: This is the list by margin class. It reads all the margins
 * 			into multiple Lists and calculates the margins for the user.
 * REFERENCE: The logic of this class is based off of my Data Structures
 * 			  and Algorithms practical 5 and the lecture slides
 * Author: Aaron Gangemi (19447337)
 * Date Modified: 27/10/2018
 */
public class ListByMargin
{
	/******************************
	 * Purpose: gets the number of elements in a linked list
	 * 			also known as the number of lines in a file
	 * @param marginList
	 * @return count
	 */
	public static int getNumLines(DSALinkedList marginList)
	{
		Iterator marginIter = marginList.iterator();
		Object currentNode;
		int count = 0;
		while (marginIter.hasNext())  //while next exists
		{
			currentNode = marginIter.next(); //go to next
			count++; //increment count
		}
		return count;
	}

	/************************************
	 * Purpose: To read in the votes for into a list from the linked list
	 * @param votesForList
	 * @param partyName
	 * @param marginList
	 * @return list
	 *******************************/
	public static DSALinkedList readVotesFor(DSALinkedList votesForList,
												String partyName,
												DSALinkedList marginList)
	{
		Iterator listIter = marginList.iterator();
		Object currentNode;
		String divisionID, votesFor, divisionName, stateName;
		while(listIter.hasNext())   //while list isn't at end
		{
			currentNode = listIter.next();  //go to next element
			if(currentNode.toString().contains(partyName))  //if node has party
			{
				divisionID = getDivisionIDFromFile(currentNode.toString());
				divisionName = getDivisionNameFromFile(currentNode.toString());
				votesFor = getVotesForFromFile(currentNode.toString());
				stateName = getStateNameFromFile(currentNode.toString());
				//Extract components from linked list before inserting into
				// list
				votesForList.insertLast(stateName + "," +
								   divisionName + "," + divisionID + "," +
										   votesFor);
				//stateName, divisionName, divisionId and votesFor are required
				//for itinerary so they are used in list
			}

		}
		return votesForList;
	}

	/***********************************
	 * Purpose: To read in the votes against into a list from the linked list
	 * @param votesAgainstList
	 * @param partyName
	 * @param theList
	 * @return list
	 *********************************/
	public static DSALinkedList readVotesAgainst(
			DSALinkedList votesAgainstList,
			String partyName, DSALinkedList theList)
	{
		Iterator listIter = theList.iterator();
		Object currentNode;
		String divisionID, votesAgainst, divisionName, stateName;
		while(listIter.hasNext()) //while list isn't at end
		{
			currentNode = listIter.next(); // go to next element
			if(!currentNode.toString().contains(partyName)) //check for name
			{
				divisionID = getDivisionIDFromFile(currentNode.toString());
				divisionName = getDivisionNameFromFile(currentNode.toString());
				votesAgainst = getVotesForFromFile(currentNode.toString());
				stateName = getStateNameFromFile(currentNode.toString());
				//Extract elements required for list
				votesAgainstList.insertLast(stateName + "," +
										divisionName + "," + divisionID + "," +
												votesAgainst);
				//entire line is key to make node unique
				//stateName, divisionName, divisionId and votesAgainst are required
				//for itinerary so they are used in list
			}
		}
		return votesAgainstList;
	}

	/***************************************
	 * Purpose: To calculate the votes for the division
	 * @param newList
	 * @param numberOfLines
	 * @return templist (Contains votes for)
	 */
	public static DSALinkedList calcVotesFor(DSALinkedList newList,
									   int numberOfLines)
	{
		Iterator listIter = newList.iterator();
		String x;
		int i = 0, count = 1;
		String[] divisionLabelArray = new String[numberOfLines];
		int[] divisionVotesArray = new int[numberOfLines];
		x = listIter.next().toString();
		String[] lineArray = x.split(",");
		divisionLabelArray[0] = lineArray[1] + "," + lineArray[2];
		//Construct and Add first label to list as comparison is required
		divisionVotesArray[0] = Integer.parseInt(lineArray[3]);
		//Construct and Add first votes to list as comparison is required
		DSALinkedList tempList = new DSALinkedList();
		//Create list
		while(listIter.hasNext())
		{
			//Iterate through list
			x = listIter.next().toString();
			//go to next in list
			if(divisionLabelArray[i].equals(processLabel(x)))
			{
				//add votes to existing element if label is already in array
				divisionVotesArray[i] += Integer.parseInt(splitVotesLine(x, 3));
			}
			else
			{
				//If label isn't in array
				i++;  //go to next element
				divisionLabelArray[i] = processLabel(x); //add label
				divisionVotesArray[i] = Integer.parseInt(splitVotesLine(x, 3));
				//add votes
				count++; //increment count
			}
		}
		for(int j = 0; j < count; j++)
		{
			tempList.insertLast(divisionLabelArray[j] + ","
													+ divisionVotesArray[j]);
			//add votes and label to list - used later - see main
		}
		return tempList;
	}

	/**************************************
	 * Purpose: To calculate the votes against the division
	 * @param votesAgainstList
	 * @param numberOfLines
	 * @return templist
	 ***********************************/
	public static DSALinkedList calcVotesAgainst(DSALinkedList votesAgainstList,
										int numberOfLines)
	{
		Iterator listIter = votesAgainstList.iterator();
		String currentNode;
		int i = 0, count = 1;
		String[] divisionLabelArray = new String[numberOfLines];
		int[] divisionVotesArray = new int[numberOfLines];
		currentNode = listIter.next().toString();
		String[] lineArray = currentNode.split(",");
		//Split first item in list for comparison in submodule
		divisionLabelArray[0] = lineArray[1] + "," + lineArray[2];
		divisionVotesArray[0] = Integer.parseInt(lineArray[3]);
		//Construct first label and firdt votes
		//required for comparison and addition of votes
		DSALinkedList tempList = new DSALinkedList();
		while (listIter.hasNext()) //check for more elements
		{
			currentNode = listIter.next().toString(); // go to next element
			if (divisionLabelArray[i].equals(processLabel(currentNode)))
			{
				//if labels are the same, then add votes and do not add labels
				divisionVotesArray[i] += Integer.parseInt(
										splitVotesLine(currentNode, 3));
			}
			else
			{
				//if labels are not the same, add label to next element and
				// add votes
				i++; //increment i
				divisionLabelArray[i] = processLabel(currentNode); //add label
				divisionVotesArray[i] = Integer.parseInt(
										splitVotesLine(currentNode, 3));
				//add votes
				count++;
			}
		}
		for (int j = 0; j < count; j++)
		{
			tempList.insertLast(divisionLabelArray[j] + "," +
													divisionVotesArray[j]);
			//loop through Array and votes and add to List
		}
		return tempList;
	}

	/************************************
	 * Purpose: Use for and against List to calc margin for division
	 * @param votesForList
	 * @param votesAgainstList
	 * @param customMargin
	 * @return writeOutList
	 */
	public static DSALinkedList calcMargin(DSALinkedList votesForList,
								  DSALinkedList votesAgainstList, int customMargin)
	{
		Iterator votesForIter = votesForList.iterator();
		Iterator votesAgainstIter = votesAgainstList.iterator();
		String divisionIdFor, divIDAgainst, stateName;
		int votesFor, votesAgainst;
		double margin, percent;
		String divisionNameFor, divisionNameAgainst;
		Object x;
		Object y;
		DSALinkedList writeOutList = new DSALinkedList();
		//create list
		while(votesForIter.hasNext() && votesAgainstIter.hasNext())
		{
			//iterate through both Lists
			x = votesForIter.next(); //go to next in votesFor List
			y = votesAgainstIter.next(); //go to next in votesAgainst List
			stateName = splitVotesLine(x.toString(), 0); //get state name
			divisionNameFor = splitVotesLine(x.toString(), 1); //get div names
			divisionNameAgainst = splitVotesLine(x.toString(), 1);
			divisionIdFor =  splitVotesLine(x.toString(), 1); //get div ID
			divIDAgainst = splitVotesLine(y.toString(), 1); //get divAgainst
			votesFor = Integer.parseInt(splitVotesLine(x.toString(), 2));
			//get votesFor from votes for List
			votesAgainst = Integer.parseInt(splitVotesLine(y.toString(), 2));
			//get votes against from votes against List
			if(divisionIdFor.equals(divIDAgainst)) //check ID's are the same
			{
				percent = (double) (votesFor)/(votesFor + votesAgainst) * 100;
				margin = 50.0 - percent;
				//calculate the percent and margin for each division
				if(margin > (customMargin * -1)  && margin < customMargin)
				{
					//check if margin is within specified range
					System.out.println("State Name: " + stateName +
											   ", " + "DivisionName: " +
											   divisionNameAgainst + ", " +
											   "Margin: " + margin);
					//print margin to terminal
					writeOutList.insertLast("StateName: " +
													stateName +
													", " + "DivisionID: " +
													divisionIdFor + ", " +
													"Margin: " + margin);
					//add margin to list for writing if required
				}
			}
		}

		return writeOutList;
	}

	/******************************
	 * Purpose: Construct the label for the List node
	 * @param line
	 * @return constructed label
	 */
	public static String processLabel(String line)
	{
		String[] lineArray = line.split(","); //split on comma
		return lineArray[0] + "," + lineArray[1]; //construct + return label
	}

	/*******************************
	 * Purpose: To split the votes Line
	 * @param line
	 * @param i (changed when called to decipher between for and against)
	 * @return get votes for or against.
	 */
	public static String splitVotesLine(String line, int i)
	{
		String[] lineArray = line.split(","); //split on comma
		return lineArray[i]; //return votes in string form
	}

	/**************************************
	 * Purpose: User enters the party name they would like to get margins
	 * @return partyName
	 **************************************/
	public static String getPartyNameBySearch()
	{
		String partyName = "";
		Scanner sc = new Scanner(System.in);
		System.out.println("Please enter the party name for marginal votes");
		partyName = sc.nextLine(); //Enter party name
		return partyName;
	}

	/**************************************
	 * Purpose: get votesFor from the file
	 * @param line
	 * @return votesFor
	 */
	public static String getVotesForFromFile(String line)
	{
		String votesFor;
		String[] lineArray = line.split(","); //split on comma
		votesFor = lineArray[13]; //return votes for
		return votesFor;
	}

	/************************************
	 * Purpose: get the user to enter the desired custom margin
	 * @return customMargin
	 ************************************/
	public static int enterCustomMargin()
	{
		int customMargin;
		Scanner sc = new Scanner(System.in);
		try
		{
			System.out.println("Please enter a margin as an integer");
			customMargin = sc.nextInt(); //user enters margin
		}
		catch (InputMismatchException e) //input is invalid
		{
			System.out.println("Margin invalid, using default value of " +
									   "6%");
			customMargin = 6; //set default margin
			sc.nextLine(); //flush invalid input
		}

		return customMargin;
	}

	/*********************************
	 * Purpose: to get the division ID from file
	 * @param line
	 * @return divisionId
	 ***********************************/
	public static String getDivisionIDFromFile(String line)
	{
		String[] divisionArray = line.split(","); //split on comma
		return divisionArray[1];
	}

	/************************************
	 * Purpose: to get the division name from file
	 * @param line
	 * @return division Name
	 */
	public static String getDivisionNameFromFile(String line)
	{
		String[] divisionArray = line.split(","); //split on comma
		return divisionArray[2];
	}

	/************************************
	 * Purpose: To get the state name from file
	 * @param line
	 * @return state name
	 */
	public static String getStateNameFromFile(String line)
	{
		String[] stateArray = line.split(","); //split on comma
		return stateArray[0];
	}

}
