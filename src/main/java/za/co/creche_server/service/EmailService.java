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
        message.setText("Hi there!\n\nThank you for subscribing to our newsletter. We're excited to have you!\n\n🧸 Little Einsteins Crèche Team \n\nDownload this Months Newsletter from here:https://drive.google.com/file/d/1L51VgHfmY_TIVuZ8NLZix1AcA-s4P8uK/view?usp=sharing ");

        mailSender.send(message);
    }
}
