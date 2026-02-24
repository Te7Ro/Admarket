package kz.guccigang.admarket.entity.email;

import jakarta.persistence.*;
import kz.guccigang.admarket.entity.BaseEntity;
import kz.guccigang.admarket.entity.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.ZonedDateTime;

@Entity
@Table(name = "email_confirmation_codes")
@Getter
@Setter
@NoArgsConstructor
public class EmailConfirmationCode extends BaseEntity {

    @Column(nullable = false)
    private String code;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false, unique = false)
    private User user;

    @Column(nullable = false)
    private ZonedDateTime expiresAt;

    @Column(nullable = false)
    private boolean used = false;
}
