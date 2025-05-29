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
import dao.UserDAO;
import model.Destination;
import service.DestinationService;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;
import model.User;

public class DestinationServiceImpl extends UnicastRemoteObject implements DestinationService {

    private final DestinationDAO destinationDAO = new DestinationDAO();
    private final UserDAO userDAO = new UserDAO();

    public DestinationServiceImpl() throws RemoteException {
        super();
    }

    @Override
    public Destination findDestinationById(Long id) throws RemoteException {
        return destinationDAO.findDestinationById(id);
    }

//    @Override
//    public List<Destination> findAll() throws RemoteException {
//        return destinationDAO.findAll();
//    }

    /**
     * Helper method to retrieve the authenticated user by session token.
     */
    private User getUserBySessionToken(String sessionToken) throws RemoteException {
        User user = userDAO.findBySessionToken(sessionToken);
        if (user == null) {
            throw new RemoteException("Invalid session token.");
        }
        return user;
    }

    @Override
    public List<Destination> findAllDestinationsBySession(String sessionToken) throws RemoteException {
        User user = getUserBySessionToken(sessionToken);
        return destinationDAO.findAllDestinationByUser(user.getUserId());
    }

    @Override
    public boolean saveDestinationBySession(String sessionToken, Destination destination) throws RemoteException {
        User user = getUserBySessionToken(sessionToken);
        destination.setUser(user);
        return destinationDAO.saveDestination(destination);
    }

    @Override
    public boolean updateDestinationBySession(String sessionToken, Destination destination) throws RemoteException {
        User user = getUserBySessionToken(sessionToken);
        Destination dbDestination = destinationDAO.findDestinationById(destination.getDestinationId());
        if (dbDestination == null || !dbDestination.getUser().getUserId().equals(user.getUserId())) {
            throw new RemoteException("Destination not found or access denied.");
        }
        dbDestination.setCountry(destination.getCountry());
        dbDestination.setCity(destination.getCity());
        return destinationDAO.updateDestination(dbDestination);
    }

    @Override
    public boolean deleteDestinationBySession(String sessionToken, Destination destination) throws RemoteException {
        User user = getUserBySessionToken(sessionToken);
        Destination dbDestination = destinationDAO.findDestinationById(destination.getDestinationId());
        if (dbDestination == null || !dbDestination.getUser().getUserId().equals(user.getUserId())) {
            throw new RemoteException("Destination not found or access denied.");
        }
        return destinationDAO.deleteDestination(dbDestination);
    }
}