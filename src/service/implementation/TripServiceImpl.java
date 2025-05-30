package service.implementation;

import dao.TripDAO;
import dao.UserDAO;
import model.Trip;
import model.User;
import service.TripService;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;
import model.TripStats;

/**
 *
 * @author Hp
 */
public class TripServiceImpl extends UnicastRemoteObject implements TripService {

    private TripDAO tripDAO = new TripDAO();
    private UserDAO userDAO = new UserDAO();

    public TripServiceImpl() throws RemoteException {
        super();
    }

    // Get user by session token
    private User getUserBySessionToken(String sessionToken) throws RemoteException {
        User user = userDAO.findBySessionToken(sessionToken);
        if (user == null) {
            throw new RemoteException("Invalid session token.");
        }
        return user;
    }

    @Override
    public List<Trip> getAllTripsBySession(String sessionToken) throws RemoteException {
        User user = getUserBySessionToken(sessionToken);
        return tripDAO.findTripsByUser(user);
    }

//    @Override
//    public List<Trip> findAll() throws RemoteException {
//        return tripDAO.findAll();
//    }

    @Override
    public Trip saveTrip(String sessionToken, Trip trip) throws RemoteException {
        User user = getUserBySessionToken(sessionToken);
        // Ensure trip is associated with the authenticated user
        trip.setUser(user);
        return tripDAO.saveTrip(trip);
    }

    @Override
    public Trip updateTrip(String sessionToken, Trip trip) throws RemoteException {
        User user = getUserBySessionToken(sessionToken);
        Trip dbTrip = tripDAO.findTripById(trip.getTripId());
        if (dbTrip == null || !dbTrip.getUser().getUserId().equals(user.getUserId())) {
            throw new RemoteException("Trip not found or access denied.");
        }
        // Update updatable fields
        dbTrip.setTripName(trip.getTripName());
        dbTrip.setStartDate(trip.getStartDate());
        dbTrip.setEndDate(trip.getEndDate());
        dbTrip.setBudget(trip.getBudget());
        dbTrip.setDestinations(trip.getDestinations());
        return tripDAO.updateTrip(dbTrip);
    }

    @Override
public boolean deleteTrip(String sessionToken, Trip trip) throws RemoteException {
    User user = getUserBySessionToken(sessionToken);
    Trip managedTrip = tripDAO.findTripById(trip.getTripId());
    if (managedTrip == null) {
        throw new RemoteException("Trip not found.");
    }
    if (!managedTrip.getUser().getUserId().equals(user.getUserId())) {
        throw new RemoteException("You don't have permission to delete this trip.");
    }
    try {
        boolean deleted = tripDAO.deleteTrip(managedTrip);
        if (!deleted) {
            throw new RemoteException("Failed to delete trip. Unknown error.");
        }
        return true;
    } catch (Exception ex) {
        // Drill down to the root cause
        Throwable cause = ex;
        while (cause.getCause() != null) {
            cause = cause.getCause();
        }
        String message = cause.getMessage() != null ? cause.getMessage().toLowerCase() : "";

        // Check for foreign key/booking constraint
        if (message.contains("foreign key") || message.contains("booking")) {
            throw new RemoteException("Cannot delete this trip: There is a booking associated with it.");
        }

        // For all other errors
        throw new RemoteException("Failed to delete trip. Please try again or contact support.");
    }
}

    @Override
    public TripStats getTripStatsBySession(String sessionToken) throws RemoteException {
        User user = getUserBySessionToken(sessionToken);
        long planned = tripDAO.countAllByUser(user);
        long completed = tripDAO.countCompletedByUser(user);
        long upcoming = tripDAO.countUpcomingByUser(user);
        return new TripStats((int) planned, (int) completed, (int) upcoming);
    }

    @Override
    public List<Trip> getAllTripsWithDetailsBySession(String sessionToken) throws RemoteException {
    User user = getUserBySessionToken(sessionToken);
    return tripDAO.findTripsWithDetailsByUser(user);
}
    
}