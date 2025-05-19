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
import util.EmailUtil;
import util.OtpUtil;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.time.LocalDateTime;

public class OtpServiceImpl extends UnicastRemoteObject implements OtpService {

    private final OtpDAO otpDAO = new OtpDAO();
    private final UserDAO userDAO = new UserDAO();

    public OtpServiceImpl() throws RemoteException {}

    @Override
    public boolean generateAndSendOtp(User user, String purpose) throws RemoteException {
        String code = OtpUtil.generateSixDigitOtp();
        Otp otp = new Otp(code, LocalDateTime.now().plusMinutes(5), false, purpose, user);
        boolean saved = otpDAO.save(otp);
        if (saved) {
            // Use email utility to send the OTP
            EmailUtil.sendOtpEmail(user.getEmail(), code);
        }
        return saved;
    }

   @Override
public boolean verifyOtp(User user, String code, String purpose) throws RemoteException {
    Otp otp = otpDAO.findValidOtpByUserAndPurpose(user, code, purpose);
    if (otp != null) {
        otpDAO.markOtpAsUsed(otp);

        // If this OTP is for registration, mark the user as verified and persist it
        if ("REGISTER".equalsIgnoreCase(purpose)) {
            user.setVerified(true);
            userDAO.update(user); // Make sure userDAO.update persists the isVerified flag
        }

        return true;
    }
    return false;
}
}