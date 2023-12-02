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
        this.connectedAirports.add(other);
    }

    public void addCompany(String aCompany) {
        this.company.add(aCompany);
    }

    public boolean isDirectlyConnectedTo(Airport anAirport) {
        boolean result = false;

        for(Airport airport : this.connectedAirports) {
            if(airport.equals(anAirport)) result = true;
        }

        return result;
    }

    public boolean isInDirectlyConnectedTo(Airport anAirport) {
        boolean result = false;

        for(Airport airport : this.connectedAirports) {
            if(airport.isDirectlyConnectedTo(anAirport)) result = true;
        }

        return result;
    }

    public ArrayList<Airport> getCommonConnections(Airport anAirport) {
        ArrayList<Airport> commonConnections = new ArrayList<Airport>();

        for(Airport airport : this.connectedAirports) {
            if(airport.isDirectlyConnectedTo(anAirport)) commonConnections.add(airport);
        }

        return commonConnections;
    }

    public String getName() {
        return this.name;
    }

    public int getNumberOfConnections() {
        return this.connectedAirports.size();
    }

    public void printCompanies() {
        for(String aCompany : company) {
            System.out.println(aCompany);
        }
    }
}
