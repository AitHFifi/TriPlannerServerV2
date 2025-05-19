package model;

import java.io.Serializable;
import java.util.List;
import javax.persistence.*;

/**
 *
 * @author Hp
 */
@Entity
@Table(name = "destinations")
public class Destination implements Serializable {
    public static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "destination_id")
    private Long destinationId;

    @Column(nullable = false)
    private String country;

    @Column(nullable = false)
    private String city;

    @Column(nullable = false)
    private String transportMode;

    @Column(nullable = false)
    private String description;

    // Many-to-Many: Destination <-> Trip
    @ManyToMany(mappedBy = "destinations", fetch = FetchType.LAZY)
    private List<Trip> trips;

    public Destination() {}

    public Destination(String country, String city, String transportMode, String description, List<Trip> trips) {
        this.country = country;
        this.city = city;
        this.transportMode = transportMode;
        this.description = description;
        this.trips = trips;
    }

    public Long getDestinationId() {
        return destinationId;
    }

    public void setDestinationId(Long destinationId) {
        this.destinationId = destinationId;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getTransportMode() {
        return transportMode;
    }

    public void setTransportMode(String transportMode) {
        this.transportMode = transportMode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Trip> getTrips() {
        return trips;
    }

    public void setTrips(List<Trip> trips) {
        this.trips = trips;
    }
}