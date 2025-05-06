import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Task2_3 {
    public static void main(String[] args) {
        int size = 0;
        int numThreads = 0;

        if (args.length != 2) {
            System.out.println("Usage: java Task2_3 <size> <number of threads>");
            System.exit(1);
        }

        try {
            size = Integer.parseInt(args[0]);
            numThreads = Integer.parseInt(args[1]);
        } catch (NumberFormatException nfe) {
            System.out.println("Integer argument expected");
            System.exit(1);
        }

        if (size <= 0) {
            System.out.println("size should be positive integer");
            System.exit(1);
        }

        if (numThreads <= 0) numThreads = Runtime.getRuntime().availableProcessors();

        boolean[] prime = new boolean[size + 1];
        for (int i = 2; i <= size; i++) {
            prime[i] = true;
        }

        Shared shared = new Shared(size);

        Thread[] threads = new Thread[numThreads];
        long start = System.currentTimeMillis();

        for (int i = 0; i < numThreads; i++) {
            threads[i] = new Thread(new Worker(shared, prime, size));
            threads[i].start();
        }

        for (int i = 0; i < numThreads; i++) {
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        long elapsedTimeMillis = System.currentTimeMillis() - start;

        int count = 0;
        for (int i = 2; i <= size; i++) {
            if (prime[i]) {
                count++;
            }
        }

        System.out.println("number of primes " + count);
        System.out.println("time in ms = " + elapsedTimeMillis);
    }
}

class Shared {
    private int currentTask = 2; //ξεκινάμε από το 2
    private int limit;
    private static Lock lock = new ReentrantLock();

    public Shared(int size) {
        this.limit = (int) Math.sqrt(size) + 1; //το μέγιστο όριο ελέγχου
        //το υπολογίζουμε σε αυτήν την κλάση και όχι στη Worker επειδή ουσιαστικά σε
        //αυτήν υλοποιείται το πρώτο loop
    }

    public int getTask() {
        lock.lock();
        try {
            if (currentTask <= limit) {
                return currentTask++;
            } else {
                return -1;
            }
        } finally {
            lock.unlock();
        }
    }
}

class Worker implements Runnable {
    private static Shared shared;
    private boolean[] prime;
    private int size;

    public Worker(Shared shared, boolean[] prime, int size) {
        this.shared = shared;
        this.prime = prime;
        this.size = size;
    }

    public void run() {
        int task;
        while ((task = shared.getTask()) != -1) {
            if (prime[task]) {
                for (int i = task * task; i <= size; i += task) {
                    prime[i] = false;
                }
            }
        }
    }
}

/*
  * Παρατηρήσεις:
  * Για 4 νήματα
  * size = 100000 8ms
  * size = 1000000 20ms
  * size = 10000000 80ms
  * size = 100000000 1248ms
 */