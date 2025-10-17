package Decorators;
import PaymentTypes.Payment;

public class FraudDetectionDecorator extends PaymentDecorator {
    private final User user;

    public FraudDetectionDecorator(Payment payment, User user) {
        super(payment);
        this.user = user;
    }

    @Override
    public String getDescription() {
        return decoratedPayment.getDescription() + " + Fraud Detection";
    }

    @Override
    public void pay(double amount) {
        System.out.println("Fraud check for user: " + user.getUsername());
        // Simulate a security prompt
        java.util.Scanner sc = new java.util.Scanner(System.in);
        System.out.print("Enter password to confirm payment: ");
        String input = sc.next();

        if (!user.verifyPassword(input)) {
            System.out.println("Fraud alert: incorrect password! Payment blocked.");
            return;
        }
        System.out.println("Fraud check passed. User authenticated.");
        super.pay(amount);
    }
}
