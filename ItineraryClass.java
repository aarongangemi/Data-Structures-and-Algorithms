import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/************************************
 * Purpose: This is the itinerary class. It adds items to the itinerary and
 * 			displays the itinerary to the user.
 * Author: Aaron Gangemi (19447337)
 * Date Modified: 27/10/2018
 **********************************/
public class ItineraryClass
{
	/***********************************
	 * Purpose: To add the margin values to the itinerary
	 * @param itinerary
	 * @param writeOutList
	 * @param locationsList
	 * @param airportList
	 * @return writeList
	 */
	public static DSALinkedList addToItinerary(DSAGraph itinerary,
											   DSALinkedList writeOutList,
											   DSALinkedList locationsList,
											   DSALinkedList airportList)
	{
		DSALinkedList writeList = new DSALinkedList();
		Iterator marginIter = writeOutList.iterator();
		Iterator airportIter = airportList.iterator();
		DSALinkedList distanceList = new DSALinkedList();
		Iterator locationIter = locationsList.iterator();
		boolean found;
		String time, modeOfTransport;
		int intTime, minutes;
		Object currentNode, nextNode, currentLocation;
		String locationFrom, locationTo, stateNameFrom, stateNameTo,
				currentAirport, airportFrom, airportTo;
		currentNode = marginIter.next(); //go to first margin
		nextNode = marginIter.next(); //go to second margin
		locationFrom = getLocation(currentNode.toString()); //get locationFrom
		locationTo = getLocation(nextNode.toString()); //get locationTo
		stateNameFrom = getStateName(currentNode.toString()); //get stateFrom
		stateNameTo = getStateName(nextNode.toString()); //get stateTo
		Calendar now = Calendar.getInstance(); //create calendar instance
		Calendar temp = Calendar.getInstance(); //create second calendar instance
		while(locationIter.hasNext())  //Go through all locations
		{
			found = false;
			currentLocation = locationIter.next(); //get current location
			modeOfTransport = getModeOfTransport(currentLocation.toString());
			time = getTime(currentLocation.toString()); //get time between
			if(time.equals("NONE")) //if time is invalid, time = 0
			{
				time = "0";
			}
			intTime = Integer.parseInt(time); //convert to integer
			if(currentLocation.toString().contains(locationFrom)
					&& currentLocation.toString().contains(locationTo)
					&& stateNameTo.equals(stateNameFrom))
			//check location from is in String and
			// check location to is in string
			{
				temp.set(Calendar.MINUTE, now.get(Calendar.MINUTE));
				temp.set(Calendar.HOUR, now.get(Calendar.HOUR));//store previous time
				now.add(Calendar.MINUTE, intTime); //update date using new time
				now.add(Calendar.HOUR, 3); //account for 3 Hours
				// waiting time
				itinerary.addVertex(locationFrom, locationTo, intTime, now,
									temp, writeList, modeOfTransport);
				//Add vertex to graph including time which is used for toString(). List is
				//passed for writing purposes
				if(marginIter.hasNext())  //update margin details if another
				// margin exists
				{
					currentNode = marginIter.next();  //update currentNode
					locationFrom = locationTo;  //previous location is current
					locationTo = getLocation(currentNode.toString());
					//change dest
					stateNameFrom = stateNameTo;
					stateNameTo = getStateName(currentNode.toString());
					//update state source and destination
				}
				else
				{
					while(locationIter.hasNext()) //if margin is empty,
					// loop through the rest of the location list
					// and do nothing
					{
						Object y = locationIter.next();
					}
				}
			}
			else if(!stateNameFrom.equals(stateNameTo))
			//check state destinations are not the same for airport
			{
				airportIter = airportList.iterator(); //get airport
				while(airportIter.hasNext() && !found) //if airport exists
				{
					currentAirport = airportIter.next().toString();
					//get airport
					if(currentAirport.contains(stateNameFrom) &&
							currentAirport.contains(stateNameTo))
					{
						modeOfTransport = getModeOfTransport(
								locationIter.next().toString());
						airportFrom = getAirportNameFrom(currentAirport);
						airportTo = getAirportNameTo(currentAirport);
						//constructs the airport names for toString of graph
						minutes = getFlightTimeInMinutes(currentAirport);
						//get flight times in minutes
						temp.set(Calendar.MINUTE, now.get(Calendar.MINUTE));
						temp.set(Calendar.HOUR, now.get(Calendar.HOUR));//store previous time
						now.add(Calendar.MINUTE, minutes);
						//update current time for calendar
						itinerary.addVertex(airportTo, airportFrom,
											minutes, now, temp, writeList,
											"Plane");
						//add airport time to graph
						temp.set(Calendar.MINUTE, now.get(Calendar.MINUTE));
						temp.set(Calendar.HOUR, now.get(Calendar.HOUR));//store previous time
						now.add(Calendar.MINUTE, intTime);
						//add location after airport to itinerary
						itinerary.addVertex(locationFrom,locationTo, intTime,
											now,temp, writeList, modeOfTransport);
						if(marginIter.hasNext()) //check another margin exists
						{
							//update margin details
							currentNode = marginIter.next().toString();
							//go to next margin
							stateNameFrom = stateNameTo;
							stateNameTo = getStateName(currentNode.toString());
							//get state to and from
							locationFrom = locationTo;
							locationTo = getLocation(currentNode.toString());
							//get location to and from
						}
						else
						{
							//If margin list is empty, nothing else to do so
							// iterate through rest of locations list till
							// empty
							while(locationIter.hasNext())
							{
								Object x = locationIter.next();
							}
						}
						found = true; //set found to true so that list stops
						//if desired airport is found
					}
				}
			}
		}
		return writeList;
	}

