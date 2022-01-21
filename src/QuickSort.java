public class QuickSort extends Sort {
    private final int[] unsortedArray;

    public QuickSort(int[] unsortedArray) {
        super();
        this.unsortedArray = unsortedArray;

    }

    public int[] sort(int low, int high) {
        if (low < high) {
            int pi = partition(low, high);
            sort(low, pi - 1);
            sort(pi + 1, high);
        }
        return unsortedArray;
    }

    private int partition(int low, int high) {
        int pivot = unsortedArray[high];
        int i = (low - 1);
        for (int j = low; j <= high - 1; j++) {
            if (unsortedArray[j] < pivot) {
                i++;
                swap(unsortedArray, i, j);
            }
        }
        swap(unsortedArray, i + 1, high);
        return (i + 1);
    }
}
