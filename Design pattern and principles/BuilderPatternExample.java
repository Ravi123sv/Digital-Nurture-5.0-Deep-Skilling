class Computer {
    private String cpu;
    private String ram;
    private String storage;
    private boolean hasGraphicCard;
    private boolean hasBluetooth;

    private Computer(Builder builder) {
        this.cpu = builder.cpu;
        this.ram = builder.ram;
        this.storage = builder.storage;
        this.hasGraphicCard = builder.hasGraphicCard;
        this.hasBluetooth = builder.hasBluetooth;
    }

    public void showSpec() {
        System.out.println("CPU:" + cpu);
        System.out.println("RAM:" + ram);
        System.out.println("STORAGE:" + storage);
        System.out.println("Has GraphicCard:" + (hasGraphicCard ? "Yes" : "No"));
        System.out.println("Has Bluetooth:" + (hasBluetooth ? "Yes" : "No"));
        System.out.println("-------------------");
    }

    public static class Builder {
        private String cpu;
        private String ram;
        private String storage;
        private boolean hasGraphicCard = false;
        private boolean hasBluetooth = false;

        public Builder(String cpu, String ram, String storage) {
            this.cpu = cpu;
            this.ram = ram;
            this.storage = storage;
        }

        public Builder setGraphicCard(boolean hasGraphicCard) {
            this.hasGraphicCard = hasGraphicCard;
            return this;
        }

        public Builder setBluetooth(boolean hasBluetooth) {
            this.hasBluetooth = hasBluetooth;
            return this;
        }

        public Computer build() {
            return new Computer(this);
        }
    }
}
public class BuilderPatternExample {
    public static void main(String[] args) {
        System.out.println("----Custom computer Builder");
        System.out.println("Building Gaming PC:");
        Computer gamingPC = new Computer.Builder("Intel Core i9", "32GB", "2TB SSD")
                .setGraphicCard(true)
                .setBluetooth(true)
                .build();
        gamingPC.showSpec();

        System.out.println("Building Office PC:");
        Computer officePC = new Computer.Builder("Intel Core i5", "8GB", "512GB SSD")
                .build();
        officePC.showSpec();
    }
}