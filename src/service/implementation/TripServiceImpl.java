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
import dao.TripDAO;
import model.Trip;
import service.TripService;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

public class TripServiceImpl extends UnicastRemoteObject implements TripService {

    private TripDAO tripDAO = new TripDAO();

    public TripServiceImpl() throws RemoteException {
        super();
    }

    @Override
    public Trip findById(Long id) throws RemoteException {
        return tripDAO.findById(id);
    }

    @Override
    public List<Trip> findAll() throws RemoteException {
        return tripDAO.findAll();
    }

    @Override
    public boolean save(Trip trip) throws RemoteException {
        return tripDAO.save(trip);
    }

    @Override
    public boolean update(Trip trip) throws RemoteException {
        return tripDAO.update(trip);
    }

    @Override
    public boolean delete(Trip trip) throws RemoteException {
        return tripDAO.delete(trip);
    }
}
