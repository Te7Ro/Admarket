package kz.guccigang.admarket.service.company;

import kz.guccigang.admarket.dto.company.CompanyCreateRequest;
import kz.guccigang.admarket.dto.company.CompanyResponse;
import kz.guccigang.admarket.dto.company.CompanyUpdateRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CompanyService {
    Page<CompanyResponse> getAll(Pageable pageable);
    CompanyResponse getById(Long id);
    CompanyResponse createCompanyProfile(CompanyCreateRequest request);
    CompanyResponse updateCompanyProfile(Long id, CompanyUpdateRequest request);
    void deleteCompanyProfile(Long id);
}
