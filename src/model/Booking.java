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
@Table(name = "bookings")
public class Booking implements Serializable {
    public static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "booking_id")
    private Long bookingId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    
    @ManyToOne
    @JoinColumn(name = "trip_id", referencedColumnName = "trip_id")
    private Trip trip;

    @Column(nullable = false)
    private String bookingDate;

    @Column(nullable = false)
    private double price;

    @Column(nullable = false)
    private String status;

    public Booking() {}

    public Booking(Trip trip, String bookingDate, double price, String status, User user) {
        this.trip = trip;
        this.bookingDate = bookingDate;
        this.price = price;
        this.status = status;
        this.user = user;
    }

    // Getters and setters
    public Long getBookingId() { return bookingId; }
    public void setBookingId(Long bookingId) { this.bookingId = bookingId; }

    public Trip getTrip() { return trip; }
    public void setTrip(Trip trip) { this.trip = trip; }

    public String getBookingDate() { return bookingDate; }
    public void setBookingDate(String bookingDate) { this.bookingDate = bookingDate; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    
    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }
}
