import java.util.*;
import java.io.*;

/***************************************
 * Purpose: This is the list nominees class. It is used for the first
 * 		    part of the program. Checks candidate objects against the
 * 		    criteria and prints them if valid
 * Author: Aaron Gangemi
 * Date Modified: 27/10/2018
 */
public class ListNominees
{
	/*************************************
	 * Purpose: The data uses an array to be sorted. This function
	 * 		    gets that arrays length
	 * @param candidateList
	 * @return count
	 */
	public static int getArraySize(DSALinkedList candidateList)
	{
		Iterator candidateIter = candidateList.iterator();
		Object temp;
		int count = 0;
		while(candidateIter.hasNext())  //check for next
		{
			temp = candidateIter.next(); //go to next
			count++; //increment count
		}
		return count;
	}

	/**************************************
	 * Purpose: To extract the objects components
	 * @param currentCandidate
	 * @param ii
	 * @return component
	 */
	public static String getComponents(String currentCandidate, int ii)
	{
		String[] lineArray = currentCandidate.split(","); //split on comma
		return lineArray[ii];
	}

	/************************************
	 * Purpose: To set the required data for the object and
	 * 			construct the object
	 * @param count
	 * @param candidateList
	 * @return candidateArray
	 */
	public static CandidateClass[] setFileContents(int count,
										 DSALinkedList candidateList)
	{
		CandidateClass[] candidateArray = new CandidateClass[count];
		Iterator candidateIter = candidateList.iterator();
		String state, party, division, surname, firstName;
		int divisionId, i = 0;
		String currentCandidate;
		while (candidateIter.hasNext())  //check list next exists
		{

			currentCandidate = candidateIter.next().toString();
			state = getComponents(currentCandidate,0); //construct state
			party = getComponents(currentCandidate, 4); //construct party
			division = getComponents(currentCandidate, 2); //construct div
			surname = getComponents(currentCandidate, 6); //construct surname
			divisionId = Integer.parseInt(getComponents(currentCandidate, 1));
			firstName = getComponents(currentCandidate, 7); //construct f'name
			CandidateClass candidate = new CandidateClass(state,party,
														  division,divisionId,
														  surname, firstName);
			//Constructor creates candidate object
			candidateArray[i] = candidate; //pass object into array
			i++; //go to next free element in array
		}
		return candidateArray;
	}

	/**********************************
	 * REFERENCE: This method has been submitted and taken from the
	 * 			  sorts practical in Data Structures and Algorithms.
	 * 			  The code has been modified to suit the assignment specification
	 * 			  but the logic is the same.
	 * Purpose: This is the insertion sort algorithm. This sorts the objects
	 * 			based on their fields
	 * @param candidateArray
	 * @param orderOption
	 * @return candidateArray(sorted)
	 */
	public static CandidateClass[] insertionSort(
						CandidateClass[] candidateArray, int orderOption)
	{
		CandidateClass temp;
		int ii = 0;
		int nn;
		for (nn = 1; nn < candidateArray.length; nn++)
		{
			ii = nn;
			temp = candidateArray[ii];  //store temp
			switch (orderOption) //switch based on user choice
			{
				case 1:
					while ((ii > 0) &&
							(candidateArray[ii - 1].getState().compareTo
												 (temp.getState()) > 0))
					{
						candidateArray[ii] = candidateArray[ii - 1];
										//shuffle foward
						ii = ii - 1; //decrement ii
					}
					break;
				case 2:
					while ((ii > 0) &&
							(candidateArray[ii - 1].getParty().compareTo
												(temp.getParty()) > 0))
					{
						candidateArray[ii] = candidateArray[ii - 1];
						//shuffle foward
						ii = ii - 1; //decrement ii
					}
					break;
				case 3:
					while ((ii > 0)
							&& (candidateArray[ii - 1].getDivision().compareTo
							(temp.getDivision()) > 0))
					{
						candidateArray[ii] = candidateArray[ii - 1];
						//shuffle foward
						ii = ii - 1; //decrement ii
					}
					break;
				case 4:
					while ((ii > 0)
							&& (candidateArray[ii - 1].getSurname().compareTo
							(temp.getSurname()) > 0))
					{
						candidateArray[ii] = candidateArray[ii - 1];
						//shuffle foward
						ii = ii - 1;
						//decrement ii
					}
					break;
				case 5:
					//Sorts based on all states
					while ((ii > 0)
							&& (candidateArray[ii - 1].getState().compareTo
							(temp.getState()) > 0))
					{
						candidateArray[ii] = candidateArray[ii - 1];
						//shuffle foward
						ii = ii - 1;
						//decrement ii to go to next
					}
					while ((ii > 0)
							&& (candidateArray[ii - 1].getParty().compareTo
							(temp.getParty()) > 0))
					{
						candidateArray[ii] = candidateArray[ii - 1];
						//shuffle foward
						ii = ii - 1;
						//decrement ii to go to next
					}
					while ((ii > 0)
							&& (candidateArray[ii - 1].getDivision().compareTo
							(temp.getDivision()) > 0))
					{
						//shuffle foward
						candidateArray[ii] = candidateArray[ii - 1];
						ii = ii - 1;
						//decrement ii to go to next
					}
					while ((ii > 0)
							&& (candidateArray[ii - 1].getSurname().compareTo
							(temp.getSurname()) > 0))
					{
						candidateArray[ii] = candidateArray[ii - 1];
						//shuffle foward
						ii = ii - 1;
						//decrement ii to go to next
					}
			}
			candidateArray[ii] = temp; //add temp
		}
		return candidateArray;
	}

