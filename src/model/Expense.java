package model;

import java.io.Serializable;
import javax.persistence.*;

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

    // Relationship: Many expenses belong to one trip (Many-to-One)
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "trip_id", referencedColumnName = "trip_id", nullable = false)
    private Trip trip;

    @Column(nullable = false)
    private String expenseType;

    @Column(nullable = false)
    private double amount;

    @Column(nullable = false)
    private String expenseDate;

    @Column(nullable = false)
    private String createdAt;

    public Expense() {}

    public Expense(Trip trip, String expenseType, double amount, String expenseDate, String createdAt) {
        this.trip = trip;
        this.expenseType = expenseType;
        this.amount = amount;
        this.expenseDate = expenseDate;
        this.createdAt = createdAt;
    }

    public Long getExpenseId() { return expenseId; }
    public void setExpenseId(Long expenseId) { this.expenseId = expenseId; }

    public Trip getTrip() { return trip; }
    public void setTrip(Trip trip) { this.trip = trip; }

    public String getExpenseType() { return expenseType; }
    public void setExpenseType(String expenseType) { this.expenseType = expenseType; }

    public double getAmount() { return amount; }
    public void setAmount(double amount) { this.amount = amount; }

    public String getExpenseDate() { return expenseDate; }
    public void setExpenseDate(String expenseDate) { this.expenseDate = expenseDate; }

    public String getCreatedAt() { return createdAt; }
    public void setCreatedAt(String createdAt) { this.createdAt = createdAt; }
}