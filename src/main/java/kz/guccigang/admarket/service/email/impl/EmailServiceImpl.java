package kz.guccigang.admarket.service.email.impl;

import jakarta.mail.internet.MimeMessage;
import kz.guccigang.admarket.service.email.EmailService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {
    private final JavaMailSender mailSender;

    public void sendConfirmationEmail(String to, String code){
        try {

            String subject = "Confirm your AdMarket account";

            String content = """
                    <!DOCTYPE html>
                    <html>
                    <head>
                        <meta charset="UTF-8">
                    </head>
                    <body style="margin:0;padding:0;font-family:Arial,sans-serif;background-color:#f4f6f8;">
                    
                        <table align="center" width="100%%" cellpadding="0" cellspacing="0" style="max-width:600px;background-color:#ffffff;margin-top:20px;border-radius:10px;box-shadow:0 4px 10px rgba(0,0,0,0.1);">
                            
                            <tr>
                                <td style="background-color:#2563eb;color:#ffffff;padding:20px;border-top-left-radius:10px;border-top-right-radius:10px;text-align:center;">
                                    <h2 style="margin:0;">AdMarket</h2>
                                </td>
                            </tr>
                    
                            <tr>
                                <td style="padding:30px;">
                    
                                    <h3 style="margin-top:0;">Confirm your email address</h3>
                    
                                    <p style="color:#555;">
                                        Thank you for registering with <strong>AdMarket</strong>.
                                        Please use the verification code below to activate your account:
                                    </p>
                    
                                    <div style="text-align:center;margin:30px 0;">
                                        <span style="display:inline-block;font-size:28px;font-weight:bold;color:#2563eb;background:#f1f5f9;padding:15px 25px;border-radius:8px;letter-spacing:4px;">
                                            %s
                                        </span>
                                    </div>
                    
                                    <p style="color:#555;">
                                        This code will expire in <strong>10 minutes</strong>.
                                    </p>
                    
                                    <p style="color:#999;font-size:12px;">
                                        If you did not create an account, you can safely ignore this email.
                                    </p>
                    
                                </td>
                            </tr>
                    
                            <tr>
                                <td style="background-color:#f8fafc;padding:15px;text-align:center;font-size:12px;color:#999;border-bottom-left-radius:10px;border-bottom-right-radius:10px;">
                                    © 2026 AdMarket. All rights reserved.
                                </td>
                            </tr>
                    
                        </table>
                    
                    </body>
                    </html>
                    """.formatted(code);

            MimeMessage message = mailSender.createMimeMessage();

            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(content, true); // true = HTML

            mailSender.send(message);

            log.info("Confirmation email sent to {}", to);

        } catch (Exception e) {

            log.error("Send confirmation email error {}", e.getMessage(), e);

            throw new RuntimeException("Failed to send email");
        }
    }

    public void sendForgetPasswordEmail(String to, String code) {
        try {

            String subject = "Reset your AdMarket password";

            String content = """
                    <!DOCTYPE html>
                    <html>
                    <head>
                        <meta charset="UTF-8">
                    </head>
                    <body style="margin:0;padding:0;font-family:Arial,sans-serif;background-color:#f4f6f8;">
                    
                        <table align="center" width="100%%" cellpadding="0" cellspacing="0"
                               style="max-width:600px;background-color:#ffffff;margin-top:20px;border-radius:10px;
                               box-shadow:0 4px 10px rgba(0,0,0,0.1);">
                    
                            <tr>
                                <td style="background-color:#dc2626;color:#ffffff;padding:20px;
                                           border-top-left-radius:10px;border-top-right-radius:10px;text-align:center;">
                                    <h2 style="margin:0;">AdMarket</h2>
                                </td>
                            </tr>
                    
                            <tr>
                                <td style="padding:30px;">
                    
                                    <h3 style="margin-top:0;color:#111;">Password Reset Request</h3>
                    
                                    <p style="color:#555;">
                                        We received a request to reset your AdMarket account password.
                                        Use the verification code below to proceed:
                                    </p>
                    
                                    <div style="text-align:center;margin:30px 0;">
                                        <span style="display:inline-block;font-size:28px;font-weight:bold;
                                                     color:#dc2626;background:#fef2f2;padding:15px 25px;
                                                     border-radius:8px;letter-spacing:4px;">
                                            %s
                                        </span>
                                    </div>
                    
                                    <p style="color:#555;">
                                        This code will expire in <strong>10 minutes</strong>.
                                    </p>
                    
                                    <p style="color:#555;">
                                        If you did not request a password reset, please ignore this email.
                                        Your password will remain unchanged.
                                    </p>
                    
                                    <p style="color:#999;font-size:12px;margin-top:30px;">
                                        For security reasons, never share this code with anyone.
                                    </p>
                    
                                </td>
                            </tr>
                    
                            <tr>
                                <td style="background-color:#f8fafc;padding:15px;text-align:center;
                                           font-size:12px;color:#999;
                                           border-bottom-left-radius:10px;border-bottom-right-radius:10px;">
                                    © 2026 AdMarket. All rights reserved.
                                </td>
                            </tr>
                    
                        </table>
                    
                    </body>
                    </html>
                    """.formatted(code);

            MimeMessage message = mailSender.createMimeMessage();

            MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(content, true);

            mailSender.send(message);

            log.info("Password reset email sent to {}", to);

        } catch (Exception e) {

            log.error("Send password reset email error {}", e.getMessage(), e);

            throw new RuntimeException("Failed to send password reset email");
        }
    }
}
