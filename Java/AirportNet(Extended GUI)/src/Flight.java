public class Flight {
    private Airport airportA;
    private Airport airportB;
    private int duration;
    private String company;

    public Flight(Airport airportA, Airport airportB, int duration, String company) {
        this.airportA = airportA;
        this.airportB = airportB;
        this.duration = duration;
        this.company = company;

        airportA.connectAirport(airportB);   //airportA is connected to airportB and airportB is connected to airportA
        /* airportB.connectAirport(airportA);  This line is not needed because the connection is bidirectional 
         *                                     and the method connectAirport() already does that for us */
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

    public boolean contains(Airport a, Airport b) {
        if(this.getAirportA().equals(a) && this.getAirportB().equals(b)) return true;
        else return false;
    }

    public String toString() {
        return "Flight operated by " + this.company + ", duration " + this.duration + " minutes";
    }
}
