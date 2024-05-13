import java.util.Scanner;

class BankAccount {
//I have used private instead of public to restrict the accessibility of bank details.
    private String name;
    private String userId;
    private String pin;
    private String accountNo;  
    private String transactionHistory = "";
    private float balance = 0;
    private int transaction = 0;

    public void signUp() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter your Name:");
        name = scan.nextLine();
        System.out.println("Enter your User_ID:");
        userId = scan.nextLine();
        System.out.println("Enter your PIN (only digits):");
        pin = scan.nextLine();
        while (!pin.matches("\\d{4}")) {
            System.out.println("PIN must consist only 5 digits. Please re-enter:");
            pin = scan.nextLine();
        }
        System.out.println("Enter your Account Number (exactly 12 digits):");
        accountNo = scan.nextLine();
        while (!accountNo.matches("\\d{12}")) {
            System.out.println("Account number must consist of exactly 12 digits. Please re-enter:");
            accountNo = scan.nextLine();
        }
        System.out.println("Registration completed. Kindly login..");
        System.out.println("***************************************");
    }

    public boolean login() {
        Scanner scan = new Scanner(System.in);
        while (true) {
            System.out.println("Enter your User_ID:");
            String username = scan.nextLine();
            if (username.equals(userId)) {
                while (true) {
                    System.out.println("Enter your Pin:");
                    String enteredPin = scan.nextLine();
                    if (enteredPin.equals(pin)) {
                        System.out.println("Login Successfully...");
                        return true;
                    } else {
                        System.out.println("Incorrect Pin");
                        System.exit(0); // Exit the program if PIN is incorrect
                    }
                }
            } else {
                System.out.println("User_ID not found");
                System.exit(0); // Exit the program if User_ID is not found
            }
        }
    }

    public void withdraw() {
        System.out.println("Enter withdraw amount:");
        Scanner scan = new Scanner(System.in);
        float amount = scan.nextFloat();
        if (balance >= amount) {
            transaction++;
            balance -= amount;
            System.out.println("Withdrawal successful");
            String str = amount + " Withdraw\n";
            transactionHistory += str;
        } else {
            System.out.println("Insufficient balance");
        }
        System.out.println("--------------------------------------");
    }

    public void deposit() {
        System.out.println("Enter amount to deposit:");
        Scanner scan = new Scanner(System.in);
        float amount = scan.nextFloat();
        if (amount <= 10000) {
            transaction++;
            balance += amount;
            System.out.println("Successfully deposited");
            String str = amount + " Deposited\n";
            transactionHistory += str;
        } else {
            System.out.println("Limit is 10000");
        }
        System.out.println("---------------------------------------");
    }

    public void transfer() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter recipient name:");
        String recipient = scan.nextLine();
        System.out.println("Enter transfer amount:");
        float amount = scan.nextFloat();
        if (balance >= amount) {
            if (amount < 50000f) {
                transaction++;
                balance -= amount;
                System.out.println("Successfully transferred to " + recipient);
            } else {
                System.out.println("LIMIT EXCEEDED");
            }
        }
        System.out.println("--------------------------------------------------");
    }

    public void viewTransactionHistory() {
        if (transaction == 0) {
            System.out.println("\nEmpty");
        } else {
            System.out.println("\n" + transactionHistory);
        }
        System.out.println("---------------------------------------------------");
    }

    public String getName() {
        return name;
    }
}

public class ATMINTERFACE{
    public static int takeIntegerInput() {
        int input = 0;
        boolean flag = false;
        while (!flag) {
            try {
                Scanner scan = new Scanner(System.in);
                input = scan.nextInt();
                flag = true;
            if (flag && (input > 2 || input < 1)) {
                System.out.println("Choose a number between 1 to 2");
            }
            } catch (Exception e) {
                System.out.println("Enter only integer value");
                flag = false;
            }
        }
        return input;
    }

    public static void main(String args[]) {
        System.out.println("**********Welcome to ATM INTERFACE**********");
        System.out.println("1. Register \n2. Quit");
        System.out.println("Enter your choice:");
        int choice = takeIntegerInput();

        if (choice == 1) {
            BankAccount b = new BankAccount();
            b.signUp();
            while (true) {
                System.out.println("1. Login \n2. Quit");
                System.out.println("Enter your choice:");
                int ch = takeIntegerInput();

                if (ch == 1) {
                    if (b.login()) {
                        System.out.println("***********Welcome back " + b.getName() + "**********");
                        boolean isFinished = false;
                        while (!isFinished) {
                            System.out.println("\n1. Transactions History \n2. Withdraw \n3. Deposit \n4. Transfer \n5. Quit");
                            System.out.println("Enter your choice: ");
                            int c = takeIntegerInput();
                            switch (c) {
                                case 1:
                                    b.viewTransactionHistory();
                                    break;
                                case 2:
                                    b.withdraw();
                                    break;
                                case 3:
                                    b.deposit();
                                    break;
                                case 4:
                                    b.transfer();
                                    break;
                                case 5:
                                    isFinished = true;
                                    break;
                            }
                        }
                    }
                } else {
                    System.exit(0);
                }
            }
        } else {
            System.exit(0);
        }
    }
}

