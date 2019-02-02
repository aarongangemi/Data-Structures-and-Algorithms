import java.util.*;
public class StateTestHarness
{
    public static void main(String[] args)
    {
      int numPassed = 0;
      int numTests = 0;
      String name;
      String country;
      int area;
      int population;
      String sourceOfPopulation;
      int locationsCount = 0;
      LocationsClass[] locations = new LocationsClass[locationsCount];
      StateClass state = new StateClass("A", "B", 20000, 200000, "D", locations,12);
      //Testing under normal conditions
      System.out.println("\n");
      System.out.println("Testing under normal conditions");
      System.out.println("===============================");
      try
      {
        numTests++;
        System.out.println("Testing creation of state");
        name = new String("John");
        country = new String("Australia");
        area = 200;
        population = 200;
        sourceOfPopulation = new String("Unknown");
        locationsCount = 1;
        locations = new LocationsClass[locationsCount];
        StateClass states = new StateClass(name, country, area, population, sourceOfPopulation, 
                                           locations, locationsCount);
        numPassed++;
        System.out.println("Passed");
      }
      catch(IllegalArgumentException e)
      {
        System.out.println("Illegal Argument");
      }
      catch(Exception e)
      {
        System.out.println("Failed");
      }
      
      //testing toString for StateClass
      try
      {
        System.out.println("\n");
        System.out.println(state.toString());
        System.out.println("Passed");
      }
      catch(Exception e)
      {
        System.out.println("Failed");
      }
      //Testing for error conditions - negative count
      try
      {
        name = new String("Aaron");
        country = new String("Australia");
        area = 20202020;
        population = 20000;
        sourceOfPopulation = new String("HEY");
        locationsCount = -1;
        locations = new LocationsClass[locationsCount];
        StateClass states = new StateClass(name, country, area, population, sourceOfPopulation, 
                                           locations, locationsCount);
        System.out.println("Failed");
      }
      catch(IllegalArgumentException e)
      {
        numTests++;
        System.out.println("Passed");
        numPassed++;
      }
      catch(Exception e)
      {
        numTests++;
        System.out.println("Passed");
        numPassed++;
      }

      //testing if string is null
      try
      {
        name = new String("Aaron");
        country = null;
        area = -1;
        population = 222;
        sourceOfPopulation = new String("Cool");
        locationsCount = 100;
        locations = new LocationsClass[locationsCount];
        StateClass states = new StateClass(name, country, area, population, sourceOfPopulation,
                                           locations, locationsCount);
        System.out.println("Failed");
        numTests++;
      }
      catch(IllegalArgumentException e)
      {
        numTests++;
        System.out.println("Passed");
        numPassed++;
      }
      catch(Exception e)
      {
        numTests++;
        System.out.println("Passed");
        numPassed++;
      }
    }
}

