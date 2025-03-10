/*
 * Τα νήματα εκτυπώνουν ταυτόχρονα και συνεπώς τα αποτελέσματα μπερδεύονται
 * μεταξύ τους. Εμφανίζονται γινόμενα ενός νήματος πριν τελειώσει ένα προηγούμενο νήμα.
 * Αυτό συμβαίνει γιατί τα νήματα εκτελούνται ταυτόχρονα και είναι ανεξάρτητα μεταξύ τους.
 * Για να υλοποιηθεί ακολουθιακή εκτέλεση των νημάτων, θα πρέπει να χρησιμοποιηθεί η μέθοδος join()
 * μέσα στο ίδιο for loop που δημιουργούνται τα νήματα.
 * Έτσι θα εκτυπώνονται τα πρώτα 20 πολλαπλάσια ενός νήματος και ύστερα 
 * θα δημιουργείται το επόμενο νήμα.   
*/


public class Task4 {
    public static void main(String[] args) {
        /* allocate array of thread objecst */
        int numThreads = 10;
        Thread[] threads = new Thread[numThreads];

        /* create and start threads */
        for (int i = 0; i < numThreads; ++i) {
            System.out.println("In main: create and start thread " + i);
            threads[i] = new myThread(i);
            threads[i].start();

            //Σε αυτό το σημείο εάν χρησιμοποιηθεί η join() τα νήματα θα εκτελούνται ακολουθιακά
            /*try {
                threads[i].join();
            } catch (InterruptedException e) {
                System.err.println("this should not happen");
            }*/

        }
        /* wait for threads to finish */
        for (int i = 0; i < numThreads; ++i) {
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                System.err.println("this should not happen");
            }
        }

        System.out.println("In main: threads all done");
    }
}

class myThread extends Thread {
    /* instance variables */
    private int myID;

    /* constructor */
    public myThread(int myID) {
        this.myID = myID;
    }

    public void run() {
        int multiply;

        for(int i = 1; i <= 20; i++) {
            multiply = i * (myID + 1);
            System.out.println(i + " * " + (myID + 1) + " = " + multiply);
        }
    }
}