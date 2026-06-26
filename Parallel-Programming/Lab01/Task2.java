/*
 * Ερώτημα 2ο
 * Δημιουργούνται δύο κλάσεις που κληρονομούν την κλάση PolymorphicThread.
 * Η κλάση TypeA εκτελεί τον κώδικα που εμφανίζει τον αριθμό του thread και τον τύπο του.
 * Η κλάση TypeB εκτελεί τον κώδικα που εμφανίζει τον αριθμό του thread, τον τύπο του και τον συνολικό αριθμό των threads.
*/

public class Task2 {
    public static void main(String[] args) {
        System.out.println("In main: create and start two threads");

        PolymorphicThread aThread = new TypeA(0);
        aThread.start();

        PolymorphicThread bThread = new TypeB(1, 2);
        bThread.start();

        try {
            aThread.join();
            bThread.join();
        } catch (InterruptedException e) {
        }

        System.out.println("In main: threads are done");
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
        System.out.println("Thread " + myID + " exits");
    }
}

class TypeB extends PolymorphicThread {
    private int total;

    /* constructor */
    public TypeB(int myID, int totalThreads) {
        super(myID);
        total = totalThreads;
    }
    // Έχει διαφορετικό constructor από την κλάση TypeA!

    /* thread code */
    @Override
    public void run() {
        System.out.println("Hello from thread " + myID + " out of " + total + ". My type is B!");
        System.out.println("Thread " + myID + " exits");

    }
    // Η μέθοδος run() είναι διαφορετική από την κλάση TypeA!
}