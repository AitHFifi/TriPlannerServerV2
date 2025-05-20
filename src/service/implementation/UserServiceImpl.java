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
        return userDAO.update(user);
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
            otpService.generateAndSendOtp(user, "REGISTER"); // Pass purpose as "REGISTER"
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
            return userDAO.update(user);
        }
        return false;
    }

    @Override
    public boolean update(String sessionToken, User user) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}

