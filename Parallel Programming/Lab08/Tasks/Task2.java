import java.util.concurrent.*;

// Java program for Merge Sort
public class Task2 {
    // Driver code
    public static void main(String[] args) {
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
        forkMerge(arr);
        long endTime = System.currentTimeMillis() - startTime;

//        System.out.println("\nSorted array is");
//        printArray(arr);
        System.out.println("The array is sorted");

        System.out.printf("Time taken is %d ms\n", endTime);
    }

    // A utility function to print array of size n
    private static void printArray(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n; ++i)
            System.out.print(arr[i] + " ");
        System.out.println();
    }

    private static void forkMerge(int[] arr) {
        ForkJoinPool pool = new ForkJoinPool(4);
        MergeTask task = new MergeTask(arr, 0, arr.length - 1);
        pool.invoke(task);
        pool.shutdown();
    }
}

class MergeTask extends RecursiveTask<Void> {
    private int[] arr;
    private int left;
    private int right;
    private static final int limit = 10000; // κατώφλι αποκοπής

    public MergeTask(int[] arr, int left, int right) {
        this.arr = arr;
        this.left = left;
        this.right = right;
    }

    @Override
    protected Void compute() {
        int workLoadSize = right - left;
        if (workLoadSize < limit) {
            // Εάν το μέγεθος του πίνακα είναι μικρότερο από το όριο
            // εκτελεί την ακολουθιακή ταξινόμηση
            sort(arr, left, right);
        } else {
            // Αλλιώς σπάει τις εργασίες σε υποεργασίες
            int mid = left + (right - left) / 2;
            MergeTask leftTask = new MergeTask(arr, left, mid);
            MergeTask rightTask = new MergeTask(arr, mid + 1, right);

            leftTask.fork(); // υπολογίζεται μόνο ο αριστερός κλάδος με παραλληλία
            rightTask.compute(); // υπολογισμός του δεξιού κλάδου ακολουθιακά
            leftTask.join();
        }
        return null;
    }

    // Main function that sorts arr[l..r] using merge()
    private void sort(int[] arr, int l, int r) {
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

    // Merges two subarrays of arr[].
    // First subarray is arr[l...m]
    // Second subarray is arr[m+1...r]
    private void merge(int[] arr, int l, int m, int r) {
        // Find sizes of two subarrays to be merged
        int n1 = m - l + 1;
        int n2 = r - m;

        // Create temp arrays
        int[] L = new int[n1];
        int[] R = new int[n2];

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
}

/*
    * Παρατηρήσεις:
    * Χρησιμοποιείται η τεχνική fork-compute-join για την παράλληλη εκτέλεση της ταξινόμησης
    * Η παραλληλία εφαρμόζεται σε υποδιαίρεση του πίνακα σε υποπίνακες και ύστερα εκτελείται
    * ακολουθιακά ο κλασικός αλγόριθμος merge sort εάν το μέγεθος του υποπίνακα είναι μικρότερο από το όριο
    *
    * Χρόνοι εκτέλεσης:
    * Για 4 νήματα:
    *
    *   Με κατώφλι αποκοπής 2500:       Με κατώφλι αποκοπής 10000:
    *   Είσοδος   ms                    Είσοδος   ms
    *   1000      16                    50000     29
    *   10000     14                    100000    46
    *   50000     30                    200000    77
    *
    * Για 8 νήματα:
    *  Με κατώφλι αποκοπής 2500:       Με κατώφλι αποκοπής 10000:
    *  Είσοδος   ms                    Είσοδος   ms
    *  1000      13                    50000     38
    *  10000     27                    100000    75
    *  50000     48                    200000    68

 */