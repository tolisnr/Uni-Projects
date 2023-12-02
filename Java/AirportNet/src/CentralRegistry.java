import java.util.ArrayList;

public class CentralRegistry {
    private static ArrayList<Airport> airports = new ArrayList<Airport>();
    private static ArrayList<Flight> flights = new ArrayList<Flight>();

    public static void addAirport(Airport anAirport) {
        airports.add(anAirport);
    }

    public static void addFlight(Flight aFlight) {
        flights.add(aFlight);
    }

    public static Airport getLargestHub() {
        Airport largestHub = airports.get(0);

        for(Airport airport : airports) {
            if(airport.getNumberOfConnections() > largestHub.getNumberOfConnections()) largestHub = airport;
        }

        return largestHub;
    }

    public static Flight getLongestFlight() {
        Flight longestFlight = flights.get(0);

        for(Flight flight : flights) {
            if(flight.getDuration() > longestFlight.getDuration()) longestFlight = flight;
        }

        return longestFlight;
    }
}
