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
import dto.TripStats;
import java.rmi.Remote;
import java.rmi.RemoteException;
import model.Trip;
import java.util.List;

public interface TripService extends Remote{
    Trip findTripsByUser(Long id) throws RemoteException;
    List<Trip> findAll() throws RemoteException;
    Trip save(Trip trip) throws RemoteException;
    Trip update(Trip trip) throws RemoteException;
    Trip delete(Trip trip) throws RemoteException;
    TripStats fetchUserTripStats(String sessionToken) throws RemoteException;
    
//    // Returns all trips for the user identified by the session token
//    List<Trip> findTripsBySessionToken(String sessionToken) throws RemoteException;
//
//    // Save a new trip for the user identified by sessionToken
//    Trip save(String sessionToken, Trip trip) throws RemoteException;
//
//    // Update a trip belonging to the user identified by sessionToken
//    Trip update(String sessionToken, Trip trip) throws RemoteException;
//
//    // Delete a trip belonging to the user identified by sessionToken
//    Trip delete(String sessionToken, Trip trip) throws RemoteException;
}
