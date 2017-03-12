package annotations;


public class TestClass {

	@Contract(pre_cond = { "nonnegative(var)", "nonnegative(test)" }, post_cond = { "nonzero(ans)" })
	public static int print1(int var, int test)
	{
		System.out.println("In print1 : " + var);
		return var-1;
	}
	
//	@Contract(pre_cond = { "nonnegative(var)" }, post_cond = { "ordered(ans)" })
//	public static int[] print2(int var)
//	{
//		System.out.println("In print1 : " + var);
//		int[] l = {1,2,3,4};
//		return l;
//	}
	
	
	public static void main(String[] args)
	{
		print1(2, -3);
		//print2(4);
	}
	
}
