/*
 * Gabriel Arteaga garteaga@calpoly.edu
 * Alexandra Deany adeany@calpoly.edu
 *
 * Project 1
 * 10/03/2016
 */

import java.util.Random;

public class SortingCounts {
    public static void main(String[] args) {
        final int MAX_SIZE = 12800;
        int[] arr1 = new int[MAX_SIZE];
        int[] arr2 = new int[MAX_SIZE];
        int[] arr3 = new int[MAX_SIZE];
        long selectionSum;
        long mergeSum;
        long quickSum;
        long selectionAvg = 0;
        long mergeAvg = 0;
        long quickAvg = 0;
        Random rand = new Random();
        // int[] test = new int[20];

        // for (int i = 0; i < test.length; i++) {
        //     test[i] = rand.nextInt(test.length);
        // }

        // Sorting.selectionSort(test, test.length);

        // for (int i : test) {
        //     System.out.print(i + " ");
        // }
        // System.out.println();

        System.out.println("Average number of comparisons in three sorting algorithms: ");
        for (int n = 100; n <= MAX_SIZE; n *= 2) {
            selectionSum = 0;
            mergeSum = 0;
            quickSum = 0;
            for (int x = 0; x < 100; x++) {
                for (int i = 0; i < n; i++) {
                    arr1[i] = rand.nextInt(n);
                    arr2[i] = rand.nextInt(n);
                    arr3[i] = rand.nextInt(n);
                }

                Sorting1.selectionSort(arr1, n);
                selectionSum += Sorting1.count;

                Sorting1.mergeSort(arr2, n);
                mergeSum += Sorting1.count;

                Sorting1.quickSort(arr3, n);
                quickSum += Sorting1.count;

                // for (int y = 0; y < 10; y++)
                //     System.out.print(arr3[y] + " ");
                // System.out.println();

                // for (int z = n - 10; z < n; z++)
                //     System.out.print(arr3[z] + " ");
                // System.out.println();
            }

            selectionAvg = selectionSum / n;
            mergeAvg = mergeSum / n;
            quickAvg = quickSum / n; 
            System.out.println("N=" + n + ": C_ss=" + selectionAvg + ", C_ms=" + mergeAvg + ", C_qs=" + quickAvg);
            System.out.println();
        }
    }
}
