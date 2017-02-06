package annotations;

public class TestClass {

	@Contract(pre_cond = { "nonnegative(var)" }, post_cond = { "nonzero(ans)" })
	public static void print1(int var)
	{
		System.out.println("In print1 : " + var);
	}
	
	
	public static void main(String[] args)
	{
		print1(1);
	}
	
}
