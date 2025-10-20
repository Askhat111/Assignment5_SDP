package Decorators;

import PaymentTypes.Payment;

public abstract class PaymentDecorator implements Payment {
    protected Payment decoratedPayment;
    public PaymentDecorator(Payment payment) {
        this.decoratedPayment = payment;
    }

    @Override
    public String getDescription() {
        return decoratedPayment.getDescription();
    }

    @Override
    public boolean pay(double amount) {
        return decoratedPayment.pay(amount);
    }

    @Override
    public void deposit(double amount) {
        decoratedPayment.deposit(amount);
    }

    @Override
    public double getBalance() {
        return decoratedPayment.getBalance();
    }
}
