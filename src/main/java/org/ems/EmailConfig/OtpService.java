package org.ems.EmailConfig;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class OtpService {
    private static final long OTP_VALID_DURATION = 5 * 60 * 1000; // 5 minutes
    private final Map<String, OtpEntry> otpStorage = new HashMap<>();

    private static class OtpEntry {
        String otp;
        long expiryTime;

        OtpEntry(String otp, long expiryTime) {
            this.otp = otp;
            this.expiryTime = expiryTime;
        }
    }

    // Store OTP with Expiry Time
    public void storeOtp(String email, String otp) {
        long expiryTime = System.currentTimeMillis() + OTP_VALID_DURATION;
        otpStorage.put(email, new OtpEntry(otp, expiryTime));
    }

    // Verify OTP and Check Expiry
    public boolean verifyOtp(String email, String userOtp) {
        OtpEntry storedOtp = otpStorage.get(email);

        if (storedOtp == null || System.currentTimeMillis() > storedOtp.expiryTime) {
            otpStorage.remove(email); // Remove expired OTP
            return false;
        }

        if (storedOtp.otp.equals(userOtp)) {
            otpStorage.remove(email); // Remove OTP after successful verification
            return true;
        }

        return false;
    }
}
