package Decorators;

import PaymentTypes.Payment;
import User.User;
import java.util.Scanner;

public class FraudDetectionDecorator extends PaymentDecorator {
    private final User user;
    private final Scanner sc;

    public FraudDetectionDecorator(Payment payment, User user, Scanner sc) {
        super(payment);
        this.user = user;
        this.sc = sc;
    }

    @Override
    public String getDescription() {
        return decoratedPayment.getDescription() + " + Fraud Detection";
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
    public boolean withdraw(double amount) {
        return decoratedPayment.withdraw(amount);
    }

    @Override
    public boolean pay(double amount) {
        System.out.println("Fraud check for user: " + user.getUsername());
        System.out.print("Enter password to confirm payment: ");
        String pass = sc.next();

        if (!user.verifyPassword(pass)) {
            System.out.println("Incorrect password!");
            return false;
        }

        System.out.println("Fraud check passed!");
        return decoratedPayment.pay(amount);
    }
}



