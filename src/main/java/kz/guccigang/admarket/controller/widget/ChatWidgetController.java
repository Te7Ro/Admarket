package kz.guccigang.admarket.controller.widget;

import kz.guccigang.admarket.service.widget.ChatWidgetMessageService;
import kz.guccigang.admarket.service.widget.ChatWidgetSessionService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/chat-widget")
public class ChatWidgetController {
    private final ChatWidgetSessionService sessionService;
    private final ChatWidgetMessageService messageService;

}
