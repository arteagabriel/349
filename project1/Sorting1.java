/*
 * Gabriel Arteaga garteaga@calpoly.edu
 * Alexandra Deany adeany@calpoly.edu
 *
 * Project 1
 * 10/03/2016
 */

public class Sorting1 {
    public static long count;

    public static void selectionSort(int[] arr, int size) {
        int temp;
        count = 0;

        for (int i = 0; i < size - 1; i++) {
            int minIndex = i;

            for (int j = i + 1; j < size; j++) {
                count++;
                if (arr[j] < arr[minIndex])
                    minIndex = j;
            }

            temp = arr[i];
            arr[i] = arr[minIndex];
            arr[minIndex] = temp;
        }
    }

    public static void mergeSort(int[] arr, int size) {
        count = 0;
        mergeSort(arr, 0, size - 1);
    }

    private static void mergeSort(int[] arr, int first, int last) {
        if (first < last) {
            int middle = (first + last) / 2;
            mergeSort(arr, first, middle);
            mergeSort(arr, middle + 1, last);
            mergeSortedHalves(arr, first, middle, last);
        }
    }

    private static void mergeSortedHalves(int[] arr, int left, int middle, int right) {
        int[] temp = new int[right - left + 1];
        int ndx1 = left;
        int ndx2 = middle + 1;
        int ndx = 0;

        while (ndx1 < middle + 1 && ndx2 < right + 1) {
            count++;
            if (arr[ndx1] < arr[ndx2]) {
                temp[ndx] = arr[ndx1];
                ndx1++;
            } else {
                temp[ndx] = arr[ndx2];
                ndx2++;
            }

            ndx++;
        }

        if (ndx1 < middle + 1) 
            for (int i = ndx1; i < middle + 1; i++)
                temp[ndx++] = arr[i];
        else
            for (int i = ndx2; i < right + 1; i++)
                temp[ndx++] = arr[i];

        int start = left;
        for (int i = 0; i < temp.length; i++) {
            arr[start] = temp[i];
            start++;
        }
    }

    public static void quickSort(int[] arr, int size) {
        count = 0;
        quickSort(arr, 0, size - 1);
    }

    private static void quickSort(int[] arr, int first, int last) {
        if (first < last) {
            setPivotToEnd(arr, first, last);
            int pivotIndex = splitList(arr, first, last);
            quickSort(arr, first, pivotIndex - 1);
            quickSort(arr, pivotIndex + 1, last);
        }
    }

    private static void setPivotToEnd(int[] arr, int left, int right) {
        int center = (left + right) / 2;
        int temp;

        count++;
        if (arr[center] < arr[left]) {
            temp = arr[center];
            arr[center] = arr[left];
            arr[left] = temp;
        }

        count++;
        if (arr[right] < arr[left]) {
            temp = arr[right];
            arr[right] = arr[left];
            arr[left] = temp;
        }

        count++;
        if (arr[center] < arr[right]) {
            temp = arr[center];
            arr[center] = arr[right];
            arr[right] = temp;
        }
    }

    private static int splitList(int[] arr, int left, int right) {
        int indexL = left;
        int indexR = right - 1;
        int pivot = arr[right];
        int temp;

        while (indexL < indexR) {
            while (arr[indexL] < pivot) {
                indexL++;
                count++;
            }
            count++;

            while (arr[indexR] > pivot) {
                if (indexL < indexR)
                    indexR--;
                else
                    break;

                count++;
            }
            count++;

            if (indexL < indexR) {
                temp = arr[indexL];
                arr[indexL] = arr[indexR];
                arr[indexR] = temp; 
                indexL++;
                indexR--;
            }
        }

        temp = arr[indexL];
        arr[indexL] = arr[right];
        arr[right] = temp;

        return indexL;
    }
}
