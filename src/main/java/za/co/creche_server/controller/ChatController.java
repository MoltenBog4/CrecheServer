package za.co.creche_server.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.co.creche_server.dto.ChatRequest;
import za.co.creche_server.dto.ChatResponse;
import za.co.creche_server.service.ChatService;

@RestController
@RequestMapping("/api/chat")
@RequiredArgsConstructor
public class ChatController {

    private final ChatService chatService;

    @PostMapping
    public ResponseEntity<ChatResponse> chat(@RequestBody ChatRequest request) {
        String reply = chatService.generateReply(request.getMessage());
        return ResponseEntity.ok(new ChatResponse(reply));
    }
}
