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
import dao.OtpDAO;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import model.Otp;
import service.OtpService;
import java.util.List;

public class OtpServiceImpl extends UnicastRemoteObject implements OtpService {

    public OtpServiceImpl() throws RemoteException{
    super();
    }
    
    private OtpDAO otpDAO = new OtpDAO();

    @Override
    public Otp findById(Long id) {
        return otpDAO.findById(id);
    }

    @Override
    public List<Otp> findAll() {
        return otpDAO.findAll();
    }

    @Override
    public void save(Otp otp) {
        otpDAO.save(otp);
    }

    @Override
    public void update(Otp otp) {
        otpDAO.update(otp);
    }

    @Override
    public void delete(Otp otp) {
        otpDAO.delete(otp);
    }
}