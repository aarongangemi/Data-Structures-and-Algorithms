/*********************************
 * Purpose: Towers of hanoi problem
 * Author: Aaron Gangemi
 * Date modified: 30/08/2018
 **********************************/

import java.util.*;
public class TowersOfHanoi
{
	public static void main(String[] args)
	{
		int nDisks = 3;
		towers(nDisks,'A', 'B','C');      //call towers
	}

	/*************************************
	 * Purpose: Calculate towers moves
	 * @param n
	 * @param src
	 * @param dest
	 * @return none
	 **************************************/
	public static void towers(int top, char n, char src, char dest)
	{
		if(top == 1)     //check if n = 1
		{
			moveDisk(n, dest);
		}
		else    //if n not 1
		{
			towers(top -1, n, dest, src);
			System.out.println("Disk " + top + " from " + n + " to " + dest);
			towers(top-1, src, n, dest);
		}
	}

	public static void moveDisk(char n, char dest)
	{
		System.out.println("Disk 1 from " + n + " to " + dest);
	}

}
