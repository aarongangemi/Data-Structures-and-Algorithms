import java.io.Serializable;

/********************************
 * Purpose: is to construct a DSAHashentry
 * Author: Aaron Gangemi
 * Date Modified: 23/10/18
 **********************************/
public class DSAHashEntry implements Serializable
{
	private String key;
	private Object value; //fields


	public DSAHashEntry(String ii, Object valueX)
	{
		key = ii;   //constructor
		value = valueX;
	}

	public String getKey()
	{
		return key;    //key accessor
	}

	public Object getValue()
	{
		return value;   //value accessor
	}
}