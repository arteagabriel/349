public class Sorting {
    public static void selectionSort(int[] arr, int size) {
        int temp;

        for (int i = 0; i < size - 1; i++) {
            int minIndex = i;

            for (int j = i + 1; j < size; j++)
                if (arr[j] < arr[minIndex])
                    minIndex = j;

            temp = arr[i];
            arr[i] = arr[minIndex];
            arr[minIndex] = temp;
        }
    }

    public static void mergeSort(int[] arr, int size) {}

    public static void quickSort(int[] arr, int size) {}
}
