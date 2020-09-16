package hw5;
import java.text.DecimalFormat;
import java.util.*;
import java.text.DecimalFormat;
import java.math.*;

public class AccountApp {
	public static void main(String[] args) {
		Scanner keyboard = new Scanner(System.in);
		DecimalFormat df2 = new DecimalFormat("#,##0.00");
		
		//Prompt user to enter info
		System.out.println("Welcome to the Account application");
		System.out.print("Enter initial checking amount: ");
		double checkingAmount = keyboard.nextDouble();
		System.out.print("Enter initial saving amount: ");
		double savingAmount = keyboard.nextDouble();
		System.out.print("Enter checking fee: ");
		double checkingFee = keyboard.nextDouble();
		System.out.print("Enter saving interest: ");
		double interest = keyboard.nextDouble();
		
		//Display Initial Information
		System.out.println("OK! This is your information");
		System.out.println("Checking Amount: $" + df2.format(checkingAmount));
		System.out.println("Savings Amount: $" + df2.format(savingAmount));
		System.out.println("Checking Fee: $" + df2.format(checkingFee));
		System.out.println("Interest Rate: " + Math.round(interest*100) + "%");
		
		//Create Accounts
		CheckingAccount checkings = new CheckingAccount(checkingAmount, checkingFee);
		SavingsAccount savings = new SavingsAccount(savingAmount, interest);
		
		//Transaction
		System.out.println("Enter the transactions for the month");
		String choice = "y";
		while(choice.equals("y")) {
			System.out.print("Withdrawl or deposit? (w/d): ");
			String actionChoice = keyboard.next();
			System.out.print("Checking or savings? (c/s):");
			String accountChoice = keyboard.next();
			System.out.print("Amount?: ");
			Double amountChoice = keyboard.nextDouble();
			
			if(actionChoice.equals("w")) {
				if(accountChoice.equals("c")) {
					checkings.withdraw(amountChoice);
				} else {
					savings.withdraw(amountChoice);
				}
			} else {
				if(accountChoice.equals("c")) {
					checkings.deposit(amountChoice);
				} else {
					savings.deposit(amountChoice);
				}
			}
//			System.out.println("Monthly Payment and Fees");
//			System.out.println("Checking fee: $" + df2.format(checkings.getMonthlyFee()));
//			System.out.println("Savings interest payment: $" + df2.format(savings.getMonthlyInterest()));
//			System.out.println("Final Balances");
//			System.out.println("Checking: $" + df2.format(checkings.getBalance()));
//			System.out.println("Savings: $" + df2.format(savings.getBalance()));
			
			System.out.print("Continue? (y/n): ");
			choice = keyboard.next();
		}
		
		checkings.monthlyProcess();
		savings.monthlyProcess();
		
		//Display Final Information
		System.out.println("Monthly Payment and Fees");
		System.out.println("Checking fee: $" + df2.format(checkings.getMonthlyFee()));
		System.out.println("Savings interest payment: $" + df2.format(savings.getMonthlyInterest()));
		System.out.println("Final Balances");
		System.out.println("Checking: $" + df2.format(checkings.getBalance()));
		System.out.println("Savings: $" + df2.format(savings.getBalance()));
	}
}
