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

    private String companyName;

    @ManyToOne
    @JoinColumn(name = "industry_id")
    private Industry industry;

    @Column(columnDefinition = "TEXT")
    private String description;

    private String websiteUrl;

    @ManyToOne
    @JoinColumn(name = "country_id")
    private Country country;

    @Column(precision = 12, scale = 2, nullable = false)
    private BigDecimal minBudget = BigDecimal.ZERO;
    @Column(precision = 12, scale = 2, nullable = false)
    private BigDecimal maxBudget = BigDecimal.ZERO;
}
