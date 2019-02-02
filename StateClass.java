/******************************
 * Purpose: This is the state class
 * Date modified: 11/08/2018
 * Author: Aaron Gangemi
 * ***************************/
import java.util.*;
import java.io.*;
public class StateClass implements Serializable
{
    private String name;
    private String country;
    private int area; //variable declarations
    private int population;
    private String sourceOfPopulation;
    private LocationsClass[] locations;
    private int locationsCount;

    public StateClass(String inName, String inCountry, int inArea,
                      int inPopulation, String inSourceOfPopulation,
                      LocationsClass[] inLocations, int inLocationsCount)
    {
      setName(inName); //alt constructor calls setters
      setCountry(inCountry);
      setArea(inArea);
      setPopulation(inPopulation);
      setSourceOfPopulation(inSourceOfPopulation);
      setLocations(inLocations);
      setLocationsCount(inLocationsCount);
    }

    public String getName() //accessors
    {
      return name;
    }

    public String getCountry()
    {
      return country;
    }

    public int getArea()
    {
      return area;
    }

    public int getPopulation()
    {
      return population;
    }

    public String getSourceOfPopulation()
    {
      return sourceOfPopulation;
    }

    public LocationsClass[] getLocations()
    {
      return locations;
    }

    public int getLocationsCount()
    {
      return locationsCount;
    }
//setters
    public void setName(String inName)
    {
      if(inName.equals(null))
      {
        throw new IllegalArgumentException("name cannot be null");
      }
      name = inName;
    }

    public void setCountry(String inCountry)
    {
      if(inCountry.equals(null))
      {
        throw new IllegalArgumentException("Country cannot be null");
      }
      country = inCountry;
    }

    public void setArea(int inArea)
    {
      if(inArea < 0)
      {
        throw new IllegalArgumentException("Area cannot be null");
      }
      area = inArea;
    }

    public void setPopulation(int inPopulation)
    {
      if(inPopulation <= 0)
      {
        throw new IllegalArgumentException("Population cannot be 0 or negative");
      }
      population = inPopulation;
    }

    public void setSourceOfPopulation(String inSourceOfPopulation)
    {
      if(inSourceOfPopulation.equals(null))
      {
        throw new IllegalArgumentException("Source of population cannot be null");
      }
      sourceOfPopulation = inSourceOfPopulation;
    }

    public void setLocations(LocationsClass[] inLocations)
    {
      locations = inLocations;
    }
    
    public void setLocationsCount(int inLocationCount)
    {
      if(inLocationCount < 0)
      {
        throw new IllegalArgumentException("Count cannot be negative");
      }
      locationsCount = inLocationCount;
    }
//call toString for display
    public String toString()
    {
      String stateString = ("Country: " + country);
      return stateString;
    }

    public void open(String fileName)
    {
      FileInputStream fileStrm = null;
      DataInputStream dataStrm;
      try
      {
        fileStrm = new FileInputStream(fileName);
        dataStrm = new DataInputStream(fileStrm);
        name = dataStrm.readUTF();  //read name
        country = dataStrm.readUTF();  //read country
        area = dataStrm.readInt();  //read area
        population = dataStrm.readInt();  //read population
        sourceOfPopulation = dataStrm.readUTF();  //read SOP
        fileStrm.close(); //close file
      }
      catch(IOException e)
      {
        if(fileStrm != null)
        {
          try
          {
          fileStrm.close();
          }
          catch(IOException ex2)
          {
          }
        }
      System.out.println("Error in file processing: " + e.getMessage());
      }
    }

    public void save(String fileName)
    {
      FileOutputStream fileStrm = null;
      DataOutputStream dataStrm;
      try
      {
        fileStrm = new FileOutputStream(fileName);
        dataStrm = new DataOutputStream(fileStrm);
        dataStrm.writeUTF(name);    //write name
        dataStrm.writeUTF(country);  //write country
        dataStrm.writeInt(area);  //write area
        dataStrm.writeInt(population);  //write population
        dataStrm.writeUTF(sourceOfPopulation);  //write SOP
        fileStrm.close();
      }
      catch(IOException e)
      {
        if(fileStrm != null)
        {   
          try
          {
            fileStrm.close();
          }
          catch(IOException ex2)
          {
          }
        }
      System.out.println("Error in file processing" + e.getMessage());
      }
    }

               
}
