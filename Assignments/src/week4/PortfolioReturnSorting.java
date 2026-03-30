package week4;

import java.util.Arrays;

class Asset {
    String id;
    double returnRate;
    double volatility;

    public Asset(String id, double returnRate, double volatility) {
        this.id = id;
        this.returnRate = returnRate;
        this.volatility = volatility;
    }

    @Override
    public String toString() {
        return id + ":" + returnRate + "%";
    }
}

public class PortfolioReturnSorting {
    public static void mergeSort(Asset[] arr, int left, int right) {
        if (left < right) {
            int mid = left + (right - left) / 2;
            mergeSort(arr, left, mid);
            mergeSort(arr, mid + 1, right);
            merge(arr, left, mid, right);
        }
    }

    private static void merge(Asset[] arr, int left, int mid, int right) {
        int n1 = mid - left + 1;
        int n2 = right - mid;
        Asset[] L = new Asset[n1];
        Asset[] R = new Asset[n2];
        for (int i = 0; i < n1; ++i) L[i] = arr[left + i];
        for (int j = 0; j < n2; ++j) R[j] = arr[mid + 1 + j];
        int i = 0, j = 0, k = left;
        while (i < n1 && j < n2) {
            if (L[i].returnRate <= R[j].returnRate) {
                arr[k] = L[i];
                i++;
            } else {
                arr[k] = R[j];
                j++;
            }
            k++;
        }
        while (i < n1) arr[k++] = L[i++];
        while (j < n2) arr[k++] = R[j++];
    }

    public static void quickSort(Asset[] arr, int low, int high) {
        if (low < high) {
            int pi = partition(arr, low, high);
            quickSort(arr, low, pi - 1);
            quickSort(arr, pi + 1, high);
        }
    }

    private static int partition(Asset[] arr, int low, int high) {
        Asset pivot = arr[high];
        int i = (low - 1);
        for (int j = low; j < high; j++) {
            if (arr[j].returnRate > pivot.returnRate ||
                    (arr[j].returnRate == pivot.returnRate && arr[j].volatility < pivot.volatility)) {
                i++;
                Asset temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        Asset temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;
        return i + 1;
    }

    public static void main(String[] args) {
        Asset[] assets = {
                new Asset("AAPL", 12, 1.2),
                new Asset("TSLA", 8, 2.5),
                new Asset("GOOG", 15, 1.1)
        };

        Asset[] mergeArr = assets.clone();
        mergeSort(mergeArr, 0, mergeArr.length - 1);
        System.out.println(Arrays.toString(mergeArr));

        Asset[] quickArr = assets.clone();
        quickSort(quickArr, 0, quickArr.length - 1);
        System.out.println(Arrays.toString(quickArr));
    }
}
