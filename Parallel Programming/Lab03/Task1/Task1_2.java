public class Task1_2 {
    static int end = 10000;
    static int counter = 0;
    static int[] array = new int[end];
    static int numThreads = 4;

    // static Lock lock = new ReentrantLock();

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
            if (array[i] != 1) {
                errors++;
                System.out.printf("%d: %d should be 1\n", i, array[i]);
            }
        }
        System.out.println(errors + " errors.");
    }

    static class CounterThread extends Thread {

        public CounterThread() {
        }

        public void run() {

            while (true) {
                /*lock.lock();
                try {
                    if (counter >= end)
                        break;
                    array[counter]++;
                    counter++;
                } finally {
                    lock.unlock();
                }*/
                if (counter >= end)
                    break;
                inc();
            }

            /*
             * Παρατηρήσεις:
             * Και πάλι κλειδώνουμε την κρίσιμη περιοχή με static synchronized μέθοδο.
             * Τα νήματα εκτελούν τον ίδιο κώδικα και δεν παρεμβάλουν λόγω του ότι η 
             * μέθοδος είναι synchronized.
             * Η λύση αυτή είναι μόνο για να δείξουμε ότι μπορεί να γίνει και με αυτόν τον τρόπο.
             * Είναι πολύ πιο απλό να χρησιμοποιήσουμε synchronized(Task1_2.class) { }.
             */
        }
        
        static synchronized void inc() {
            if (counter < end) {
                array[counter]++;
                counter++;
            }
        }
    }
}