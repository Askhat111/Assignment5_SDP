import PaymentTypes.Payment;

public class CashbackDecorator extends PaymentDecorator {
    public CashbackDecorator(Payment payment) {
        super(payment);
    }
    @Override
    public String getDescription() {
        return decoratedPayment.getDescription() + " + Cashback";
    }
    @Override
    public boolean pay(double amount) {
        System.out.println("Cashback: " + (amount * 0.05) + " points");
        return decoratedPayment.pay(amount);
    }
}
