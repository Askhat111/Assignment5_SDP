package Decorators;

import PaymentTypes.Payment;

public class DiscountDecorator extends PaymentDecorator {
    private final double percent;
    public DiscountDecorator(Payment payment, double percent) {
        super(payment);
        this.percent = percent;
    }

    @Override
    public String getDescription() {
        String type = percent >= 0 ? "Discount" : "Surcharge";
        return decoratedPayment.getDescription() + " + " + Math.abs(percent) + "% " + type;
    }

    @Override
    public boolean pay(double amount) {
        double newAmount = amount * (1 - percent / 100.0);
        System.out.println("Applied: " + Math.abs(percent) + "% " + (percent >= 0 ? "Discount" : "Surcharge") + ". Total: " + newAmount);
        return decoratedPayment.pay(newAmount);
    }
}

