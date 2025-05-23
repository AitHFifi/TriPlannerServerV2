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
import model.Destination;
import java.util.List;

public interface DestinationService extends Remote{
    Destination findById(Long id) throws RemoteException;
    List<Destination> findAll() throws RemoteException;
    List<Destination> findAllByUser(Long id) throws RemoteException;
    boolean save(Destination destination) throws RemoteException;
    boolean update(Destination destination) throws RemoteException;
    boolean delete(Destination destination) throws RemoteException;
}