public class Task1_1While {
    /*
    static int end = 10000;
    static int counter = 0;
    static int[] array = new int[end];
    static int numThreads = 4;
    */

    public static void main(String[] args) {

        SharedCounter counter = new SharedCounter();

        int end = 10000;
        int[] array = new int[end];
        int numThreads = 4;

        CounterThread threads[] = new CounterThread[numThreads];

        // Περνάμε τα ορίσματα μέσω της main() στον κατασκευαστή της κλάσης CounterThread
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

// Η κλάση αυτή περιέχει μόνο την μεταβλητή count και τις μεθόδους get() και inc()
// Η μεταβλητή count είναι κοινή για όλα τα νήματα που θα δημιουργηθούν
class SharedCounter {

    int count;

    public SharedCounter() {
        this.count = 0;
    }

    public int get() {
        return count;
    }

    public void inc() {
        count++;
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
            if (counter.get() >= end)
                break;
            array[counter.get()]++;
            counter.inc();
        }
    }
}