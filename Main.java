import java.util.*;
import java.io.*;

public class Main implements Serializable
{
    public static void main(String[] args)
	{
		int menuOption = displayMenu();
		String key;
		DSAHashTable hashTable = null;
		int noOfLines;
		String keyToDelete, keyToFind, fileName;
		while(menuOption >= 1 && menuOption <= 6)
		{
			switch (menuOption)
			{
				case 1:
					hashTable.displayHashTable();    //display table
					menuOption = displayMenu();
					break;
				case 2:
					noOfLines = IOClass.getNoOfLines();  //get lines for length
					hashTable = new DSAHashTable(noOfLines); //create table
					hashTable = IOClass.readIntoHashTable(hashTable); //read table
					menuOption = displayMenu();
					break;
				case 3:
					keyToDelete = enterKey(); //user enters key
					if (hashTable.containsKey(keyToDelete)) //check key exists
					{
						hashTable.remove(keyToDelete);
					}
					menuOption = displayMenu();
					break;
				case 4:
					keyToFind = enterFindKey();  //user enters key
					if (hashTable.containsKey(keyToFind)) //check exists
					{
						System.out.println(keyToFind);
					}
					menuOption = displayMenu();
					break;
				case 5:
					fileName = getWriteFileName();
					save(hashTable, fileName);
					menuOption = displayMenu(); //save to file
					break;
				case 6:
					fileName = getReadFileName(); //load file
					hashTable = load(fileName);
					menuOption = displayMenu();
					break;
			}
		}
    }

	/*********************************
	 * Purpose: display menu to user
	 * @return menuOption
	 */
	public static int displayMenu()
	{
		Scanner sc = new Scanner(System.in);
		int menuOption;
		System.out.println("Please enter a menu option");
		System.out.println("1. Display hash table");
		System.out.println("2. Read in RandomNames7000.csv");
		System.out.println("3. Enter key value to delete");
		System.out.println("4. Enter key value to find");
		System.out.println("5. Save to file");
		System.out.println("6. Load file");
		menuOption = sc.nextInt();
		return menuOption;
	}

	/*****************************
	 * Purpose: user enters key to delete
	 * @return
	 */
	public static String enterKey()
	{
		String keyValue;
		Scanner sc = new Scanner(System.in);
		System.out.println("Please enter a key to delete");
		keyValue = sc.nextLine(); //user enters key
		return keyValue;
	}

	/********************************
	 * User enters key to find
	 * @return keyValue
	 */
	public static String enterFindKey()
	{
		String keyValue;
		Scanner sc = new Scanner(System.in);
		System.out.println("Please enter a key to find");
		keyValue = sc.nextLine();
		return keyValue;
	}

	/*******************************
	 * Purpose: To get the filename to write to
	 * @return filename
	 */
	public static String getWriteFileName()
	{
		Scanner sc = new Scanner(System.in);
		System.out.println("Please enter a filename to write to");
		String fileName = sc.nextLine();
		return fileName;
	}

	/******************************
	 * Purpose: get filename to read to
	 * @return filename
	 */
	public static String getReadFileName()
	{
		Scanner sc = new Scanner(System.in);
		System.out.println("Please enter a filename to read in");
		String fileName = sc.nextLine();
		return fileName;
	}

	/****************************
	 * Purpose: save to file
	 * @param objToSave
	 * @param fileName
	 */
	private static void save(DSAHashTable objToSave, String fileName)
	{
		FileOutputStream fileStrm;
		ObjectOutputStream objStrm;
		try
		{
			fileStrm = new FileOutputStream(fileName);
			objStrm = new ObjectOutputStream(fileStrm);
			objStrm.writeObject(objToSave);  //save table to file
			objStrm.close(); //close file
		}
		catch(IOException e)
		{
			throw new IllegalArgumentException("Unable to save object to file");
		}
	}

	/******************************
	 * Purpose: load serialized file
	 * @param fileName
	 * @return
	 */
	private static DSAHashTable load(String fileName)
	{
		FileInputStream fileStrm;
		ObjectInputStream objStrm;
		DSAHashTable inObj = null;
		try
		{
			fileStrm = new FileInputStream(fileName);
			objStrm = new ObjectInputStream(fileStrm);
			inObj = (DSAHashTable) objStrm.readObject(); //load hash table
			objStrm.close(); //close file
		}
		catch (ClassNotFoundException e)
		{
			System.out.println("Class Binary Search Tree not found" + e.getMessage());
		}
		catch (Exception e)
		{
			throw new IllegalArgumentException("Unable to load object from file");
		}
		return inObj;
	}

}
