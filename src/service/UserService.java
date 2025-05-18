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
import model.User;
import java.util.List;

public interface UserService extends Remote{
    User findById(Long id) throws RemoteException;
    List<User> findAll() throws RemoteException;
    boolean save(User user) throws RemoteException;
    boolean update(User user) throws RemoteException;
    boolean delete(User user) throws RemoteException;
    User findByUsername(String username) throws RemoteException;
    User findByEmail(String email) throws RemoteException;
}
