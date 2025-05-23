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
import model.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.Query; 
import java.util.List;

public class UserDAO {

    public User findById(Long id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        User user = (User) session.get(User.class, id);
        session.close();
        return user;
    }

    public List<User> findAll() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<User> list = session.createQuery("from User").list();
        session.close();
        return list;
    }

    public boolean saveUser(User user) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.save(user);
            tx.commit();
            return true;
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            return false;
        } finally {
            session.close();
        }
    }

    public boolean update(User user) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.update(user);
            tx.commit();
            return true;
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            return false;
        } finally {
            session.close();
        }
    }

    public boolean delete(User user) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.delete(user);
            tx.commit();
            return true;
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            return false;
        } finally {
            session.close();
        }
    }

    public User findByUsername(String username) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query query = session.createQuery("from User where username = :username");
        query.setParameter("username", username);
        User user = (User) query.uniqueResult();
        session.close();
        return user;
    }

    public User findByEmail(String email) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query query = session.createQuery("from User where email = :email");
        query.setParameter("email", email);
        User user = (User) query.uniqueResult();
        session.close();
        return user;
    }
    
    // Find user by username
    public User findByExistingUsername(String username) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query query = session.createQuery("from User where username = :username");
        query.setParameter("username", username);
        User user = (User) query.uniqueResult();
        session.close();
        return user;
    }

    // Find user by email
    public User findByExistingEmail(String email) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query query = session.createQuery("from User where email = :email");
        query.setParameter("email", email);
        User user = (User) query.uniqueResult();
        session.close();
        return user;
    }

    // Find user by phone number
    public User findByPhoneNumber(String phoneNumber) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query query = session.createQuery("from User where phoneNumber = :phoneNumber");
        query.setParameter("phoneNumber", phoneNumber);
        User user = (User) query.uniqueResult();
        session.close();
        return user;
    }
}