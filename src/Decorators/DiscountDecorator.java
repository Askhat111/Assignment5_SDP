package Decorators;
import PaymentTypes.Payment;

public class DiscountDecorator extends PaymentDecorator {
    private double percent;

    public DiscountDecorator(Payment payment, double percent) {
        super(payment);
        this.percent = percent;
    }

    @Override
    public String getDescription() {
        return decoratedPayment.getDescription() + " + Discount(" + percent + "%)";
    }

    @Override
    public double getBalance() {
        return decoratedPayment.getBalance();
    }

    @Override
    public void deposit(double amount) {
        decoratedPayment.deposit(amount);
    }

    @Override
    public boolean pay(double amount) {
        double discounted = amount * (1 - percent / 100.0);
        return decoratedPayment.pay(discounted);
    }

    @Override
    public boolean withdraw(double amount) {
        return decoratedPayment.withdraw(amount);
    }
}


