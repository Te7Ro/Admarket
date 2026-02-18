package kz.guccigang.admarket.entity.ai;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import kz.guccigang.admarket.entity.BaseEntity;
import kz.guccigang.admarket.entity.offer.Offer;
import kz.guccigang.admarket.enums.ModerationStatus;
import kz.guccigang.admarket.enums.RiskLabel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "offer_risk_analysis")
@Getter
@Setter
@NoArgsConstructor
public class OfferRiskAnalysis extends BaseEntity {
    @OneToOne
    @JoinColumn(name = "offer_id", nullable = false)
    private Offer offer;

    @Column(precision = 4, scale = 3,nullable = false)
    @DecimalMin("0.000")
    @DecimalMax("1.000")
    private BigDecimal riskScore;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private RiskLabel riskLabel;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ModerationStatus moderationStatus;

    @Column(columnDefinition = "jsonb", nullable = false)
    private String reasons;
}
