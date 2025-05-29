package model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;

/**
 *
 * @author Hp
 */
@Entity
@Table(name = "expenses")
public class Expense implements Serializable {
    public static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "expense_id")
    private Long expenseId;

    // Many expenses belong to one trip
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "trip_id", referencedColumnName = "trip_id", nullable = false)
    private Trip trip;

    // Each expense belongs to a user (add this for user scoping)
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", referencedColumnName = "user_id", nullable = false)
    private User user;

    @Column(name = "category", nullable = false)
    private String category;

    @Column(nullable = false)
    private double amount;

    @Column(name = "expense_date", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date date;

    @Column(length = 255)
    private String description;

    public Expense() {}

    public Expense(Trip trip, User user, String category, double amount, Date date, String description) {
        this.trip = trip;
        this.user = user;
        this.category = category;
        this.amount = amount;
        this.date = date;
        this.description = description;
    }

    public Long getExpenseId() { return expenseId; }
    public void setExpenseId(Long expenseId) { this.expenseId = expenseId; }

    public Trip getTrip() { return trip; }
    public void setTrip(Trip trip) { this.trip = trip; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public double getAmount() { return amount; }
    public void setAmount(double amount) { this.amount = amount; }

    public Date getDate() { return date; }
    public void setDate(Date date) { this.date = date; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    @Override
    public String toString() {
        return (category != null ? category : "Expense") + " (" + amount + ")";
    }
}