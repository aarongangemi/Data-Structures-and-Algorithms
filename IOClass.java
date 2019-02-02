/******************************
 * Purpose: This is the IOClass which reads in the data
 * Date modified: 11/08/2018
 * Author: Aaron Gangemi
 * ****************************/
import java.util.*;
import java.io.*;
public class IOClass
{
/******************************
 * Purpose: This submodule gets the number of lines for each count
 * IMPORT: fileName
 * EXPORT: countArray
 * **************************/
  public static int[] getNoLines(String fileName)
  {
    String geographicalName = "";
    FileInputStream fileStrm = null;
    InputStreamReader rdr;
    BufferedReader bufRdr;       //variable declarations
    int locationCount = 0;
    int stateCount = 0;
    int countryCount = 0;
    int numberOfLines = 0;
    int[] countArray = new int[3];
    try
    {
      fileStrm = new FileInputStream(fileName);
      rdr = new InputStreamReader(fileStrm);
      bufRdr = new BufferedReader(rdr);
      String line = bufRdr.readLine();  //read in first line
      while(line != null)   //check continuosly if no line 
      {
        geographicalName = processLines(line);  //gets "STATE" OR "LOCATION
                                                //OR "COUNTRY"
        if(geographicalName.equals("LOCATION"))
        {
          locationCount++;     //increment location count
        }
        else if(geographicalName.equals("STATE"))
        {
          stateCount++; //increment statecount
        }
        else if(geographicalName.equals("COUNTRY"))
        {
          countryCount++;  //increment countrycount
        }
           
        line = bufRdr.readLine(); //read next line in
      }
      countArray[0] = locationCount; //store 3 counts in array
      countArray[1] = stateCount;
      countArray[2] = countryCount; 
      fileStrm.close();
    }
    catch(IOException e)
    {
      if(fileStrm!= null)
      {
        try
        {
          fileStrm.close();
        }
        catch(IOException e2)
        {
          System.out.println("Error in file processing");
        }
      }
    }

    return countArray;
  }
/********************************
 * Purpose: To read in the locations 
 * Import: fileName, countArray
 * Export: locations
 * ****************************/

  public static LocationsClass[] readLocations(String fileName, int[] countArray)
  {
    FileInputStream fileStrm = null;
    InputStreamReader rdr;
    BufferedReader bufRdr;
    LocationsClass[] locationArray = new LocationsClass[countArray[0]];
    int i = 0;
    int j = 0;
    String[] geographicalArray = new String[countArray[0] + countArray[1] + 
                                            countArray[2]];
    String locationName = "";
    String stateName = "";
    String position = "";
    String description = "";   
    //Variable declarations
    try
    {
      fileStrm = new FileInputStream(fileName);
      rdr = new InputStreamReader(fileStrm);
      bufRdr = new BufferedReader(rdr);
      String line = bufRdr.readLine();    //read first line
      while(line != null)     //check if line is null
      {
        geographicalArray[i] = processLines(line);//check if text is "LOCATION"
        if(geographicalArray[i].equals("LOCATION"))
        {
          locationName = processComponents(line, 1); //sort location components
          stateName = processComponents(line, 2);
          position = processComponents(line, 4);
          description = processComponents(line, 5);
          LocationsClass location = new LocationsClass
          (locationName, stateName, position, description);
          locationArray[i] = location;   //store location object in array
          i++;   //increment count
        }
        line = bufRdr.readLine();  //read next line
      }
      fileStrm.close(); //close stream
    }
    catch(IOException e)  //catch IOException
    {
      System.out.println("Error in file processing");
    }
    return locationArray;  //export array for use
  }
/**************************************
 * Purpose: To read in the state components into object
 * Import: locationArray, countArray, fileName
 * Export: StateArray
 * ************************************/
  public static StateClass[] readStates(LocationsClass[] locationArray, int[] countArray, String fileName)
  {
    FileInputStream fileStrm = null;   
    InputStreamReader rdr;
    BufferedReader bufRdr;
    StateClass[] statesArray = new StateClass[countArray[1]];
    String stateName = "";
    String country = "";
    int area = 1;
    int population = 1;
    String sourceOfPopulation = "";
    String[] labelArray = new String[countArray[0] + countArray[1] 
                                     + countArray[2]];
    int i = 0;
    //variable declarations
    try
    {
      fileStrm = new FileInputStream(fileName);
      rdr = new InputStreamReader(fileStrm);
      bufRdr = new BufferedReader(rdr);
      String line = bufRdr.readLine();   //read in first line
      while(line !=null)
      {
        labelArray[i] = processLines(line); //check if "STATE"
        if(labelArray[i].equals("STATE")) 
        {
          stateName = processComponents(line, 1);  //process STATE components
          country = processComponents(line, 2);
          area = Integer.parseInt(processComponents(line, 4));
          population = Integer.parseInt(processComponents(line, 6));
          sourceOfPopulation = processComponents(line, 7);
          StateClass state = new StateClass(stateName, country, area,
          population, sourceOfPopulation, locationArray, countArray[0]);
          statesArray[i] = state;
          i++;
        }
        line = bufRdr.readLine(); //read next line
      }
      fileStrm.close();   //close fileStream
    }
    catch(IOException e)  //catch IOException
    {
      System.out.println("Error in file processing");
    }
    return statesArray;
  }  
/*****************************
 * Purpose: to construct an IO object with sorted components
 * Import: countArray, fileName, states
 * Export: countryArray
 * *************************/
  public static CountryClass[] readCountry(int[] countArray, String fileName, 
                                           StateClass[] states)
  {
    //variable declarations
    FileInputStream fileStrm = null;
    InputStreamReader rdr;
    BufferedReader bufRdr;         
    CountryClass[] countryArray = new CountryClass[countArray[2]];   
    String[] labelArray = new String[countArray[0] + countArray[1] + countArray[2]];     
    String name = "";
    String officialLanguage = "";
    int area = 1;
    int population;
    int i = 0;
    String sourceOfPopulation = "";
    try
    {   
      fileStrm = new FileInputStream(fileName);
      rdr = new InputStreamReader(fileStrm);
      bufRdr = new BufferedReader(rdr);
      String line = bufRdr.readLine();    //read first line
      while(line != null)     //run until no more lines
      {
        labelArray[i] = processLines(line);
        if(labelArray[i].equals("COUNTRY")) //check if "COUNTRY"
        {
          name = processComponents(line, 1);  //process Country components
          officialLanguage = processComponents(line, 3);
          area = Integer.parseInt(processComponents(line, 4));
          population = Integer.parseInt(processComponents(line, 5));
          sourceOfPopulation = processComponents(line, 6);
          CountryClass country = new CountryClass(name, officialLanguage, area,
                                               population, sourceOfPopulation,
                                               states, countryArray, countArray[1]);
          //construct country object
          countryArray[i] = country; //pass counry objects into array
          i++;  //increment count
        }
        line = bufRdr.readLine();  //read next line
      }
      fileStrm.close();  //close fileStrm
    }
    catch(IOException e)    //catch IOExcpetion
    {
      System.out.println("Error in file processing");
    }
    return countryArray;
  }
/*******************************
 * Purpose: is to determine the label of either "STATE, LOCATION OR COUNTRY"
 * IMPORT: line
 * Export: geographicalName
 * ****************************/
  public static String processLines(String line)
  {
    String[] lineArray = line.split(":");    //split at :
    String geographicalName = lineArray[0];  // store name
    return geographicalName;    //export name
  }
/********************************************
 * Purpose: to sort components of each category
 * Import: line, i
 * export: componentX
 * *****************************************/

