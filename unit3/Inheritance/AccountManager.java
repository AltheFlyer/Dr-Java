/**
 * AccountManager.java
 * @version 1.0
 * @author Allen Liu
 * @since April 10, 2019
 * Manages accounts in a bank
 */
class AccountManager {
 
    private BankAccount[] accounts;
    private int counterID;
        
    AccountManager() {
        accounts = new BankAccount[100];
        counterID = -1;
    }
    
    void printAllAccounts() {
        for (int i = 0; i < accounts.length; ++i) {
            if (accounts[i] != null) {
                System.out.println(accounts[i].getBalance());
            } else {
                return;
            }
        }
    }
    
    boolean createCheckingAccount() {
        return createAccount(new CheckingAccount(generateNewID()));
    }
    
    boolean createCheckingAccount(double balance) {
        return createAccount(new CheckingAccount(generateNewID(), balance));
    }
    
    boolean createSavingsAccount() {
        return createAccount(new SavingsAccount(generateNewID()));
    }
    
    boolean createSavingsAccount(double balance, double rate) {
        return createAccount(new SavingsAccount(generateNewID(), balance, rate));
    }
    
    
    private boolean createAccount(BankAccount b) {
        for (int i = 0; i < accounts.length; ++i) {
            if (accounts[i] == null) {
                accounts[i] = b;
                return true;
            }
        }
        return false;
    }
    
    boolean deleteAccount(int index) {
        if (accounts[index] == null) {
            return false;
        } else {
            accounts[index] = null;
            //Shift everything down
            for (int i = index + 1; i < accounts.length; ++i) {
                accounts[i - 1] = accounts[i];
                if (accounts[i] == null) {
                    return true;
                }
            }
            accounts[accounts.length - 1] = null;
        }
        return true;
    }
    
    boolean deposit(int index, double amount) {
        if (accounts[index] != null) {
            return accounts[index].deposit(amount);
        }
        return false;
    }
    
    boolean withdraw(int index, double amount) {
        if (accounts[index] != null) {
            return accounts[index].withdraw(amount);
        }
        return false;
    }
    
    void addInterest() {
        for (int i = 0; i < accounts.length; ++i) {
            if (accounts[i] instanceof SavingsAccount) {
                ((SavingsAccount) accounts[i]).addInterest();
            }
        }
    }
    
    double totalMoney() {
        double total = 0;
        for (int i = 0; i < accounts.length; ++i) {
            if (accounts[i] != null) {
                total += accounts[i].getBalance();
            } else {
                return total;
            }
        }
        return total;
    }
    
    private int generateNewID() {
        counterID++;
        return counterID;
    }
}