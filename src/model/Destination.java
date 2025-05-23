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

    // Many-to-Many: Destination <-> Trip
    @ManyToMany(mappedBy = "destinations", fetch = FetchType.LAZY)
    private List<Trip> trips;
    
    // Many Destinations belong to One User
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id") // Assumes destinations table has a user_id foreign key
    private User user;

    public Destination() {}

    public Destination(String country, String city, List<Trip> trips) {
        this.country = country;
        this.city = city;
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

    public List<Trip> getTrips() {
        return trips;
    }

    public void setTrips(List<Trip> trips) {
        this.trips = trips;
    }
    
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}