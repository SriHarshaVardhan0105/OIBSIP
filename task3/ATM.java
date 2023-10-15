import java.util.ArrayList;
import java.util.Scanner;

class User {
    private int userId;
    private int userPin;
    private double balance;
    private ArrayList<Transaction> transactionHistory;

    public User(int userId, int userPin, double initialBalance) {
        this.userId = userId;
        this.userPin = userPin;
        this.balance = initialBalance;
        this.transactionHistory = new ArrayList<>();
    }

    public int getUserId() {
        return userId;
    }

    public int getUserPin() {
        return userPin;
    }

    public double getBalance() {
        return balance;
    }

    public ArrayList<Transaction> getTransactionHistory() {
        return transactionHistory;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            transactionHistory.add(new Transaction("Deposit", amount));
            System.out.println("Deposit successful.");
        } else {
            System.out.println("enter valid amount");
        }
    }

    public boolean withdraw(double amount) {
        if (balance >= amount) {
            balance -= amount;
            transactionHistory.add(new Transaction("Withdrawal", amount));
            return true;
        } else {
            return false;
        }
    }

    public boolean transfer(User recipient, double amount) {
        if (withdraw(amount)) {
            recipient.deposit(amount);
            transactionHistory.add(new Transaction("Transfer to " + recipient.getUserId(), amount));
            return true;
        } else {
            return false;
        }
    }

    public void showBalance() {
        System.out.println("Your current balance is: Rs. " + getBalance());
    }
}

class Transaction {
    private String type;
    private double amount;

    public Transaction(String type, double amount) {
        this.type = type;
        this.amount = amount;
    }

    public String getType() {
        return type;
    }

    public double getAmount() {
        return amount;
    }
}

public class ATM {
    public static void main(String[] args) {
        User user = new User(93906, 19180, 2385.34);

        Scanner sc = new Scanner(System.in);
        System.out.println(
                "====================================================================================================");
        System.out.print("Enter User ID: ");
        int userId = sc.nextInt();
        System.out.print("Enter PIN: ");
        int pin = sc.nextInt();
        System.out.println(
                "---------------------------------------------------------------------------------------------------");
        if (userId == user.getUserId() && pin == user.getUserPin()) {
            System.out.println("login successful.");
            int choice;
            do {
                System.out.println("1. Transaction History");
                System.out.println("2. Show Balance");
                System.out.println("3. Withdraw");
                System.out.println("4. Deposit");
                System.out.println("5. Transfer");
                System.out.println("6. Quit");
                System.out.print("Enter your choice: ");
                choice = sc.nextInt();

                switch (choice) {
                    case 1:
                        ArrayList<Transaction> transactions = user.getTransactionHistory();
                        for (Transaction transaction : transactions) {
                            System.out.println(transaction.getType() + ": " + transaction.getAmount());
                        }
                        break;
                    case 2:
                        user.showBalance();
                        break;
                    case 3:
                        System.out.print("Enter the amount to withdraw: Rs. ");
                        double withdrawAmount = sc.nextDouble();
                        if (user.withdraw(withdrawAmount)) {
                            System.out.println("Withdrawal successful.");
                        } else {
                            System.out.println("Insufficient funds.");
                        }
                        break;
                    case 4:
                        System.out.print("Enter the amount to deposit: Rs. ");
                        double depositAmount = sc.nextDouble();
                        user.deposit(depositAmount);
                        break;
                    case 5:
                        System.out.print("Enter recipient's User ID: ");
                        int recipientId = sc.nextInt();
                        System.out.print("Enter the amount to transfer: Rs. ");
                        double transferAmount = sc.nextDouble();
                        User recipient = user; // Replace with recipient lookup logic
                        if (user.transfer(recipient, transferAmount)) {
                            System.out.println("Transfer successful.");
                        } else {
                            System.out.println("Insufficient funds.");
                        }
                        break;
                    case 6:
                        System.out.println("Quitting ATM system.");
                        break;
                    default:
                        System.out.println("Invalid choice. Please try again.");
                        break;
                }
                System.out.println(
                        "==============================================================================================");
            } while (choice != 6);
        } else {
            System.out.println("login failed. Exiting ATM system.");
        }
    }
}
