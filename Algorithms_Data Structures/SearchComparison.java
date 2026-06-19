class Product{
    private int productID;
    private String productName;

    public  Product(int productID, String productName){
        this.productID = productID;
        this.productName = productName;
    }
    public int getProductID() {
        return productID;
    }
    public String getProductName(){
        return  productName;
    }
}

public class SearchComparison{
    public static int linearSearch(Product[] products, int targetId) {
        for (int i = 0; i < products.length; i++) {
            if (products[i].getProductID() == targetId) {
                return i;
            }
        }
        return -1;
    }
    public static int binarySearch(Product[] products, int targetId) {
        int low = 0;
        int high = products.length - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (products[mid].getProductID() == targetId) return mid;
            if (products[mid].getProductID() < targetId) low = mid + 1;
            else high = mid - 1;
        }
        return -1;
    }

    public static void main(String[] args) {
        Product[] products = {
                new Product(101, "Laptop"),
                new Product(102, "Mouse"),
                new Product(103, "Keyboard"),
                new Product(104, "Monitor")
        };

        System.out.println("--- Search Results ---");

        int target = 103;
        int linearResult = SearchComparison.linearSearch(products, target);
        System.out.println("Linear Search found ID " + target + " at index: " + linearResult);

        int binaryResult = binarySearch(products, target);
        System.out.println("Binary Search found ID " + target + " at index: " + binaryResult);
    }
}