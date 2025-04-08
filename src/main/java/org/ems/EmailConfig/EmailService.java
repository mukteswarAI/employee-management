package org.ems.EmailConfig;


import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.extern.slf4j.Slf4j;
import org.ems.employee.model.Employee;
import org.ems.employee.model.LeaveRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class EmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String emailSender;

    // This Method is for Sending the mail and the Parameter is EmailDetails Class
    public void sendOtpMail(EmailDetails emailDetails) {
        try {
//            SimpleMailMessage message = new SimpleMailMessage();
//            message.setFrom(emailSender);
//            message.setTo(emailDetails.getRecipient());
//            message.setText(emailDetails.getMessageBody());
//            message.setSubject(emailDetails.getSubject());

            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
            mimeMessageHelper.setTo(emailDetails.getRecipient());
            mimeMessageHelper.setSubject(emailDetails.getSubject());
            mimeMessageHelper.setText("""
                    <div style="font-family: Arial, sans-serif; color: #333333; padding: 20px; background-color: #f4f4f4; border-radius: 10px;">
                                  <div style="background-color: #ffffff; padding: 20px; border-radius: 10px; box-shadow: 0 0 10px rgba(0,0,0,0.1); text-align: center;">
                                      <h2 style="color: #0056b3;">Your Verification Code</h2>
                                      <p style="font-size: 18px; margin-bottom: 30px;">Please use the following One-Time Password (OTP) to complete your verification process:</p>
                                      <div style="font-size: 24px; font-weight: bold; color: #28a745; padding: 10px 20px; border: 2px dashed #28a745; display: inline-block; border-radius: 5px;">
                                          %s
                                      </div>
                                      <p style="margin-top: 30px; font-size: 14px; color: #555555;">If you did not request this code, please ignore this email or contact our support team.</p>
                                  </div>
                                  <div style="text-align: center; font-size: 12px; color: #888888; margin-top: 20px;">
                                      © 2025 EvoStaff . All rights reserved.
                                  </div>
                          </div>
	        """.formatted(emailDetails.getOtp()),true);


            javaMailSender.send(mimeMessage);
            System.out.println("Email sent successfully to " + emailDetails.getRecipient());
            System.out.println("Email sender " + emailSender);


        }catch (MailException | MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    public void sendUserCredentials(EmailDetails emailDetails, Employee employee) {
        try {
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true, "UTF-8");

            mimeMessageHelper.setTo(emailDetails.getRecipient());
            mimeMessageHelper.setSubject(emailDetails.getSubject());

            // Email body with Employee login credentials
            String emailContent = """
                    <div style="font-family: Arial, sans-serif; color: #333333; padding: 20px; background-color: #f4f4f4; border-radius: 10px;">
                        <div style="background-color: #ffffff; padding: 20px; border-radius: 10px; box-shadow: 0 0 10px rgba(0,0,0,0.1); text-align: center;">
                            <h2 style="color: #0056b3;">Welcome to EvoStaff</h2>
                            <p style="font-size: 18px; margin-bottom: 30px;">Your account has been created successfully! Below are your login details:</p>
                            <div style="font-size: 18px; font-weight: bold; padding: 10px; background-color: #f8f9fa; border-radius: 5px; border: 1px solid #ddd; text-align: left; display: inline-block;">
                                <p><strong>Email ID:</strong> %s</p>
                                <p><strong>Password:</strong> %s</p>
                            </div>
                            <p style="margin-top: 30px; font-size: 14px; color: #555555;">
                                Please use these credentials to log in to your account. It is highly recommended that you change your password after your first login.
                            </p>
                            <a href="http://localhost:8181/" style="display: inline-block; padding: 10px 20px; background-color: #28a745; color: #ffffff; text-decoration: none; border-radius: 5px; font-size: 16px; font-weight: bold;">Login Now</a>
                        </div>
                        <div style="text-align: center; font-size: 12px; color: #888888; margin-top: 20px;">
                            © 2025 EvoStaff. All rights reserved.
                        </div>
                    </div>
                    """.formatted(employee.getEmail(), employee.getPassword());

            mimeMessageHelper.setText(emailContent, true); // Enable HTML formatting
            javaMailSender.send(mimeMessage); // Send email

        } catch (MailException | MessagingException e) {
            throw new RuntimeException("Failed to send login credentials email", e);
        }
    }


    public void sendLeaveRequest(EmailDetails emailDetails, LeaveRequest leaveRequest) {
        try {
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true, "UTF-8");

            mimeMessageHelper.setTo(emailDetails.getRecipient()); // Employee email
            mimeMessageHelper.setSubject("Leave Request Submitted - #" + leaveRequest.getLeaveId());

            // Email body with Leave Request details
//            String emailContent = """
//                <div style="font-family: Arial, sans-serif; color: #333333; padding: 20px; background-color: #f4f4f4; border-radius: 10px;">
//                    <div style="background-color: #ffffff; padding: 20px; border-radius: 10px; box-shadow: 0 0 10px rgba(0,0,0,0.1); text-align: center;">
//                        <h2 style="color: #0056b3;">Leave Request Submitted</h2>
//                        <p style="font-size: 18px; margin-bottom: 30px;">Dear %s,</p>
//                        <p>Your leave request has been successfully submitted. Below are the details:</p>
//                        <div style="font-size: 16px; padding: 10px; background-color: #f8f9fa; border-radius: 5px; border: 1px solid #ddd; text-align: left; display: inline-block;">
//                            <p><strong>Leave Request ID:</strong> #%d</p>
//                            <p><strong>Leave Type:</strong> %s</p>
//                            <p><strong>Description:</strong> %s</p>
//                            <p><strong>Contact Number:</strong> %s</p>
//                            <p><strong>Status:</strong> Pending</p>
//                        </div>
//                        <p style="margin-top: 20px; font-size: 14px; color: #555555;">
//                            Our HR team will review your request and update you soon. You will receive an email once your request is processed.
//                        </p>
//                        <p style="font-size: 14px; color: #777777;">For any urgent queries, please contact HR.</p>
//                    </div>
//                    <div style="text-align: center; font-size: 12px; color: #888888; margin-top: 20px;">
//                        © 2025 EvoStaff. All rights reserved.
//                    </div>
//                </div>
//                """.formatted(
//                    leaveRequest.getEmpName(),
//                    leaveRequest.getLeaveId(),
//                    leaveRequest.getLeaveType(),
//                    leaveRequest.getLeaveDescription(),
//                    leaveRequest.getContactNumber()
//            );
            String emailContent = String.format(
                    "<div style=\"font-family: Arial, sans-serif; color: #333333; padding: 20px; background-color: #f4f4f4; border-radius: 10px;\">" +
                    "    <div style=\"background-color: #ffffff; padding: 20px; border-radius: 10px; box-shadow: 0 0 10px rgba(0,0,0,0.1); text-align: center;\">" +
                    "        <h2 style=\"color: #0056b3;\">Leave Request Submitted</h2>" +
                    "        <p style=\"font-size: 18px; margin-bottom: 30px;\">Dear %s,</p>" +
                    "        <p>Your leave request has been successfully submitted. Below are the details:</p>" +
                    "        <div style=\"font-size: 16px; padding: 10px; background-color: #f8f9fa; border-radius: 5px; border: 1px solid #ddd; text-align: left; display: inline-block;\">" +
                    "            <p><strong>Leave Request ID:</strong> #%d</p>" +
                    "            <p><strong>Leave Type:</strong> %s</p>" +
                    "            <p><strong>Description:</strong> %s</p>" +
                    "            <p><strong>Contact Number:</strong> %s</p>" +
                    "            <p><strong>Status:</strong> Pending</p>" +
                    "        </div>" +
                    "        <p style=\"margin-top: 20px; font-size: 14px; color: #555555;\">" +
                    "            Our HR team will review your request and update you soon. You will receive an email once your request is processed." +
                    "        </p>" +
                    "        <p style=\"font-size: 14px; color: #777777;\">For any urgent queries, please contact HR.</p>" +
                    "    </div>" +
                    "    <div style=\"text-align: center; font-size: 12px; color: #888888; margin-top: 20px;\">" +
                    "        © 2025 EvoStaff. All rights reserved." +
                    "    </div>" +
                    "</div>",
                    leaveRequest.getEmpName(),
                    leaveRequest.getLeaveId(),
                    leaveRequest.getLeaveType(),
                    leaveRequest.getLeaveDescription(),
                    leaveRequest.getContactNumber()
                );

            mimeMessageHelper.setText(emailContent, true); // Enable HTML formatting
            javaMailSender.send(mimeMessage); // Send email

        } catch (MailException | MessagingException e) {
            throw new RuntimeException("Failed to send leave request email", e);
        }




    }






}
