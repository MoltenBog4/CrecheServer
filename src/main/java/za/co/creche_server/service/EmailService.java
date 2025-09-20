package za.co.creche_server.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendThankYouEmail(String toEmail) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(toEmail);
        message.setSubject("🎉 Thanks for Subscribing to Little Einsteins!");
        message.setText("Hi there!\n\nThank you for subscribing to our newsletter. We're excited to have you!\n\n🧸 Little Einsteins Crèche Team");

        mailSender.send(message);
    }
}
