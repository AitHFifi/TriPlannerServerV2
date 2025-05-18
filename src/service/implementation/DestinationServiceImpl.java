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
import dao.DestinationDAO;
import model.Destination;
import service.DestinationService;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

public class DestinationServiceImpl extends UnicastRemoteObject implements DestinationService {

    private DestinationDAO destinationDAO = new DestinationDAO();

    public DestinationServiceImpl() throws RemoteException {
        super();
    }

    @Override
    public Destination findById(Long id) throws RemoteException {
        return destinationDAO.findById(id);
    }

    @Override
    public List<Destination> findAll() throws RemoteException {
        return destinationDAO.findAll();
    }

    @Override
    public boolean save(Destination destination) throws RemoteException {
        return destinationDAO.save(destination);
    }

    @Override
    public boolean update(Destination destination) throws RemoteException {
        return destinationDAO.update(destination);
    }

    @Override
    public boolean delete(Destination destination) throws RemoteException {
        return destinationDAO.delete(destination);
    }
}