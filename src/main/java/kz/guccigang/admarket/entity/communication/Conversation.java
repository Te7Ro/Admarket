package kz.guccigang.admarket.entity.communication;

import jakarta.persistence.*;
import kz.guccigang.admarket.entity.BaseEntity;
import kz.guccigang.admarket.entity.User;
import kz.guccigang.admarket.entity.offer.Offer;
import kz.guccigang.admarket.enums.ConversationType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.ZonedDateTime;

@Entity
@Table(name = "conversations")
@Getter
@Setter
@NoArgsConstructor
public class Conversation extends BaseEntity {
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ConversationType type;

    @ManyToOne
    @JoinColumn(name = "offer_id")
    private Offer offer;

    @ManyToOne
    @JoinColumn(name = "created_by_user_id")
    private User createdBy;

    @Column(nullable = false)
    private ZonedDateTime lastMessageAt;
}
