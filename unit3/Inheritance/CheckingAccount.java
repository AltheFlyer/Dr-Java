/**
 * CheckingAccount.java
 * @version 1.0
 * @author Allen Liu
 * @since April 5, 2019
 * The base class for a checking account, 
 * with a minimum balance before a withdrawal fee is applied
 */
class CheckingAccount extends BankAccount {
 
    private static final double WITHDRAWAL_FEE = 1;
    private static final double MINIMUM_BALANCE = 100;
    
    CheckingAccount() {
        super();
    }
    
    CheckingAccount(double balance) {
        super(balance);
    }
    
    /**
     * withdraw
     * Withdraws money like normal, but charges a fee if the balance is under the minimum value
     * @param amount the amount to withdraw
     * @return boolean, whether the transaction was successful or not
     */
    @Override
    public boolean withdraw(double amount) {
        if (getBalance() < MINIMUM_BALANCE) {
            super.withdraw(WITHDRAWAL_FEE);
        }
        return super.withdraw(amount);
    }
}