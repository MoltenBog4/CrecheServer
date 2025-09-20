package za.co.creche_server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import za.co.creche_server.service.EmailService;

@RestController
@RequestMapping("/api/subscribe")
@CrossOrigin(origins = "http://localhost:5173") // Match your React dev server port
public class SubscriptionController {

    @Autowired
    private EmailService emailService;

    @PostMapping
    public String subscribe(@RequestBody SubscriptionRequest request) {
        emailService.sendThankYouEmail(request.getEmail());
        return "✅ Email sent to: " + request.getEmail();
    }

    public static class SubscriptionRequest {
        private String email;

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }
    }
}
