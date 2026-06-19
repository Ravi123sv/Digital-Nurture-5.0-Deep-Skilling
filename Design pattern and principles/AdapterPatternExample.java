interface PaymentProcessor{
    void processPayment(double payment);
}

class PayPalGateway {
    public void sendPayment(double amount){
        System.out.println("Successfully Send: " +amount+ " via paypal");
    }
}
class StripeGateway {
    public void chargeCard(double amount){
        System.out.println("Successfully Charging Card: " +amount+ " via stripe");
    }
}

class PayPalAdaptor implements PaymentProcessor{
    private PayPalGateway payPal;

    public PayPalAdaptor(PayPalGateway payPal){
        this.payPal = payPal;
    }
    @Override
    public void processPayment(double amount) {
        payPal.sendPayment(amount);
    }
}

class StripeAdaptor implements PaymentProcessor{
    private StripeGateway stripe;
    public StripeAdaptor(StripeGateway stripe){
        this.stripe = stripe;
    }
    @Override
    public void processPayment(double amount) {
        stripe.chargeCard(amount);
    }
}

public class AdapterPatternExample  {
    public static void main(String[] args) {
        PayPalGateway oldPayPalSystem =  new PayPalGateway();
        PaymentProcessor payPalAdapter = new PayPalAdaptor(oldPayPalSystem);
        System.out.println("Initiating PayPal Checkout:");
        payPalAdapter.processPayment(50.00);
        System.out.println("\n----------------------------------\n");
        StripeGateway oldStripeSystem = new StripeGateway();
        PaymentProcessor stripeAdapter = new StripeAdaptor(oldStripeSystem);
        System.out.println("Initiating Stripe Checkout:");
        stripeAdapter.processPayment(120.75);
    }
}