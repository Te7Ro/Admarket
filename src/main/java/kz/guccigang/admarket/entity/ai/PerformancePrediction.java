package kz.guccigang.admarket.entity.ai;

import jakarta.persistence.*;
import kz.guccigang.admarket.entity.BaseEntity;
import kz.guccigang.admarket.entity.creator.CreatorProfile;
import kz.guccigang.admarket.entity.offer.Offer;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "offer_risk_analysis")
@Getter
@Setter
@NoArgsConstructor
public class PerformancePrediction extends BaseEntity {
    @ManyToOne
    @JoinColumn(name = "offer_id", nullable = false)
    private Offer offer;

    @ManyToOne
    @JoinColumn(name = "creator_id", nullable = false)
    private CreatorProfile creator;

    @Column(nullable = false)
    private Integer predictedViews;

    @Column(precision = 6, scale = 3, nullable = false)
    private BigDecimal predictedEngagement;

    @Column(precision = 10, scale = 4)
    private BigDecimal predictedRoi;

    @Column(precision = 4, scale = 3, nullable = false)
    private BigDecimal confidenceScore;
}
