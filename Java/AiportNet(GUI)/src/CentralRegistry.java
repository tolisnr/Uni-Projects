import java.util.ArrayList;

public class CentralRegistry {
    private static ArrayList<Airport> airports = new ArrayList<Airport>();
    private static ArrayList<Flight> flights = new ArrayList<Flight>();
    private static Flight flight;

    public static void addAirport(Airport anAirport) {
        airports.add(anAirport);
    }

    public static void addFlight(Flight aFlight) {
        flight = aFlight;
        flights.add(flight);
    }

    public static Airport getLargestHub() {
        Airport largestHub = airports.get(0);

        for(Airport airport : airports) {
            if(airport.getNumberOfConnections() > largestHub.getNumberOfConnections()) largestHub = airport;
        }
        // This method is supposed to return the airport with the most connections
        return largestHub;
    }

    public static Flight getLongestFlight() {
        Flight longestFlight = flights.get(0);

        for(Flight flight : flights) {
            if(flight.getDuration() > longestFlight.getDuration()) longestFlight = flight;
        }
        // This method is supposed to return the longest flight between two airports
        return longestFlight;
    }

    public static Airport getAirport(String cityName) {
        for(Airport airport : airports) {
            if(airport.getCity().equalsIgnoreCase(cityName)) return airport;
        }

        return null;
    }

    public static String getDirectFlightsDetails(Airport a, Airport b) {
        int numberOfFlights = 0;
        ArrayList<Flight> flightList = new ArrayList<Flight>();

        for(Flight flight : flights) {
            if(flight.contains(a, b)) { 
                numberOfFlights++;
                flightList.add(flight);
            }
        }

        String details = "DIRECT FLIGHTS DETAILS" + "\n";

        if(a.isDirectlyConnectedTo(b)) {
            for(int i = 1; i <= numberOfFlights; i++) {
                details += "[" + i + "]" + flightList.get(i-1) + "\n";
            }
        }
        // This method is a little bit tricky. Firstly, it iterates through the flights ArrayList
        // and checks if the flight contains the two airports. If it does, it adds it to the flightList.
        // Then, it checks if the two airports are directly connected. If they are, it iterates through
        // the flightList and adds the details of the flights to the "details" String.
        return details;
    }

    public static String getInDirectFlightsDetails(Airport a, Airport b) {

        String details = "INDIRECT FLIGHTS through..." + "\n";

        if(a.isInDirectlyConnectedTo(b)) {
            Airport airport = a.getCommonConnections(b).get(0);
            details += "[1]" + airport.getCity() + ", " + airport.getCode() + " airport" + "\n";
        }
        //This method is simplier, than the getDirectFlightsDetails. It checks if Airport a 
        //is indirectly connected to Airport b, and if it is, it takes the first object from the 
        //CommonConnections ArrayList, and adds the details of the airport to the "details" String.

        return details;
    }
}