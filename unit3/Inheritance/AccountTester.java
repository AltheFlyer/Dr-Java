public class AccountTester {
 
    public static void main(String[] args) {
        testBankAccount();
        testSavingsAccount();
        testCheckingAccount();
    }
    
    public static void printBalances(BankAccount ... accs) {
        for (BankAccount b: accs) {
            System.out.println(b.getBalance());
        }
    }
    
    public static void testCheckingAccount() {
        CheckingAccount simpleAccount = new CheckingAccount(0);
        CheckingAccount presetAccount = new CheckingAccount(0,300);
    
        /* Expected:
         * 0
         * 300
         */
        printBalances(simpleAccount, presetAccount);
        
        /* Expected:
         * 29
         * 280
         */
        simpleAccount.deposit(50);
        simpleAccount.withdraw(20);
        presetAccount.withdraw(20);
        printBalances(simpleAccount, presetAccount);
        
        /* Expected:
         * 80
         * 78
         */
        presetAccount.withdraw(200);
        printBalances(presetAccount);
        presetAccount.withdraw(1);
        printBalances(presetAccount);
    }
    
    public static void testSavingsAccount() {
        SavingsAccount simpleAccount = new SavingsAccount(0);
        SavingsAccount presetAccount = new SavingsAccount(0,1100, 10);
        
        /* Expected:
         * -100
         * 1000
         */
        printBalances(simpleAccount, presetAccount);
        
        /* Expected:
         * 102.5
         * 1100
         */
        simpleAccount.deposit(200);
        simpleAccount.addInterest();
        presetAccount.addInterest();
        printBalances(simpleAccount, presetAccount);
    }
    
    public static void testBankAccount() {
        //BankAccount simpleAccount = new BankAccount(0);
        //BankAccount presetAccount = new BankAccount(0,2019);
        
        /* Expected: 
         * 0
         * 2019
         */
        //printBalances(simpleAccount, presetAccount);
        
        /* Expected:
         * 500
         * 2142.12
         */
        //simpleAccount.deposit(500);
        //presetAccount.deposit(123.12);
        //printBalances(simpleAccount, presetAccount);
        
        /* Expected:
         * 377
         * 0.12 (or some bs)
         */
        //simpleAccount.withdraw(123);
        //presetAccount.withdraw(2142);
        //printBalances(simpleAccount, presetAccount);
        
        /* Expected:
         * false
         * 0.12 (or some bs)
         * false
         * false
         */
        //System.out.println(presetAccount.withdraw(100));
        //printBalances(presetAccount);
        //System.out.println(presetAccount.deposit(-1000));
        //System.out.println(presetAccount.withdraw(-1000));
    }
}