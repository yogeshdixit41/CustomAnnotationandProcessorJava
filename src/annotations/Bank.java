package annotations;

public class Bank {

	private String accOwner;
	private Double balance;
	
	public Bank(String name)
	{
		this.accOwner = name;
		this.balance = 0.0;
	}
	
	/**
	 * @return the balance
	 */
	public Double getBalance() {
		return balance;
	}

	/**
	 * @param balance the balance to set
	 */
	public void setBalance(Double balance) {
		this.balance = balance;
	}

	@Contract(pre_cond = { "isPositive(amount)", "lessThan(amount, @balance)" }, post_cond = { "checkbalance(ans)" })
	public double withdraw(Double amount)
	{
		this.balance = balance - amount;
		return balance;
	}
	
	@Contract(pre_cond = { "nonnegative(amount)" , "maxLimitNotBreached(amount)"}, post_cond = { "checkbalance(ans)" })
	public double deposit(Double amount)
	{
		this.balance = balance + amount;
		return balance;
	}
	
	public static void main(String [] args)
	{
		Bank newAccount = new Bank("Test Account");
		newAccount.deposit(100.00);
		newAccount.withdraw(500.00);
	}
	
}
