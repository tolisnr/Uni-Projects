// Java program for Merge Sort
class MergeSort {
    // Merges two subarrays of arr[].
    // First subarray is arr[l..m]
    // Second subarray is arr[m+1..r]
    void merge(int arr[], int l, int m, int r) {
        // Find sizes of two subarrays to be merged
        int n1 = m - l + 1;
        int n2 = r - m;

        // Create temp arrays
        int L[] = new int[n1];
        int R[] = new int[n2];

        // Copy data to temp arrays
        for (int i = 0; i < n1; ++i)
            L[i] = arr[l + i];
        for (int j = 0; j < n2; ++j)
            R[j] = arr[m + 1 + j];

        // Merge the temp arrays
        // Initial indexes of first and second subarrays
        int i = 0, j = 0;

        // Initial index of merged subarray
        int k = l;
        while (i < n1 && j < n2) {
            if (L[i] <= R[j]) {
                arr[k] = L[i];
                i++;
            } else {
                arr[k] = R[j];
                j++;
            }
            k++;
        }

        // Copy remaining elements of L[] if any
        while (i < n1) {
            arr[k] = L[i];
            i++;
            k++;
        }

        // Copy remaining elements of R[] if any
        while (j < n2) {
            arr[k] = R[j];
            j++;
            k++;
        }
    }

    // Main function that sorts arr[l..r] using merge()
    void sort(int arr[], int l, int r) {
        if (l < r) {
            // Find the middle point
            int m = l + (r - l) / 2;

            // Sort first and second halves
            sort(arr, l, m);
            sort(arr, m + 1, r);

            // Merge the sorted halves
            merge(arr, l, m, r);
        }
    }

    // A utility function to print array of size n
    static void printArray(int arr[]) {
        int n = arr.length;
        for (int i = 0; i < n; ++i)
            System.out.print(arr[i] + " ");
        System.out.println();
    }

    // Driver code
    public static void main(String args[]) {
//        int arr[] = { 12, 11, 13, 5, 6, 7 };
        int size = Integer.parseInt(args[0]);
        try {
            size = Integer.parseInt(args[0]);
            if (args.length != 1) {
                System.err.println("Usage: java Task2 <size>");
                System.exit(1);
            }
            if (size < 0) {
                System.err.println("Size must be a positive integer");
                System.exit(1);
            }
        } catch (NumberFormatException e) {
            System.err.println("Invalid input: not a number");
            System.exit(1);
        }

        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = (int) (Math.random() * size + 1);
        }

//        System.out.println("Given array is");
//        printArray(arr);

        long startTime = System.currentTimeMillis();

        MergeSort ob = new MergeSort();
        ob.sort(arr, 0, arr.length - 1);

        long endTime = System.currentTimeMillis() - startTime;

        System.out.println("The array is sorted");
        System.out.printf("Time taken is %d ms\n", endTime);

//        System.out.println("\nSorted array is");
//        printArray(arr);
    }
}
