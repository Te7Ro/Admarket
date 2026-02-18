package kz.guccigang.admarket.entity.company;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import kz.guccigang.admarket.entity.BaseEntity;
import kz.guccigang.admarket.entity.Category;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "company_preferred_categories")
@Getter
@Setter
@NoArgsConstructor
public class CompanyPreferredCategories extends BaseEntity {
    @ManyToOne
    @JoinColumn(name = "company_id", nullable = false)
    private CompanyProfile company;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;
}
