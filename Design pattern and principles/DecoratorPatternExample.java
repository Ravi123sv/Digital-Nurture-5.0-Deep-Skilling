interface Notifier {
    void send(String message);
}

class EmailNotifier implements Notifier {
    @Override
    public void send(String message) {
        System.out.println("-> Sending EMAIL: " + message);
    }
}

abstract class NotifierDecorator implements Notifier {
    protected Notifier wrappedNotifier;

    public NotifierDecorator(Notifier wrappedNotifier) {
        this.wrappedNotifier = wrappedNotifier;
    }

    @Override
    public void send(String message) {
        wrappedNotifier.send(message);
    }
}

class SMSNotifierDecorator extends NotifierDecorator {
    public SMSNotifierDecorator(Notifier wrappedNotifier) {
        super(wrappedNotifier);
    }

    @Override
    public void send(String message) {
        super.send(message);
        System.out.println("-> Sending SMS: " + message);
    }
}

class SlackNotifierDecorator extends NotifierDecorator {
    public SlackNotifierDecorator(Notifier wrappedNotifier) {
        super(wrappedNotifier);
    }

    @Override
    public void send(String message) {
        super.send(message);
        System.out.println("-> Sending SLACK MESSAGE: " + message);
    }
}

public class DecoratorPatternExample {
    public static void main(String[] args) {
        System.out.println("--- Notification System ---");

        System.out.println("\n[ Alerting User 1: Basic Preferences ]");
        Notifier simpleNotifier = new EmailNotifier();
        simpleNotifier.send("Your password has been changed.");

        System.out.println("\n[ Alerting User 2: Extra Security ]");
        Notifier smsNotifier = new SMSNotifierDecorator(new EmailNotifier());
        smsNotifier.send("Suspicious login detected.");

        System.out.println("\n[ Alerting the IT Manager: Code Red! ]");
        Notifier superNotifier = new SlackNotifierDecorator(
                new SMSNotifierDecorator(
                        new EmailNotifier()
                )
        );
        superNotifier.send("THE SERVER IS ON FIRE!");
    }
}