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
                  <meta name="viewport" content="width=device-width, initial-scale=1.0">
                  <title>Confirm your email</title>
                </head>
                
                <body style="margin:0;padding:0;background:#F9FAFB;font-family:Arial,Helvetica,sans-serif;line-height:1.5;color:#111827;">
                  <!-- Preheader (hidden) -->
                  <div style="display:none;max-height:0;overflow:hidden;opacity:0;color:transparent;">
                    Your AdMarket verification code is 02026. It expires in 10 minutes.
                  </div>
                
                  <table role="presentation" width="100%%" cellpadding="0" cellspacing="0" style="width:100%%;background:#F9FAFB;padding:24px 12px;">
                    <tr>
                      <td align="center">
                
                        <!-- Card -->
                        <table role="presentation" cellpadding="0" cellspacing="0"
                               style="width:100%%;max-width:600px;background:#ffffff;border:1px solid #E5E7EB;border-radius:16px;overflow:hidden;box-shadow:0 12px 30px rgba(17,24,39,0.08);">
                
                          <!-- Header -->
                          <tr>
                            <td style="padding:22px 24px;background:#ffffff;border-bottom:1px solid #E5E7EB;">
                              <table role="presentation" width="100%%" cellpadding="0" cellspacing="0">
                                <tr>
                                  <td align="left" style="vertical-align:middle;">
                                    <table role="presentation" cellpadding="0" cellspacing="0">
                                      <tr>
                                        <td style="width:34px;height:34px;background:#1E3A8A;border-radius:10px;text-align:center;vertical-align:middle;">
                                          <span style="display:inline-block;color:#ffffff;font-weight:800;font-size:18px;line-height:34px;">A</span>
                                        </td>
                                        <td style="padding-left:10px;vertical-align:middle;">
                                          <span style="font-size:18px;font-weight:800;color:#1E3A8A;">AdMarket</span>
                                        </td>
                                      </tr>
                                    </table>
                                  </td>
                                  <td align="right" style="vertical-align:middle;">
                                    <span style="display:inline-block;background:#EFF6FF;color:#3B82F6;border-radius:999px;padding:6px 12px;font-size:12px;font-weight:700;">
                                      ✨ AI-Powered Matching
                                    </span>
                                  </td>
                                </tr>
                              </table>
                            </td>
                          </tr>
                
                          <!-- Body -->
                          <tr>
                            <td style="padding:28px 24px;">
                              <h1 style="margin:0 0 8px 0;font-size:22px;line-height:1.25;color:#111827;">
                                Confirm your email address
                              </h1>
                
                              <p style="margin:0 0 16px 0;font-size:15px;color:#4B5563;">
                                Thanks for joining <strong style="color:#111827;">AdMarket</strong>.
                                Use the verification code below to activate your account.
                              </p>
                
                              <!-- Code block -->
                              <div style="margin:22px 0 18px 0;text-align:center;">
                                <div style="display:inline-block;background:#F3F4F6;border:1px solid #E5E7EB;border-radius:14px;padding:14px 18px;">
                                  <div style="font-size:28px;font-weight:800;letter-spacing:6px;color:#1E3A8A;">
                                    %s
                                  </div>
                                </div>
                              </div>
                
                              <!-- Details -->
                              <table role="presentation" width="100%%" cellpadding="0" cellspacing="0"
                                     style="width:100%%;background:#F9FAFB;border:1px solid #E5E7EB;border-radius:12px;">
                                <tr>
                                  <td style="padding:14px 14px;">
                                    <p style="margin:0;font-size:13px;color:#4B5563;">
                                      ⏱️ This code expires in <strong>10 minutes</strong>.
                                    </p>
                                  </td>
                                </tr>
                              </table>
                
                              <p style="margin:18px 0 0 0;font-size:12px;color:#6B7280;">
                                If you didn’t create an account, you can safely ignore this email.
                              </p>
                            </td>
                          </tr>
                
                          <!-- Footer -->
                          <tr>
                            <td style="padding:16px 24px;background:#ffffff;border-top:1px solid #E5E7EB;text-align:center;">
                              <p style="margin:0;font-size:12px;color:#6B7280;">
                                © 2026 AdMarket. All rights reserved.
                              </p>
                            </td>
                          </tr>
                
                        </table>
                        <!-- /Card -->
                
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
                  <meta name="viewport" content="width=device-width, initial-scale=1.0">
                  <title>Reset your password</title>
                </head>
                
                <body style="margin:0;padding:0;background:#F9FAFB;font-family:Arial,Helvetica,sans-serif;line-height:1.5;color:#111827;">
                  <!-- Preheader (hidden) -->
                  <div style="display:none;max-height:0;overflow:hidden;opacity:0;color:transparent;">
                    Your AdMarket password reset code is 02026. It expires in 10 minutes.
                  </div>
                
                  <table role="presentation" width="100%%" cellpadding="0" cellspacing="0" style="width:100%%;background:#F9FAFB;padding:24px 12px;">
                    <tr>
                      <td align="center">
                
                        <!-- Card -->
                        <table role="presentation" cellpadding="0" cellspacing="0"
                               style="width:100%%;max-width:600px;background:#ffffff;border:1px solid #E5E7EB;border-radius:16px;overflow:hidden;box-shadow:0 12px 30px rgba(17,24,39,0.08);">
                
                          <!-- Header -->
                          <tr>
                            <td style="padding:22px 24px;background:#ffffff;border-bottom:1px solid #E5E7EB;">
                              <table role="presentation" width="100%%" cellpadding="0" cellspacing="0">
                                <tr>
                                  <td align="left" style="vertical-align:middle;">
                                    <table role="presentation" cellpadding="0" cellspacing="0">
                                      <tr>
                                        <td style="width:34px;height:34px;background:#1E3A8A;border-radius:10px;text-align:center;vertical-align:middle;">
                                          <span style="display:inline-block;color:#ffffff;font-weight:800;font-size:18px;line-height:34px;">A</span>
                                        </td>
                                        <td style="padding-left:10px;vertical-align:middle;">
                                          <span style="font-size:18px;font-weight:800;color:#1E3A8A;">AdMarket</span>
                                        </td>
                                      </tr>
                                    </table>
                                  </td>
                                  <td align="right" style="vertical-align:middle;">
                                    <span style="display:inline-block;background:#FEF2F2;color:#DC2626;border-radius:999px;padding:6px 12px;font-size:12px;font-weight:700;">
                                      🔒 Security Notice
                                    </span>
                                  </td>
                                </tr>
                              </table>
                            </td>
                          </tr>
                
                          <!-- Body -->
                          <tr>
                            <td style="padding:28px 24px;">
                              <h1 style="margin:0 0 8px 0;font-size:22px;line-height:1.25;color:#111827;">
                                Password reset request
                              </h1>
                
                              <p style="margin:0 0 16px 0;font-size:15px;color:#4B5563;">
                                We received a request to reset your <strong style="color:#111827;">AdMarket</strong> password.
                                Use the code below to proceed.
                              </p>
                
                              <!-- Code block -->
                              <div style="margin:22px 0 18px 0;text-align:center;">
                                <div style="display:inline-block;background:#FEF2F2;border:1px solid #FECACA;border-radius:14px;padding:14px 18px;">
                                  <div style="font-size:28px;font-weight:800;letter-spacing:6px;color:#DC2626;">
                                    %s
                                  </div>
                                </div>
                              </div>
                
                              <!-- Details -->
                              <table role="presentation" width="100%%" cellpadding="0" cellspacing="0"
                                     style="width:100%%;background:#F9FAFB;border:1px solid #E5E7EB;border-radius:12px;">
                                <tr>
                                  <td style="padding:14px 14px;">
                                    <p style="margin:0;font-size:13px;color:#4B5563;">
                                      ⏱️ This code expires in <strong>10 minutes</strong>.
                                    </p>
                                  </td>
                                </tr>
                              </table>
                
                              <p style="margin:18px 0 0 0;font-size:12px;color:#6B7280;">
                                If you didn’t request a password reset, ignore this email. For security, never share this code with anyone.
                              </p>
                            </td>
                          </tr>
                
                          <!-- Footer -->
                          <tr>
                            <td style="padding:16px 24px;background:#ffffff;border-top:1px solid #E5E7EB;text-align:center;">
                              <p style="margin:0;font-size:12px;color:#6B7280;">
                                © 2026 AdMarket. All rights reserved.
                              </p>
                            </td>
                          </tr>
                
                        </table>
                        <!-- /Card -->
                
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
