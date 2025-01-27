
import java.util.*;

class User2 {
    private String username;
    private String password;
    private List<PaymentMethod> paymentMethods;

    public User2 (String username, String password) {
        this.username = username;
        this.password = password;
        this.paymentMethods = new ArrayList<>();
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public List<PaymentMethod> getPaymentMethods() {
        return paymentMethods;
    }

    public void addPaymentMethod(PaymentMethod paymentMethod) {
        paymentMethods.add(paymentMethod);
    }
}

class PaymentMethod {
    private String type; // e.g., "Credit Card", "PayPal"
    private String details; // e.g., card number or PayPal email

    public PaymentMethod(String type, String details) {
        this.type = type;
        this.details = details;
    }

    public String getType() {
        return type;
    }

    public String getDetails() {
        return details;
    }
}

class Payment {
    private User user;
    private double amount;
    private double discount;
    private double lateFee;
    private PaymentMethod paymentMethod;
    private Date dueDate;
    private Date paymentDate;

    public Payment(User user, double amount, double discount, double lateFee, PaymentMethod paymentMethod, Date dueDate) {
        this.user = user;
        this.amount = amount;
        this.discount = discount;
        this.lateFee = lateFee;
        this.paymentMethod = paymentMethod;
        this.dueDate = dueDate;
    }

    public void processPayment(Date paymentDate) {
        this.paymentDate = paymentDate;
        double finalAmount = amount - discount;

        // Check if payment is late
        if (paymentDate.after(dueDate)) {
            finalAmount += lateFee; // Add late fee if payment is late
            System.out.println("Payment is late. A late fee of $" + lateFee + " has been added.");
        }

        if (finalAmount < 0) {
            finalAmount = 0; // Ensure the final amount is not negative
        }
        System.out.println("Processing payment of $" + finalAmount + " for user " + user.getUsername() + " using " + paymentMethod.getType());
        // Here you would integrate with a payment gateway
        System.out.println("Payment successful!");
    }
}

class AutoPaymentSystem {
    private Map<String, User> users;

    public AutoPaymentSystem() {
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

    public void addPaymentMethod(User user, String type, String details) {
        PaymentMethod paymentMethod = new PaymentMethod(type, details);
        User.addPaymentMethod(paymentMethod);
        System.out.println("Payment method added successfully.");
    }

    public void processAutoPayment(User user, double amount, double discount, double lateFee, PaymentMethod paymentMethod, Date dueDate) {
        Payment payment = new Payment(user, amount, discount, lateFee, paymentMethod, dueDate);
        payment.processPayment(new Date()); // Process payment with the current date
    }
}

public class AutoPaymentSystemApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        AutoPaymentSystem autoPaymentSystem = new AutoPaymentSystem();

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
                    autoPaymentSystem.registerUser(regUsername, regPassword);
                    break;

                case 2:
                    System.out.print("Enter username: ");
                    String loginUsername = scanner.nextLine();
                    System.out.print("Enter password: ");
                    String loginPassword = scanner.nextLine();
            }
        }

    }
}