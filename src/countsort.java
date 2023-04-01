import java.util.Arrays;
public class countsort {
    public static void main(String[] args) {
        int[] arr = {5, 2, 9, 5, 2, 3, 5};
        System.out.println("Unsorted array: " + Arrays.toString(arr));

        countingSort(arr);
        System.out.println("Sorted array: " + Arrays.toString(arr));
    }

    public static void countingSort(int[] arr) {
        int n = arr.length;

        int maxVal = arr[0];
        for (int i = 1; i < n; i++) {
            if (arr[i] > maxVal) {
                maxVal = arr[i];
            }
        }


        int[] count = new int[maxVal + 1];
        for (int i = 0; i <= maxVal; i++) {
            count[i] = 0;
        }

        for (int i = 0; i < n; i++) {
            count[arr[i]]++;
        }

        for (int i = 1; i <= maxVal; i++) {
            count[i] += count[i - 1];
        }

        int[] sorted = new int[n];
        for (int i = n - 1; i >= 0; i--) {
            sorted[count[arr[i]] - 1] = arr[i];
            count[arr[i]]--;
        }

        for (int i = 0; i < n; i++) {
            arr[i] = sorted[i];
        }
    }
}
