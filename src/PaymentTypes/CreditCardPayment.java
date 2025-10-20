package PaymentTypes;

public class CreditCardPayment implements Payment {
    private double balance;
    private String cardType;
    public CreditCardPayment(double balance, String cardType) {
        this.balance = balance;
        this.cardType = cardType;
    }
    @Override
    public String getDescription() {
        return cardType + " Credit Card";
    }

    @Override
    public boolean pay(double amount) {
        if (balance >= amount) {
            balance -= amount;
            System.out.println("Paid " + amount + " via " + cardType + " Credit Card. Remaining: " + balance);
            return true;
        } else {
            System.out.println(cardType + " Credit Card: Insufficient funds!");
            return false;
        }
    }

    @Override
    public double getBalance() {
        return balance;
    }

    @Override
    public void deposit(double amount) {
        balance += amount;
        System.out.println("Deposited " + amount + " to " + cardType + " Credit Card. New balance: " + balance);
    }
}
