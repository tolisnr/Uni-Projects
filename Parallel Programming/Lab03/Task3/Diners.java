public class Diners {

    static final int numphils = 5;
    static final int sleeptime = 1;
    
    
     public static void main(String[] args) {
       Philosopher[] phil = new Philosopher[numphils];
       Fork[] fork = new Fork[numphils];
       Moderator moderator = new Moderator(); // Ο διαιτητής

       for (int i = 0; i < numphils; ++i)
            fork[i] = new Fork(i);

       for (int i = 0; i < numphils; ++i) {
            phil[i] = new Philosopher(i, sleeptime,
                        fork[i], fork[(i+1)%numphils], moderator);
            phil[i].start();
       }
    }

}
