import java.util.ArrayList;
import java.util.List;

interface Observer{
    void update(double price);
}
interface stock{
    void register(Observer o);
    void unregister(Observer o);
    void notifyObservers();
}
class MobileApp implements Observer{
    @Override
    public void update(double price) {
        System.out.println("-> Mobile App alert! New stock price: $" + price);
    }
}
class WebApp implements Observer{
    @Override
    public void update(double price) {
        System.out.println("-> Web App alert! New stock price: $" + price);
    }
}

class StockMarket implements stock{
    private List<Observer> observers = new ArrayList<>();
    private double price;
    @Override
    public void register(Observer o){
        observers.add(o);
    }
    @Override
    public void unregister(Observer o){
        observers.remove(o);
    }
    @Override
    public void notifyObservers(){
        for(Observer o : observers){
            o.update(price);
        }
    }
    public void setPrice(double newPrice){
        this.price = newPrice;
        System.out.println("\n[Stock Market] Price changed to $" + newPrice + ". Notifying subscribers...");
        notifyObservers();
    }
}
public class ObserverpatternExample  {
    public static void main(String[] args) {
        System.out.println("-- Stock Market Monitoring ---");
        StockMarket market = new StockMarket();

        Observer MobileClient = new MobileApp();
        Observer WebClient = new WebApp();
        market.register(MobileClient);
        market.register(WebClient);
        market.setPrice(100);
        market.unregister(MobileClient);
        market.setPrice(120);
    }
}
