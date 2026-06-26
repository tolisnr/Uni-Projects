public class Task2_3Lab2 {
    public static void main(String[] args) {

        int numThreads = 4;

        SharedData sharedData = new SharedData(4);
        CounterThread threads[] = new CounterThread[numThreads];

        for (int i = 0; i < numThreads; i++) {
            threads[i] = new CounterThread(sharedData);
            threads[i].start();
        }

        for (int i = 0; i < numThreads; i++) {
            try {
                threads[i].join();
            } catch (InterruptedException e) {
            }
        }
        check_array(sharedData, numThreads);
    }

    static void check_array(SharedData data, int numThreads) {
        int i, errors = 0;

        System.out.println("Checking...");

        for (i = 0; i < data.getEnd(); i++) {
            if (data.get(i) != numThreads * i) {
                errors++;
                System.out.printf("%d: %d should be %d\n", i, data.get(i), numThreads * i);
            }
        }
        System.out.println(errors + " errors.");
    }
}

class SharedData {

    private int end;
    private int[] a;
    private Object[] lock; 

    public SharedData(int numThreads) {
        this.end = 1000;
        this.a = new int[end];
        this.lock = new Object[end];

        for (int i = 0; i < end; i++) {
            lock[i] = new Object();
        }
    }

    public int get(int i) {
        synchronized(lock[i]) {
            return a[i];
        }
    }

    public int getEnd() {
        return end;
    }

    // Προσθήκη αυτής της μεθόδου για να έχουμε κλείδωμα μόνο μέσα στην κλάση
    // SharedData
    public void critical(int i) {
        // critical section
        synchronized(lock[i]) {
            a[i]++;
        }
    }
}

class CounterThread extends Thread {

    private SharedData data;
    private int end;

    public CounterThread(SharedData data) {
        this.data = data;
        this.end = data.getEnd();
    }

    public void run() {

        for (int i = 0; i < end; i++) {
            for (int j = 0; j < i; j++)
                data.critical(i);
        }
    }
}