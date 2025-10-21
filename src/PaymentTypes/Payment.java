package PaymentTypes;
public interface Payment {
    String getDescription();
    double getBalance();
    void deposit(double amount);
    boolean withdraw(double amount);
    boolean pay(double amount);
}