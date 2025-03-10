/* 
 * Παρατηρήσεις:
 * 1. Οι κλάσεις TypeA και TypeB κληρονομούν την κλάση PolymorphicThread.
 * 2. Δημιουργούνται 2 πίνακες τύπου Thread, ένας για την κλάση TypeA και ένας για την κλάση TypeB.
 * 3. Δημιουργούνται 10 νήματα τύπου TypeA και 10 νήματα τύπου TypeB.
 * 4. Τα νήματα εκτελούνται ταυτόχρονα και δεν εξασφαλίζεται ότι θα τελειώσουν με την σειρά που δημιουργήθηκαν.
*/


public class Task3 {
    public static void main(String[] args) {
        /* allocate array of thread objects */
        int numThreads = 10;
        Thread[] threadsΑ = new Thread[numThreads];
        Thread[] threadsΒ = new Thread[numThreads];

        
        for (int i = 0; i < numThreads; ++i) {
            // Δημιουργία 10 νημάτων από την κλάση TypeA
            System.out.println("In main: create and start thread " + i + " of TypeA");
            threadsΑ[i] = new TypeA(i);
            threadsΑ[i].start();

            // Δημιουργία 10 νημάτων από την κλάση TypeB
            System.out.println("In main: create and start thread " + i + " of TypeB");
            threadsΒ[i] = new TypeB(i, numThreads);
            threadsΒ[i].start();
        }

        /* Τερματισμός όλων των νημάτων */
        for (int i = 0; i < numThreads; ++i) {
            try {
                threadsΑ[i].join();
                threadsΒ[i].join();
            } catch (InterruptedException e) {
                System.err.println("this should not happen");
            }
        }

        System.out.println("In main: threads all done");
    }
}


abstract class PolymorphicThread extends Thread{
    protected int myID;

    public PolymorphicThread(int myID) {
        this.myID = myID;
    }

    public abstract void run();
    
}

class TypeA extends PolymorphicThread {
    /* thread code */

    public TypeA(int myID) {
        super(myID);
    }

    @Override
    public void run() {
        System.out.println("Hello from thread " + myID + ". My type is A!");
        System.out.println("Thread " + myID + " of type A exits");
    }
}

class TypeB extends PolymorphicThread {
    private int total;

    /* constructor */
    public TypeB(int myID, int totalThreads) {
        super(myID);
        total = totalThreads;
    }
    // Έχω διαφορετικό constructor από την κλάση TypeA!

    /* thread code */
    @Override
    public void run() {
        System.out.println("Hello from thread " + myID + " out of " + total + ". My type is B!");
        System.out.println("Thread " + myID + " of type B exits");

    }
    // Η μέθοδος run() είναι διαφορετική από την κλάση TypeA!
}