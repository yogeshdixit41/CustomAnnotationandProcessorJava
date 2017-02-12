package annotations;

public class TestClass {

	@Contract(pre_cond = { "nonnegative(var)" }, post_cond = { "nonzero(ans)" })
	public static int print1(int var)
	{
		System.out.println("In print1 : " + var);
		return var-1;
	}
	
	
	public static void main(String[] args)
	{
		print1(1);
	}
	
}
