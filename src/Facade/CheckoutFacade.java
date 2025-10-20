package Facade;

import PaymentTypes.Payment;
import java.util.Scanner;
import Decorators.*;
import PaymentTypes.*;
import User.User;

public class CheckoutFacade {
    private final Scanner sc = new Scanner(System.in);

    public void runCheckout(User user) {
        System.out.println("Choose payment method (credit/paypal/crypto/kaspi/halyk): ");
        String paymentType = sc.next();
        System.out.print("Enter initial balance for this payment method: ");
        double balance = sc.nextDouble();
        Payment payment = PaymentFactory.createPayment(paymentType, balance);

        System.out.print("Enter discount percentage: ");
        double discountPercent = sc.nextDouble();
        payment = new DiscountDecorator(payment, discountPercent);

        System.out.println("Choose cashback type: 1 - Standard (5%), 2 - Premium (10%), 3 - Partner (15%)");
        int cashbackOption = sc.nextInt();
        double cashbackPercent = switch (cashbackOption) {
            case 1 -> 5;
            case 2 -> 10;
            case 3 -> 15;
            default -> 0;
        };
        String cashbackName = switch (cashbackOption) {
            case 1 -> "Standard";
            case 2 -> "Premium";
            case 3 -> "Partner";
            default -> "None";
        };
        payment = new CashbackDecorator(payment, cashbackPercent, cashbackName);

        System.out.print("Add fraud detection? (y/n): ");
        if (sc.next().equalsIgnoreCase("y")) {
            payment = new FraudDetectionDecorator(payment, user);
        }

        System.out.print("Enter amount to pay: ");
        double amount = sc.nextDouble();

        boolean result = payment.pay(amount);
        System.out.println(result ? "Order processed." : "Payment failed.");
        System.out.println("Final balance: " + payment.getBalance());
    }
}

