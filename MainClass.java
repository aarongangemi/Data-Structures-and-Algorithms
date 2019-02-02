/***********************************
 * Purpose: This is the main class
 * Date modified: 11/08/2018
 * Author: Aaron Gangemi
 * *******************************/
import java.util.*;
import java.io.*;
public class MainClass
{
    public static void main(String[] args)
    {
      String fileName = "";     //variable declarations
      int menuOption = menu();
      int[] numberOfLines = new int[100];
      StateClass[] states = new StateClass[100];
      LocationsClass[] locations = new LocationsClass[200];
      CountryClass[] countries = new CountryClass[200];
      while(menuOption != 5)    //exit if user selects 5 
      {
        switch(menuOption)
        {
          case 1:
            fileName = validateFileName(); //get fileName
            numberOfLines = IOClass.getNoLines(fileName); //getNoOFLines
            locations = IOClass.readLocations(fileName, numberOfLines); 
            states = IOClass.readStates(locations, numberOfLines, fileName);
            countries = IOClass.readCountry(numberOfLines, 
                                                     fileName, states);
            menuOption = menu();
          case 2:
            fileName = validateFileName();
            IOClass.loadLocations(fileName);
            IOClass.loadStates(fileName);
            IOClass.loadCountry(fileName);
            menuOption = menu();
          case 3:
            displayAll(numberOfLines, states, locations, countries);
          case 4:
            IOClass.save(locations, states, countries, fileName);
            menuOption = menu();
          case 5:
      
        }   
      }
    }
/************************************
 * Purpose: This submodule displays the menu to the user
 * Import: NONE
 * Export: menuOption
 * ******************************/

    public static int menu()
    { 
      int menuOption = 0;
      boolean isValid = false;
      while(!isValid)
      {
        try
        {  
          Scanner sc = new Scanner(System.in);
          System.out.println("Please enter a valid integer from the menu below");
          System.out.println("1. Read in file");
          System.out.println("2. Read serialized file");
          System.out.println("3. Display data");
          System.out.println("4. Save serialized");
          System.out.println("5.Exit");
          menuOption = sc.nextInt();
          isValid = true;
          if(menuOption < 1 || menuOption > 5)  //determine if menu is valid
          {
            isValid = false;
          }
        }
        
        catch(InputMismatchException e)   //catch invalid data types
        {
          System.out.println("Please enter a valid integer betweem 1 and 3");
          System.out.println("\n");
        }
      }
      return menuOption;
    }
/***********************************
 * Purpose: This submodule is used to get a valid filename
 * Import: NONE
 * Export: fileName
 * *******************************/
    public static String validateFileName()
    {
      boolean isValid = false;
      String fileName = "";
      Scanner sc = new Scanner(System.in);
      while(!isValid)     //ensure does not crash
      {
        try
        {
          System.out.println("Please enter a valid filename");
          fileName = sc.nextLine();   //user inputs filename
          isValid = true;
        }
        catch(IllegalArgumentException e)   //catch exceptions
        {
          System.out.println("Please input a valid filename");
        }
        catch(InputMismatchException e)
        {
          System.out.println("Please enter a valid filename");
        }
      }
      return fileName;
    }
/*********************************
 * Purpose: To display all data by calling on toStrings
 * Import: numberOfLines, states, locations, countries
 * Export: NONE
 * *****************************/
    public static void displayAll(int[] numberOfLines, StateClass[] states, LocationsClass[] locations, CountryClass[] countries)
    {
      int i = 0;
      int j = 0;
      for(i = 0; i < numberOfLines[2]; i++)
      {
        System.out.println(countries[i]);  //calls on the countries toString
      }
      for(j = 0; j < numberOfLines[0]; j++)
      {
        System.out.println(locations[j]);  //display states and locations
      }
    }
} 


