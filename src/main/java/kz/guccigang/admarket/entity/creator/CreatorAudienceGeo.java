package kz.guccigang.admarket.entity.creator;

import jakarta.persistence.*;
import kz.guccigang.admarket.entity.BaseEntity;
import kz.guccigang.admarket.entity.Country;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "creator_audience_geo")
@Getter
@Setter
@NoArgsConstructor
public class CreatorAudienceGeo extends BaseEntity {
    @ManyToOne
    @JoinColumn(name = "creator_id", nullable = false)
    private CreatorProfile creator;

    @ManyToOne
    @JoinColumn(name = "country_id", nullable = false)
    private Country country;

    @Column(precision = 5, scale = 2, nullable = false)
    private BigDecimal percentage;
}
