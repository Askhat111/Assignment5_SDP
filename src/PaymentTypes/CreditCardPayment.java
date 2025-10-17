public class CreditCardPayment implements Payment {
    @Override
    public String getDescription() {
        return "Credit Card Payment";
    }
    @Override
    public void pay(double amount) {
        System.out.println("Paid " + amount + " with Credit Card.");
    }
}