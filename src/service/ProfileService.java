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
import model.Profile;
import java.util.List;

public interface ProfileService extends Remote{
    Profile findById(Long id) throws RemoteException;
    List<Profile> findAll() throws RemoteException;
    void save(Profile profile) throws RemoteException;
    void update(Profile profile) throws RemoteException;
    void delete(Profile profile) throws RemoteException;
}
