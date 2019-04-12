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
    private int numAccounts;
        
    AccountManager() {
        accounts = new BankAccount[100];
        counterID = -1;
        numAccounts = 0;
    }
    
    void printAllAccounts() {
        for (int i = 0; i < accounts.length; ++i) {
            if (accounts[i] != null) {
                System.out.printf("ID: %d, Total: %.2f\n", accounts[i].getID() ,accounts[i].getBalance());
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
                numAccounts++;
                return true;
            }
        }
        return false;
    }
    
    boolean deleteAccount(int id) {
        int j = this.binarySearch(id, 0, numAccounts - 1);
        if (accounts[j] != null && accounts[j].getID() == id) {
            accounts[id] = null;
            //Shift everything down
            for (int i = j + 1; i < accounts.length; ++i) {
                accounts[i - 1] = accounts[i];
                if (accounts[i] == null) {
                    numAccounts--;
                    return true;
                }
            }
            accounts[accounts.length - 1] = null;
            numAccounts--;
            return true;
        }
        return false;
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
    
    public int binarySearch(int value, int low, int high) {
        if (low == high || low == high - 1) {
            if (accounts[low].getID() == value) {
                return low;
            } else if (accounts[high].getID() == value){
                return high;
            } else {
                return -1;
            }
        }
        
        int pivot = (low + high) / 2;
        if (accounts[pivot].getID() == value) {
            return pivot;
        }
        if (accounts[pivot].getID() > value) {
            return binarySearch(value, low, pivot);
        } else {
            return binarySearch(value, pivot, high);
        }
    }
}