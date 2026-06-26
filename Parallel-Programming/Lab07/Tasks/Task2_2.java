public class Task2_2 {
    public static void main(String[] args) {

        int size = 0;
        int numThreads = 0;
        if (args.length != 2) {
            System.out.println("Usage: java SieveOfEratosthenes <size> <number of threads>");
            System.exit(1);
        }

        try {
            size = Integer.parseInt(args[0]);
            numThreads = Integer.parseInt(args[1]);
        }
        catch (NumberFormatException nfe) {
            System.out.println("Integer argument expected");
            System.exit(1);
        }
        if (size <= 0) {
            System.out.println("size should be positive integer");
            System.exit(1);
        }
        if(numThreads <= 0) numThreads = Runtime.getRuntime().availableProcessors();

        boolean[] prime = new boolean[size+1];
        Worker[] threads = new Worker[numThreads];

        for(int i = 2; i <= size; i++)
            prime[i] = true;

        // get current time
        long start = System.currentTimeMillis();

        int limit = (int) Math.sqrt(size) + 1;
        for(int i = 0; i < numThreads; i++) {
            threads[i] = new Worker(i, size, prime, limit, numThreads);
            threads[i].start();
        }

        for(int i = 0; i < numThreads; i++) {
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // get current time and calculate elapsed time
        long elapsedTimeMillis = System.currentTimeMillis()-start;

        int count = 0;
        for(int i = 2; i <= size; i++)
            if (prime[i] == true) {
                //System.out.println(i);
                count++;
            }

        System.out.println("number of primes "+count);
        System.out.println("time in ms = "+ elapsedTimeMillis);
    }
}

class Worker extends Thread {
    private int myID;
    private int size;
    private boolean[] table;
    private int limit;
    private int nThreads;

    public Worker(int myID, int size, boolean[] table, int limit, int nThreads) {
        this.myID = myID;
        this.size = size;
        this.table = table;
        this.limit = limit;
        this.nThreads = nThreads;
    }

    public void run() {
        for (int p = 2 + myID; p <= limit; p += nThreads) {
            if (table[p]) {
                for (int i = p * p; i <= size; i += p)
                    table[i] = false;
            }
        }
    }
}

/*
    * Παρατηρήσεις (για 4 νήματα):
    * Για size=100000 6ms
    * Για size=1000000 14ms
    * Για size=10000000 124ms
    * Για size=100000000 1769ms
 */