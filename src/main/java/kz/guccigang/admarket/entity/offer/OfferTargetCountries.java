package kz.guccigang.admarket.entity.offer;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import kz.guccigang.admarket.entity.BaseEntity;
import kz.guccigang.admarket.entity.Country;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "offer_target_countries")
@Getter
@Setter
@NoArgsConstructor
public class OfferTargetCountries extends BaseEntity {
    @ManyToOne
    @JoinColumn(name = "offer_id", nullable = false)
    private Offer offer;

    @ManyToOne
    @JoinColumn(name = "country_id", nullable = false)
    private Country country;
}
