package kz.guccigang.admarket.controller.communication;

import kz.guccigang.admarket.service.communication.ConversationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/conversation")
public class ConversationController {
    private final ConversationService conversationService;
}
