package kz.guccigang.admarket.entity.creator;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import kz.guccigang.admarket.entity.BaseEntity;
import kz.guccigang.admarket.entity.Category;
import kz.guccigang.admarket.entity.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "creator_profiles")
@Getter
@Setter
@NoArgsConstructor
public class CreatorProfile extends BaseEntity {
    @OneToOne
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    private User user;

    @Column(nullable = false)
    private String displayName;

    @Column(columnDefinition = "TEXT")
    private String bio;

    @ManyToOne
    @JoinColumn(name = "primary_category_id", nullable = false)
    private Category primaryCategory;

    @Column(nullable = false)
    private Integer followersCount;

    @Column(nullable = false)
    private Integer avgViews;

    @Column(precision = 5, scale = 2, nullable = false)
    @DecimalMax("100.00")
    @DecimalMin("0.00")
    private BigDecimal engagementRate;

    private String contactEmail;
}
