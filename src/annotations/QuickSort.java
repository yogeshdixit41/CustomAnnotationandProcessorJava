package annotations;

import java.util.ArrayList;
import java.util.Random;

public class QuickSort {
	int [] arr;
	
	public QuickSort(int [] arr)
	{
		this.arr= arr;
	}
	
	int partition(int low, int high)
	{
		int pivot = arr[high];
		int i = (low-1);
		for(int j=low;j<=high-1;j++)
		{
			if(arr[j] <= pivot)
			{
				i++;
				int temp = arr[i];
				arr[i] = arr[j];
				arr[j] = temp;
			}
		}
		
		int temp = arr[i+1];
		arr[i+1] = arr[high];
		arr[high] = temp;
		
		return i+1;
	}
	
	@Contract(pre_cond = { "" }, post_cond = { "checkPivotValid(ans,@arr)." }, source_files = {"Sublist.pl"})
	int partitionWithCOntract(int low, int high)
	{
		return partition(low, high);
	}
	
	void sort(int low, int high)
	{
		if(low < high)
		{
			//int pi = partitionWithCOntract(low,high);
			int pi = partition(low,high);
			
			sort(low, pi-1);
			sort(pi+1, high);
		}
	}
	
	/* A utility function to print array of size n */
    void printArray()
    {
        int n = arr.length;
        for (int i=0; i<n; ++i)
            System.out.print(arr[i]+" ");
        System.out.println();
    }
 
    
    public static void main(String args[])
    {
        int arr[] = new int[100];
        int n = arr.length;
        
        Random rand = new Random();
        for(int i=0; i< 100;i++)
        {
        	int  num = rand.nextInt(1000) + 1;
        	arr[i] = num;
        }
        
        long startTime = System.nanoTime();
        QuickSort ob = new QuickSort(arr);
        ob.sort(0, n-1);
        long elapsedTime = System.nanoTime() - startTime;
        
        System.out.println("Total execution time to create 1000K objects in Java in millis: "
                + elapsedTime/1000000);
        
        System.out.println("sorted array");
        ob.printArray();
    }

}
