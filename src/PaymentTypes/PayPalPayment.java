package PaymentTypes;
public class PayPalPayment implements Payment {
    private double balance;
    public PayPalPayment(double balance) {
        this.balance = balance;
    }
    @Override
    public String getDescription() { return "PayPal Payment"; }
    @Override
    public boolean pay(double amount) {
        if (balance >= amount) {
            balance -= amount;
            System.out.println("Paid " + amount + " via Credit Card. Remaining: " + balance);
            return true;
        } else {
            System.out.println("Credit Card: Insufficient funds!");
            return false;
        }
    }
    @Override
    public double getBalance() { return balance; }
    @Override
    public void deposit(double amount) {
        balance += amount;
        System.out.println("Deposited " + amount + " to PayPal. New balance: " + balance);
    }
}