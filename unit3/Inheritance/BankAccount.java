/**
 * BankAccount.java
 * @version 1.0
 * @author Allen Liu
 * @since April 5, 2019
 * The base class for a simple bank account
 */
public class BankAccount {

    private double balance;
    
    BankAccount() {
        balance = 0;
    }
    
    BankAccount(double balance) {
        this.balance = balance;
    }
    
    /**
     * deposit
     * @param amount The amount to deposit into the account, should be positive
     * @return boolean, whether the transaction was successful or not
     */
    public boolean deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            return true;
        }
        return false;
    }
    
    /**
     * withdraw
     * @param amount The amount to withdraw from the account, should be positive
     * @return boolean, whether the transaction was successful or not
     */
    public boolean withdraw(double amount) {
        if ((amount > 0) && (balance >= amount)) {
            balance -= amount;
            return true;
        }
        return false;
    }
    
    /**
     * getBalance
     * @return double, the balance of the account
     */
    public double getBalance() {
        return balance;
    }
      
}