import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class WordCount {
    public static void main(String[] args) throws FileNotFoundException, IOException {
    
        if (args.length != 1) {
			System.out.println("WordCount  <file name>");
			System.exit(1);
        }
        
        String fileString = new String(Files.readAllBytes(Paths.get(args[0])));//, StandardCharsets.UTF_8);
        String[] words = fileString.split("[ \n\t\r.,;:!?(){}]");    
        
        TreeMap<String, Integer> map = new TreeMap<String, Integer>();
        //HashMap<String, Integer> map = new HashMap<String, Integer>();

        // get current time
        long start = System.currentTimeMillis();
            
         for (int wordCounter = 0; wordCounter < words.length; wordCounter++) {
            String key = words[wordCounter].toLowerCase();
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

        for (String name: map.keySet()) {
			String key = name.toString();
			String value = map.get(name).toString();
    		System.out.println(key + "\t " + value);
        }

        // get current time and calculate elapsed time
        long elapsedTimeMillis = System.currentTimeMillis()-start;
        System.out.println("time in ms = "+ elapsedTimeMillis);
    }
}
