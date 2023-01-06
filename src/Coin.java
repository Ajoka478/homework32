public enum Coin {
    TWENTY(20),
    FIFTY(50),
    HUNDRED(100);

    private int value;

    public int getValue() {
        return value;
    }
    Coin( int value) {
        this.value = value;
    }
}
