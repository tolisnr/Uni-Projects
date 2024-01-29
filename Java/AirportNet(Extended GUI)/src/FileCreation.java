import java.io.*;
import java.awt.Desktop;

public class FileCreation {
    
    public FileCreation(Airport a, Airport b) {
        try {
            File file = new File(this.generateString(a, b));
            FileWriter writer = new FileWriter(file);

            writer.write(this.generateDetails(a, b));
            writer.close();

            if (Desktop.isDesktopSupported()) {
                Desktop.getDesktop().open(file);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (UnsupportedOperationException e) {
            e.printStackTrace();
        }
        /*
         * In this constructor we create a file with the name of the two airports
         * and we write the details of the flights in it. Then we open the file
         * with the default text editor of the system.
         * The Desktop class is used to open the file, which is a java.awt class.
        */
    }

    private String generateString(Airport a, Airport b) {

        return a.getCity() + "To" + b.getCity() + ".txt";
    }

    private String generateDetails(Airport a, Airport b) {
        String city, airport, destination, direct, indirect = "";
        String details = "";

        city = "CITY: " + a.getCity() + ", " + a.getCountry() + "\n";
        airport = "AIRPORT: " + a.getName() + " (" + a.getCode() + ")\n";
        destination = "DESTINATION: " + b.getCity() + "\n";
        direct = CentralRegistry.getDirectFlightsDetails(a, b);
        indirect = CentralRegistry.getInDirectFlightsDetails(a, b);

        details = city + airport + "\n" + destination + "\n" + direct + "\n" + indirect;

        return details;
    }

}
