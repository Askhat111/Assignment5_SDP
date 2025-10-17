import PaymentTypes.Payment;
import java.util.Scanner;
public class CheckoutFacade {
    private final Scanner sc = new Scanner(System.in);
    public void runCheckout(User user) {
        System.out.println("Choose payment method (credit/paypal/crypto): ");
        String type = sc.next();
        System.out.print("Enter initial balance for this payment method: ");
        double balance = sc.nextDouble();
        Payment payment = PaymentFactory.createPayment(type, balance);

        System.out.print("Enter discount percentage (e.g., 10 or -5): ");
        double percent = sc.nextDouble();
        payment = new DiscountDecorator(payment, percent);

        System.out.print("Add cashback? (y/n): ");
        if (sc.next().equalsIgnoreCase("y")) payment = new CashbackDecorator(payment);

        System.out.print("Add fraud detection? (y/n): ");
        if (sc.next().equalsIgnoreCase("y")) payment = new FraudDetectionDecorator(payment, user);

        System.out.print("Enter amount to pay: ");
        double amount = sc.nextDouble();

        boolean result = payment.pay(amount);
        System.out.println(result ? "Order processed." : "Payment failed.");
        System.out.println("Final balance: " + payment.getBalance());
    }
}
