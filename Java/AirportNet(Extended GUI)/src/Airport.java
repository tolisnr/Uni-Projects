import java.util.ArrayList;

public class Airport {
    
    private String name;
    private String code;
    private String country;
    private String city;
    private ArrayList<Airport> connectedAirports;
    private ArrayList<String> company;

    public Airport(String name, String code, String city, String country) {
        this.name = name;
        this.code = code;
        this.country = country;
        this.city = city;
        this.company = new ArrayList<String>();
        this.connectedAirports = new ArrayList<Airport>();
    }

    public void connectAirport(Airport other) {
        this.connectedAirports.add(other);  /*  This method is supposed to connect both airports
                                                as long as the flights are bidirectional,
                                                like this case */
        other.connectedAirports.add(this); // If this line is not added, the connection is not bidirectional
                                          // and we get wrong results
    }

    public void addCompany(String aCompany) {
        this.company.add(aCompany); //This method is supposed to add a company to the list of companies
    }

    public boolean isDirectlyConnectedTo(Airport anAirport) {

        for(Airport airport : this.connectedAirports) {
            if(airport.equals(anAirport)) return true;
        }
        // This method iterates through the connectedAirports ArrayList and checks if the "anAirport"
        // is in the list. If it is, it returns true, otherwise it returns false.    
        return false;
    }

    public boolean isInDirectlyConnectedTo(Airport anAirport) {

        for(Airport airport : this.connectedAirports) {
            if(airport.isDirectlyConnectedTo(anAirport)) return true;
        }
        // This method works like a nested loop. It iterates through the connectedAirports ArrayList
        // and takes each airport and checks if it is directly connected to "anAirport". If it is,
        // it returns true, otherwise it returns false.
        return false;
    }

    public ArrayList<Airport> getCommonConnections(Airport anAirport) {
        ArrayList<Airport> commonConnections = new ArrayList<Airport>();

        for(Airport airport : this.connectedAirports) {
            if(airport.isDirectlyConnectedTo(anAirport)) commonConnections.add(airport);
        }
        // This method is supposed to return the common connections between two airports
        // by creating an ArrayList of Airports and adding the common connections to it
        return commonConnections;
    }

    public String getName() {
        return this.name;
    }

    public String getCode() {
        return this.code;
    }

    public String getCity() {
        return this.city;
    }

    public String getCountry() {
        return this.country;
    }

    public int getNumberOfConnections() {
        return this.connectedAirports.size();
    }

    public ArrayList<String> getCompanies() {
        return this.company;
    }

    public String toString() {
        return this.getCity();
    }
}