	/*************************************
	 * Purpose: get the location from file
	 * @param line
	 * @return
	 */
	public static String getLocation(String line)
	{
		String[] lineArray = line.split(" " ); //split file on comma
		String divisionName = lineArray[3]; //get division name
		String[] divisionLine = divisionName.split(","); //split for location
		return divisionLine[0]; //return location
	}

	/****************************************
	 * Purpose: To get the state name from file
	 * @param line
	 * @return stateName
	 */
	public static String getStateName(String line)
	{
		String[] lineArray = line.split(" "); //split on white space
		String stateComponent = lineArray[1]; //get state line
		String[] stateArray = stateComponent.split(","); //split on comma
		String state = stateArray[0]; //get state name
		return state;
	}

	/*************************************
	 * Purpose: get the time from airport file
	 * @param line
	 * @return time
	 */
	public static String getTime(String line)
	{
		String[] lineArray = line.split(","); //split on comma
		String time = lineArray[9]; //return time
		return time;
	}

	/***********************************
	 * Purpose: to get airport source name from file
	 * @param line
	 * @return airportName
	 */
	public static String getAirportNameFrom(String line)
	{
		String[] lineArray = line.split(","); //split on comma
		return lineArray[1];
	}

	/**********************************
	 * Purpose: get airport destination
	 * @param line
	 * @return airport destination
	 */
	public static String getAirportNameTo(String line)
	{
		String[] lineArray = line.split(","); //split on comma
		return lineArray[5];
	}

	/************************************
	 * Purpose: get flight time in minutes
	 * @param line
	 * @return minutes
	 */
	public static int getFlightTimeInMinutes(String line)
	{

		String[] lineArray = line.split(","); //split on comma
		String time = lineArray[9]; //get time
		String[] timeArray = time.split(":"); //split on colon
		int hours = Integer.parseInt(timeArray[0]); //construct hours
		int minutes = Integer.parseInt(timeArray[1]); //constructs minutes
		minutes += (hours * 60); //get total minutes with hours
		return minutes;
	}

	/**********************************
	 * Purpose: to get the mode of transport from file
	 * @param line
	 * @return transport
	 */
	public static String getModeOfTransport(String line)
	{
		String[] lineArray = line.split(","); //split on comma
		return lineArray[10];
	}
}

