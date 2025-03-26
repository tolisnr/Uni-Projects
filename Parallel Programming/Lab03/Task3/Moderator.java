public class Moderator {

    /*
     * Παρατηρήσεις:
     * Ο διαιτητής είναι υπεύθυνος για την χορήγηση και αποδέσμευση των πιρουνιών
     * Έχουμε μεταφέρει τα κλειδώματα από τον Philosopher στον Moderator
     * Τα κλειδώματα είναι απαραίτητα για την αποφυγή του deadlock
     */

    public void requestPermission(int philosopherId, Fork leftFork, Fork rightFork) {
        // Κλειδώνουμε πρώτα το μικρότερο και μετά το μεγαλύτερο πιρούνι
        if (leftFork.getIdentity() < rightFork.getIdentity()) {
            leftFork.get();
            rightFork.get();
        } else {
            rightFork.get();
            leftFork.get();
        }
        System.out.println("Moderator grants permission to Philosopher " + philosopherId);
    }

    public void releasePermission(int philosopherId, Fork leftFork, Fork rightFork) {
        // Ξεκλείδωμα των πιρουνιών
        leftFork.put();
        rightFork.put();
        System.out.println("Moderator releases permission for Philosopher " + philosopherId);
    }
}