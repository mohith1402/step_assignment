package week4;

import java.util.Arrays;

public class AccountIDLookup {
    public static void linearSearch(String[] arr, String target) {
        int first = -1;
        int last = -1;
        int comparisons = 0;
        for (int i = 0; i < arr.length; i++) {
            comparisons++;
            if (arr[i].equals(target)) {
                if (first == -1) first = i;
                last = i;
            }
        }
        System.out.println("Linear first " + target + ": index " + first + " (" + comparisons + " comparisons)");
    }

    public static void binarySearch(String[] arr, String target) {
        Arrays.sort(arr);
        int low = 0;
        int high = arr.length - 1;
        int matchIndex = -1;
        int comparisons = 0;

        while (low <= high) {
            comparisons++;
            int mid = low + (high - low) / 2;
            int cmp = arr[mid].compareTo(target);
            if (cmp == 0) {
                matchIndex = mid;
                break;
            } else if (cmp < 0) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        int count = 0;
        if (matchIndex != -1) {
            count = 1;
            int left = matchIndex - 1;
            while (left >= 0 && arr[left].equals(target)) {
                count++;
                left--;
                comparisons++;
            }
            int right = matchIndex + 1;
            while (right < arr.length && arr[right].equals(target)) {
                count++;
                right++;
                comparisons++;
            }
        }
        System.out.println("Binary " + target + ": index " + matchIndex + " (" + comparisons + " comparisons), count=" + count);
    }

    public static void main(String[] args) {
        String[] logs = {"accB", "accA", "accB", "accC"};
        System.out.println("Sorted logs: " + Arrays.toString(logs));
        linearSearch(logs, "accB");
        binarySearch(logs, "accB");
    }
}