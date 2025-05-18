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

public interface BookingService extends Remote {
    Booking findById(Long id) throws RemoteException;
    List<Booking> findAll() throws RemoteException;
    boolean save(Booking booking) throws RemoteException;
    boolean update(Booking booking) throws RemoteException;
    boolean delete(Booking booking) throws RemoteException;
}
