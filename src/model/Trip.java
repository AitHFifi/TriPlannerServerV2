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
@Table(name = "trips")
public class Trip implements Serializable {
    public static final long serialVersionUID = 1L;

    @Id
    @Column(name = "trip_id", unique = true, nullable = false)
    private String tripId; // Custom ID e.g., "TRIP-1745580288508"

    @Column(nullable = false)
    private String tripName;

    @Column(nullable = false)
    private String startDate;

    @Column(nullable = false)
    private String endDate;

    @Column(nullable = false)
    private double budget; // Budget to be used by the system, cannot be exceeded

    @OneToOne(mappedBy = "trip", cascade = CascadeType.ALL)
    private Destination destination;

    // Constructors
    public Trip() {}

    public Trip(String tripId, String tripName, String startDate, String endDate, double budget) {
        this.tripId = tripId;
        this.tripName = tripName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.budget = budget;
    }

    // Getters and setters
    public String getTripId() { return tripId; }
    public void setTripId(String tripId) { this.tripId = tripId; }

    public String getTripName() { return tripName; }
    public void setTripName(String tripName) { this.tripName = tripName; }

    public String getStartDate() { return startDate; }
    public void setStartDate(String startDate) { this.startDate = startDate; }

    public String getEndDate() { return endDate; }
    public void setEndDate(String endDate) { this.endDate = endDate; }

    public double getBudget() { return budget; }
    public void setBudget(double budget) { this.budget = budget; }

    public Destination getDestination() { return destination; }
    public void setDestination(Destination destination) { this.destination = destination; }
}
