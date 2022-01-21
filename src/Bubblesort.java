public class Bubblesort extends Sort {


    private final int[] unsortedArray;

    public Bubblesort(int[] unsortedArray) {
        super();

        this.unsortedArray = unsortedArray;


    }

    public int[] sort() {
        boolean issorted = false;
        while (!issorted) {
            issorted = true;
            for (int j = 0; j < SortTemplate.SIZE - 1; j++) {
                if (unsortedArray[j] < unsortedArray[j + 1]) {
                    swap(unsortedArray, j, j + 1);
                    issorted = false;
                }
            }
        }

        reverse(unsortedArray);
        return unsortedArray;
    }
}
