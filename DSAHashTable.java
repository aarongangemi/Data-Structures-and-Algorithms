import java.io.Serializable;
import java.util.*;

/**********************************
 * Purpose: Create and modify the DSA Hash Table
 * Author: Aaron Gangemi
 * Date Modified: 23/10/2018
 */
public class DSAHashTable implements Serializable
{
	private DSAHashEntry[] hashTable;
	private DSAHashEntry deletedItem;  //fields
	//constructor - creates table
	public DSAHashTable(int arraySize)
	{
		hashTable = new DSAHashEntry[arraySize];
		deletedItem = new DSAHashEntry(" ", " ");
	}

	/*******************************
	 * Purpose: To display the hash table
	 */
	public void displayHashTable()
	{
		System.out.println("Table: ");
		for(int i = 0; i < hashTable.length; i++)
		{
			//loop through hash table
			if(hashTable[i] != null)
			{
				System.out.println(hashTable[i].getKey() + "," +
										   hashTable[i].getValue());
			}
			else
			{
				System.out.println("** ");
			}
		}
		System.out.println(" ");
	}

	/********************************
	 * Purpose: get hashed key using ASCII
	 * @param key
	 * @return key
	 ****************************/
	public int get(String key)
	{
		return 5 - Integer.parseInt(key) % 5;
	}

	/**********************************
	 * Purpose: To hash the current value
	 * @param key
	 * @return hashValue
	 */
	public int hash(String key)
	{
		int hashIdx = 0;
		for(int ii = 0; ii < key.length(); ii++)
		{
			hashIdx = hashIdx + key.charAt(ii); //loop through
			//change to ASCII
		}
		return hashIdx % hashTable.length;
	}

	/********************************
	 * Purpose: add item to hash table
	 * @param item
	 */
	public void put(DSAHashEntry item)
	{
		String key = item.getKey();
		int hashVal = hash(key); //uses double hashing
		int stepSize = get(key);
		while(hashTable[hashVal] != null && !hashTable[hashVal].getKey().equals(" "))
		{
			hashVal += stepSize; //add step size
			hashVal = hashVal % hashTable.length; //mod length
		}
		hashTable[hashVal] = item; //add item
	}

	/******************************
	 * Purpose: to remove an item from the hash table
	 * @param key
	 * @return temp
	 */
	public DSAHashEntry remove(String key)
	{
		//hash key first
		int hashVal = hash(key);
		//step size with key
		int stepSize = get(key);
		DSAHashEntry temp = null;
		//check exists
		while(hashTable[hashVal] != null)
		{
			//check key is same
			if(hashTable[hashVal].getKey().equals(key))
			{
				temp = hashTable[hashVal];
				//check for deleted item
				hashTable[hashVal] = deletedItem;
			}
			//add hash val
			hashVal += stepSize;
			//mod hashval
			hashVal = hashVal % hashTable.length;
		}
		return temp;
	}

	/*******************************
	 * Purpose: to check if the hash table contains the key
	 * @param key
	 * @return boolValue
	 */
	public boolean containsKey(String key)
	{
		int hashVal = hash(key);
		DSAHashEntry item = null;
		boolean foundKey = false;
		while (hashTable[hashVal] != null && !foundKey)
		{
			//loop through and if key is found
			if(hashTable[hashVal].getKey().equals(key))
			{
				foundKey = true;
			}
			++hashVal; //increment hash table index
			hashVal = hashVal % hashTable.length;
		}
		return foundKey;
	}
}

