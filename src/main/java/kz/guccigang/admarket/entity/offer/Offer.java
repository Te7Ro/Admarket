package kz.guccigang.admarket.entity.offer;

import jakarta.persistence.*;
import kz.guccigang.admarket.entity.BaseEntity;
import kz.guccigang.admarket.entity.Category;
import kz.guccigang.admarket.entity.company.CompanyProfile;
import kz.guccigang.admarket.enums.OfferStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "offers")
@Getter
@Setter
@NoArgsConstructor
public class Offer extends BaseEntity {
    @ManyToOne
    @JoinColumn(name = "company_id", nullable = false)
    private CompanyProfile company;

    @Column(nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String description;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @Column(precision = 12, scale = 2, nullable = false)
    private BigDecimal budget;

    @Column(length = 3, nullable = false)
    private String currency;

    @Column(nullable = false)
    private Integer targetMinAge;
    @Column(nullable = false)
    private Integer targetMaxAge;

    private LocalDate campaignStartDate;
    private LocalDate campaignEndDate;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private OfferStatus status;
}
