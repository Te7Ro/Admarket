package kz.guccigang.admarket.dto.offer;

import kz.guccigang.admarket.dto.category.CategoryResponse;
import kz.guccigang.admarket.dto.company.CompanyResponse;
import kz.guccigang.admarket.enums.OfferStatus;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.List;

@Data
public class OfferResponse {
    private Long id;
    private ZonedDateTime createdAt;
    private ZonedDateTime updatedAt;
    private CompanyResponse company;
    private String title;
    private String description;
    private CategoryResponse category;
    private BigDecimal budget;
    private String currency;
    private Integer targetMinAge;
    private Integer targetMaxAge;
    private LocalDate campaignStartDate;
    private LocalDate campaignEndDate;
    private OfferStatus status;
    private List<OfferTargetCountryResponse> targetCountries;
}
