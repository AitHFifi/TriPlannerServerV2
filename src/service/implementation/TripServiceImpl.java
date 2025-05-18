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
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import model.Trip;
import service.TripService;
import java.util.List;

public class TripServiceImpl extends UnicastRemoteObject implements TripService {

    public TripServiceImpl() throws RemoteException{
    super();
    }
    
    private TripDAO tripDAO = new TripDAO();

    @Override
    public Trip findById(Long id) {
        return tripDAO.findById(id);
    }

    @Override
    public List<Trip> findAll() {
        return tripDAO.findAll();
    }

    @Override
    public void save(Trip trip) {
        tripDAO.save(trip);
    }

    @Override
    public void update(Trip trip) {
        tripDAO.update(trip);
    }

    @Override
    public void delete(Trip trip) {
        tripDAO.delete(trip);
    }
}
