package kz.guccigang.admarket.entity.company;

import jakarta.persistence.*;
import kz.guccigang.admarket.entity.BaseEntity;
import kz.guccigang.admarket.entity.Country;
import kz.guccigang.admarket.entity.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "company_profiles")
@Getter
@Setter
@NoArgsConstructor
public class CompanyProfile extends BaseEntity {
    @OneToOne
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    private User user;

    @Column(nullable = false)
    private String companyName;

    @ManyToOne
    @JoinColumn(name = "industry_id", nullable = false)
    private Industry industry;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String description;

    private String websiteUrl;

    @ManyToOne
    @JoinColumn(name = "country_id", nullable = false)
    private Country country;

    @Column(precision = 12, scale = 2, nullable = false)
    private BigDecimal minBudget;
    @Column(precision = 12, scale = 2, nullable = false)
    private BigDecimal maxBudget;
}
