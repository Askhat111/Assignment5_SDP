package Decorators;
import PaymentTypes.Payment;

public abstract class PaymentDecorator implements Payment {
    protected Payment decoratedPayment;

    public PaymentDecorator(Payment payment) {
        this.decoratedPayment = payment;
    }
    @Override
    public String getDescription() {
        return decoratedPayment.getDescription();
    }
    @Override
    public void pay(double amount) {
        decoratedPayment.pay(amount);
    }
}