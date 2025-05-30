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
import model.TripStats;
import java.rmi.Remote;
import java.rmi.RemoteException;
import model.Trip;
import java.util.List;

public interface TripService extends Remote{
    // Returns all trips for the authenticated user (via session token)
    List<Trip> getAllTripsBySession(String sessionToken) throws RemoteException;

    // Returns all trips in the system (admin/global)
    // List<Trip> findAll() throws RemoteException;

    // Saves a new trip for the authenticated user
    Trip saveTrip(String sessionToken, Trip trip) throws RemoteException;

    // Updates an existing trip for the authenticated user
    Trip updateTrip(String sessionToken, Trip trip) throws RemoteException;

    // Deletes a trip for the authenticated user
    boolean deleteTrip(String sessionToken, Trip trip) throws RemoteException;

    // Returns trip statistics for the authenticated user
    TripStats getTripStatsBySession(String sessionToken) throws RemoteException;
    
    // Returns all trips for the authenticated user with all details, for export
    List<Trip> getAllTripsWithDetailsBySession(String sessionToken) throws RemoteException;
}
