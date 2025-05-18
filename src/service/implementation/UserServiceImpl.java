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
import dao.UserDAO;
import model.User;
import service.UserService;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

public class UserServiceImpl extends UnicastRemoteObject implements UserService {

    private UserDAO userDAO = new UserDAO();

    public UserServiceImpl() throws RemoteException {
        super();
    }

    @Override
    public User findById(Long id) throws RemoteException {
        return userDAO.findById(id);
    }

    @Override
    public List<User> findAll() throws RemoteException {
        return userDAO.findAll();
    }

    @Override
    public boolean save(User user) throws RemoteException {
        return userDAO.save(user);
    }

    @Override
    public boolean update(User user) throws RemoteException {
        return userDAO.update(user);
    }

    @Override
    public boolean delete(User user) throws RemoteException {
        return userDAO.delete(user);
    }
    
       @Override
    public User findByUsername(String username) throws RemoteException {
        return userDAO.findByUsername(username);
    }

    @Override
    public User findByEmail(String email) throws RemoteException {
        return userDAO.findByEmail(email);
    }
}

