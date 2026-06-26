import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.*;

public class Task1_2 {
    public static void main(String[] args) throws Exception {
        FileInputStream fileInputStream = new FileInputStream("bible.txt");
        BufferedReader in = new BufferedReader(new InputStreamReader(fileInputStream));

        ConcurrentHashMap<String, Integer> mapper = new ConcurrentHashMap<>();

        // get current time
        long start = System.currentTimeMillis();

        in.lines().parallel()
                .filter(str -> str.length() > 10) // φιλτράρουμε τις γραμμές που έχουν μήκος μεγαλύτερο από 10
                .flatMap(str -> Arrays.stream(str.split(" "))) // διαχωρίζουμε τις λέξεις με κενά
                .forEach(str -> mapper.merge(str, 1, Integer::sum)); // μετράμε τις λέξεις και αν δεν υπάρχουν τις προσθέτουμε με 1

        in.close();
        fileInputStream.close();

        // get current time and calculate elapsed time
        long elapsedTimeMillis = System.currentTimeMillis() - start;

        mapper.forEach((key, value) -> System.out.println(key + ":" + value));

        System.out.println("time in ms = " + elapsedTimeMillis);
    }
}

/*
    Παρατηρήσεις:
    * Στον αρχικό κώδικα όπου χρησιμοποιούταν το HashMap, η εκτέλεση του προγράμματος
    * διήρκησε 6560ms.
    * Στον κώδικα με το ConcurrentHashMap, η εκτέλεση του προγράμματος διήρκησε 1554ms, με 4 νήματα.
 */