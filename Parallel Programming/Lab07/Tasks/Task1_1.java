import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class Task1_1 {
    public static void main(String[] args) throws FileNotFoundException, IOException {

        if (args.length != 2) {
            System.out.println("WordCount  <file name> <number of threads>");
            System.exit(1);
        }

        String fileString = new String(Files.readAllBytes(Paths.get(args[0])));//, StandardCharsets.UTF_8);
        String[] words = fileString.split("[ \n\t\r.,;:!?(){}]");

        int numOfThreads = Integer.parseInt(args[1]);
        if(numOfThreads <= 0) {
            numOfThreads = Runtime.getRuntime().availableProcessors();
        }

        ConcurrentHashMap<String, Integer> map = new ConcurrentHashMap<>();
        //Χρησιμοποιούμε το ConcurrentHashMap για να υποστηρίξουμε την
        //ταυτόχρονη και ασφαλή πρόσβαση από πολλαπλά νήματα

        // get current time
        long start = System.currentTimeMillis();


        int blockSize = words.length / numOfThreads;
        Worker[] workers = new Worker[numOfThreads];

        for (int i = 0; i < numOfThreads; i++) {
            int startIdx = i * blockSize;
            int endIdx = startIdx + blockSize;
            if (i == numOfThreads - 1) endIdx = words.length;

            workers[i] = new Worker(words, startIdx, endIdx, map);
            workers[i].start();
        }

        for (int i = 0; i < numOfThreads; i++) {
            try {
                workers[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        TreeMap<String, Integer> sortedMap = new TreeMap<>(map);
        //Χρησιμοποιούμε το TreeMap για να διατηρήσουμε τη σειρά των κλειδιών

        for (String name: sortedMap.keySet()) {
            String key = name.toString();
            String value = sortedMap.get(name).toString();
            System.out.println(key + "\t " + value);
        }

        // get current time and calculate elapsed time
        long elapsedTimeMillis = System.currentTimeMillis()-start;
        System.out.println("time in ms = "+ elapsedTimeMillis);
    }
}

class Worker extends Thread {
    private String words[];
    private int start;
    private int end;
    private ConcurrentHashMap<String, Integer> map;

    public Worker(String[] words, int start, int end, ConcurrentHashMap<String, Integer> map) {
        this.words = words;
        this.start = start;
        this.end = end;
        this.map = map;
    }

    public void run() {
        for (int counter = start; counter < end; counter++) {
            String key = words[counter].toLowerCase();
            if (key.length() > 0) {
                if (map.get(key) == null) {
                    map.put(key, 1);
                }
                else {
                    int value = map.get(key).intValue();
                    value++;
                    map.put(key, value);
                }
            }
        }
    }
}

/*
  * Παρατηρήσεις:
  * Στον αρχικό κώδικα χρησιμοποιούσαμε το TreeMap για να αποθηκεύσουμε τα αποτελέσματα
  * Εδώ πρώτα χρησιμοποιούμε το ConcurrentHashMap για να υποστηρίξουμε την ταυτόχρονη και ασφαλή πρόσβαση από πολλαπλά νήματα
  * και ύστερα χρησιμοποιούμε το TreeMap για να διατηρήσουμε τη σειρά των κλειδιών
  * Χωρίς παραλληλισμό η εκτέλεση του προγράμματος διαρκεί 6218ms
  * Με 2 νήματα: 4716ms
  * Με 4 νήματα: 4235ms
  * Με 8 νήματα: 3211ms
 */