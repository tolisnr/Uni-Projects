public class Task1_3 {
    public static void main(String[] args) {

        int end = 1000;
        int[] array = new int[end];
        int numThreads = 4;

        CounterThread threads[] = new CounterThread[numThreads];
        // Lock lock = new ReentrantLock();

        for (int i = 0; i < numThreads; i++) {
            threads[i] = new CounterThread(end, array);
            threads[i].start();
        }

        for (int i = 0; i < numThreads; i++) {
            try {
                threads[i].join();
            } catch (InterruptedException e) {
            }
        }

        check_array(end, array, numThreads);
    }

    // Η μέθοδος παραμένει στατική αλλιώς θα πρέπει να κληθεί μέσα από αντικείμενο
    // Εξάλλου δεν τροποποιεί κάτι, απλώς ελέγχει τα τελικά αποτελέσματα
    static void check_array(int end, int array[], int numThreads) {
        int i, errors = 0;

        System.out.println("Checking...");

        for (i = 0; i < end; i++) {
            if (array[i] != numThreads * i) {
                errors++;
                System.out.printf("%d: %d should be %d\n", i, array[i], numThreads * i);
            }
        }
        System.out.println(errors + " errors.");
    }
}

class CounterThread extends Thread {

    int end;
    int array[];

    public CounterThread(int end, int[] array) {
        this.end = end;
        this.array = array;
    }

    public void run() {

        for (int i = 0; i < end; i++) {
            for (int j = 0; j < i; j++) {
                /*
                lock.lock();
                try {
                    array[i]++;
                } finally {
                    lock.unlock();
                }*/
                synchronized(Task1_3.class) {
                    array[i]++;
                }
            }
        }
        /*
         * Παρατηρήσεις:
         * Κλειδώνουμε με χρήση synchronized την κρίσιμη περιοχή.
         * Το κλείδωμα γίνεται στην κλάση Task1_3 διότι αυτήν μοιράζεται σε 
         * όλα τα νήματα. Έτσι δεν μπορούν να τροποποιήσουν τον πίνακα ταυτόχρονα.
         */
    }
}