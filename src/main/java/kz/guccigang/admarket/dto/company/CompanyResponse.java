package kz.guccigang.admarket.dto.company;

import kz.guccigang.admarket.dto.category.CategoryResponse;
import kz.guccigang.admarket.entity.Country;
import lombok.Data;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.List;

@Data
public class CompanyResponse {
    private Long id;
    private ZonedDateTime createdAt;
    private ZonedDateTime updatedAt;
    private Long userId;
    private String companyName;
    private String industryName;
    private String description;
    private String websiteUrl;
    private Country country;
    private BigDecimal minBudget;
    private BigDecimal maxBudget;
    private List<CategoryResponse> preferredCategories;
}
