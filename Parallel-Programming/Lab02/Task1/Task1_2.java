public class Task1_2 {
    /*
    static int end = 1000;
    static int[] array = new int[end];
    static int numThreads = 4;
    */

    public static void main(String[] args) {

        int numThreads = 4;

        SharedData sharedData = new SharedData();
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

        for (i = 0; i < data.end(); i++) {
            if (data.array(i) != numThreads * i) {
                errors++;
                System.out.printf("%d: %d should be %d\n", i, data.array(i), numThreads * i);
            }
        }
        System.out.println(errors + " errors.");
    }
}

class SharedData {

    private int end;
    private int[] a;

    public SharedData() {
        this.end = 1000;
        this.a = new int[end];
    }

    public void arrayInc(int i) {
        a[i]++;
    }

    public int array(int i) {
        return a[i];
    }

    public int end() {
        return end;
    }
}

class CounterThread extends Thread {

    private SharedData data;
    private int end;

    public CounterThread(SharedData data) {
        this.data = data;
        this.end = data.end();
    }

    public void run() {

        for (int i = 0; i < end; i++) {
            for (int j = 0; j < i; j++)
                data.arrayInc(i);
        }
    }
}