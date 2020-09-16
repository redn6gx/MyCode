package test;

import static org.junit.jupiter.api.Assertions.*;
import hw5.*;

import org.junit.jupiter.api.Test;

class Hw5Test {

	@Test
	void testCheckingAccount() {
		CheckingAccount acct = new CheckingAccount(1000, .50);
		assertTrue(acct instanceof Account);
		assertTrue(acct instanceof Balanceable);
		assertTrue(acct instanceof Depositable);
		assertTrue(acct instanceof Withdrawable);
		assertEquals(1000.00, acct.getBalance());
		acct.setBalance(1500);
		assertEquals(1500, acct.getBalance());
		acct.setBalance(1000);
		assertEquals(.5, acct.getFee());
		assertTrue(acct.withdraw(150));
		assertTrue(acct.withdraw(100));
		acct.deposit(400);
		acct.deposit(100);
		assertEquals(1250.00, acct.getBalance());
		assertFalse(acct.withdraw(1300.00));
		acct.monthlyProcess();
		assertEquals(2.50, acct.getMonthlyFee());
		assertEquals(1247.5, acct.getBalance());
	}
	
	@Test
	void testSavingsAccount() {
		SavingsAccount acct = new SavingsAccount(1000, .02);
		assertTrue(acct instanceof Account);
		assertTrue(acct instanceof Balanceable);
		assertTrue(acct instanceof Depositable);
		assertTrue(acct instanceof Withdrawable);
		assertEquals(1000.00, acct.getBalance());
		assertEquals(.02, acct.getInterestRate());
		acct.setBalance(1500);
		assertEquals(1500, acct.getBalance());
		acct.setBalance(1000);
		assertTrue(acct.withdraw(150));
		assertTrue(acct.withdraw(100));
		acct.deposit(400);
		acct.deposit(100);
		assertFalse(acct.withdraw(1260));
		assertEquals(1250.00, acct.getBalance());
		acct.monthlyProcess();
		assertEquals(25.00, acct.getMonthlyInterest());
		assertEquals(1275.00, acct.getBalance());
		
	}

}
