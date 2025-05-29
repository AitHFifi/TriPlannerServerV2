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
    /**
     * Find a destination by its unique ID.
     * @param id The destination ID.
     * @return The Destination object, or null if not found.
     */
    Destination findDestinationById(Long id) throws RemoteException;

    /**
     * Find all destinations (admin/global).
     * @return List of all destinations in the system.
     */
//    List<Destination> findAll() throws RemoteException;

    /**
     * Find all destinations belonging to the user identified by the session token.
     * @param sessionToken The session token representing the authenticated user.
     * @return List of the user's destinations.
     */
    List<Destination> findAllDestinationsBySession(String sessionToken) throws RemoteException;

    /**
     * Save a new destination for the user identified by the session token.
     * @param sessionToken The session token representing the authenticated user.
     * @param destination The Destination to save.
     * @return true if saved successfully, false otherwise.
     */
    boolean saveDestinationBySession(String sessionToken, Destination destination) throws RemoteException;

    /**
     * Update an existing destination for the user identified by the session token.
     * @param sessionToken The session token representing the authenticated user.
     * @param destination The updated Destination object.
     * @return true if updated successfully, false otherwise.
     */
    boolean updateDestinationBySession(String sessionToken, Destination destination) throws RemoteException;

    /**
     * Delete a destination for the user identified by the session token.
     * @param sessionToken The session token representing the authenticated user.
     * @param destination The Destination to delete.
     * @return true if deleted successfully, false otherwise.
     */
    boolean deleteDestinationBySession(String sessionToken, Destination destination) throws RemoteException;
}