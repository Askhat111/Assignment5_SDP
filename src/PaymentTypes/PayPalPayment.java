package PaymentTypes;

public class PayPalPayment implements Payment {
    private double balance;
    public PayPalPayment(double balance) {
        this.balance = balance;
    }

    @Override
    public String getDescription() {
        return "PayPal account";
    }

    @Override
    public double getBalance() {
        return balance;
    }

    @Override
    public void deposit(double amount) {
        balance += amount;
        System.out.println("Deposited: " + amount + ". New balance: " + balance);
    }

    @Override
    public boolean withdraw(double amount) {
        if (balance >= amount) {
            balance -= amount;
            System.out.println("Withdrew: " + amount + ". New balance: " + balance);
            return true;
        }
        System.out.println("Not enough for withdrawal.");
        return false;
    }

    @Override
    public boolean pay(double amount) {
        if (balance >= amount) {
            balance -= amount;
            System.out.println("Payment completed using PayPal.");
            return true;
        }
        System.out.println("Not enough for withdrawal in PayPal account.");
        return false;
    }
}
