public class PayPalPayment implements Payment {
    @Override
    public String getDescription() {
        return "PayPal Payment";
    }
    @Override
    public void pay(double amount) {
        System.out.println("Paid " + amount + " with PayPal.");
    }
}