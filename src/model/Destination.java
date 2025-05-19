/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Hp
 */
import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name = "destinations")
public class Destination implements Serializable {
    public static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "destination_id")
    private Long destinationId;

    @OneToOne
    @JoinColumn(name = "trip_id", referencedColumnName = "trip_id")
    private Trip trip;

    @Column(nullable = false)
    private String country;

    @Column(nullable = false)
    private String city;

    @Column(nullable = false)
    private String transportMode;

    @Column(nullable = false)
    private String description;

    public Destination() {}

    public Destination(Trip trip, String country, String city, String transportMode, String description) {
        this.trip = trip;
        this.country = country;
        this.city = city;
        this.transportMode = transportMode;
        this.description = description;
    }

    // Getters and setters
    public Long getDestinationId() { return destinationId; }
    public void setDestinationId(Long destinationId) { this.destinationId = destinationId; }

    public Trip getTrip() { return trip; }
    public void setTrip(Trip trip) { this.trip = trip; }

    public String getCountry() { return country; }
    public void setCountry(String country) { this.country = country; }

    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }

    public String getTransportMode() { return transportMode; }
    public void setTransportMode(String transportMode) { this.transportMode = transportMode; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
}
