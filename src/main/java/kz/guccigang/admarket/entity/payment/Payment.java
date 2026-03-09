package kz.guccigang.admarket.entity.payment;

import jakarta.persistence.*;
import kz.guccigang.admarket.entity.BaseEntity;
import kz.guccigang.admarket.entity.User;
import kz.guccigang.admarket.enums.PaymentStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "payments")
@NoArgsConstructor
public class Payment extends BaseEntity {
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(nullable = false)
    private Long amount;

    @Enumerated(EnumType.STRING)
    @Column(name = "payment_status", nullable = false)
    private PaymentStatus paymentStatus;
}
