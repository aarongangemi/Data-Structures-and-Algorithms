/*********************************
 * Purpose: This is the locations class
 * Date modified: 11/08/2018
 * Author: Aaron Gangemi
 * *****************************/

import java.util.*;
import java.io.*;
public class LocationsClass implements Serializable
{
    private String name;   //variable declarations
    private String state;
    private String position;
    private String description;

    public LocationsClass(String inName, String inState, String inPosition, String inDescription)
    {
      setName(inName); //alt constructor calls setters
      setState(inState);
      setPosition(inPosition);
      setDescription(inDescription);
    }

    public String getname() //accessors
    {
      return name;
    }
   
    public String getState()
    {
      return state;
    }

    public String getPosition()
    {
      return position;
    }

    public String getDescription()
    {
      return description;   
    }

    public void setName(String inName) //setters
    {
      if(inName.equals(null))
      {
        throw new IllegalArgumentException("name cannot be null");
      }
      name = inName;
    }

    public void setState(String inState)
    {
      if(inState.equals(null))
      {
        throw new IllegalArgumentException("State cannot be null");
      }
      state = inState;
    }
    public void setPosition(String inPosition)
    {
      if(inPosition.equals(null))
      {
        throw new IllegalArgumentException("position cannot be null");
      }
      position = inPosition;
    }

    public void setDescription(String inDescription)
    {
      if(inDescription.equals(null))
      {
        throw new IllegalArgumentException("Description cannot be null");
      }
      description = inDescription;
    }

    public String toString()        //toString
    {
      String LocationString = ("State:"  + state + "," + "Location:" + name
                               + "," + "Position" + position + "," + "Description:" 
                               + description);
      return LocationString;
    }
    
    public void open(String fileName)    //opens file and reads data
    {
      FileInputStream fileStrm = null;
      DataInputStream dataStrm;
      try
      {
        fileStrm = new FileInputStream(fileName);
        dataStrm = new DataInputStream(fileStrm);
        name = dataStrm.readUTF(); //read name
        state = dataStrm.readUTF(); //read state
        position = dataStrm.readUTF();  //read position
        description = dataStrm.readUTF(); //read description
        fileStrm.close(); //close fileStrm
      } 
      catch(IOException e) //catch IOExceptions
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
        dataStrm.writeUTF(name); //writes data as sorted
        dataStrm.writeUTF(state);
        dataStrm.writeUTF(position);
        dataStrm.writeUTF(description);
        fileStrm.close();
      }
      catch(IOException e)   //catch exceptions
      {
        if(fileStrm !=null)
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
