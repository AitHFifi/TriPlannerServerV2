package model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "trips")
public class Trip implements Serializable {
    public static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "trip_id", unique = true, nullable = false)
    private Long tripId; 

    @Column(nullable = false)
    private String tripName;

    @Column(nullable = false)
    private Date startDate;

    @Column(nullable = false)
    private Date endDate;

    @Column(nullable = false)
    private double budget;
    
    // Many-to-one: Trip -> User (owner/creator)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    // One-to-many: Trip -> Expense
    @OneToMany(mappedBy = "trip", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Expense> expenses;

    // One-to-one: Trip -> Booking
    @OneToOne(mappedBy = "trip", fetch = FetchType.LAZY)
    private Booking booking;

    // Many-to-many: Trip <-> Destination
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "trip_destination",
        joinColumns = @JoinColumn(name = "trip_id", referencedColumnName = "trip_id"),
        inverseJoinColumns = @JoinColumn(name = "destination_id", referencedColumnName = "destination_id")
    )
private List<Destination> destinations;

    public Trip() {}

    public Trip(String tripName, Date startDate, Date endDate, double budget, User user) {
        this.tripName = tripName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.budget = budget;
        this.user = user;
    }

    public Long getTripId() { return tripId; }
    public void setTripId(Long tripId) { this.tripId = tripId; }

    public String getTripName() { return tripName; }
    public void setTripName(String tripName) { this.tripName = tripName; }

    public Date getStartDate() { return startDate; }
    public void setStartDate(Date startDate) { this.startDate = startDate; }

    public Date getEndDate() { return endDate; }
    public void setEndDate(Date endDate) { this.endDate = endDate; }

    public double getBudget() { return budget; }
    public void setBudget(double budget) { this.budget = budget; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }

    public List<Expense> getExpenses() { return expenses; }
    public void setExpenses(List<Expense> expenses) { this.expenses = expenses; }

    public Booking getBooking() { return booking; }
    public void setBooking(Booking booking) { this.booking = booking; }

    public List<Destination> getDestinations() { return destinations; }
    public void setDestinations(List<Destination> destinations) { this.destinations = destinations; }
}