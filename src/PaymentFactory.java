import PaymentTypes.*;
public class PaymentFactory {
    public static Payment createPayment(String type, double initialBalance) {
        switch (type.toLowerCase()) {
            case "credit": return new CreditCardPayment(initialBalance);
            case "paypal": return new PayPalPayment(initialBalance);
            case "crypto": return new CryptoPayment(initialBalance);
            default:
                throw new IllegalArgumentException("Invalid payment type.");
        }
    }
}

