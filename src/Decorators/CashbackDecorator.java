package Decorators;

import PaymentTypes.Payment;
import User.User;

public class CashbackDecorator extends PaymentDecorator {
    private final double cashbackPercent;
    private final String cashbackType;
    private final User user;

    public CashbackDecorator(Payment payment, double cashbackPercent, String cashbackType, User user) {
        super(payment);
        this.cashbackPercent = cashbackPercent;
        this.cashbackType = cashbackType;
        this.user = user;
    }

    @Override
    public String getDescription() {
        return decoratedPayment.getDescription() + " + " + cashbackType + " Cashback (" + cashbackPercent + "%)";
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
        boolean result = decoratedPayment.pay(amount);
        if (result) {
            double cashback = amount * cashbackPercent / 100.0;
            user.addBonus(cashback);
            System.out.println("Cashback added: " + cashback + " bonus points. Total: " + user.getBonusPoints());
        }
        return result;
    }

    @Override
    public boolean withdraw(double amount) {
        return decoratedPayment.withdraw(amount);
    }
}




