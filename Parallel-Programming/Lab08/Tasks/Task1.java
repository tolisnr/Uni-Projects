import java.util.concurrent.*;

public class Task1 {
    public static void main(String[] args) {

        long numSteps = 10000;
        double sum = 0.0;

        // parse command line
        if (args.length != 1) {
		    System.err.println("arguments:  number_of_steps");
            System.exit(1);
        }
        try {
		    numSteps = Long.parseLong(args[0]);
        } catch (NumberFormatException e) {
            System.err.println("argument "+ args[0] +" must be long int");
            System.exit(1);
        }

        /* start timing */
        long startTime = System.currentTimeMillis();

        double step = 1.0 / (double)numSteps;
//        /* do computation */
//        for (long i=0; i < numSteps; ++i) {
//            double x = ((double)i+0.5)*step;
//            sum += 4.0/(1.0+x*x);
//        }
        sum = forkPi(numSteps, step);

        double pi = sum * step;

        /* end timing and print result */
        long endTime = System.currentTimeMillis() - startTime;
        System.out.printf("parallel program results with %d steps\n", numSteps);
        System.out.printf("computed pi = %22.20f\n" , pi);
        System.out.printf("difference between estimated pi and Math.PI = %22.20f\n", Math.abs(pi - Math.PI));
        System.out.printf("time to compute = %d ms\n", endTime);
    }

    private static double forkPi(long numSteps, double step) {
        ForkJoinPool pool = new ForkJoinPool(4);
        PiTask task = new PiTask(0, numSteps, step);
        double sum = pool.invoke(task);
        pool.shutdown();
        return sum;
    }
}

class PiTask extends RecursiveTask<Double> {
    private static final int limit = 50000;
    private long start, end;
    private double step;
    private double result;

    public PiTask(long start, long end, double step) {
        this.start = start;
        this.end = end;
        this.step = step;
    }

    @Override
    protected Double compute() {
        if (end - start <= limit) {
            result = computePi(start, end);
        } else {
            long mid = (start + end) / 2;
            PiTask leftTask = new PiTask(start, mid, step);
            PiTask rightTask = new PiTask(mid, end, step);

            leftTask.fork();
            double rightResult = rightTask.compute();
            double leftResult = leftTask.join();
            result += leftResult + rightResult;
        }
        return result;
    }

    private double computePi(long start, long end) {
        double sum = 0.0;
        for (long i = start; i < end; ++i) {
            double x = ((double)i + 0.5) * step;
            sum += 4.0 / (1.0 + x * x);
        }
        return sum;
    }
}

/*
* Χρόνοι εκτέλεσης
*
* Στη στατική κατανομή
* με 4 νήματα       με 8 νήματα
*
* Είσοδος   ms      Είσοδος   ms
* 100000    13      100000    3
* 200000    8       200000    5
* 300000    13      300000    8
* 400000    12      400000    15
*
* Με forkJoin και 4 νήματα
*
* Με κατώφλι 1000       Με κατώφλι 10000        Με κατώφλι 50000
* Είσοδος   ms          Είσοδος   ms            Είσοδος   ms
* 100000    14          100000    6             100000    7
* 200000    9           200000    9             200000    13
* 300000    9           300000    9             300000    9
* 400000    13          400000    10            400000    11
*
*
* Συμπεράσματα
* Σχεδόν ίδιοι χρόνοι εκτέλεσης με τη στατική κατανομή και τη forkJoin
* Ωστόσο, αυτό δεν είναι πανάκεια επειδή τα νούμερα είναι σχετικά μικρά και πολλαπλάσια του 100000
* Παράδειγμα για είσοδο 382944 με κατώφλι 50000 δίνει το μεγαλύτερο χρόνο (26ms) από όλες τις
* προηγούμενες εκτελέσεις. Γενικά, εάν το κατώφλι διαιρεί ακριβώς το πλήθος των βημάτων
* θα έχουμε καλύτερη κατανομή των εργασιών και μικρότερο χρόνο εκτέλεσης.
* Η επιλογή ενός κατωφλιού είναι σχετική με το μέγεθος του προβλήματος. Εδώ η καλύτερη επιλογή
* είναι το 10000 και φαίνεται από τους χρόνους εκτέλεσης.
 */