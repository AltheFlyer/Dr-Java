/**
 * BankAccount.java
 * @version 1.2
 * @author Allen Liu
 * @since April 5, 2019
 * The base class for a simple bank account
 */
abstract public class BankAccount implements Comparable<BankAccount> {

    private double balance;
    private final int ID;
    
    BankAccount(int id) {
        this.balance = 0;
        this.ID = id;
    }
    
    BankAccount(int id, double balance) {
        this.balance = balance;
        this.ID = id;
    }
    
    /**
     * deposit
     * @param amount The amount to deposit into the account, should be positive
     * @return boolean, whether the transaction was successful or not
     */
    public boolean deposit(double amount) {
        if (amount > 0) {
            this.balance += amount;
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
        if (this.balance >= amount) {
            this.balance -= amount;
            return true;
        }
        this.balance -= amount;
        return false;
    }
    
    /**
     * getBalance
     * @return double, the balance of the account
     */
    public double getBalance() {
        return balance;
    }
      
    /**
     * getID
     * @return int, the id of the bank account
     */
    public int getID() {
        return ID;
    }
    
    /**
     * compareTo
     * compares the balances between two accounts
     * @param account the account to compare to
     * @return int the difference in balance, positive if the checked account has a higher balance,
     * negative if the checked account has a lower balance
     */
    public int compareTo(BankAccount account) {
        return (int) (100 * (this.getBalance() - account.getBalance()));
    }
}