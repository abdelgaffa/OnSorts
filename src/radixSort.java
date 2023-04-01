public class radixSort {
    public static void main(String[] args) {
        int[] numbers = {387, 468, 134, 123, 68, 221, 769, 37, 7};
        radixSort.sort(numbers, numbers);
        int[] expectedSortedNumbers = {7, 37, 68, 123, 134, 221, 387, 468, 769};
        sort(expectedSortedNumbers, numbers);
        System.out.println("Sorted array:");
        for (int i = 0; i < numbers.length; i++) {
            System.out.print(numbers[i] + " ");
        }
    }
    public static void sort(int[] numbers, int[] ints) {
        int maximumNumber = findMaximumNumberIn(numbers);
        int numberOfDigits = calculateNumberOfDigitsIn(maximumNumber);
        int placeValue = 1;
        while (numberOfDigits-- > 0) {
            applyCountingSortOn(numbers, placeValue);
            placeValue *= 10;
        }
    }
    public static int findMaximumNumberIn(int[] numbers) {
        int max = numbers[0];
        for (int i = 1; i < numbers.length; i++) {
            if (numbers[i] > max) {
                max = numbers[i];
            }
        }
        return max;
    }

    public static int calculateNumberOfDigitsIn(int number) {
        if (number == 0) {
            return 1;
        }
        int count = 0;
        while (number != 0) {
            count++;
            number /= 10;
        }
        return count;
    }

    public static void applyCountingSortOn(int[] numbers, int placeValue) {
        int range = 10;
        int[] frequency = new int[range];
        int[] sortedValues = new int[numbers.length];

        for (int i = 0; i < numbers.length; i++) {
            int digit = (numbers[i] / placeValue) % range;
            frequency[digit]++;
        }

        for (int i = 1; i < range; i++) {
            frequency[i] += frequency[i - 1];
        }

        for (int i = numbers.length - 1; i >= 0; i--) {
            int digit = (numbers[i] / placeValue) % range;
            sortedValues[frequency[digit] - 1] = numbers[i];
            frequency[digit]--;
        }

        System.arraycopy(sortedValues, 0, numbers, 0, numbers.length);
    }
}
