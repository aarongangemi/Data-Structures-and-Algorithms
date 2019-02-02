/*****************************************
 * Purpose: to solve infix equations by converting to postfix
 * Author: Aaron Gangemi
 * Date modified: 30/08/2018
 */

import java.io.*;
import java.util.*;
public class EquationSolver
{
	public static void main(String[] args)
	{
		String equation = ParseNextTerm();      //read in equation
		solve(equation);    //solve equation - see below
	}

	/************************************
	 * Purpose: to call the method that solves the equation
	 * @param equation
	 * Export: NOTHING
	 ***********************************/
	public static void solve(String equation)
	{
		DSAQueue1 queue;
		queue = parseInfixToPostfix(equation);
		System.out.println("The answer is : "+ evaluatePostFix(queue));
	}

	/************************************
	 * Purpose: reads in equation
	 * @param: nothing
	 * Export: equation
	 **********************************/
	public static String ParseNextTerm()
	{
        FileInputStream fileStrm = null;
        InputStreamReader rdr;
        BufferedReader bufRdr;
		String equation = "";
		try
		{
			fileStrm = new FileInputStream("test.csv");
			rdr = new InputStreamReader(fileStrm);
			bufRdr = new BufferedReader(rdr);
			equation = bufRdr.readLine();      //read in file
		}
		catch(IOException e)
		{
			System.out.println("Unable to read equation");
		}
		return equation;
	}

	/**********************************
	 * Purpose: to convert infix to postfix
	 * @param equation
	 * @return postFix
	 *******************************/
	private static DSAQueue1 parseInfixToPostfix(String equation)
	{
		boolean s = true;
		EquationSolverStack eStack = new EquationSolverStack(100);
		DSAQueue1 postFix = new DSAQueue1();
		String charString = "";
		StringTokenizer token = new StringTokenizer(equation, "+-/*%()", true);
		while (token.hasMoreTokens())         //check for more tokens
		{
			String term = token.nextToken();            //get the next token

			if (term.equals("("))        //if the term is '('
			{
				eStack.push("(");      //push to stack
			}
			else if (term.equals(")"))     //if term is ')'
			{
				while (!(eStack.top().equals("(")))   //check if bracket is open
				{
					postFix.enqueue(eStack.pop());     //enqueue top of stack
				}
				eStack.pop();      //pop stack    - should be empty
			}
			else if ((term.equals("+")) || (term.equals("-")) || (term.equals("*"))
					 || (term.equals("/")) || (term.equals("%")))      //check for operators
			{
				while ((!eStack.isEmpty()) && (!(eStack.top().equals('('))) &&
						(precedenceOf(eStack.top().toString().charAt(0)) >= precedenceOf(term.charAt(0))))
				//Check if stack is not empty and top isn't and open brack
					// and check precedence
				{
					postFix.enqueue(eStack.pop());    //enqueue item at top of stack
				}
				eStack.push(term);      //push the term to stack
			}
			else
			{
				postFix.enqueue(term);    //if number then put number in queue
			}
		}
		while (!eStack.isEmpty())    //check if stack is empty
		{
			postFix.enqueue(eStack.pop());    //enqueue top of stack in postfix
		}
		return postFix;

	}

	/*****************************************
	 * Purpose: to check the precedence of the operator
	 * @param term
	 * @return charValue
	 *****************************************/
	public static int precedenceOf(char term)
	{
		int charValue = 0;
		if((term == '+') || (term == '-'))      //check for operator
		{
			charValue = 1;   //return value
		}
		else if((term == '*') || (term == '/') || (term == '%'))
		{
			charValue = 2;      //value is of greater precedence
		}
		return charValue;
	}

	/*************************************
	 * Purpose: to evaluate the postfix result
	 * @param postFix
	 * @return top of stack
	 */
	public static Object evaluatePostFix(DSAQueue1 postFix)
	{
		OperandStack oStack = new OperandStack(100);
		double num1, num2;
		double result;
		while(!postFix.isEmpty())
		{
			Object myObj = postFix.dequeue();    //dequeue the object
			if(isDouble(myObj))        //check for double
			{
				oStack.push(myObj);      //push onto stack
			}
			else    //check for operator
			{
				num2 = Double.parseDouble(oStack.pop().toString());
				//convert stack object to double
				num1 = Double.parseDouble(oStack.pop().toString());
				result = executeOperation(myObj.toString().charAt(0), num1, num2);
				//determines result
				oStack.push(result);   //push result onto stack
			}
		}
		return ((double) oStack.top());    //return stack top
	}

	/**************************************
	 * Purpose: to calculate the result
	 * @param op
	 * @param op1
	 * @param op2
	 * @return result
	 *************************************/
	private static double executeOperation(char op, double op1, double op2)
	{
		double result;
		switch(op)
		{
			case '+':
				result = op1 + op2;      //add
				break;
			case '-':                    //subtract
				result = op1 - op2;
				break;
			case '*':                   //multiply
				result = op1 * op2;
				break;
			case '/':                  //divide
				result = op1 / op2;
				break;
			case '%':                  //mod
				result = op1 % op2;
				break;
			default:                //return 0.0
				result = 0.0;
		}
		return result;
	}

	/**************************************
	 * to check if it is a double
	 * @param myObj
	 * @return isDouble
	 **********************************/
	public static boolean isDouble(Object myObj)
	{
		boolean isDouble = true;
		try
		{
			Double.parseDouble(myObj.toString());    //convert to double
		}
		catch(NumberFormatException n)
		{
			isDouble = false;
		}
		return isDouble;      //return true or false
	}
}

