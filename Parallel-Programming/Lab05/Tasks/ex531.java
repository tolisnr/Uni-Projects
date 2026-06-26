import java.lang.Math;
// import java.util.LinkedList;
import java.util.concurrent.ConcurrentLinkedQueue;
//Αντικατάσταση της LinkedList με την ConcurrentLinkedQueue για thread-safe αποθήκευση

public class ex531 {

    public static void main(String[] args) {

        int size = 0;
        if (args.length != 2) {
            System.out.println("Usage: java SimpleSat <vector size> <number of threads>");
            System.exit(1);
        }

        try {
            size = Integer.parseInt(args[0]);
        } catch (NumberFormatException nfe) {
            System.out.println("Integer argument expected");
            System.exit(1);
        }
        if (size <= 0) {
            System.out.println("size should be positive integer");
            System.exit(1);
        }
        int iterations = (int) Math.pow(2, size);

        int numThreads = Integer.parseInt(args[1]);
        if (numThreads == 0)
            numThreads = Runtime.getRuntime().availableProcessors();


        // Saves Results but occupies large space
        // LinkedList<String> output = new LinkedList<String>();

        // Use ConcurrentLinkedQueue for thread-safe storage
        ConcurrentLinkedQueue<String> output = new ConcurrentLinkedQueue<>();

        CircuitThread[] threads = new CircuitThread[numThreads];

        long startTime = System.currentTimeMillis();

        int block = iterations / numThreads;
        int start = 0;
        int end = 0;
        for (int i = 0; i < numThreads; i++) {
            start = i * block;
            end = start + block;
            if (i == (numThreads - 1))
                end = iterations;
            threads[i] = new CircuitThread(start, end, size, output);
            // Starting threads
            threads[i].start();
        }

        // Join threads
        for (int i = 0; i < numThreads; i++) {
            try {
                threads[i].join();
            } catch (InterruptedException e) {}
        }

        long elapsedTimeMillis = System.currentTimeMillis() - startTime;

        System.out.println(output);

        System.out.println("All done\n");
        System.out.println("time in ms = " + elapsedTimeMillis);

    }
}

class CircuitThread extends Thread {
    private int start, end;
    private int size;
    private ConcurrentLinkedQueue<String> output;

    public CircuitThread(int start, int end, int size, ConcurrentLinkedQueue<String> output) {
        this.start = start;
        this.end = end;
        this.size = size;
        this.output = output;
    }

    public void run() {
        boolean[] v = new boolean[size];
        for (int i = start; i < end; i++) {
            check_circuit(i, size, output, v);
        }
    }

    private void check_circuit(int start, int size, ConcurrentLinkedQueue<String> output, boolean[] v) {
        // boolean[] v = new boolean[size]; /* Each element is a bit of z */

        for (int i = size - 1; i >= 0; i--)
            v[i] = (start & (1 << i)) != 0;
        
        boolean value = (v[0] || v[1])
                && (!v[1] || !v[3])
                && (v[2] || v[3])
                && (!v[3] || !v[4])
                && (v[4] || !v[5])
                && (v[5] || !v[6])
                && (v[5] || v[6])
                && (v[6] || !v[15])
                && (v[7] || !v[8])
                && (!v[7] || !v[13])
                && (v[8] || v[9])
                && (v[8] || !v[9])
                && (!v[9] || !v[10])
                && (v[9] || v[11])
                && (v[10] || v[11])
                && (v[12] || v[13])
                && (v[13] || !v[14])
                && (v[14] || v[15])
                && (v[14] || v[16])
                && (v[17] || v[1])
                && (v[18] || !v[0])
                && (v[19] || v[1])
                && (v[19] || !v[18])
                && (!v[19] || !v[9])
                && (v[0] || v[17])
                && (!v[1] || v[20])
                && (!v[21] || v[20])
                && (!v[22] || v[20])
                && (!v[21] || !v[20])
                && (v[22] || !v[20]);

        if (value) {
            saveResult(v, size, start, output);
        }
    }

    private void saveResult(boolean[] v, int size, int z, ConcurrentLinkedQueue<String> output) {
        String result = null;
        result = String.valueOf(z);

        for (int i = 0; i < size; i++)
            if (v[i])
                result += " 1";
            else
                result += " 0";

        // Save result
        // output.add("\n" + result);
        output.offer("\n" + result); // Thread-safe add
    }
}

/*
 * Εκτελέσεις:
 *    Size:       1   | 2   | 4   |  8   |
 *    23          290 | 354 | 279 |  461 |
 *    24          697 | 675 | 419 |  633 |
 *    25          1242| 931 | 793 |  699 |
 *    26          1985| 1524| 1696|  1847|
 */

/*
    Παρατηρήσεις: 
    Οι συντομότεροι χρόνοι φαίνεται να συγκλίνουν ανάμεσα στα 2 και 4 νήματα.
    Αυτό οφείλεται στο γεγονός ότι ανάλογα με την CPU του υπολογιστή (στην περίπτωση μου
    2 πυρήνες με 2 νήματα ο καθένας) μπορεί να μεγιστοποιηθεί η χρησιμότητά της.
    Περισσότερα από 8 νήματα δεν φαίνεται να προσφέρουν επιπλέον κέρδος χρόνου.
    Αυτό μπορεί να οφείλεται στο γεγονός ότι η εκτέλεση με περισσότερα νήματα
    απασχολεί περισσότερο από όσο πρέπει την μνήμη και την CPU.
*/