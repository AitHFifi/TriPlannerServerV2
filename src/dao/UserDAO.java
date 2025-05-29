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

public class UserDAO {
    
    // Register a User 
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

    // Update a User 
    public boolean updateUser(User user) {
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

    // Delete a User
    public boolean deleteUser(User user) {
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

    // Find user by Username
    public User findByUsername(String username) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query query = session.createQuery("from User where username = :username");
        query.setParameter("username", username);
        User user = (User) query.uniqueResult();
        session.close();
        return user;
    }

    // Find user by email
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
    
    // Find user by SessionToken
    public User findBySessionToken(String sessionToken) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        User user = null;
        try {
            Query query = session.createQuery("from User where sessionToken = :sessionToken");
            query.setParameter("sessionToken", sessionToken);
            user = (User) query.uniqueResult();
        } finally {
            session.close();
        }
        return user;
    }
}