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
import model.User;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface OtpService extends Remote {
    boolean generateAndSendOtp(User user) throws RemoteException;
    boolean verifyOtp(User user, String code) throws RemoteException;
}