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
import java.security.SecureRandom;

public class OtpUtil {
    private static final SecureRandom secureRandom = new SecureRandom();

    public static String generateOtpCode(int length) {
        StringBuilder otp = new StringBuilder();
        for (int i = 0; i < length; i++) {
            otp.append(secureRandom.nextInt(10)); // 0-9
        }
        return otp.toString();
    }

    // Convenience method for 6-digit OTPs
    public static String generateSixDigitOtp() {
        return generateOtpCode(6);
    }
}
