/********************************
 * Purpose: Country class used for prac1
 * Date modified: 11/08/2018
 * Author: Aaron Gangemi
 * *****************************/

import java.util.*;
import java.io.*;
public class CountryClass implements Serializable
{
    private String name;                 //variable declaration
    private String officialLanguage;
    private int area;
    private int population;
    private String sourceOfPopulation;
    private StateClass[] states;
    private CountryClass[] countries;
    private int stateCount;

    public CountryClass(String inName, String inOfficialLanguage, int inArea,
                        int inPopulation, String inSourceOfPopulation, 
                        StateClass[] inStates, CountryClass[] inCountries,
                         int inStateCount)
    {
      setName(inName);              //alternate constructor calls setters
      setOfficialLanguage(inOfficialLanguage);
      setArea(inArea);
      setPopulation(inPopulation);
      setSourceOfPopulation(inSourceOfPopulation);
      setStates(inStates);
      setCountries(inCountries);
      setStateCount(inStateCount);
    }
  
    public String getName()       //country class accessors
    {
      return name;
    }
 
    public String getOfficialLanguage()
    {
      return officialLanguage;
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

    public StateClass[] getStates()
    {
      return states;
    }

    public CountryClass[] getCountries()
    {
      return countries;
    }
    public int getStateCount()
    {
      return stateCount;
    }

    public void setName(String inName)            //country class setters
    {
      if(inName.equals(null))
      {
        throw new IllegalArgumentException("invalid name, name cannot be null");
      }
      name = inName;
    }

    public void setOfficialLanguage(String inOfficialLanguage)
    {
      if(inOfficialLanguage.equals(null))
      {
        throw new IllegalArgumentException("Official language cannot be null");
      }
      officialLanguage = inOfficialLanguage;
    }
    
    public void setArea(int inArea)
    {
      if(inArea < 0)
      {
        throw new IllegalArgumentException("area cannot be negative");
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

    public void setStates(StateClass[] inStates)
    {
      states = inStates;
    }

    public void setCountries(CountryClass[] inCountries)
    {
      countries = inCountries;
    }

    public void setStateCount(int inStateCount)
    {
      if(inStateCount < 0)
      {
        throw new IllegalArgumentException("array count cannot be negative");
      }
      stateCount = inStateCount;
    }
    public String toString()       //toString used for printing all
    {
      String countryString = ("Country: " + name);
      return countryString;
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
        officialLanguage = dataStrm.readUTF();  //read OfficialLanguage
        area = dataStrm.readInt();  //read area
        population = dataStrm.readInt();  //read population
        sourceOfPopulation = dataStrm.readUTF();  //read SourceOfPop
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
          catch(IOException ex3)
          {
          }
        }
        System.out.println("Error in file processing" + e.getMessage());
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
        dataStrm.writeUTF(name);  //read name
        dataStrm.writeUTF(officialLanguage);  //read officialLang
        dataStrm.writeInt(area); //read area
        dataStrm.writeInt(population);  //read population
        dataStrm.writeUTF(sourceOfPopulation);  //read SOP
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
