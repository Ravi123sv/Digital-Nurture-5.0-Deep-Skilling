interface Image {
    void display();
}

class RealImage implements Image {
    private String filename;

    public RealImage(String filename) {
        this.filename = filename;
        loadFromRemoteServer(filename);
    }

    private void loadFromRemoteServer(String filename) {
        System.out.println("-> DOWNLOADING heavy image: " + filename + " (This takes time...)");
        try {
            Thread.sleep(1500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void display() {
        System.out.println("Displaying: " + filename);
    }
}

class ProxyImage implements Image {
    private RealImage realImage;
    private String filename;

    public ProxyImage(String filename) {
        this.filename = filename;
    }

    @Override
    public void display() {
        if (realImage == null) {
            realImage = new RealImage(filename);
        }
        realImage.display();
    }
}

public class ProxyPatternExample {
    public static void main(String[] args) {
        System.out.println("--- Image Viewer Application ---");

        Image image1 = new ProxyImage("4K_Beach_Wallpaper.jpg");
        Image image2 = new ProxyImage("8K_Mountain_View.png");

        System.out.println("\n[ App Opened. Placeholders created, but NO downloads yet. ]");

        System.out.println("\nUser scrolls to Image 1...");
        image1.display();

        System.out.println("\nUser clicks on Image 1 again...");
        image1.display();

        System.out.println("\nUser scrolls to Image 2...");
        image2.display();
    }
}