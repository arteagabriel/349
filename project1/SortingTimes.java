import java.util.Random;

public class SortingTimes {
    public static void main(String[] args) {
        final int MAX_SIZE = 100;
        int[] arr1 = new int[MAX_SIZE];
        int[] arr2 = new int[MAX_SIZE];
        int[] arr3 = new int[MAX_SIZE];
        long ss;
        long ms = 0;
        long qs = 0;
        long startTime;
        long endTime;
        Random rand = new Random();

        System.out.println("Running Times of three sorting algorithms:");
        for (int n = 5000; n <= 80000; n *= 2) {
            for (int x = 0; x < 5; x++) {
                for (int i = 0; i < n; i++)
                    arr1[i] = rand.nextInt(n);

                startTime = System.nanoTime();
                Sorting.selectionSort(arr1, n);
                endTime = System.nanoTime();
                ss = (endTime - startTime) / 1000000;

                System.out.println("N=" + n + ": T_ss=" + ss + ", T_ms=" + ms + ", T_qs=" + qs);
                for (int y = 0; y < MAX_SIZE; y++)
                    System.out.print(arr1[y] + " ");
                System.out.println();

                for (int z = arr1.length - 10; z < arr1.length; z++)
                    System.out.print(arr1[z] + " ");
                System.out.println();
            }

            System.out.println();
        }
    }
}
