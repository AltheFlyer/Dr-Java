import java.util.Scanner;

class UserInterface {
    
    public static void main(String[] args) {
        AccountManager bank = new AccountManager();
        Scanner input = new Scanner(System.in);

        bank.createCheckingAccount(1);
        bank.createCheckingAccount(22);
        bank.createCheckingAccount(333);
        bank.createCheckingAccount(4000);
        bank.deleteAccount(0);
        bank.createCheckingAccount(1);
        System.out.println(bank.binarySearch(4, 0, 3));
        
        while (true) {
            double bal = 0;
            String command = "";
            System.out.println("What would you like to do?");
            command = input.nextLine();
            
            if (command.equals("total")) {
                System.out.println(bank.totalMoney());
                bank.printAllAccounts();
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
            } else if (command.equals("end")) {
                return;
            }
            
        }
        
        
    }
}