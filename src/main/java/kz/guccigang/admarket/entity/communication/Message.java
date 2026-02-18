package kz.guccigang.admarket.entity.communication;

import jakarta.persistence.*;
import kz.guccigang.admarket.entity.BaseEntity;
import kz.guccigang.admarket.entity.User;
import kz.guccigang.admarket.enums.MessageType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.ZonedDateTime;

@Entity
@Table(name = "messages")
@Getter
@Setter
@NoArgsConstructor
public class Message extends BaseEntity {
    @ManyToOne
    @JoinColumn(name = "conversation_id", nullable = false)
    private Conversation conversation; // FK на Conversation

    @ManyToOne
    @JoinColumn(name = "sender_user_id", nullable = false)
    private User senderUser; // FK на User

    @Enumerated(EnumType.STRING)
    @Column(name = "message_type", nullable = false)
    private MessageType messageType;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String body;

    private ZonedDateTime readAt;
}
