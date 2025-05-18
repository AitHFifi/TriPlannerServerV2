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
import java.rmi.Naming;
import model.User;
import service.UserService;
import service.OtpService;
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

    @Override
    public User register(User user) throws RemoteException {
        user.setVerified(false);
        boolean saved = userDAO.save(user);
        if (saved) {
            try {
                // Get the remote OTP service. Adjust the lookup URL as needed for your environment.
                OtpService otpService = (OtpService) Naming.lookup("rmi://127.0.0.1:5000/otp");
                otpService.generateAndSendOtp(user);
            } catch (Exception e) {
                throw new RemoteException("Failed to generate/send OTP", e);
            }
            return user;
        }
        return null;
    }
}

