import java.util.Arrays;

/**
 * AccountManager.java
 * @version 1.0
 * @author Allen Liu
 * @since April 10, 2019
 * Manages accounts in a bank
 */

class AccountManager {
 
    //The array of bank accounts, either checking or savings
    //Should remain sorted by ID
    private BankAccount[] accounts;
    //The ID of the next account
    private int counterID;
    //The number of accounts in the bank
    private int numAccounts;
        
    AccountManager() {
        accounts = new BankAccount[100];
        counterID = -1;
        numAccounts = 0;
    }
    
    /**
     * printAllAccounts
     * Prints all bank accounts to console, showing ID, and balance
     */
    public void printAllAccounts() {
        for (int i = 0; i < numAccounts; ++i) {
            if (accounts[i] != null) {
                System.out.printf("ID: %d, Total: %.2f\n", accounts[i].getID() ,accounts[i].getBalance());
            } else {
                return;
            }
        }
    }
    
    /**
     * createCheckingAccount
     * Creates a checking account if there is space for one
     * @return boolean whether an account was successfully created
     */
    public boolean createCheckingAccount() {
        return createAccount(new CheckingAccount(generateNewID()));
    }
    
    /**
     * createCheckingAccount
     * Creates a checking account if there is space for one
     * @param balance the starting balance in the account
     * @return boolean whether an account was successfully created
     */
    public boolean createCheckingAccount(double balance) {
        return createAccount(new CheckingAccount(generateNewID(), balance));
    }
    
    /**
     * createSavingsAccount
     * Creates a savings account if there is space for one
     * @return boolean whether an account was successfully created
     */
    public boolean createSavingsAccount() {
        return createAccount(new SavingsAccount(generateNewID()));
    }
    
    /**
     * createSavingsAccount
     * Creates a savings account if there is space for one
     * @param balance the starting balance in the account
     * @param rate the interest rate on the account
     * @return boolean whether an account was successfully created
     */
    public boolean createSavingsAccount(double balance, double rate) {
        return createAccount(new SavingsAccount(generateNewID(), balance, rate));
    }
    
    /**
     * createAccount
     * Generic account creation method
     * @param account the account to add to the bank
     * @return boolean whether the operation was succesful or not
     */
    private boolean createAccount(BankAccount account) {
        if (numAccounts < accounts.length) {
            accounts[numAccounts] = account;
            numAccounts++;
            return true;
        }
        //If there is no space in the bank
        counterID--;
        return false;
    }
    
    /**
     * deleteAccount
     * Deletes an account based on id
     * @param id the ID of the account to delete
     * @return whether the operation was successful or not (if the id exists)
     */
    public boolean deleteAccount(int id) {
        //Get index by id
        int index = this.binarySearch(id, 0, numAccounts - 1);
        //If the account with the id doesn't exist, operation fails
        if (index == -1) {
            return false;
        }
        
        //Deletes account by dereferencing
        accounts[index] = null;
        //Shift everything down to maintain order by ID
        for (int i = index + 1; i < numAccounts; ++i) {
            accounts[i - 1] = accounts[i];
        }
        accounts[accounts.length - 1] = null;
        numAccounts--;
        return true;
    }
        
    /**
     * deposit
     * Adds money to the account with the given id
     * @param id the id of the account to deposit in 
     * @param amount the amount of money to deposit
     * @return boolean whether the operation was successful or not
     */
    public boolean deposit(int id, double amount) {
        int index = this.binarySearch(id, 0, numAccounts - 1);
        if (index == -1) {
            return false;
        }
        
        return accounts[index].deposit(amount);
    }
    
    /**
     * withdraw
     * Withdraws money from the account with the given id
     * @param id the id of the account to deposit in 
     * @param amount the amount of money to withdraw
     * @return boolean whether the operation was successful or not
     */
    public boolean withdraw(int id, double amount) {
        //Get the account index by id
        int index = this.binarySearch(id, 0, numAccounts - 1);
        //If id doesn't exist, operation fails
        if (index == -1) {
            return false;
        }
        
        return accounts[index].withdraw(amount);
    }
    
    /**
     * addInterest
     * adds interest to all savings accounts in the bank
     */
    public void addInterest() {
        for (int i = 0; i < accounts.length; ++i) {
            if (accounts[i] instanceof SavingsAccount) {
                ((SavingsAccount) accounts[i]).addInterest();
            }
        }
    }
    
    /**
     * totalMoney
     * counts the total amount of money in the bank
     * @return double The amount of money in the entire bank
     */
    public double totalMoney() {
        double total = 0;
        for (int i = 0; i < numAccounts; ++i) {
            total += accounts[i].getBalance();            
        }
        return total;
    }
    
    /**
     * generateNewID
     * generates the ID for the next bank account in the bank
     * @return int the new ID to use
     */
    private int generateNewID() {
        counterID++;
        return counterID;
    }
    
    /**
     * binarySearch
     * recursively searches for the index of the account within the accounts array, given an ID
     * @param id the ID of the account to search for
     * @param low the lowest bound of the array range to search in
     * @param high the highest bound of the array range to search in
     * @return int the index of the account with the given id, returns -1 if no such account
     */
    private int binarySearch(int id, int low, int high) {
        //If something somehow breaks along the way (no bank accounts)
        if (high < low) {
            return -1;
        }
        //When there are only 1 or 2 values to compare, do a straight comparison
        if (low == high || low == high - 1) {
            if (accounts[low].getID() == id) {
                return low;
            } else if (accounts[high].getID() == id){
                return high;
            } else {
                return -1;
            }
        }
        
        int pivot = (low + high) / 2;
        System.out.println(low + " " + high);
        if (accounts[pivot].getID() == id) {
            return pivot;
        }
        if (accounts[pivot].getID() > id) {
            return binarySearch(id, low, pivot);
        } else {
            return binarySearch(id, pivot, high);
        }
    }
    
    /**
     * getAccountsByBalance
     * @return BankAccount[] The array of bank accounts, sorted by balance
     */
    public BankAccount[] getAccountsByBalance() {
        BankAccount[] pushAccs = new BankAccount[numAccounts];
        for (int i = 0; i < numAccounts; ++i) {
            pushAccs[i] = accounts[i];
        }
        Arrays.sort(pushAccs);
        
        return pushAccs;
    }
}