/* 
 * Ερώτημα 1ο
 * Αν δεν χρησιμοποιήσουμε δομή δεδομένων για τα νήματα τότε δεν θα είναι δυνατό να κρατήσουμε αναφορά σε αυτά κάθε φορά
 * που δημιουργείται ένα νέο νήμα. Συνεπώς η κλήση της μεθόδου join() δεν θα είναι δυνατή. Έτσι το νήμα της main
 * θα τερματίσει πριν τα υπόλοιπα νήματα.
 *
 * Επίσης, δεν μπορούμε να δημιουργήσουμε (για παράδειγμα) 20 νήματα, χωρίς δομή δεδομένων (τύπου πίνακα ή λίστας)
 * καθώς θα έπρεπε να δηλώσουμε 20 μεταβλητές τύπου Thread, κάτι που δεν είναι πρακτικό.
*/


/*
 * Task1.java
 *
 * creates threads using a class extending Thread. 
 * 
 */
public class HelloThread {

    public static void main(String[] args) {

        int numThreads = 20;
        Thread thread = new MyThread(); // Δηλώνεται αρχική μεταβλητή thread

        /* create and start threads */
        for (int i = 0; i < numThreads; ++i) {
            System.out.println("In main: create and start thread " + i);
            thread.start(); // Ξεκινάει το νήμα
        }

        /* wait for all threads to finish */
        try {
            for (int i = 0; i < numThreads; ++i) {
                thread.join(); //Σφάλμα: Χρειάζεται να κρατάμε αναφορά στα νήματα
            }
        } catch (InterruptedException e) {
            System.out.println("In main: thread interrupted");
        }

        System.out.println("In main: threads all done"); // Το νήμα της main τερματίζει πριν τα υπόλοιπα νήματα
    }
}

/* class containing code for each thread to execute */
class MyThread extends Thread {

    /* thread code */
    public void run() {
        System.out.println("Hello from thread " + Thread.currentThread().getName());
    }

}
