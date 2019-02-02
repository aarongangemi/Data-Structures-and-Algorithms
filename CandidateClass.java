/***********************************
 * Purpose: This is the candidate class. It constructs a candidate
 * 			Object and is used for the sorting algorithm in the
 * 			list nominees algorithm
 * Author: Aaron Gangemi
 * Date Modified: 27/10/2018
 */
public class CandidateClass
{
	private String state;  //classfields
	private String party;
	private String division;
	private int divisionId;
	private String surname;
	private String firstName;

	public CandidateClass(String inState, String inParty, String inDivision,
					 int inDivisionId, String inSurname, String inFirstName)
	{
		setState(inState);
		setParty(inParty);
		setDivision(inDivision);
		setDivisionId(inDivisionId);
		setSurname(inSurname);
		setFirstName(inFirstName);
		//Constructor calls
		//Setters for each
		//classfield
	}

	/***************************
	 * Purpose: State accessor
	 * @return state
	 */
	public String getState()
	{
		return state;
	}

	/**************************
	 * Purpose: State mutator
	 * @param inState
	 */
	public void setState(String inState)
	{
		state = inState;
	}

	/***************************
	 * Purpose: party accessor
	 * @return party
	 */
	public String getParty()
	{
		return party;
	}

	/***************************
	 * party mutator
	 * @param inParty
	 */
	public void setParty(String inParty)
	{
		party = inParty;
	}

	/*****************************
	 * Purpose: division accessor
	 * @return division
	 */
	public String getDivision()
	{
		return division;
	}

	/*****************************
	 * Purpose: division mutator
	 * @param inDivision
	 */
	public void setDivision(String inDivision)
	{
		division = inDivision;
	}

	/****************************
	 * Purpose: divisionID accessor
	 * @return divisionId
	 */
	public int getDivisionId()
	{
		return divisionId;
	}

	/****************************
	 * Purpose: divisionID mutator
	 * @param inDivisionId
	 */
	public void setDivisionId(int inDivisionId)
	{
		divisionId = inDivisionId;
	}

	/*******************************
	 * Purpose: surname accessor
	 * @return surname
	 */
	public String getSurname()
	{
		return surname;
	}

	/******************************
	 * surname mutator
	 * @param inSurname
	 */
	public void setSurname(String inSurname)
	{
		surname = inSurname;
	}

	/****************************
	 * Purpose: firstName accessor
	 * @return
	 */
	public String getFirstName()
	{
		return firstName;
	}

	/****************************
	 * Purpose: firstname mutator
	 * @param inFirstName
	 */
	public void setFirstName(String inFirstName)
	{
		firstName = inFirstName;
	}

	/***************************
	 * Purpose: Used to print the candidate to terminal
	 * @return string
	 */
	public String toString()
	{
		return "State: " + state + " || " + "Division: " + division + " || " +
				"Party: " + party + " || " + "Surname: " + surname + " || " +
				"Firstname: " + firstName;
	}
}