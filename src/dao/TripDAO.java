package dao;

import model.Trip;
import model.User;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.Date;
import java.util.List;

public class TripDAO {

    public Trip saveTrip(Trip trip) {
        Transaction tx = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            tx = session.beginTransaction();
            session.save(trip);
            tx.commit();
            return trip;
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            return null;
        } finally {
            session.close();
        }
    }

    public Trip updateTrip(Trip trip) {
        Transaction tx = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            tx = session.beginTransaction();
            session.update(trip);
            tx.commit();
            return trip;
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            return null;
        } finally {
            session.close();
        }
    }

    public boolean deleteTrip(Trip trip) {
    Transaction tx = null;
    Session session = HibernateUtil.getSessionFactory().openSession();
    try {
        tx = session.beginTransaction();
        session.delete(trip);
        tx.commit();
        return true;
    } catch (Exception e) {
        if (tx != null) tx.rollback();
        throw e; // Let the service handle and wrap the exception
    } finally {
        session.close();
    }
}

    // Find all trips for a given user
    public List<Trip> findTripsByUser(User user) {
    Session session = HibernateUtil.getSessionFactory().openSession();
    try {
        List<Trip> trips = session.createQuery(
                "select distinct t from Trip t left join fetch t.destinations where t.user.id = :userId")
                .setParameter("userId", user.getUserId())
                .list();
        // Force initialize user for each trip
        for (Trip trip : trips) {
            org.hibernate.Hibernate.initialize(trip.getUser());
        }
        return trips;
    } finally {
        session.close();
    }
}
    
    // Find a trip by its id
    public Trip findTripById(Long id) {
    Session session = HibernateUtil.getSessionFactory().openSession();
    try {
        Trip trip = (Trip) session.get(Trip.class, id);
        // Force initialization of destinations and user
        if (trip != null) {
            org.hibernate.Hibernate.initialize(trip.getDestinations());
            org.hibernate.Hibernate.initialize(trip.getUser());
        }
        return trip;
    } finally {
        session.close();
    }
}

//    // Find all trips (admin/global)
//    public List<Trip> findAll() {
//        Session session = HibernateUtil.getSessionFactory().openSession();
//        try {
//            return (List<Trip>) session.createQuery("from Trip").list();
//        } finally {
//            session.close();
//        }
//    }
//
//    // Count all trips
//    public long countAll() {
//        Session session = HibernateUtil.getSessionFactory().openSession();
//        try {
//            return (Long) session.createQuery("select count(t) from Trip t").uniqueResult();
//        } finally {
//            session.close();
//        }
//    }
//
//    // Count upcoming trips (any user)
//    public long countUpcoming() {
//        Session session = HibernateUtil.getSessionFactory().openSession();
//        try {
//            return (Long) session.createQuery(
//                    "select count(t) from Trip t where t.startDate > :now")
//                    .setParameter("now", new Date())
//                    .uniqueResult();
//        } finally {
//            session.close();
//        }
//    }
//
//    // Count completed trips (any user)
//    public long countCompleted() {
//        Session session = HibernateUtil.getSessionFactory().openSession();
//        try {
//            return (Long) session.createQuery(
//                    "select count(t) from Trip t where t.endDate < :now")
//                    .setParameter("now", new Date())
//                    .uniqueResult();
//        } finally {
//            session.close();
//        }
//    }

    // Count all trips for a given user
    public long countAllByUser(User user) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            return (Long) session.createQuery(
                    "select count(t) from Trip t where t.user.id = :userId")
                    .setParameter("userId", user.getUserId())
                    .uniqueResult();
        } finally {
            session.close();
        }
    }

    // Count upcoming trips for a given user
    public long countUpcomingByUser(User user) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            return (Long) session.createQuery(
                    "select count(t) from Trip t where t.user.id = :userId and t.startDate > :now")
                    .setParameter("userId", user.getUserId())
                    .setParameter("now", new Date())
                    .uniqueResult();
        } finally {
            session.close();
        }
    }

    // Count completed trips for a given user
    public long countCompletedByUser(User user) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            return (Long) session.createQuery(
                    "select count(t) from Trip t where t.user.id = :userId and t.endDate < :now")
                    .setParameter("userId", user.getUserId())
                    .setParameter("now", new Date())
                    .uniqueResult();
        } finally {
            session.close();
        }
    }
    
    // Find all trips for a given user, INCLUDING expenses, booking, and destinations (eager fetch)
    public List<Trip> findTripsWithDetailsByUser(User user) {
    Session session = HibernateUtil.getSessionFactory().openSession();
    try {
        // Only fetch one collection in the main query!
        List<Trip> trips = session.createQuery(
                "select distinct t from Trip t " +
                "left join fetch t.destinations " +
                "left join fetch t.booking " +
                "where t.user.userId = :userId")
            .setParameter("userId", user.getUserId())
            .list();
        for (Trip trip : trips) {
            org.hibernate.Hibernate.initialize(trip.getExpenses()); // separate query per trip!
            org.hibernate.Hibernate.initialize(trip.getUser());
            if (trip.getBooking() != null) {
                org.hibernate.Hibernate.initialize(trip.getBooking().getUser());
            }
            if (trip.getExpenses() != null) {
                trip.getExpenses().forEach(exp -> org.hibernate.Hibernate.initialize(exp.getUser()));
            }
        }
        return trips;
    } finally {
        session.close();
    }
}
}