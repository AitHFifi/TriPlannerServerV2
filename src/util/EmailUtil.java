/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

/**
 *
 * @author Hp
// */
//public class EmailUtil {
//
//    // Stub method for sending OTP email (replace with JavaMail in production)
//    public static boolean sendOtpEmail(String recipientEmail, String otpCode) {
//        // In production, use JavaMail API to send real emails.
//        // For now, just print to console as a stub.
//        System.out.println("Sending OTP " + otpCode + " to email: " + recipientEmail);
//        // Simulate success
//        return true;
//    }
//}

import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;

public class EmailUtil {

    // Replace these with your SMTP server details
    private static final String SMTP_HOST = "smtp.gmail.com";
    private static final String SMTP_PORT = "587"; 
    private static final String SENDER_EMAIL = "";
    private static final String SENDER_PASSWORD = ""; // Use app password, not your main password

    public static boolean sendOtpEmail(String recipientEmail, String otpCode) {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true"); // TLS
        props.put("mail.smtp.host", SMTP_HOST);
        props.put("mail.smtp.port", SMTP_PORT);

        Session session = Session.getInstance(props,
            new Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(SENDER_EMAIL, SENDER_PASSWORD);
                }
            });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(SENDER_EMAIL));
            message.setRecipients(
                Message.RecipientType.TO, InternetAddress.parse(recipientEmail));
            message.setSubject("Your OTP Code");
            message.setText("Your OTP code is: " + otpCode + "\n\nIf you did not request this, please ignore this email.");

            Transport.send(message);
            System.out.println("OTP email sent successfully to " + recipientEmail);
            return true;
        } catch (MessagingException e) {
            e.printStackTrace();
            System.err.println("Failed to send OTP email to " + recipientEmail);
            return false;
        }
    }
}
