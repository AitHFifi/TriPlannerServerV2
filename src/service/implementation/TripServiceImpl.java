package service.implementation;

import dao.TripDAO;
import dao.UserDAO;
import model.Trip;
import model.User;
import service.TripService;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 *
 * @author Hp
 */
public class TripServiceImpl extends UnicastRemoteObject implements TripService {

    private TripDAO tripDAO = new TripDAO();
    private UserDAO userDAO = new UserDAO(); // You need to have a UserDAO for fetching users by session
    // Simulated session store: sessionToken -> userId
    // In production, inject or use your actual session management system
    private static final Map<String, Long> sessionStore = new ConcurrentHashMap<>();

    public TripServiceImpl() throws RemoteException {
        super();
    }

    
    private User getUserBySessionToken(String sessionToken) throws RemoteException {
        Long userId = sessionStore.get(sessionToken);
        if (userId == null) {
            throw new RemoteException("Invalid or expired session token.");
        }
        User user = userDAO.findById(userId);
        if (user == null) {
            throw new RemoteException("User not found for this session.");
        }
        return user;
    }

    @Override
    public List<Trip> findTripsBySessionToken(String sessionToken) throws RemoteException {
        User user = getUserBySessionToken(sessionToken);
        return tripDAO.findByUser(user);
    }

    @Override
    public Trip save(String sessionToken, Trip trip) throws RemoteException {
        User user = getUserBySessionToken(sessionToken);
        trip.setUser(user); // Make sure the trip is assigned to the session user
        return tripDAO.save(trip);
    }

    @Override
    public Trip update(String sessionToken, Trip trip) throws RemoteException {
        User user = getUserBySessionToken(sessionToken);
        Trip dbTrip = tripDAO.findById(trip.getTripId());
        if (dbTrip == null) {
            throw new RemoteException("Trip not found.");
        }
        if (!dbTrip.getUser().getUserId().equals(user.getUserId())) {
            throw new RemoteException("You are not authorized to update this trip.");
        }
        // Optionally: copy updatable fields from input "trip" to "dbTrip"
        dbTrip.setTripName(trip.getTripName());
        dbTrip.setStartDate(trip.getStartDate());
        dbTrip.setEndDate(trip.getEndDate());
        dbTrip.setBudget(trip.getBudget());
        dbTrip.setDestinations(trip.getDestinations());
        return tripDAO.update(dbTrip);
    }

    @Override
    public Trip delete(String sessionToken, Trip trip) throws RemoteException {
        User user = getUserBySessionToken(sessionToken);
        Trip dbTrip = tripDAO.findById(trip.getTripId());
        if (dbTrip == null) {
            throw new RemoteException("Trip not found.");
        }
        if (!dbTrip.getUser().getUserId().equals(user.getUserId())) {
            throw new RemoteException("You are not authorized to delete this trip.");
        }
        return tripDAO.delete(dbTrip);
    }

    // For demo/testing: allow adding session tokens (in production, manage this elsewhere)
    public static void addSessionToken(String token, Long userId) {
        sessionStore.put(token, userId);
    }
}