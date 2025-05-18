/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

/**
 *
 * @author Hp
 */
import model.Trip;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;

public class TripDAO {

    public Trip findById(Long id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Trip trip = (Trip) session.get(Trip.class, id);
        session.close();
        return trip;
    }

    public List<Trip> findAll() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Trip> list = session.createQuery("from Trip").list();
        session.close();
        return list;
    }

    public void save(Trip trip) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        session.save(trip);
        tx.commit();
        session.close();
    }

    public void update(Trip trip) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        session.update(trip);
        tx.commit();
        session.close();
    }

    public void delete(Trip trip) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = session.beginTransaction();
        session.delete(trip);
        tx.commit();
        session.close();
    }
}