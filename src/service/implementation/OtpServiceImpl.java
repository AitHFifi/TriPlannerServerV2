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
import dao.UserDAO;
import model.Otp;
import model.User;
import service.OtpService;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.time.LocalDateTime;
import java.util.Random;
import util.EmailUtil;

public class OtpServiceImpl extends UnicastRemoteObject implements OtpService {

    private final OtpDAO otpDAO = new OtpDAO();
    private final UserDAO userDAO = new UserDAO();

    public OtpServiceImpl() throws RemoteException {}

    @Override
    public boolean generateAndSendOtp(User user) throws RemoteException {
        String code = String.format("%06d", new Random().nextInt(999999));
        Otp otp = new Otp(code, LocalDateTime.now().plusMinutes(5), false, user);
        boolean saved = otpDAO.save(otp);
        if (saved) {
             // Use email utility to send the OTP
            EmailUtil.sendOtpEmail(user.getEmail(), code);
        }
        return saved;
    }

    @Override
    public boolean verifyOtp(User user, String code) throws RemoteException {
        Otp otp = otpDAO.findValidOtpByUser(user, code);
        if (otp != null) {
            otpDAO.markOtpAsUsed(otp);
            user.setVerified(true);
            userDAO.update(user); // <-- persist the verified status!
            return true;
        }
        return false;
    }
}