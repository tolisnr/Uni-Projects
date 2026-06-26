//import java.util.concurrent.locks.Lock;
//import java.util.concurrent.locks.ReentrantLock;

public class Task1_1 {
    static int end = 1000;
    static int[] array = new int[end];
    static int numThreads = 4;

    //static Lock lock = new ReentrantLock();

    public static void main(String[] args) {

        CounterThread threads[] = new CounterThread[numThreads];

        for (int i = 0; i < numThreads; i++) {
            threads[i] = new CounterThread();
            threads[i].start();
        }

        for (int i = 0; i < numThreads; i++) {
            try {
                threads[i].join();
            } catch (InterruptedException e) {
            }
        }
        check_array();
    }

    static void check_array() {
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

    static class CounterThread extends Thread {

        public CounterThread() {
        }

        public void run() {

            for (int i = 0; i < end; i++) {
                for (int j = 0; j < i; j++) {
                    inc(i);
                    /*
                     * Παρατηρήσεις:
                     * Επειδή ο κώδικας υλοποιείται με static classes και static μεταβλητές,
                     * δημιουργείται μία μέθοδος static synchronized void inc(int i) η οποία
                     * είναι στατική άρα όλα τα νήματα εκτελούν τον ίδιο κώδικα. Είναι επίσης
                     * synchronized άρα τα νήματα περιμένουν να τελειώσει το προηγούμενο νήμα
                     * πριν συνεχίσουν. Η λύση αυτή δεν είναι βέλτιστη και υλοποιείται μόνο 
                     * για τις ανάγκες της άσκησης. Θα μπορούσε να γίνει με την χρήση του
                     * synchronized και κλείδωμα στην Task1_1 κλάση.
                     */
                    //array[i]++;
                }
            }
        }

        static synchronized void inc(int i) {
            array[i]++;
        }
    }
}