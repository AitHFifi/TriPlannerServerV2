/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

/**
 *
 * @author Hp
 */
public class EmailUtil {

    // Stub method for sending OTP email (replace with JavaMail in production)
    public static boolean sendOtpEmail(String recipientEmail, String otpCode) {
        // In production, use JavaMail API to send real emails.
        // For now, just print to console as a stub.
        System.out.println("Sending OTP " + otpCode + " to email: " + recipientEmail);
        // Simulate success
        return true;
    }
}
