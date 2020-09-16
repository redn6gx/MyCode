package hw5;

public abstract class Account  implements Depositable, Withdrawable, Balanceable {

	private double balance;

	/**
	 * set opening balance for new account
	 * 
	 */
	public Account(double startBalance) {
		balance = startBalance;
	}

	@Override
	public double getBalance() {
		return balance;
	}

	@Override
	public void setBalance(double amount) {
		balance = amount;
	}

	/**
	 * withdraw amount from account
	 * return false -- balance is less than amount. Withdrawal not done.
	 *        true  -- success.
	 * 
	 */
	@Override
	public boolean withdraw(double amount) {
		if (amount <= getBalance()) {
			double t = getBalance() - amount;
			setBalance(t);
			return true;
		} else {
			return false;
		}
	}

	/**
	 * add amount to balance for account
	 */
	@Override
	public void deposit(double amount) {
		setBalance(getBalance() + amount);
	};

	/**
	 * monthly processing for account
	 */
	public abstract void monthlyProcess();

}
