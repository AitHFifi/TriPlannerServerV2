/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author AitHFifi
 */
import java.io.Serializable;

public class TripStats implements Serializable {
    private int planned;
    private int completed;
    private int upcoming;

    public TripStats(int planned, int completed, int upcoming) {
        this.planned = planned;
        this.completed = completed;
        this.upcoming = upcoming;
    }

    public int getPlanned() { return planned; }
    public int getCompleted() { return completed; }
    public int getUpcoming() { return upcoming; }
}
