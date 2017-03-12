package annotations;

public class Bank {

	private String accOwner;
	private Double balance;
	
	public Bank(String name)
	{
		this.accOwner = name;
		this.balance = 0.0;
	}
	
	@Contract(pre_cond = { "nonnegative(amount)", "lessThan(amount, balance)" }, post_cond = { "checkbalance(amount, returnValue)" })
	public double withdraw(Double amount)
	{
		this.balance = balance - amount;
		return balance;
	}
	
	@Contract(pre_cond = { "nonnegative(amount)" }, post_cond = { "checkbalance(amount, returnValue)" })
	public double deposit(Double amount)
	{
		this.balance = balance + amount;
		return balance;
	}
	
	public static void main(String [] args)
	{
		Bank newAccount = new Bank("Test Account");
		newAccount.deposit(1000.00);
		newAccount.withdraw(500.00);
	}
	
}
