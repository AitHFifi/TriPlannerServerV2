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
import model.Otp;
import model.User;
import org.hibernate.Session;
import org.hibernate.Transaction;
import java.time.LocalDateTime;

public class OtpDAO {

     public boolean save(Otp otp) {
        Transaction tx = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            tx = session.beginTransaction();
            session.save(otp);
            tx.commit();
            return true;
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            return false;
        } finally {
            session.close();
        }
    }

    // New: Find valid OTP by user, code, and purpose
    public Otp findValidOtpByUserAndPurpose(User user, String code, String purpose) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Otp otp = (Otp) session.createQuery("FROM Otp WHERE user = :user AND code = :code AND purpose = :purpose AND used = false AND expirationTime > :now")
                .setParameter("user", user)
                .setParameter("code", code)
                .setParameter("purpose", purpose)
                .setParameter("now", LocalDateTime.now())
                .uniqueResult();
        session.close();
        return otp;
    }

    public void markOtpAsUsed(Otp otp) {
        Transaction tx = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            tx = session.beginTransaction();
            otp.setUsed(true);
            session.update(otp);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
        } finally {
            session.close();
        }
    }
}