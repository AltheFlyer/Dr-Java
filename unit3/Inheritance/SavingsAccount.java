/**
 * BankAccount.java
 * @version 1.2
 * @author Allen Liu
 * @since April 5, 2019
 * The base class for a savings account that can accumulate interest
 */
public class SavingsAccount extends BankAccount {
    
    private double interestRate;
    
    SavingsAccount(int id) {
        super(id, -100);
        interestRate = 2.5;
    }
    
    SavingsAccount(int id, double balance, double rate) {
        super(id, balance - 100);
        interestRate = rate;
    }
    
    /**
     * addInterest
     * Adds interest to the balance of the current account
     */
    public void addInterest() {
        double in = getBalance() * (interestRate / 100);
        if (in >= 0) {
            super.deposit(in);
        } else {
            super.withdraw(-in);
        }
    }
   
}