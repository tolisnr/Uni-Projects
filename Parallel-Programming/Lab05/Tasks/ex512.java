public class ex512 {
    public static void main(String args[]) {
        int size = 1000;

        //Σκελετός κώδικα για διαβίβαση δεδομένων από το terminal
        int numThreads = 0;

        if (args.length != 1) {
            System.out.println("Usage: java ThreadParSqrt <number of threads>");
            System.exit(1);
        }

        try {
            numThreads = Integer.parseInt(args[0]);
        } catch (NumberFormatException nfe) {
            System.out.println("Integer argument expected");
            System.exit(1);
        }
        if (numThreads == 0)
            numThreads = Runtime.getRuntime().availableProcessors();

        double[][] a = new double[size][size];
        double[][] b = new double[size][size];
        double[][] c = new double[size][size];

        MatrixThread[] threads = new MatrixThread[numThreads];

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                a[i][j] = 0.1;
                b[i][j] = 0.3;
                c[i][j] = 0.5;
            }
        }

        // get current time
        //long startTime = System.currentTimeMillis();

        // Στατική κατανομή των block στην main
        int block = size / numThreads;
        int start = 0;
        int end = 0;

        for (int i = 0; i < numThreads; i++) {
            start = i * block;
            end = start + block;
            if (i == (numThreads - 1))
                end = size;
            threads[i] = new MatrixThread(start, end, a, b, c);
            threads[i].start();
        }

        // Join threads
        for (int i = 0; i < numThreads; i++) {
            try {
                threads[i].join();
            } catch (InterruptedException e) {
            }
        }

        // get current time and calculate elapsed time
        // long elapsedTimeMillis = System.currentTimeMillis() - startTime;
        // System.out.println("time in ms = " + elapsedTimeMillis);
    }
}

class MatrixThread extends Thread {
    private int start, end;
    private double[][] a, b, c;

    public MatrixThread(int start, int end, double[][] a, double[][] b, double[][] c) {
        this.start = start;
        this.end = end;
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public void run() {
        for (int i = start; i < end; i++) {
            for (int j = 0; j < a[i].length/*size*/; j++) {
                a[i][j] = b[i][j] + c[i][j];
            }
        }
    }
}