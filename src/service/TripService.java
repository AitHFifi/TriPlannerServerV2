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
import model.Trip;
import java.util.List;

public interface TripService extends Remote{
    Trip findById(Long id) throws RemoteException;
    List<Trip> findAll() throws RemoteException;
    void save(Trip trip) throws RemoteException;
    void update(Trip trip) throws RemoteException;
    void delete(Trip trip) throws RemoteException;
}