  public static String processComponents(String line, int i)
  {
    String[] lineArray = line.split(":");   //Split at :
    String component = lineArray[i];    //store variables with "="
    String[] componentArray = component.split("=");   //get rid of "="
    String componentX = componentArray[1];   //store value required
    return componentX;     //export value for sorting
  }
/****************************************
 * Purpose: is to save the data into a file into serialized form
 * Import: locations, fileName
 * Export: NONE
 * *************************************/
  public static void save(LocationsClass[] locations, StateClass[] states, 
                          CountryClass[] countries, String fileName)
  {
    //variable declarations
    int i = 0;
    FileOutputStream fileStrm;
    ObjectOutputStream objStrm;
    try
    {
      fileStrm = new FileOutputStream(fileName);
      objStrm = new ObjectOutputStream(fileStrm);
      for(i = 0; i < locations.length; i++)
      {
        objStrm.writeObject(locations[i]);  //write all values to file
      }
      for(i = 0; i < states.length; i++)
      {
        objStrm.writeObject(states[i]);
      }
      for(i = 0; i < countries.length; i++)
      {
        objStrm.writeObject(countries[i]);
      }
      objStrm.close(); //close object stream
    }
    catch(IllegalArgumentException e)    //catch exceptions
    {
      System.out.println("Unable to save object to file");
    }
    catch(IOException e)
    {
      System.out.println("Unable to save object to file");
    }
  }
/******************************************
 * Purpose: is to load the data from serialized form
 * Import: fileName
 * Export: inObj
 * *******************************************/
  public static LocationsClass loadLocations(String fileName)
  {
    //variable declarations
    FileInputStream fileStrm;
    ObjectInputStream objStrm;
    LocationsClass inObj = null;
    try
    {
      fileStrm = new FileInputStream(fileName);
      objStrm = new ObjectInputStream(fileStrm);
      inObj = (LocationsClass)objStrm.readObject();  //read obj
      objStrm.close();    //close fileStream
    }
    catch(ClassNotFoundException e)  //catch exceptions
    {
      System.out.println("LocationsClass not found" + e.getMessage());
    }
    catch(Exception e)
    {
      System.out.println("Unable to load object from file");
    }
    return inObj;
  }
/******************************************
 * Purpose: is to load state data into serialized form
 * IMPORT: fileName
 * EXPORT: inObj
 * *************************************/
  public static StateClass loadStates(String fileName)
  {
    FileInputStream fileStrm;
    ObjectInputStream objStrm;
    StateClass inObj = null;
    try
    {
      fileStrm = new FileInputStream(fileName);
      objStrm = new ObjectInputStream(fileStrm);
      inObj = (StateClass)objStrm.readObject();
      objStrm.close();
    }
    catch(ClassNotFoundException e)
    {
      System.out.println("StateClass not found" + e.getMessage());
    }
    catch(Exception e)
    {
      System.out.println("Unable to load object from file");
    }
    return inObj;
  }
/*********************************************
 * Purpose: is to load country data into serialized form
 * IMPORT: fileName
 * EXPORT: inObj
 ********************************************/
  public static CountryClass loadCountry(String fileName)
  {
    FileInputStream fileStrm;
    ObjectInputStream objStrm;
    CountryClass inObj = null;
    try
    {
      fileStrm = new FileInputStream(fileName);
      objStrm = new ObjectInputStream(fileStrm);
      inObj = (CountryClass)objStrm.readObject();
      objStrm.close();
    }
    catch(ClassNotFoundException e)
    {
      System.out.println("CountryClass not found" + e.getMessage());
    }
    catch(Exception e)
    {
      System.out.println("Unable to load object from file");
    }
    return inObj;
  }
}

