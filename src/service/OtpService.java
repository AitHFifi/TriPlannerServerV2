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
import model.Otp;
import java.util.List;

public interface OtpService extends Remote{
    Otp findById(Long id) throws RemoteException;
    List<Otp> findAll() throws RemoteException;
    void save(Otp otp) throws RemoteException;
    void update(Otp otp) throws RemoteException;
    void delete(Otp otp) throws RemoteException;
}
