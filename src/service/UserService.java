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

public interface UserService extends Remote{
    boolean update(User user) throws RemoteException; // Used in the register form
    User findByUsername(String username) throws RemoteException; // Used in the ForgotPassword
    User findByEmail(String email) throws RemoteException; // Used in the ForgotPassword
    User register(User user) throws RemoteException; // Used in the register form 
    String login(String identifier, String password) throws RemoteException; // Used in the login form 
    void logout(String sessionToken) throws RemoteException; // Used in the Dashboard
    User getCurrentUser(String sessionToken) throws RemoteException; // Used in the login form
    boolean updatePassword(String sessionToken, String newPassword) throws RemoteException; // Used in the reset password
//    boolean update(String sessionToken, User user) throws RemoteException; // User to modify their infos.
}
