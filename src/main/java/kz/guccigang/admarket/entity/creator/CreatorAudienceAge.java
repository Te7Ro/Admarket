package kz.guccigang.admarket.entity.creator;

import jakarta.persistence.*;
import kz.guccigang.admarket.entity.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "creator_audience_age")
@Getter
@Setter
@NoArgsConstructor
public class CreatorAudienceAge extends BaseEntity {
    @ManyToOne
    @JoinColumn(name = "creator_id", nullable = false)
    private CreatorProfile creator;

    @Column(nullable = false)
    private Integer ageBegin;
    @Column(nullable = false)
    private Integer ageEnd;

    @Column(precision = 5, scale = 2, nullable = false)
    private BigDecimal percentage;
}
