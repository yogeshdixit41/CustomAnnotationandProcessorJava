package annotations;


public class TestClass {
	
	private int testInstance;
	public TestClass(int val)
	{
		testInstance = val;
	}

	@Contract(pre_cond = { "nonnegative(var)", "nonnegative(@testInstance)" }, post_cond = { "nonzero(ans)" }, source_files = {"mypl.pl"})
	public int print1(int var, int test)
	{
		System.out.println("In print1 : " + var);
		return var-1;
	}
	
	@Contract(pre_cond = { "nonnegative(var)" }, post_cond = { "nonzero(ans)" }, source_files = {"mypl.pl"})
	public static int print12(int var)
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
		TestClass obj = new TestClass(-11);
		//obj.print1(5, -3);
		print12(3);
		//print2(4);
	}
	
}
