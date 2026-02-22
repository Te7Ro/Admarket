package kz.guccigang.admarket.service.company;

import kz.guccigang.admarket.dto.industry.IndustryCreateRequest;
import kz.guccigang.admarket.dto.industry.IndustryResponse;

import java.util.List;

public interface IndustryService {
    List<IndustryResponse> getAllIndustry();
    IndustryResponse getIndustryById(Long id);
    IndustryResponse getIndustryByName(String name);
    IndustryResponse createIndustry(IndustryCreateRequest request);
    void deleteIndustry(Long id);
}
