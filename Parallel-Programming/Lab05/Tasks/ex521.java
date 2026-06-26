import java.io.File;
import java.io.IOException;
import java.awt.Color;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

// Αποτελέσματα στο τέλος του κώδικα

public class ex521 {
    public static void main(String args[]) {

        String fileNameR = null;
        String fileNameW = null;

        // Input and Output files using command line arguments
        if (args.length != 3) {
            System.out.println("Usage: java RGBtoGrayScale <file to read > <file to write> <threads>");
            System.exit(1);
        }
        fileNameR = args[0];
        fileNameW = args[1];

        int numThreads = Integer.parseInt(args[2]);
        if (numThreads == 0)
            numThreads = Runtime.getRuntime().availableProcessors();

        RGBThread[] threads = new RGBThread[numThreads];

        // Reading Input file to an image
        BufferedImage img = null;
        try {
            img = ImageIO.read(new File(fileNameR));
        } catch (IOException e) {
            System.out.println("Error: Unable to read the input file: " + fileNameR);
            System.exit(1);
        }

        // Start timing
        long startTime = System.currentTimeMillis();

        // Coefficinets of R G B to GrayScale
        double redCoefficient = 0.299;
        double greenCoefficient = 0.587;
        double blueCoefficient = 0.114;

        int block = img.getHeight() / numThreads;
        // Με την μέθοδο getHeight() παίρνουμε το ύψος της εικόνας, δηλαδή το πλήθος των γραμμών
        // Επιλέγουμε προσπέλαση κατά γραμμές για να μην επιβαρυνθεί η μνήμη cache
        int start = 0;
        int end = 0;

        for (int i = 0; i < numThreads; i++) {
            start = i * block;
            end = start + block;
            if (i == (numThreads - 1))
                end = img.getHeight();
            threads[i] = new RGBThread(start, end, img, redCoefficient, greenCoefficient, blueCoefficient);
            // Starting threads
            threads[i].start();
        }

        // Join threads
        for (int i = 0; i < numThreads; i++) {
            try {
                threads[i].join();
            } catch (InterruptedException e) {
            }
        }

        // Stop timing
        long elapsedTimeMillis = System.currentTimeMillis() - startTime;

        // Saving the modified image to Output file
        try {
            File file = new File(fileNameW);
            ImageIO.write(img, "jpg", file);
        } catch (IOException e) {
        }

        System.out.println("Done...");
        System.out.println("time in ms = " + elapsedTimeMillis);
    }
}


class RGBThread extends Thread{ 
    private int start, end;
    private BufferedImage img;
    private double redCoefficient, greenCoefficient, blueCoefficient;

    public RGBThread(int start, int end, BufferedImage img, double redCoefficient, double greenCoefficient, double blueCoefficient) {
        this.start = start;
        this.end = end;
        this.img = img;
        this.redCoefficient = redCoefficient;
        this.greenCoefficient = greenCoefficient;
        this.blueCoefficient = blueCoefficient;
    }

    public void run() {
        for (int y = start; y < end; y++) {
            for (int x = 0; x < img.getWidth(); x++) {
                // Retrieving contents of a pixel
                int pixel = img.getRGB(x, y);
                // Creating a Color object from pixel value
                Color color = new Color(pixel, true);
                // Retrieving the R G B values, 8 bits per r,g,b
                // Calculating GrayScale
                int red = (int) (color.getRed() * redCoefficient);
                int green = (int) (color.getGreen() * greenCoefficient);
                int blue = (int) (color.getBlue() * blueCoefficient);

                int rgb = red + green + blue;

                // Creating new Color object
                color = new Color(rgb, rgb, rgb);
                // Setting new Color object to the image
                img.setRGB(x, y, color.getRGB());
            }
        }
    }
}
/*
 * Εκτελέσεις:
 *    Name:            Size:       1   | 2   | 4   |  8   |       
 * 1. 1house.jpg       1,23MB      281 | 325 | 367 |  302 |
 * 2. 2aerial.jpg      1,68MB      837 | 793 | 499 |  545 |
 * 3. 3tiger.jpg       2,03MB      2335| 1707| 1194|  1165|
 * 4. 4food.jpg        2,58MB      2137| 1355| 965 |  1158|
 * 5. 5landscape.jpg   2,86MB      1496| 1309| 937 |  1103|
 * 6. 6berries.jpg     3,90MB      2278| 1435| 1179|  1611|
 * 7. 7lake.jpg        6,10MB      1874| 1677| 1532|  1190|
 */

 /* Παρατηρήσεις:
    Οι εκτελέσεις έτρεξαν σε Laptop με επεξεργαστή AMD Ryzen 3 3200U με 2 πυρήνες
    και 2 threads ανά πυρήνα. Η συχνότητα του επεξεργαστή είναι 2.60GHz.
    Η μνήμη RAM είναι 10GB και η κάρτα γραφικών είναι AMD Radeon Vega 3 Graphics.
    Για να αυτοματοποιήσω τις εκτελέσεις έγραψα ένα αρχείο batch που εκτελεί το πρόγραμμα
    με 1, 2, 4 και 8 threads. Το αρχείο batch το ονόμασα test.bat και το εκτέλεσα από το cmd.


    Το αρχείο batch περιέχει τις παρακάτω εντολές:
    @echo off
    java ex521 "7lake.jpg" "new.jpg" 1
    java ex521 "7lake.jpg" "new.jpg" 2
    java ex521 "7lake.jpg" "new.jpg" 4
    java ex521 "7lake.jpg" "new.jpg" 8
    pause
*/