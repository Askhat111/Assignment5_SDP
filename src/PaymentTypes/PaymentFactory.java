package PaymentTypes;

public class PaymentFactory {
    public static Payment createPayment(String type, double initialBalance) {
        switch (type.toLowerCase()) {
            case "kaspi": return new CreditCardPayment(initialBalance, "Kaspi");
            case "halyk": return new CreditCardPayment(initialBalance, "Halyk");
            case "credit":return new CreditCardPayment(initialBalance, "Standard");
            case "paypal": return new PayPalPayment(initialBalance);
            case "crypto": return new CryptoPayment(initialBalance);
            default: throw new IllegalArgumentException("Invalid payment type.");
        }
    }
}

