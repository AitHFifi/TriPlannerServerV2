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
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import model.Destination;
import service.DestinationService;
import java.util.List;

public class DestinationServiceImpl extends UnicastRemoteObject implements DestinationService {

    public DestinationServiceImpl() throws RemoteException{
    super();
    }
    
    private DestinationDAO destinationDAO = new DestinationDAO();

    @Override
    public Destination findById(Long id) {
        return destinationDAO.findById(id);
    }

    @Override
    public List<Destination> findAll() {
        return destinationDAO.findAll();
    }

    @Override
    public void save(Destination destination) {
        destinationDAO.save(destination);
    }

    @Override
    public void update(Destination destination) {
        destinationDAO.update(destination);
    }

    @Override
    public void delete(Destination destination) {
        destinationDAO.delete(destination);
    }
}