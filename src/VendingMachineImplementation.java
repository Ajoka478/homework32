import java.util.*;
import java.util.stream.Collectors;

public class VendingMachineImplementation {
    private long totalSales;
    private Item currentItem;
    private long currentBalance;
    private Inventory<Item> itemInventory = new Inventory<Item>();
    private final Scanner sc = new Scanner(System.in);

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
        return currentBalance >= currentItem.getPrice();
    }

    public void reset() {
        itemInventory.clear();
        totalSales = 0;
        currentItem = null;
        currentBalance = 0;
    }

    public void printStore() {
        System.out.println("В автомате доступны:");
        System.out.printf("[%s] - %s%n", Item.CHOCOLATE.getPrice(), Item.CHOCOLATE.getName());
        System.out.printf("[%s] - %s%n", Item.CANDY.getPrice(), Item.CANDY.getName());
        System.out.printf("[%s] - %s%n", Item.WATER.getPrice(), Item.WATER.getName());
        System.out.printf("[%s] - %s%n", Item.PEPSI.getPrice(), Item.PEPSI.getName());
        System.out.println();
        System.out.println("Монет на сумму: 0");
    }

    public void printMenu() {
        System.out.println();
        System.out.println("Что хотите сделать:");
        System.out.println("a - закинуть монеты");
        System.out.println("q - выйти");
    }

    public void choiceAction() {
        String choice = sc.nextLine();
        int choiceMoney = 0;
        switch (choice) {
            case "a":
                try {
                    chooseProduct(insertMoney());
                } catch (Exception e) {
                    System.out.println("Choose correct option");
                }
                break;
            case "q":
                break;
            default:
                System.out.println("Wrong command");
                choiceAction();
        }
    }

    public int insertMoney() {
        int choiceMoney = 0;
        try {
            System.out.println("Выберите монету:");
            System.out.println("1 - 20");
            System.out.println("2 - 50");
            System.out.println("3 - 100");
            choiceMoney = sc.nextInt();
            System.out.printf("Монет на сумму: %s", Coin.values()[choiceMoney - 1].getValue());
            System.out.println();
            return choiceMoney;
        } catch (Exception e) {
            System.out.println("Choose correct option");
            insertMoney();
        }
        return 0;
    }

    public void chooseProduct(int choiceMoney) {
//        var itemsStore = new HashMap<String, Integer>();
//        for (Item i : Item.values()) {
//            itemsStore.put(i.getName(), i.getPrice());
//        }
        List<Item> itemList = new ArrayList<>(List.of(Item.values()));
        var filtered = itemList.stream()
                        .filter(item -> choiceMoney>=item.getPrice())
                                .collect(Collectors.toList());
        filtered.forEach(System.out::println);
//        itemsStore.stream()
//                .filter(item -> choiceMoney >= item.getPrice())
//                .collect(Collectors.toSet());
//        itemsStore.forEach(System.out::println);
    }

}
