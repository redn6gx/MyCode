package hw5;

public class SavingsAccount extends Account {
	
	//TODO instance variables go here
	double startBalance;
	double monthlyInterestRate;
	double monthlyInterestPayment;
	
	public SavingsAccount(double startBalance, double rate) {
		//TODO complete constructor method
		super(startBalance);
		monthlyInterestRate = rate;
		monthlyInterestPayment = 0;
	}
	
	public double getInterestRate() {
		return monthlyInterestRate;
	}

	@Override
	public void monthlyProcess() {
		//TODO complete method
		monthlyInterestPayment = monthlyInterestRate * super.getBalance();
		super.setBalance(super.getBalance() + monthlyInterestPayment);
	}
	
	public double getMonthlyInterest() {
		//TODO 
		return monthlyInterestPayment;
	}
	
}
