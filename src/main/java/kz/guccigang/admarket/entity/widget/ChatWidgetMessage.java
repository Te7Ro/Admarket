package kz.guccigang.admarket.entity.widget;

import jakarta.persistence.*;
import kz.guccigang.admarket.entity.BaseEntity;
import kz.guccigang.admarket.enums.ChatSender;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "chat_widget_messages")
@Getter
@Setter
@NoArgsConstructor
public class ChatWidgetMessage extends BaseEntity {
    @ManyToOne
    @JoinColumn(name = "session_id", nullable = false)
    private ChatWidgetSession session;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ChatSender sender;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String text;

    @Column(length = 64)
    private String intent;

    @Column(columnDefinition = "jsonb", nullable = false)
    private String metadata;
}
