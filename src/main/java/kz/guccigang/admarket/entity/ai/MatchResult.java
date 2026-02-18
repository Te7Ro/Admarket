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
@Table(name = "match_results")
@Getter
@Setter
@NoArgsConstructor
public class MatchResult extends BaseEntity {
    @ManyToOne
    @JoinColumn(name = "offer_id", nullable = false)
    private Offer offer;

    @ManyToOne
    @JoinColumn(name = "creator_id", nullable = false)
    private CreatorProfile creator;

    @Column(precision = 5, scale = 4, nullable = false)
    private BigDecimal similarityScore;

    @Column(precision = 5, scale = 4, nullable = false)
    private BigDecimal performanceScore;

    @Column(precision = 5, scale = 4, nullable = false)
    private BigDecimal riskPenalty;

    @Column(precision = 5, scale = 4, nullable = false)
    private BigDecimal finalScore;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String explanation;

    @Column(columnDefinition = "jsonb", nullable = false)
    private String breakdown;
}
