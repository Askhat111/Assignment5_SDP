package Decorators;
import PaymentTypes.Payment;

public abstract class PaymentDecorator implements Payment {
    protected final Payment decoratedPayment;

    public PaymentDecorator(Payment payment) {
        this.decoratedPayment = payment;
    }

    @Override
    public String getDescription() {
        return decoratedPayment.getDescription();
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
        return decoratedPayment.pay(amount);
    }

    @Override
    public boolean withdraw(double amount) {
        return decoratedPayment.withdraw(amount);
    }
}

