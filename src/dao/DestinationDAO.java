package dao;

import model.Destination;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.util.List;

public class DestinationDAO {

    public Destination findDestinationById(Long id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            Destination destination = (Destination) session.get(Destination.class, id);
            if (destination != null) {
                org.hibernate.Hibernate.initialize(destination.getUser());
            }
            return destination;
        } finally {
            session.close();
        }
    }

//    public List<Destination> findAll() {
//        Session session = HibernateUtil.getSessionFactory().openSession();
//        try {
//            List<Destination> list = session.createQuery("from Destination").list();
//            for (Destination d : list) {
//                org.hibernate.Hibernate.initialize(d.getUser());
//            }
//            return list;
//        } finally {
//            session.close();
//        }
//    }

    public boolean saveDestination(Destination destination) {
        Transaction tx = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            tx = session.beginTransaction();
            session.save(destination);
            tx.commit();
            return true;
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            return false;
        } finally {
            session.close();
        }
    }

    public boolean updateDestination(Destination destination) {
        Transaction tx = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            tx = session.beginTransaction();
            session.update(destination);
            tx.commit();
            return true;
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            return false;
        } finally {
            session.close();
        }
    }

    public boolean deleteDestination(Destination destination) {
        Transaction tx = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            tx = session.beginTransaction();
            session.delete(destination);
            tx.commit();
            return true;
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            return false;
        } finally {
            session.close();
        }
    }
    
    public List<Destination> findAllDestinationByUser(Long userId) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            List<Destination> result = session.createQuery(
                "from Destination d where d.user.userId = :userId")
                .setParameter("userId", userId)
                .list();
            for (Destination d : result) {
                org.hibernate.Hibernate.initialize(d.getUser());
            }
            return result;
        } finally {
            session.close();
        }
    }
}