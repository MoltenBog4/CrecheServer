package za.co.creche_server.controller;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.MediaType;
import okhttp3.RequestBody;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping("/api/chat")
public class ChatController {

    private final OkHttpClient client = new OkHttpClient();

    @PostMapping
    public ResponseEntity<String> chat(@org.springframework.web.bind.annotation.RequestBody Map<String, String> payload) {
        String userMessage = payload.get("message");

        String rasaUrl = "http://localhost:5005/webhooks/rest/webhook";
        MediaType mediaType = MediaType.parse("application/json");

        String jsonRequest = "{\"sender\":\"user\", \"message\":\"" + userMessage + "\"}";
        RequestBody body = RequestBody.create(jsonRequest, mediaType);  // ✅ OK for Java 8

        Request request = new Request.Builder()
                .url(rasaUrl)
                .post(body)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                return ResponseEntity.status(response.code()).body("Rasa error.");
            }

            String responseBody = response.body().string();
            JSONArray responseArray = new JSONArray(responseBody);

            if (responseArray.length() > 0) {
                JSONObject firstMsg = responseArray.getJSONObject(0);
                String reply = firstMsg.optString("text", "Sorry, I didn’t get that.");
                return ResponseEntity.ok(reply);
            } else {
                return ResponseEntity.ok("I didn’t understand that. Try rephrasing?");
            }

        } catch (IOException e) {
            return ResponseEntity.status(500).body("Error communicating with Rasa: " + e.getMessage());
        }
    }
}
