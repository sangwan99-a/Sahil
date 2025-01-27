
import java.util.*;

class User1 {
    private String username;
    private String password;
    private ShoppingCart cart;

    public User1(String username, String password) {
        this.username = username;
        this.password = password;
        this.cart = ShoppingCart.getInstance();
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public ShoppingCart getCart() {
        return cart;
    }
}

class Product {
    private String name;
    private double price;
    private int stock;

    public Product(String name, double price, int stock) {
        this.name = name;
        this.price = price;
        this.stock = stock;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getStock() {
        return stock;
    }

    public void reduceStock(int quantity) {
        this.stock -= quantity;
    }

    @Override
    public String toString() {
        return name + " - $" + price + " (Stock: " + stock + ")";
    }
}

class ShoppingCart {
    private static ShoppingCart instance;
    private List<Product> products;

    private ShoppingCart() {
        products = new ArrayList<>();
    }

    public static ShoppingCart getInstance() {
        if (instance == null) {
            instance = new ShoppingCart();
        }
        return instance;
    }

    public void addProduct(Product product) {
        products.add(product);
    }

    public void removeProduct(Product product) {
        products.remove(product);
    }

    public List<Product> getProducts() {
        return products;
    }

    public double getTotalPrice() {
        double total = 0;
        for (Product product : products) {
            total += product.getPrice();
        }
        return total;
    }

    public void clearCart() {
        products.clear();
    }
}

class Order {
    private User user;
    private List<Product> products;
    private double totalPrice;

    public Order(User user, List<Product> products) {
        this.user = user;
        this.products = products;
        this.totalPrice = calculateTotalPrice();
    }

    private double calculateTotalPrice() {
        double total = 0;
        for (Product product : products) {
            total += product.getPrice();
        }
        return total;
    }

    public void printOrderDetails() {
        System.out.println("Order for user: " + user.getUsername());
        System.out.println("Products ordered:");
        for (Product product : products) {
            System.out.println(product);
        }
        System.out.println("Total Price: $" + totalPrice);
    }
}

class ShoppingSystem {
    private Map<String, User> users;
    private List<Product> products;

    public ShoppingSystem() {
        users = new HashMap<>();
        products = new ArrayList<>();
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

    public void addProduct(String name, double price, int stock) {
        products.add(new Product(name, price, stock));
        System.out.println("Product added: " + name);
    }

    public void listProducts() {
        System.out.println("Available Products:");
        for (Product product : products) {
            System.out.println(product);
        }
    }

    public Product findProduct(String name) {
        for (Product product : products) {
            if (product.getName().equalsIgnoreCase(name) && product.getStock() > 0) {
                return product;
            }
        }
        return null;
    }
}

public class OnlineShoppingSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ShoppingSystem shoppingSystem = new ShoppingSystem();

        // Adding some initial products to the system
        shoppingSystem.addProduct("Laptop", 999.99, 10);
        shoppingSystem.addProduct("Smartphone", 499.99, 20);
        shoppingSystem.addProduct("Headphones", 199.99, 15);

    }
}