/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

/**
 *
 * @author Hp
 */
import java.rmi.Remote;
import java.rmi.RemoteException;
import model.Booking;
import java.util.List;
import model.BookingStats;

public interface BookingService extends Remote {
    // Returns all trips for the authenticated user (via session token)
    List<Booking> getAllBookingsBySession(String sessionToken) throws RemoteException;

    // Returns all trips in the system (admin/global)
    // List<Booking> findAll() throws RemoteException;

    // Saves a new trip for the authenticated user
    Booking saveBooking(String sessionToken, Booking booking) throws RemoteException;

    // Updates an existing trip for the authenticated user
    Booking updateBooking(String sessionToken, Booking booking) throws RemoteException;

    // Deletes a trip for the authenticated user
    Booking deleteBooking(String sessionToken, Booking booking) throws RemoteException;
    
    // Returns booking statistics for the authenticated user
    BookingStats getBookingStatsBySession(String sessionToken) throws RemoteException;
    
    // Returns all bookings for a specific trip (for the current user/session)
    List<Booking> getAllBookingsByTrip(String sessionToken, Long tripId) throws RemoteException;
}
