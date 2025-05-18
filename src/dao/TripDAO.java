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

    public boolean save(Trip trip) {
        Transaction tx = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            tx = session.beginTransaction();
            session.save(trip);
            tx.commit();
            return true;
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            return false;
        } finally {
            session.close();
        }
    }

    public boolean update(Trip trip) {
        Transaction tx = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            tx = session.beginTransaction();
            session.update(trip);
            tx.commit();
            return true;
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            return false;
        } finally {
            session.close();
        }
    }

    public boolean delete(Trip trip) {
        Transaction tx = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            tx = session.beginTransaction();
            session.delete(trip);
            tx.commit();
            return true;
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            return false;
        } finally {
            session.close();
        }
    }
}