package week3;

import java.util.Arrays;

class Trade {
    String id;
    int volume;

    public Trade(String id, int volume) {
        this.id = id;
        this.volume = volume;
    }

    @Override
    public String toString() {
        return id + ":" + volume;
    }
}

public class HistoricalTradeVolume {
    public static void mergeSort(Trade[] arr, int left, int right) {
        if (left < right) {
            int mid = left + (right - left) / 2;
            mergeSort(arr, left, mid);
            mergeSort(arr, mid + 1, right);
            merge(arr, left, mid, right);
        }
    }

    private static void merge(Trade[] arr, int left, int mid, int right) {
        int n1 = mid - left + 1;
        int n2 = right - mid;
        Trade[] L = new Trade[n1];
        Trade[] R = new Trade[n2];
        for (int i = 0; i < n1; ++i) L[i] = arr[left + i];
        for (int j = 0; j < n2; ++j) R[j] = arr[mid + 1 + j];
        int i = 0, j = 0;
        int k = left;
        while (i < n1 && j < n2) {
            if (L[i].volume <= R[j].volume) {
                arr[k] = L[i];
                i++;
            } else {
                arr[k] = R[j];
                j++;
            }
            k++;
        }
        while (i < n1) { arr[k] = L[i]; i++; k++; }
        while (j < n2) { arr[k] = R[j]; j++; k++; }
    }

    public static void quickSort(Trade[] arr, int low, int high) {
        if (low < high) {
            int pi = partition(arr, low, high);
            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }
    }

    private static int partition(Trade[] arr, int low, int high) {
        int pivot = arr[high].volume;
        int i = (low - 1);
        for (int j = low; j < high; j++) {
            if (arr[j].volume >= pivot) {
                i++;
                Trade temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        Trade temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;
        return i + 1;
    }

    public static Trade[] mergeSortedLists(Trade[] l1, Trade[] l2) {
        Trade[] merged = new Trade[l1.length + l2.length];
        int i = 0, j = 0, k = 0;
        while (i < l1.length && j < l2.length) {
            if (l1[i].volume <= l2[j].volume) {
                merged[k++] = l1[i++];
            } else {
                merged[k++] = l2[j++];
            }
        }
        while (i < l1.length) merged[k++] = l1[i++];
        while (j < l2.length) merged[k++] = l2[j++];
        return merged;
    }

    public static void main(String[] args) {
        Trade[] trades = {new Trade("1", 100), new Trade("2", 300), new Trade("3", 500)};
        mergeSort(trades, 0, trades.length - 1);
        System.out.println(Arrays.toString(trades));

        quickSort(trades, 0, trades.length - 1);
        System.out.println(Arrays.toString(trades));

        long total = 0;
        for(Trade t : trades) total += t.volume;
        System.out.println(total);
    }
}