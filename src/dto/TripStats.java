/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

/**
 *
 * @author AitHFifi
 */
import java.io.Serializable;

public class TripStats implements Serializable {
    private long totalTrips;
    private long upcomingTrips;
    private long completedTrips;
    private double totalBudget;

    public TripStats(long totalTrips, long upcomingTrips, long completedTrips, double totalBudget) {
        this.totalTrips = totalTrips;
        this.upcomingTrips = upcomingTrips;
        this.completedTrips = completedTrips;
        this.totalBudget = totalBudget;
    }

    // Getters and setters
    public long getTotalTrips() { return totalTrips; }
    public long getUpcomingTrips() { return upcomingTrips; }
    public long getCompletedTrips() { return completedTrips; }
    public double getTotalBudget() { return totalBudget; }
}
