package za.co.creche_server.service;

import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class ChatService {

    private final RestTemplate restTemplate = new RestTemplate();

    public String generateReply(String userMessage) {
        String url = "http://localhost:11434/api/generate";

        // 🧠 System prompt for Little Einsteins
        String systemPrompt = "You are a friendly and helpful virtual assistant for a South African crèche called 'Little Einsteins'.\n" +
                "Here are the facts about the crèche:\n" +
                "- Fees: R1500 per month\n" +
                "- Operating Hours: 7am to 5pm, Monday to Friday\n" +
                "- Age Group: 6 months to 5 years\n" +
                "- Curriculum: Montessori-based learning, play-focused\n" +
                "- Location: Durban North, South Africa\n" +
                "- Safety: 24/7 surveillance, biometric sign-in\n" +
                "- Staff: Certified in Early Childhood Development (ECD) and First Aid\n\n" +
                "Always answer clearly, politely, and in a tone suitable for parents.\n\n";

        // Combine system prompt with user input
        String fullPrompt = systemPrompt + userMessage;

        // 📨 Prepare request
        Map<String, Object> request = new HashMap<>();
        request.put("model", "llama3");
        request.put("prompt", fullPrompt);
        request.put("stream", false);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(request, headers);

        // 📡 Make the call to Ollama
        try {
            ResponseEntity<Map> response = restTemplate.postForEntity(url, entity, Map.class);
            Map body = response.getBody();
            return body != null ? body.get("response").toString() : "No response from AI.";
        } catch (Exception e) {
            e.printStackTrace(); // Show full error in console
            return "Error talking to AI: " + e.getMessage();
        }
    }
}
