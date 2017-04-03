package annotations;

public class QuickSort {
	int [] arr;
	
	public QuickSort(int [] arr)
	{
		this.arr= arr;
	}
	
	@Contract(pre_cond = { "" }, post_cond = { "checkPivotValid(ans,@arr)." }, source_files = {"Sublist.pl"})
	int partition(int low, int high)
	{
		int pivot = arr[high];
		int i = (low-1);
		for(int j=low;j<=high-1;j++)
		{
			if(arr[j] > pivot)
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
	
	void sort(int low, int high)
	{
		if(low < high)
		{
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
 
    // Driver program
    public static void main(String args[])
    {
        int arr[] = {10, 7, 8, 9, 1, 5};
        int n = arr.length;
 
        QuickSort ob = new QuickSort(arr);
        ob.sort(0, n-1);
 
        System.out.println("sorted array");
        ob.printArray();
    }

}
