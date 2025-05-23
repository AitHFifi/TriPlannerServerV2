package service.implementation;

import dao.TripDAO;
import dao.UserDAO;
import model.Trip;
import model.User;
import service.TripService;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

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

    // Find a single trip by user ID (returns the first trip for the user or null)
    @Override
    public Trip findTripByUser(Long id) throws RemoteException {
        User user = userDAO.findById(id);
        List<Trip> trips = tripDAO.findByUser(user);
        return (trips != null && !trips.isEmpty()) ? trips.get(0) : null;
    }

    @Override
    public List<Trip> findAll() throws RemoteException {
        return tripDAO.findAll();
    }

    @Override
    public Trip saveTrip(Trip trip) throws RemoteException {
        return tripDAO.saveTrip(trip);
    }

    @Override
    public Trip update(Trip trip) throws RemoteException {
        Trip dbTrip = tripDAO.findById(trip.getTripId());
        if (dbTrip == null) {
            throw new RemoteException("Trip not found.");
        }
        // Update updatable fields
        dbTrip.setTripName(trip.getTripName());
        dbTrip.setStartDate(trip.getStartDate());
        dbTrip.setEndDate(trip.getEndDate());
        dbTrip.setBudget(trip.getBudget());
        dbTrip.setDestinations(trip.getDestinations());
        return tripDAO.update(dbTrip);
    }

    @Override
    public Trip delete(Trip trip) throws RemoteException {
        Trip dbTrip = tripDAO.findById(trip.getTripId());
        if (dbTrip == null) {
            throw new RemoteException("Trip not found.");
        }
        return tripDAO.delete(dbTrip);
    }

    @Override
    public model.TripStats getTripStatsByUser(Long userId) throws RemoteException {
        User user = userDAO.findById(userId);
        long planned = tripDAO.countAllByUser(user);
        long completed = tripDAO.countCompletedByUser(user);
        long upcoming = tripDAO.countUpcomingByUser(user);
        return new model.TripStats((int) planned, (int) completed, (int) upcoming);
    }

    @Override
    public List<Trip> getAllTripsByUser(Long id) throws RemoteException {
    User user = userDAO.findById(id);
    if (user == null) return null;
    return tripDAO.findByUser(user);
}
}