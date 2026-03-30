package week1;

import java.util.concurrent.ConcurrentHashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class InventoryManager {
    private ConcurrentHashMap<String, Integer> stock = new ConcurrentHashMap<>();
    private Map<String, Integer> waitingList = new LinkedHashMap<>();

    public int checkStock(String productId) {
        return stock.getOrDefault(productId, 0);
    }

    public synchronized String purchaseItem(String productId, int userId) {
        int currentStock = stock.getOrDefault(productId, 0);
        if (currentStock > 0) {
            stock.put(productId, currentStock - 1);
            return "Success";
        } else {
            waitingList.put(String.valueOf(userId), waitingList.size() + 1);
            return "Added to waiting list";
        }
    }

    public static void main(String[] args) {
        InventoryManager manager = new InventoryManager();

        manager.stock.put("PROD123", 2);

        System.out.println("Initial Stock: " + manager.checkStock("PROD123"));
        System.out.println("User 1 purchase: " + manager.purchaseItem("PROD123", 101));
        System.out.println("User 2 purchase: " + manager.purchaseItem("PROD123", 102));
        System.out.println("User 3 purchase: " + manager.purchaseItem("PROD123", 103));

        System.out.println("Waiting List: " + manager.waitingList);
    }
}