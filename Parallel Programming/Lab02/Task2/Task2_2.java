import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Task2_2 {
    public static void main(String[] args) {

        int end = 1000;
        int[] array = new int[end];
        int numThreads = 4;

        CounterThread threads[] = new CounterThread[numThreads];
        Lock lock = new ReentrantLock();

        for (int i = 0; i < numThreads; i++) {
            threads[i] = new CounterThread(end, array, lock);
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
    Lock lock;

    //static Lock lock = new ReentrantLock();
    //Στατικό επειδή δεν θέλουμε να δημιουργείται αντικείμενο για το lock
    //σε κάθε thread αλλά να υπάρχει ένα κοινό για να επιτευχθεί το κλείδωμα

    public CounterThread(int end, int[] array, Lock lock) {
        this.end = end;
        this.array = array;
        this.lock = lock;
    }

    public void run() {

        for (int i = 0; i < end; i++) {
            for (int j = 0; j < i; j++) {
                lock.lock();
                try {
                    array[i]++;
                } finally {
                    lock.unlock();
                }
            }
        }
    }
}