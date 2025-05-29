/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service.implementation;

/**
 *
 * @author Hp
 */

import dao.BookingDAO;
import dao.UserDAO;
import model.Booking;
import service.BookingService;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;
import model.BookingStats;
import model.User;

public class BookingServiceImpl extends UnicastRemoteObject implements BookingService {

    private BookingDAO bookingDAO = new BookingDAO();
    private UserDAO userDAO = new UserDAO(); 

    public BookingServiceImpl() throws RemoteException {
        super();
    }

    // Get user by session token
    private User getUserBySessionToken(String sessionToken) throws RemoteException {
        User user = userDAO.findBySessionToken(sessionToken);
        if (user == null) {
            throw new RemoteException("Invalid session token.");
        }
        return user;
    }

    @Override
    public List<Booking> getAllBookingsBySession(String sessionToken) throws RemoteException {
        User user = getUserBySessionToken(sessionToken);
        return bookingDAO.findBookingsByUser(user);
    }

    @Override
    public Booking saveBooking(String sessionToken, Booking booking) throws RemoteException {
        User user = getUserBySessionToken(sessionToken);
        booking.setUser(user); 
        boolean saved = bookingDAO.saveBooking(booking);
        if (!saved) throw new RemoteException("Failed to save booking.");
        return booking;
    }

    @Override
    public Booking updateBooking(String sessionToken, Booking booking) throws RemoteException {
        User user = getUserBySessionToken(sessionToken);
        booking.setUser(user); 
        boolean updated = bookingDAO.updateBooking(booking);
        if (!updated) throw new RemoteException("Failed to update booking.");
        return booking;
    }

    @Override
    public Booking deleteBooking(String sessionToken, Booking booking) throws RemoteException {
        User user = getUserBySessionToken(sessionToken);

    // Find the managed booking instance from DB
    Booking managedBooking = bookingDAO.findBookingById(booking.getBookingId());
    if (managedBooking == null) {
        throw new RemoteException("Booking not found.");
    }
    // Check user ownership
    if (!managedBooking.getUser().getUserId().equals(user.getUserId())) {
        throw new RemoteException("You don't have permission to delete this booking.");
    }
    boolean deleted = bookingDAO.deleteBooking(managedBooking);
    if (!deleted) throw new RemoteException("Failed to delete booking. It may be referenced elsewhere.");
    return managedBooking;
    }
    
    @Override
    public BookingStats getBookingStatsBySession(String sessionToken) throws RemoteException {
        User user = getUserBySessionToken(sessionToken);
        long planned = bookingDAO.countAllBookingByUser(user);
        long upcoming = bookingDAO.countPendingBookingByUser(user);
        return new BookingStats((int) planned, (int) upcoming);
    }
}
