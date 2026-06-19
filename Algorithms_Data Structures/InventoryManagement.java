import java.util.HashMap;

class Product {
    private String productId;
    private String productName;
    private int quantity;
    private double price;

    public Product(String productId, String productName, int quantity, double price) {
        this.productId = productId;
        this.productName = productName;
        this.quantity = quantity;
        this.price = price;
    }

    public String getProductId() { return productId; }
    public void setProductId(String productId) { this.productId = productId; }

    public String getProductName() { return productName; }
    public void setProductName(String productName) { this.productName = productName; }

    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    @Override
    public String toString() {
        return "[" + productId + "] " + productName + " (Qty: " + quantity + ", $" + price + ")";
    }
}

public class InventoryManagement {

    private HashMap<String, Product> inventory = new HashMap<>();

    public void addProduct(Product product) {
        inventory.put(product.getProductId(), product);
        System.out.println("-> Added: " + product.getProductName());
    }

    public void updateProduct(String productId, int newQuantity, double newPrice) {
        if (inventory.containsKey(productId)) {
            Product p = inventory.get(productId);
            p.setQuantity(newQuantity);
            p.setPrice(newPrice);
            System.out.println("-> Updated: " + p.getProductName());
        } else {
            System.out.println("-> Error: Product ID " + productId + " not found.");
        }
    }

    public void deleteProduct(String productId) {
        if (inventory.containsKey(productId)) {
            Product removed = inventory.remove(productId);
            System.out.println("-> Deleted: " + removed.getProductName());
        } else {
            System.out.println("-> Error: Product ID " + productId + " not found.");
        }
    }

    public void showInventory() {
        System.out.println("\n--- Current Inventory ---");
        for (Product p : inventory.values()) {
            System.out.println(p.toString());
        }
        System.out.println("-------------------------\n");
    }

    public static void main(String[] args) {
        InventoryManagement warehouse = new InventoryManagement();

        Product p1 = new Product("A100", "Wireless Mouse", 50, 25.99);
        Product p2 = new Product("B200", "Mechanical Keyboard", 30, 89.99);

        warehouse.addProduct(p1);
        warehouse.addProduct(p2);
        warehouse.showInventory();

        warehouse.updateProduct("A100", 45, 19.99);
        warehouse.showInventory();

        warehouse.deleteProduct("B200");
        warehouse.showInventory();
    }
}