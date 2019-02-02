import java.util.*;
public class UnitTestCandidate
{
	/****************************************
	 * Purpose: To test whether the candidate class works
	 * Author: Aaron Gangemi (19447337)
	 * Date Modified: 27/10/2018
	 */
	public static void testCandidateClass()
	{
		int countPassed = 0;
		System.out.println("Testing candidate class");
		System.out.println(".......................");
		String state = "WA";
		String party = "ALP";
		String division = "DivisionX";
		int divisionId = 101;
		String surname = "Jones";
		String firstname = "Sam";    //DEFAULT VALUES
		CandidateClass newCandidate = new CandidateClass(state, party,
														 division, divisionId,
														 surname, firstname);
		//IF FIELDS MATCH THEN CLASS TEST PASSED
		if(newCandidate.getState().equals(state))
		{
			System.out.println("STATE PASSED");
			countPassed++;
		}
		if(newCandidate.getParty().equals(party))
		{
			System.out.println("PARTY PASSED");
			countPassed++;
		}
		if(newCandidate.getDivision().equals(division))
		{
			System.out.println("DIVISION PASSED");
			countPassed++;
		}
		if(newCandidate.getDivisionId() == divisionId)
		{
			System.out.println("DIVISION ID PASSED");
			countPassed++;
		}
		if(newCandidate.getSurname().equals(surname))
		{
			System.out.println("SURNAME PASSED");
			countPassed++;
		}
		if(newCandidate.getFirstName().equals(firstname))
		{
			System.out.println("FIRSTNAME PASSED");
			countPassed++;
		}

		if(countPassed == 6)
		{
			System.out.println("ALL TESTS PASSED");
		}
	}
}
