import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;


public class BruteForceStringMatch {

    public static void main(String args[]) throws IOException {
        int numThreads = 0;
    
        if (args.length != 3) {
			System.out.println("BruteForceStringMatch  <file name> <pattern> <numThreads>");
			System.exit(1);
        }

        String fileString = new String(Files.readAllBytes(Paths.get(args[0])));//, StandardCharsets.UTF_8);
        char[] text = new char[fileString.length()]; 
        int n = fileString.length();
        for (int i = 0; i < n; i++) { 
            text[i] = fileString.charAt(i); 
        } 
 
        String patternString = new String(args[1]);                                                
        char[] pattern = new char[patternString.length()]; 
        int m = patternString.length(); 
        for (int i = 0; i < m; i++) { 
            pattern[i] = patternString.charAt(i); 
        }

        try {
            numThreads = Integer.parseInt(args[2]);
            if (numThreads == 0)
                numThreads = Runtime.getRuntime().availableProcessors();
        } catch (NumberFormatException e) {
            System.out.println("Invalid number of threads: " + args[2]);
            System.exit(1);
        }
        
        int matchLength = n - m;
        char[] match = new char[matchLength+1]; 
        for (int i = 0; i <= matchLength; i++) { 
            match[i] = '0'; 
        }

        matchSum sum = new matchSum();
        
        // get current time
		long start = System.currentTimeMillis();
        StrThread[] threads = new StrThread[numThreads];
		
        for(int i = 0 ; i < numThreads; i++) {
            int startIndex = i * (matchLength / numThreads);
            int endIndex = (i + 1) * (matchLength / numThreads);
            if (i == numThreads - 1) {
                endIndex = matchLength;
            }
            threads[i] = new StrThread(startIndex, endIndex, text, pattern, match, sum);
            threads[i].start();
        }
        for (int i = 0; i < numThreads; i++) {
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                System.out.println("Thread interrupted: " + e.getMessage());
            }
        }

        // get current time and calculate elapsed time
		long elapsedTimeMillis = System.currentTimeMillis()-start;
		System.out.println("time in ms = "+ elapsedTimeMillis);
		
		// /* print results
        for (int i = 0; i < matchLength; i++) { 
            if (match[i] == '1') System.out.print(i+" ");
        }
        System.out.println();
        sum.print();
        // */
   }
}

class matchSum { // Κοινός μετρητής για τα matches
    int matchCount;

    public matchSum() {
        this.matchCount = 0;
    }

    public synchronized void inc() { // Με χρήση synchronized για να είναι thread-safe
        this.matchCount++;
    }

    public synchronized void print() {
        System.out.println("Total matches " + this.matchCount);
    }
}

class StrThread extends Thread {
    private int start;
    private int end;
    private char[] text;
    private char[] pattern;
    private char[] match;
    private matchSum sum;

    public StrThread(int start, int end, char[] text, char[] pattern, char[] match, matchSum sum) {
        this.start = start;
        this.end = end;
        this.text = text;
        this.pattern = pattern;
        this.match = match;
        this.sum = sum;
    }

    public void run() {
        for (int j = start; j < end; j++) {
            int i;
            for (i = 0; i < pattern.length && pattern[i] == text[i + j]; i++);
            if (i >= pattern.length) {
                match[j] = '1';
                sum.inc(); // Χρησιμοποιούμε την synchronized μέθοδο inc() για να αυξήσουμε το matchCount
            }
        }
    }
}