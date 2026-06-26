public class Task1_2While {
    /*
     * static int end = 10000;
     * static int counter = 0;
     * static int[] array = new int[end];
     * static int numThreads = 4;
     */

    public static void main(String[] args) {

        int numThreads = 4;
        SharedData data = new SharedData(numThreads);

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
    //Η μέθοδος παραμένει στατική αλλιώς θα πρέπει να κληθεί μέσα από αντικείμενο
    //Εξάλλου δεν τροποποιεί κάτι, απλώς ελέγχει τα τελικά αποτελέσματα
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
 * Σε αντίθεση με την προηγούμενη υλοποίηση για την κλάση SharedCounterArrayGlobalWhile
 * όπου υπήρχε η κλάση SharedCounter για τον διαμοιρασμό μόνο ενός κοινού counter
 * τώρα πρόσθετουμε και τα υπόλοιπα στοιχεία όπως ο πίνακας, η μεταβλητή end κτλ.
 */
class SharedData {

    private int count;
    private int end;
    private int[] a;

    public SharedData(int numThreads) {
        this.count = 0;
        this.end = 10000;
        this.a = new int[end];
    }

    public int get() {
        return count;
    }

    public void inc() {
        count++;
    }

    public void arrayInc() {
        a[count]++;
    }

    public int array(int i) {
        return a[i];
    }

    public int end() {
        return end;
    }

}

class CounterThread extends Thread {
    private SharedData data;

    public CounterThread(SharedData data) {
        this.data = data;
    }

    public void run() {

        while (true) {
            if (data.get() >= data.end())
                break;
            data.arrayInc();
            data.inc();
        }
    }
}