/**
 * BankAccount.java
 * @version 1.0
 * @author Allen Liu
 * @since April 5, 2019
 * The base class for a sacings account that can accumulate interest
 */
public class SavingsAccount extends BankAccount {
    
    private double interestRate;
    
    SavingsAccount() {
        super(-100);
        interestRate = 2.5;
    }
    
    SavingsAccount(double balance, double rate) {
        super(balance - 100);
        interestRate = rate;
    }
    
    /**
     * addInterest
     * Adds interest to the balance of the current account
     */
    public void addInterest() {
        super.deposit(getBalance() * (interestRate / 100));
    }
}