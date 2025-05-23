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
    Trip findTripByUser(Long id) throws RemoteException; 
    List<Trip> getAllTripsByUser(Long id) throws RemoteException;
    List<Trip> findAll() throws RemoteException;
    Trip saveTrip(Trip trip) throws RemoteException;
    Trip update(Trip trip) throws RemoteException;
    Trip delete(Trip trip) throws RemoteException;
    TripStats getTripStatsByUser(Long userId) throws RemoteException;
}
