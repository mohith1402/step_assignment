package week2;

import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;

public class FraudDetector {
    public List<int[]> findTwoSum(int[] amounts, int target) {
        HashMap<Integer, Integer> map = new HashMap<>();
        List<int[]> pairs = new ArrayList<>();

        for (int amt : amounts) {
            int complement = target - amt;
            if (map.containsKey(complement)) {
                pairs.add(new int[]{complement, amt});
            }
            map.put(amt, amt);
        }
        return pairs;
    }

    public static void main(String[] args) {
        FraudDetector detector = new FraudDetector();

        int[] transactions = {1200, 500, 800, 1500, 300, 700};
        int fraudTarget = 2000;

        List<int[]> suspiciousPairs = detector.findTwoSum(transactions, fraudTarget);

        System.out.println("Target Fraud Amount: " + fraudTarget);
        System.out.println("Suspicious Transaction Pairs Found:");

        for (int[] pair : suspiciousPairs) {
            System.out.println("- [" + pair[0] + ", " + pair[1] + "]");
        }
    }
}