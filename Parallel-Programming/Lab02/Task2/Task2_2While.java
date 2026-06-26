import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


public class Task2_2While {
    public static void main(String[] args) {

        SharedCounter counter = new SharedCounter();

        int end = 10000;
        int[] array = new int[end];
        int numThreads = 4;

        CounterThread threads[] = new CounterThread[numThreads];

        // Περνάμε τα ορίσματα μέσω της main() στον κατασκευαστή της κλάσης
        // CounterThread
        for (int i = 0; i < numThreads; i++) {
            threads[i] = new CounterThread(end, counter, array);
            threads[i].start();
        }

        for (int i = 0; i < numThreads; i++) {
            try {
                threads[i].join();
            } catch (InterruptedException e) {
            }
        }
        check_array(end, array);
    }

    // Η μέθοδος παραμένει στατική αλλιώς θα πρέπει να κληθεί μέσα από αντικείμενο
    // Εξάλλου δεν τροποποιεί κάτι, απλώς ελέγχει τα τελικά αποτελέσματα
    static void check_array(int end, int array[]) {
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
}

// Η κλάση αυτή περιέχει μόνο την μεταβλητή count και τις μεθόδους get() και
// inc()
// Η μεταβλητή count είναι κοινή για όλα τα νήματα που θα δημιουργηθούν
class SharedCounter {

    static Lock lock = new ReentrantLock();

    int count;

    public SharedCounter() {
        this.count = 0;
    }

    public int get() {
        lock.lock();
        try {
            return count;
        } finally {
            lock.unlock();
        }
    }

    public void inc() {
        lock.lock();
        try {
            count++;
        } finally {
            lock.unlock();
        }
    }
    /*
     * Η μέθοδος critical() ελέγχει αν το count είναι μικρότερο από το end
     * Αν ναι, αυξάνει το count και το αντίστοιχο στοιχείο του πίνακα array[]
     * Αν όχι, επιστρέφει false
     */
    public boolean critical(int end, int array[]) {
        lock.lock();
        try {
            if(count >= end) return false;
            array[count]++;
            inc();
            return true;
        } finally {
            lock.unlock();
        }
    }

}

class CounterThread extends Thread {
    private int end;
    private int array[];
    private SharedCounter counter;

    public CounterThread(int end, SharedCounter counter, int array[]) {
        this.end = end;
        this.counter = counter;
        this.array = array;
    }

    public void run() {

        while (true) {
            if(!counter.critical(end, array)) break;
        }
    }
}