	/********************************
	 * Purpose: To filter the array and insert all elements to a
	 * 			linked list for writing to file
	 * @param filterOption
	 * @param sortedArray
	 * @return writeList
	 */
	public static DSALinkedList filterArray(int filterOption,
								   CandidateClass[] sortedArray)
	{
		int ii;
		String stateFilter, divisionFilter, partyFilter;
		DSALinkedList writeList = new DSALinkedList();
		switch (filterOption)
		{
			case 1:
				stateFilter = getStateFilter(); //get filter
				for(ii = 0; ii < sortedArray.length; ii++) //loop through array
				{
					if(sortedArray[ii].getState().equals(stateFilter))
					{
						//check if state contains filter
						System.out.println(sortedArray[ii].toString());
						writeList.insertLast(sortedArray[ii]);
						//print and add to list
					}
				}
				break;
			case 2:
				partyFilter = getPartyFilter(); //get filter
				for(ii = 0; ii < sortedArray.length; ii++) //loop through array
				{
					if(sortedArray[ii].getParty().equals(partyFilter))
					{
						//check if state contains filter
						System.out.println(sortedArray[ii].toString());
						writeList.insertLast(sortedArray[ii]);
						//print and add to list
					}
				}
				break;
			case 3:
				divisionFilter = getDivisionFilter(); // get filter
				for(ii = 0; ii < sortedArray.length; ii++) //loop through array
				{
					if(sortedArray[ii].getDivision().equals(divisionFilter))
					{
						System.out.println(sortedArray[ii].toString());
						writeList.insertLast(sortedArray[ii]);
						//print and add to list
					}
				}
				break;
			case 4:
				for(ii = 0; ii < sortedArray.length; ii++)
				{
					System.out.println(sortedArray[ii].toString());
					writeList.insertLast(sortedArray[ii]);
					//print and add to list
				}
				break;
		}
		return writeList;
	}

	/*********************************
	 * Purpose: To ask the user to enter a state filter
	 * @return stateFilter
	 */
	public static String getStateFilter()
	{
		String stateFilter;
		Scanner sc = new Scanner(System.in);
		System.out.println("Please enter a state to filter by");
		stateFilter = sc.nextLine();
		while(stateFilter.equals(" ")) //check for validity
		{
			System.out.println("Please enter a valid state");
			stateFilter = sc.nextLine(); //re-enter if invalid
		}
		return stateFilter;
	}

	/************************************
	 * Purpose: To get the user to enter the division to filter the data
	 * @return divisionFilter
	 */
	public static String getDivisionFilter()
	{
		String divisionFilter;
		Scanner sc = new Scanner(System.in);
		System.out.println("Please enter a division to filter by");
		divisionFilter = sc.nextLine(); //user enters division
		while(divisionFilter.equals(" ")) //check if invalid
		{
			System.out.println("Please enter a valid division");
			divisionFilter = sc.nextLine(); //re-enter filter
		}
		return divisionFilter;
	}

	/******************************
	 * Purpose: To get the user to enter a valid party
	 * @return
	 */
	public static String getPartyFilter()
	{
		String partyFilter;
		Scanner sc = new Scanner(System.in);
		System.out.println("Please enter a party to filter by");
		partyFilter = sc.nextLine(); //user enters party as a filter
		while(partyFilter.equals(" ")) //check if valid
		{
			System.out.println("Please enter a valid division");
			partyFilter = sc.nextLine(); //re enter
		}
		return partyFilter;
	}
}

