package hw5;

public class CheckingAccount extends Account {
	
	// TODO add instance variables 
	double checkFee;
	//double startBalance;
	double monthlyFee;
	int numOfDeposits;
	int numOfWithdrawls;
	
	public CheckingAccount(double startBalance, double checkFee) {
        // TODO complete constructor 
		super(startBalance);
		this.checkFee = checkFee;
		numOfDeposits = 0;
		numOfWithdrawls = 0;
		monthlyFee = 0;
	}
	
	public double getFee() {
		return checkFee;
	}

	public double getMonthlyFee() {
		// TO DO 
		return monthlyFee;
	}
	
	@Override
	public boolean withdraw(double amount) {
		// TODO complete override method 
		boolean result = super.withdraw(amount);
		numOfWithdrawls++;
		return result;
	}

	@Override
	public void deposit(double amount) {
		// TODO complete override method 
		numOfDeposits++;
		super.deposit(amount);
	}
	
	@Override
	public void monthlyProcess() {
		// TODO 
		int total = numOfDeposits + numOfWithdrawls;
		monthlyFee = total * checkFee;
		
		double x = super.getBalance();
		super.setBalance(x-monthlyFee);
	}
	
}
