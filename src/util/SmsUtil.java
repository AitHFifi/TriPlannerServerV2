package util;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

public class SmsUtil {
    // Replace these with your real Twilio credentials
    public static final String ACCOUNT_SID = "AC33ed1a57c9cb86e59d3d53f8e99a2aca";
    public static final String AUTH_TOKEN = "a67e3a817c8b3944054d425a8f383b17";
    public static final String FROM_NUMBER = "+17622390124"; // Your Twilio phone number

    static {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
    }

     // For Rwanda country code
    public static String formatRwandaPhoneNumber(String phone) {
        phone = phone.trim();
        if (phone.startsWith("+250")) {
            return phone;
        }
        // Remove leading 0 if present (local format)
        if (phone.startsWith("0")) {
            phone = phone.substring(1);
        }
        // Now it's e.g., "734485999" or "788xxxxxx"
        return "+250" + phone;
    }
    
    public static void sendOtpSms(String toPhone, String otpCode) {
        String formattedPhone = formatRwandaPhoneNumber(toPhone);
        String messageBody = "Your OTP code is: " + otpCode;

        Message.creator(
                new PhoneNumber(formattedPhone),
                new PhoneNumber(FROM_NUMBER),
                messageBody
        ).create();
    }
}