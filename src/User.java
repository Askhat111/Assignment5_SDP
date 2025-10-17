public class User {
    private final String username;
    private final String password;
    private double balance;

    public User(String username, String password, double balance) {
        this.username = username;
        this.password = password;
        this.balance = balance;
    }
    public String getUsername() { return username; }
    public boolean verifyPassword(String inputPassword) {
        return this.password.equals(inputPassword);
    }
    public double getBalance() { return balance; }
    public boolean spend(double amount) {
        if (balance >= amount) {
            balance -= amount;
            return true;
        }
        return false;
    }
    public void addFunds(double amount) {
        balance += amount;
    }
}

