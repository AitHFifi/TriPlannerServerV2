/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package session;

/**
 *
 * @author Hp
 */
import dao.UserDAO;
import model.User;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class SessionManager {
    private static final Map<String, User> sessionMap = new ConcurrentHashMap<>();

    // Generate a session, store it, and return token
    public static String createSession(User user) {
        String token = UUID.randomUUID().toString();
        user.setSessionToken(token);
        new UserDAO().updateUser(user); // Persist the session token in the DB
        sessionMap.put(token, user);
        return token;
    }

    public static User getUser(String token) {
        return sessionMap.get(token);
    }

    public static void invalidateSession(String token) {
        User user = sessionMap.remove(token);
        if (user != null) {
            user.setSessionToken(null);
            new UserDAO().updateUser(user); // Clear the session token in the DB
        }
    }

    public static boolean isValid(String token) {
        return sessionMap.containsKey(token);
    }
}
