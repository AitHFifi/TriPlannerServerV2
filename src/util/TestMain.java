/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

/**
 *
 * @author AitHFifi
 */
public class TestMain {
      public static void main(String[] args) {
        try {
            // Replace with your own test number and code
            String testPhone = "+250792402862";
            String testCode = "123456";
            SmsUtil.sendOtpSms(testPhone, testCode);
            System.out.println("SMS sent. If you see no error, Jackson and Twilio are working!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
