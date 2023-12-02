public class Flight {
    private Airport airportA;
    private Airport airportB;
    private int duration;

    public Flight(Airport airportA, Airport airportB, int duration, String company) {
        this.airportA = airportA;
        this.airportB = airportB;
        this.duration = duration;

        airportA.connectAirport(airportB);
        airportB.connectAirport(airportA);
        
        airportA.addCompany(company);
        airportB.addCompany(company);
    }

    public int getDuration() {
        return this.duration;
    }

    public Airport getAirportA() {
        return this.airportA;
    }

    public Airport getAirportB() {
        return this.airportB;
    }
}
