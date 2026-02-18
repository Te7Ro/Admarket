package kz.guccigang.admarket.entity.widget;

import jakarta.persistence.*;
import kz.guccigang.admarket.entity.BaseEntity;
import kz.guccigang.admarket.entity.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.ZonedDateTime;

@Entity
@Table(name = "chat_widget_session")
@Getter
@Setter
@NoArgsConstructor
public class ChatWidgetSession extends BaseEntity {
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(nullable = false)
    private ZonedDateTime startedAt = ZonedDateTime.now();
    private ZonedDateTime endedAt;

    @Column(columnDefinition = "jsonb", nullable = false)
    private String context;
}
