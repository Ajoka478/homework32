public enum Item {
    BANANACHIPS("bananachips", 100),
    CANDY("candy", 30),
    WATER("water", 25);
    private String name;
    private int price;

    Item(String name, int price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }
}
