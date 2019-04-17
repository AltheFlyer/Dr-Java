import java.util.Scanner;
import java.util.Arrays;

/**
 * UserInterface.java
 * @version 1.6
 * @author Allen Liu
 * @since April 15, 2019
 * The User Interface to the bank through console
 */
class UserInterface {
    
    public static void main(String[] args) {
        AccountManager bank = new AccountManager();
        Scanner input = new Scanner(System.in);
        String command = "";
        
        bank.createCheckingAccount(1.1);
        bank.createCheckingAccount(1.2);
        bank.createCheckingAccount(1.11);
        bank.createCheckingAccount(4000);
        bank.deleteAccount(0);
        bank.createSavingsAccount(1, 100);
        
        while (!command.equals("end")) {
            double bal = 0;
            
            System.out.println("What would you like to do?");
            System.out.println("You can 'create', 'sort', 'total', 'sort', 'delete', 'deposit', 'withdraw', 'interest', or 'end'");
            command = input.nextLine();
            
            if (command.equals("total")) {
                System.out.println(bank.totalMoney());
                bank.printAllAccounts();
            } else if (command.equals("sort")) {
                BankAccount[] sorted = bank.getAccountsByBalance();
                for (int i = 0; i < sorted.length; ++i) {
                    System.out.printf("ID: %d, Total: %.2f\n", sorted[i].getID(), sorted[i].getBalance());
                }
            } else if (command.equals("create")) {
                System.out.println("Enter the type: checking or savings");
                command = input.nextLine();
                if (command.equals("checking")) {
                    System.out.println("Enter the initial balance: ");
                    bal = input.nextDouble();
                    input.nextLine();
                    if (bank.createCheckingAccount(bal)) {
                        System.out.println("Action successful");
                    } else {
                        System.out.println("Action unsuccessful, too may accounts");
                    }
                } else if (command.equals("savings")) {
                    System.out.println("Enter the initial balance: ");
                    bal = input.nextDouble();
                    input.nextLine();
                    System.out.println("Enter the interest rate.");
                    double interest = input.nextDouble();
                    input.nextLine();
                    if (bank.createSavingsAccount(bal, interest)) {
                        System.out.println("Action successful");
                    } else {
                        System.out.println("Action unsuccessful, too may accounts");
                    }
                } else {
                    System.out.println("Account type not found");
                }
            } else if (command.equals("delete")) {
                System.out.println("Remove which account?");
                int acc = input.nextInt();
                input.nextLine();
                if (bank.deleteAccount(acc)) {
                    System.out.println("Successfully removed account");
                } else {
                    System.out.println("Error, no such account");
                }
            } else if (command.equals("deposit")) {
                System.out.println("Enter the account ID");
                int id = input.nextInt();
                System.out.println("Enter the amount to deposit");
                double amount = input.nextDouble();
                input.nextLine();
                if (bank.deposit(id, amount)) {
                    System.out.println("Operation was successful");
                } else {
                    System.out.println("Operation was unsuccessful");
                }
            } else if (command.equals("withdraw")) {
                System.out.println("Enter the account ID");
                int id = input.nextInt();
                System.out.println("Enter the amount to withdraw");
                double amount = input.nextDouble();
                input.nextLine();
                if (bank.withdraw(id, amount)) {
                    System.out.println("Operation was successful");
                } else {
                    System.out.println("Operation was unsuccessful");
                }
            } else if (command.equals("interest")) {
                bank.addInterest();
            } else if (command.equals("end")) {
                System.out.println("Closing Manager");
            } else {
                System.out.println("Unknown/unrecognized command");
            }          
        }
        System.out.println("Manager closed");
        
    }
}