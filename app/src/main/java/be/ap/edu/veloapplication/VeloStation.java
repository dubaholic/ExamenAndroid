package be.ap.edu.veloapplication;

public class VeloStation {

    private String naam;
    private String point_lat;
    private String point_lng;

    public VeloStation(String naam, String point_lat, String point_lng) {
        this.naam = naam;
        this.point_lat = point_lat;
        this.point_lng = point_lng;
    }

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        this.naam = naam;
    }

    public String getPoint_lat() {
        return point_lat;
    }

    public void setPoint_lat(String point_lat) {
        this.point_lat = point_lat;
    }

    public String getPoint_lng() {
        return point_lng;
    }

    public void setPoint_lng(String point_lng) {
        this.point_lng = point_lng;
    }
}
