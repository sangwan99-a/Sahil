import java.util.*;

class User {
    private String username;
    private String password;
    private BankAccount account;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.account = new BankAccount();
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public BankAccount getAccount() {
        return account;
    }
}

class BankAccount {
    private double balance;
    private List<String> transactionHistory;

    public BankAccount() {
        this.balance = 0.0;
        this.transactionHistory = new ArrayList<>();
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            transactionHistory.add("Deposited: $" + amount);
        } else {
            System.out.println("Deposit amount must be positive.");
        }
    }

    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            transactionHistory.add("Withdrew: $" + amount);
        } else {
            System.out.println("Invalid withdrawal amount.");
        }
    }

    public double getBalance() {
        return balance;
    }

    public List<String> getTransactionHistory() {
        return transactionHistory;
    }
}

class BankingSystem {
    private Map<String, User> users;

    public BankingSystem() {
        users = new HashMap<>();
    }

    public void registerUser (String username, String password) {
        if (!users.containsKey(username)) {
            users.put(username, new User(username, password));
            System.out.println("User  registered successfully.");
        } else {
            System.out.println("Username already exists.");
        }
    }

    public User authenticateUser (String username, String password) {
        User user = users.get(username);
        if (user != null && user.getPassword().equals(password)) {
            return user;
        } else {
            System.out.println("Invalid username or password.");
            return null;
        }
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BankingSystem bankingSystem = new BankingSystem();

        while (true) {
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter username: ");
                    String regUsername = scanner.nextLine();
                    System.out.print("Enter password: ");
                    String regPassword = scanner.nextLine();
                    bankingSystem.registerUser (regUsername, regPassword);
                    break;

                case 2:
                    System.out.print("Enter username: ");
                    String loginUsername = scanner.nextLine();
                    System.out.print("Enter password: ");
                    String loginPassword = scanner.nextLine();
                    User user = bankingSystem.authenticateUser (loginUsername, loginPassword);

                    if (user != null) {
                        boolean loggedIn = true;
                        while (loggedIn) {
                            System.out.println("1. Deposit");
                            System.out.println("2. Withdraw");
                            System.out.println("3. Check Balance");
                            System.out.println("4. Transaction History");
                            System.out.println("5. Logout");
                            System.out.print("Choose an option: ");
                            int userChoice = scanner.nextInt();

                            switch (userChoice) {
                                case 1:
                                    System.out.print("Enter amount to deposit: ");
                                    double depositAmount = scanner.nextDouble();
                                    user.getAccount().deposit(depositAmount);
                                    break;

                                case 2:
                                    System.out.print("Enter amount to withdraw: ");
                                    double withdrawAmount = scanner.nextDouble();
                                    user.getAccount().withdraw(withdrawAmount);
                                    break;

                                case 3:
                                    System.out.println("Current Balance: $" + user.getAccount().getBalance());
                                    break;

                                case 4:
                                    System.out.println("Transaction History:");
                                    for (String transaction : user.getAccount().getTransactionHistory()) {
                                        System.out.println(transaction);
                                    }
                                    break;

                                case 5:
                                    loggedIn = false;
                                    System.out.println("Logged out successfully.");
                                    break;

                                default:
                                    System.out.println("Invalid option. Please try again.");
                            }
                        }
                    }
                    break;

                case 3:
                    System.out.println("Exiting...");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }
}