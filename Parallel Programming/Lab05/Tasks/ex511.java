public class ex511 {
    public static void main(String args[]) {
    int size = 1000000;

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
    
    double[] a = new double[size];
    double[] b = new double[size];
    double[] c = new double[size];

    VectorThread[] threads = new VectorThread[numThreads];

    for(int i = 0; i < size; i++) {
        a[i] = 0.0;
		b[i] = 1.0;
        c[i] = 0.5;
    }

    // get current time
    // long startTime = System.currentTimeMillis();
	
    // Στατική κατανομή των block στην main
    int block = size / numThreads;
    int start = 0;
    int end = 0;

    for (int i = 0; i < numThreads; i++) {
        start = i * block;
        end = start + block;
        if (i == (numThreads - 1))
            end = size;
        threads[i] = new VectorThread(start, end, a, b, c);
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

class VectorThread extends Thread {
    private int start, end;
    private double[] a, b, c;

    public VectorThread(int start, int end, double[] a, double[] b, double[] c) {
        this.start = start;
        this.end = end;
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public void run() {
        for (int i = start; i < end; i++) {
            a[i] = b[i] + c[i];
        }
    }
}