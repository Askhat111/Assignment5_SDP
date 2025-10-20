package Decorators;

import PaymentTypes.Payment;

public class CashbackDecorator extends PaymentDecorator {
    private final double cashbackPercentage;
    private final String cashbackType;

    public CashbackDecorator(Payment payment, double cashbackPercentage, String cashbackType) {
        super(payment);
        this.cashbackPercentage = cashbackPercentage;
        this.cashbackType = cashbackType;
    }

    @Override
    public String getDescription() {
        return decoratedPayment.getDescription() + " + " + cashbackType + " Cashback (" + cashbackPercentage + "%)";
    }

    @Override
    public boolean pay(double amount) {
        double cashback = amount * (cashbackPercentage / 100.0);
        System.out.println("Applied " + cashbackType + " cashback: " + cashback + " points");
        boolean result = decoratedPayment.pay(amount);

        if (result) {
            System.out.println("Cashback: " +cashback);
        }
        return result;
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
