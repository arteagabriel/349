import java.util.Random;

public class SortingTimes {
    public static void main(String[] args) {
        final int MAX_SIZE = 80000;
        int[] arr1 = new int[MAX_SIZE];
        int[] arr2 = new int[MAX_SIZE];
        int[] arr3 = new int[MAX_SIZE];
        long ss;
        long ms = 0;
        long qs = 0;
        long startTime;
        long endTime;
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

        System.out.println("Running Times of three sorting algorithms:");
        for (int n = 5000; n <= MAX_SIZE; n *= 2) {
            for (int x = 0; x < 5; x++) {
                for (int i = 0; i < n; i++) {
                    arr1[i] = rand.nextInt(n);
                    arr2[i] = rand.nextInt(n);
                    arr3[i] = rand.nextInt(n);
                }

                startTime = System.nanoTime();
                Sorting.selectionSort(arr1, n);
                endTime = System.nanoTime();
                ss = (endTime - startTime) / 1000000;

                startTime = System.nanoTime();
                Sorting.mergeSort(arr2, n);
                endTime = System.nanoTime();
                ms = (endTime - startTime) / 1000000;

                startTime = System.nanoTime();
                Sorting.quickSort(arr3, n);
                endTime = System.nanoTime();
                qs = (endTime - startTime) / 1000000;

                System.out.println("N=" + n + ": T_ss=" + ss + ", T_ms=" + ms + ", T_qs=" + qs);
                // for (int y = 0; y < 10; y++)
                //     System.out.print(arr3[y] + " ");
                // System.out.println();

                // for (int z = n - 10; z < n; z++)
                //     System.out.print(arr3[z] + " ");
                // System.out.println();
            }

            System.out.println();
        }
    }
}
