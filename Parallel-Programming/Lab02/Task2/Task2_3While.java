import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Task2_3While {
    public static void main(String[] args) {

        int numThreads = 4;
        SharedData data = new SharedData();

        CounterThread threads[] = new CounterThread[numThreads];

        for (int i = 0; i < numThreads; i++) {
            threads[i] = new CounterThread(data);
            threads[i].start();
        }

        for (int i = 0; i < numThreads; i++) {
            try {
                threads[i].join();
            } catch (InterruptedException e) {
            }
        }
        check_array(data, numThreads);
    }

    // Η μέθοδος παραμένει στατική αλλιώς θα πρέπει να κληθεί μέσα από αντικείμενο
    // Εξάλλου δεν τροποποιεί κάτι, απλώς ελέγχει τα τελικά αποτελέσματα
    static void check_array(SharedData data, int numThreads) {
        int i, errors = 0;
        int end = data.end();

        System.out.println("Checking...");

        for (i = 0; i < end; i++) {
            if (data.array(i) != 1) {
                errors++;
                System.out.printf("%d: %d should be 1\n", i, data.array(i));
            }
        }
        System.out.println(errors + " errors.");
    }
}

/*
 * Δημιουργία κλάσης SharedData
 * Σε αντίθεση με την προηγούμενη υλοποίηση για την κλάση
 * SharedCounterArrayGlobalWhile
 * όπου υπήρχε η κλάση SharedCounter για τον διαμοιρασμό μόνο ενός κοινού
 * counter
 * τώρα πρόσθετουμε και τα υπόλοιπα στοιχεία όπως ο πίνακας, η μεταβλητή end
 * κτλ.
 */
class SharedData {

    private int count;
    private int end;
    private int[] a;

    static Lock lock = new ReentrantLock();

    public SharedData() {
        this.count = 0;
        this.end = 10000;
        this.a = new int[end];
    }

    public int get() {
        return count;
    }

    public void inc() {
        lock.lock();
        try {
            count++;
        } finally {
            lock.unlock();
        }
    }

    public void arrayInc() {
        lock.lock();
        try {
            a[count]++;
        } finally {
            lock.unlock();
        }
    }

    public int end() {
        return end;
    }

    public int array(int i) {
        return a[i];
    }
    /*
     * Η μέθοδος critical() είναι υπεύθυνη για την αύξηση του count και του
     * πίνακα a[] και επιστρέφει true αν το count δεν έχει φτάσει το end
     * αλλιώς επιστρέφει false
     */
    public boolean critical() {
        lock.lock();
        try {
            if(count >= end) return false;
            arrayInc();
            inc();
            return true;
        } finally {
            lock.unlock();
        }
    }
}

class CounterThread extends Thread {
    private SharedData data;

    public CounterThread(SharedData data) {
        this.data = data;
    }

    public void run() {

        while (true) {
            if (!data.critical()) {
                break;
            }
        }
    }
}
