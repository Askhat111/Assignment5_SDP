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
    public boolean pay(double amount) {
        System.out.println("Fraud check for user: " + user.getUsername());
        java.util.Scanner sc = new java.util.Scanner(System.in);
        System.out.print("Enter password to confirm payment: ");
        if (!user.verifyPassword(sc.next())) {
            System.out.println("Fraud alert: Incorrect password!");
            return false;
        }
        return decoratedPayment.pay(amount);
    }
}

