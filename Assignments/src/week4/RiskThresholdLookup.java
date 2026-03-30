package week4;

import java.util.Arrays;

public class RiskThresholdLookup {

    public static void linearSearch(int[] arr, int target) {
        int comparisons = 0;
        boolean found = false;

        for (int i = 0; i < arr.length; i++) {
            comparisons++;
            if (arr[i] == target) {
                found = true;
                break;
            }
        }

        if (found) {
            System.out.println("Linear: threshold=" + target + " \u2192 found (" + comparisons + " comps)");
        } else {
            System.out.println("Linear: threshold=" + target + " \u2192 not found (" + comparisons + " comps)");
        }
    }

    public static void binarySearch(int[] arr, int target) {
        int low = 0;
        int high = arr.length - 1;
        int floor = -1;
        int ceiling = -1;
        int comparisons = 0;

        while (low <= high) {
            int mid = low + (high - low) / 2;

            comparisons++;
            if (arr[mid] < target) {
                floor = arr[mid];
                low = mid + 1;
            } else {
                comparisons++;
                if (arr[mid] > target) {
                    ceiling = arr[mid];
                    high = mid - 1;
                } else {
                    floor = arr[mid];
                    ceiling = arr[mid];
                    break;
                }
            }
        }

        System.out.println("Binary floor(" + target + "): " + floor + ", ceiling: " + ceiling + " (" + comparisons + " comps)");
    }

    public static void main(String[] args) {
        int[] risks = {10, 25, 50, 100};

        System.out.println("Sorted risks: " + Arrays.toString(risks));

        linearSearch(risks, 30);
        binarySearch(risks, 30);
    }
}