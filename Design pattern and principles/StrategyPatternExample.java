interface PaymentStrategy {
    void pay(double amount);
}

class CreditCardPayment implements PaymentStrategy {
    private String cardNumber;

    public CreditCardPayment(String cardNumber){
        this.cardNumber = cardNumber;
    }

    @Override
    public void pay(double amount) {
        System.out.println("-> Paid $" + amount + " using Credit Card ending in " + cardNumber.substring(cardNumber.length() - 4));
    }
}

class PayPalPayment implements PaymentStrategy {
    private String email;

    public PayPalPayment(String email){
        this.email = email;
    }

    @Override
    public void pay(double amount) {
        System.out.println("-> Paid $" + amount + " using PayPal ending in " + email);
    }
}

class PaymentContext {
    private PaymentStrategy currentStrategy;

    public void setPaymentStrategy(PaymentStrategy strategy) {
        this.currentStrategy = strategy;
    }

    public void executePayment(double amount) {
        if(currentStrategy == null){
            System.out.println("Error: Payment Strategy is null");
        } else {
            currentStrategy.pay(amount);
        }
    }
}

public class StrategyPatternExample {
    public static void main(String[] args) {
        System.out.println("--- E-Commerce Checkout System ---\n");


        PaymentContext checkoutCart = new PaymentContext();
        double cartTotal = 150.75;

        System.out.println("User selected Credit Card...");

        checkoutCart.setPaymentStrategy(new CreditCardPayment("1234567890129988"));

        checkoutCart.executePayment(cartTotal);

        System.out.println("\nUser changed their mind and selected PayPal...");
        checkoutCart.setPaymentStrategy(new PayPalPayment("user@example.com"));
        checkoutCart.executePayment(cartTotal);
    }
}