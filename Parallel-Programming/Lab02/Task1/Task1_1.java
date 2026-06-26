
public class Task1_1 {

    /*
    static int end = 1000;
    static int[] array = new int[end];
    static int numThreads = 4;
    */

    public static void main(String[] args) {
        
        int end = 1000;
        int[] array = new int[end];
        int numThreads = 4;

        CounterThread threads[] = new CounterThread[numThreads];

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

        /*
        new Task1_1().check_array(end, array, numThreads);
        Εάν η μέθοδος check_array() είναι μη στατική, τότε πρέπει να δημιουργήσουμε ένα αντικείμενο της κλάσης Task1_1
        και να καλέσουμε την μέθοδο check_array() μέσω του αντικειμένου αυτού.
        */
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
            for (int j = 0; j < i; j++)
                array[i]++;
        }
    }
}