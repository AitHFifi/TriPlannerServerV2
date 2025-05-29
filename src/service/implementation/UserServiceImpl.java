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
import org.mindrot.jbcrypt.BCrypt;
import session.SessionManager;

public class UserServiceImpl extends UnicastRemoteObject implements UserService {

    private UserDAO userDAO = new UserDAO();

    public UserServiceImpl() throws RemoteException {
        super();
    }

    @Override
    public boolean update(User user) throws RemoteException {
        return userDAO.updateUser(user);
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
    boolean saved = userDAO.saveUser(user);
    if (saved) {
        try {
            OtpService otpService = (OtpService) Naming.lookup("rmi://127.0.0.1:5000/otp");
            otpService.generateAndSendOtp(user, "REGISTER"); 
        } catch (Exception e) {
            throw new RemoteException("Failed to generate/send OTP", e);
        }
        return user;
    }
    return null;
} 
    
@Override
    public String login(String identifier, String password) throws RemoteException {
        User user = null;
        // Check if identifier looks like an email
        if (identifier.contains("@")) {
            user = userDAO.findByEmail(identifier);
        } else {
            user = userDAO.findByUsername(identifier);
        }
        if (user != null && BCrypt.checkpw(password, user.getPassword())) {
            return SessionManager.createSession(user);
        }
        return null;
    }

    @Override
    public void logout(String sessionToken) throws RemoteException {
        SessionManager.invalidateSession(sessionToken);
    }

    @Override
    public User getCurrentUser(String sessionToken) throws RemoteException {
        return SessionManager.getUser(sessionToken);
    }

    @Override
    public boolean updatePassword(String sessionToken, String newPassword) throws RemoteException {
        User user = SessionManager.getUser(sessionToken);
        if (user != null) {
            String hashed = BCrypt.hashpw(newPassword, BCrypt.gensalt());
            user.setPassword(hashed);
            return userDAO.updateUser(user);
        }
        return false;
    }

//    @Override
//    public boolean update(String sessionToken, User user) throws RemoteException {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }

    @Override
    public boolean isUsernameTaken(String username) throws RemoteException {
        return userDAO.findByExistingUsername(username) != null;
    }

    @Override
    public boolean isEmailTaken(String email) throws RemoteException {
        return userDAO.findByExistingEmail(email) != null;
    }

    @Override
    public boolean isPhoneNumberTaken(String phoneNumber) throws RemoteException {
        return userDAO.findByPhoneNumber(phoneNumber) != null;
    }

    @Override
    public boolean requestOtpForUserChange(String sessionToken, String actionType) throws RemoteException {
        User user = SessionManager.getUser(sessionToken);
        if (user == null) {
            throw new RemoteException("Invalid session. Please login again.");
        }
        try {
            OtpService otpService = (OtpService) Naming.lookup("rmi://127.0.0.1:5000/otp");
            // The service now returns boolean
            return otpService.generateAndSendOtp(user, actionType);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RemoteException("Failed to generate/send OTP.", e);
        }
    }


    @Override
    public boolean updateUser(String sessionToken, User user, String otp) throws RemoteException {
        User currentUser = SessionManager.getUser(sessionToken);
        if (currentUser == null || !currentUser.getUserId().equals(user.getUserId())) {
            throw new RemoteException("Unauthorized or invalid session.");
        }

        // Determine if OTP verification is required and, if so, for which action
        String actionType = null;
        boolean criticalChange = false;

        if (!user.getEmail().equals(currentUser.getEmail())) {
            actionType = "EMAIL";
            criticalChange = true;
        } else if (user.getPassword() != null && !user.getPassword().isEmpty()
                && !BCrypt.checkpw(user.getPassword(), currentUser.getPassword())) {
            actionType = "PASSWORD";
            criticalChange = true;
        }

        // If OTP is required, verify it
        if (criticalChange) {
            if (otp == null || otp.isEmpty()) {
                throw new RemoteException("OTP required for critical changes.");
            }
            try {
                OtpService otpService = (OtpService) Naming.lookup("rmi://127.0.0.1:5000/otp");
                boolean valid = otpService.verifyOtp(currentUser, otp, actionType);
                if (!valid) {
                    return false;
                }
            } catch (Exception e) {
                e.printStackTrace();
                throw new RemoteException("Failed to verify OTP.", e);
            }
        }

        // For password update, hash the password if it is being changed
        if (user.getPassword() != null && !user.getPassword().isEmpty()
                && !BCrypt.checkpw(user.getPassword(), currentUser.getPassword())) {
            String hashed = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
            currentUser.setPassword(hashed);
        }

        // Only allow permitted fields to be updated
        currentUser.setUsername(user.getUsername());
        currentUser.setEmail(user.getEmail());
        currentUser.setPhoneNumber(user.getPhoneNumber());

        // Update in DB
        return userDAO.updateUser(currentUser);
    }
}


