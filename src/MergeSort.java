public class MergeSort extends Sort {


    private final int[] unsortedArray;

    public MergeSort(int[] unsortedArray) {
        super();

        this.unsortedArray = unsortedArray;


    }

    void merge(int l, int m, int r) {
        int n1 = m - l + 1;
        int n2 = r - m;


        int L[] = new int[n1];
        int R[] = new int[n2];

        for (int i = 0; i < n1; ++i)
            L[i] = unsortedArray[l + i];
        for (int j = 0; j < n2; ++j)
            R[j] = unsortedArray[m + 1 + j];


        int i = 0, j = 0;


        int k = l;
        while (i < n1 && j < n2) {
            if (L[i] <= R[j]) {
                unsortedArray[k] = L[i];
                i++;
            } else {
                unsortedArray[k] = R[j];
                j++;
            }
            k++;
        }


        while (i < n1) {
            unsortedArray[k] = L[i];
            i++;
            k++;
        }

        while (j < n2) {
            unsortedArray[k] = R[j];
            j++;
            k++;
        }
    }

    public int[] sort(int l, int r) {
        if (l < r) {
            int m = l + (r - l) / 2;

            sort(l, m);
            sort(m + 1, r);

            merge(l, m, r);
        }
        return unsortedArray;
    }

}
