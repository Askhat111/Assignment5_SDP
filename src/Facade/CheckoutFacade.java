package Facade;

import java.util.*;
import Decorators.*;
import PaymentTypes.*;
import User.*;

public class CheckoutFacade {
    private final Scanner sc = new Scanner(System.in);
    private final Map<String, Payment> userPayments = new HashMap<>();

    public void mainMenu(User user) {
        while (true) {
            System.out.println("\n1)Add new payment method");
            System.out.println("2)List my payment methods");
            System.out.println("3)Use existing payment method");
            System.out.println("0)Exit");
            System.out.print("Enter option:");
            int choice = safeIntInput();

            if (choice == 0) break;
            if (choice == 1) {
                Payment payment = buildPayment(user);
                System.out.print("Assign a name for this method:");
                String name = sc.next();
                userPayments.put(name, payment);
                System.out.println("Added payment as:" + name);
            }
            if (choice == 2) {
                if (userPayments.isEmpty()) System.out.println("No payments yet.");
                else
                    userPayments.forEach((name, pay) ->
                            System.out.println(name + " — " + pay.getDescription() + " [Balance: " + pay.getBalance() + "]"));
            }
            if (choice == 3) {
                if (userPayments.isEmpty()) {
                    System.out.println("No payment methods! Add one first.");
                    continue;
                }
                System.out.print("Enter payment name:");
                String name = sc.next();
                Payment payment = userPayments.get(name);
                if (payment == null) { System.out.println("Not found."); continue; }
                accountMenu(user, payment);
            }
        }
    }

    public void accountMenu(User user, Payment payment) {
        while (true) {
            System.out.println("\n1)Top up");
            System.out.println("2)Withdraw");
            System.out.println("3)Make a purchase");
            System.out.println("4)Transaction history");
            System.out.println("5)Bonus points");
            System.out.println("0)Exit");
            System.out.print("Enter option:");
            int act = safeIntInput();

            if (act == 0) break;
            if (act == 1) {
                System.out.print("Amount:");
                double sum = safeDoubleInput();
                payment.deposit(sum);
                user.addTransaction(new Transaction("DEPOSIT", sum, true));
            }
            if (act == 2) {
                System.out.print("Amount:");
                double sum = safeDoubleInput();
                boolean ok = payment.withdraw(sum);
                user.addTransaction(new Transaction("WITHDRAW", sum, ok));
            }
            if (act == 3) {
                System.out.print("Purchase amount:");
                double sum = safeDoubleInput();
                boolean success = payment.pay(sum);
                user.addTransaction(new Transaction("PAYMENT", sum, success));
                if (success)
                    System.out.println("Payment successful!");
                else
                    System.out.println("Payment failed.");
            }
            if (act == 4) {
                for (Transaction tx : user.getHistory())
                    System.out.println(tx);
            }
            if (act == 5) {
                System.out.println("Bonus points:" + user.getBonusPoints());
            }
        }
    }

    public Payment buildPayment(User user) {
        System.out.println("Choose payment method (credit/paypal/crypto): ");
        String paymentType = sc.next();
        System.out.print("Enter initial balance:");
        double balance = safeDoubleInput();
        Payment payment = PaymentFactory.createPayment(paymentType, balance);

        System.out.print("Enter discount percentage:");
        double discountPercent = safeDoubleInput();
        payment = new DiscountDecorator(payment, discountPercent);

        System.out.println("Choose cashback type: 1 - Standard (5%), 2 - Premium (10%), 3 - Partner (25%)");
        int cashbackOption = safeIntInput();
        double cashbackPercent = switch (cashbackOption) {
            case 1 -> 5;
            case 2 -> 10;
            case 3 -> 25;
            default -> 0;
        };
        String cashbackName = switch (cashbackOption) {
            case 1 -> "Standard";
            case 2 -> "Premium";
            case 3 -> "Partner";
            default -> "None";
        };
        payment = new CashbackDecorator(payment, cashbackPercent, cashbackName, user);

        System.out.print("Add fraud detection? (y/n):");
        if (sc.next().equalsIgnoreCase("y")) {
            payment = new FraudDetectionDecorator(payment, user, sc);
        }

        System.out.println("Payment ready:" + payment.getDescription());
        return payment;
    }

    private int safeIntInput() {
        while (!sc.hasNextInt()) {
            System.out.print("Enter a number:");
            sc.next();
        }
        return sc.nextInt();
    }

    private double safeDoubleInput() {
        while (!sc.hasNextDouble()) {
            System.out.print("Enter a number:");
            sc.next();
        }
        return sc.nextDouble();
    }
}

