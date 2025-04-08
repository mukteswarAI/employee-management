package org.ems.utils;

import java.security.SecureRandom;

public class OtpGenerator {

    private static final SecureRandom secureRandom = new SecureRandom();

    // This Method will generate a 4-digit OTP
    public static String generateOtp() {
        return String.format("%04d", secureRandom.nextInt(10000)); // Generates 0000-9999
    }

    public static void main(String[] args) {
        System.out.println("Generated OTP: " + generateOtp());
    }
}
