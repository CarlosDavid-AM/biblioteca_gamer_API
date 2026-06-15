package com.biblioteca.gemer.Controller;

import com.biblioteca.gemer.Service.JuegosService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/")
public class ChatController {
    private final ChatClient chatClient;

    ChatController(ChatClient.Builder builder, JuegosService juegosService) {
        this.chatClient = builder
                .defaultSystem("""
                You are a video game library assistant.
                You always answer based on the information you obtain from the available tools.
                If you don't know the answer, you will respond with "I don't know."
                """)
                .defaultTools(juegosService)
                .defaultAdvisors(new SimpleLoggerAdvisor())
                .build();
    }

    @PostMapping("/api/chat")
    Output chat(@RequestBody @Valid Input input) {
        String response = chatClient
                .prompt(input.prompt()).call().content();
        return new Output(response);
    }

    record Input(@NotBlank String prompt) {}
    record Output(String content) {}
}
