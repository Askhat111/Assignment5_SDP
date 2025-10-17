package Decorators;
import PaymentTypes.Payment;

public class DiscountDecorator extends PaymentDecorator {
    private final DiscountStrategy strategy;

    public DiscountDecorator(Payment payment, DiscountStrategy strategy) {
        super(payment);
        this.strategy = strategy;
    }

    @Override
    public String getDescription() {
        return decoratedPayment.getDescription() + " + " + strategy.getDescription();
    }

    @Override
    public void pay(double amount) {
        double discounted = strategy.applyDiscount(amount);
        System.out.println("Applied: " + strategy.getDescription() + ". New total: " + discounted);
        super.pay(discounted);
    }
}