import java.util.Arrays;
import java.util.List;

public class VendingMachineImplementation {
    private long totalSales;
    private Item currentItem;
    private long currentBalance;
    private Inventory<Item> itemInventory = new Inventory<Item>();

    public VendingMachineImplementation() {
        initialize();
    }

    private void initialize() {
        for (Item i : Item.values()) {
            itemInventory.put(i, 5);
        }
    }

    public long selectItemGetPrice(Item item) {
        if (itemInventory.hasItem(item)) {
            currentItem = item;
        }
        return currentItem.getPrice();
    }

    private Item collectItem() throws NotEnoughMoneyException {
        if (isFullPaid()) {
            itemInventory.deduct(currentItem);
            return currentItem;
        }
        long remainingBalance = currentItem.getPrice() - currentBalance;
        throw new NotEnoughMoneyException("Price not full paid, remaining : ", remainingBalance);
    }

    private boolean isFullPaid() {
        if (currentBalance >= currentItem.getPrice()) {
            return true;
        }
        return false;
    }

    public void reset() {
        itemInventory.clear();
        totalSales = 0;
        currentItem = null;
        currentBalance = 0;
    }

    public void printStore(){
        System.out.println("В автомате доступны:");
        System.out.println(Item.BANANACHIPS);
        System.out.println(Item.CANDY);
        System.out.println(Item.WATER);
    }


}
