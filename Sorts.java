import java.util.*;

public class Sorts
{
    public static void main(String[] args)
    {
        System.out.println("Testing shell sort");
        int[] A = new int[10];
        for(int i=0;i<10;i++)
        {
            A[i] = (int) (Math.random() * 10);
        }
        shellSort(A);
        printArray(A);

        System.out.println("Testing counting sort");
        for(int i=0;i<10;i++)
        {
            A[i] = (int) (Math.random()*A.length);
        }
        A = countingSort(A);
        printArray(A);
    }

    public static void shellSort(int[] A)
    {
        int n = A.length;
        for(int gap=n/2;gap>0;gap/=2)
        {
            for(int i = gap;i<n;i+=1)
            {
                int temp = A[i];
                int j;
                for(j=i;j>=gap && A[j-gap]>temp;j-=gap)
                {
                    A[j]=A[j-gap];
                }
                A[j]=temp;
            }
        }
    }

    public static int[] countingSort(int[] array)
    {
        // array to be sorted in, this array is necessary
        // when we sort object datatypes, if we don't,
        // we can sort directly into the input array
        int[] aux = new int[array.length];

        // find the smallest and the largest value
        int min = array[0];
        int max = array[0];
        for (int i = 1; i < array.length; i++)
        {
            if (array[i] < min)
            {
                min = array[i];
            }
            else if (array[i] > max)
            {
                max = array[i];
            }
        }

        // init array of frequencies
        int[] counts = new int[max - min + 1];

        // init the frequencies
        for (int i = 0;  i < array.length; i++)
        {
            counts[array[i] - min]++;
        }

        // recalculate the array - create the array of occurences
        counts[0]--;
        for (int i = 1; i < counts.length; i++)
        {
            counts[i] = counts[i] + counts[i-1];
        }
        for (int i = array.length - 1; i >= 0; i--)
        {
            aux[counts[array[i] - min]--] = array[i];
        }
        return aux;
    }

    public static void printArray(int[] A)
    {
        for(int i=0;i<A.length;i++)
        {
            System.out.println(A[i]);
        }
    }
}