public class Task2_1 {
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

        int range = size / numThreads;
        int limit = (int) Math.sqrt(size) + 1;

        for(int i = 0; i < numThreads; i++) {
            int startIndex = 2 + i * range;
            int endIndex = startIndex + range;
            if (i == numThreads - 1) {
                endIndex = size;
            }
            threads[i] = new Worker(startIndex, endIndex, prime, limit);
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

    private int start;
    private int end;
    private int limit;
    private boolean[] prime;

    public Worker(int start, int end, boolean[] prime, int limit) {
        this.start = start;
        this.end = end;
        this.prime = prime;
        this.limit = limit;
    }

    public void run() {
        for (int i = 2; i <= limit; i++) {
            if (prime[i]) {
                for (int j = i * i; j <= end; j += i) {
                    if (j >= start) { // Για να μη βγω εκτός ορίων
                        prime[j] = false;
                    }
                }
            }
        }
    }
}

/*
    Παρατηρήσεις:
    * Για 4 νήματα:         Ακολουθιακό πρόγραμμα
    * 100000: 10ms          5ms
    * 1000000: 59ms         37ms
    * 10000000: 84ms        191ms
    * 100000000: 1563ms     2552ms
 */