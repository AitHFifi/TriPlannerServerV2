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

public class BookingStats implements Serializable {
    private int planned;
    private int upcoming;

    public BookingStats(int planned, int upcoming) {
        this.planned = planned;
        this.upcoming = upcoming;
    }

    public int getPlanned() { return planned; }
    public int getUpcoming() { return upcoming; }
}
