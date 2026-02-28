package kz.guccigang.admarket.service.company;

import kz.guccigang.admarket.dto.company.CompanyCreateRequest;
import kz.guccigang.admarket.dto.company.CompanyResponse;
import kz.guccigang.admarket.dto.company.CompanyUpdateRequest;
import kz.guccigang.admarket.entity.User;
import kz.guccigang.admarket.entity.company.CompanyProfile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CompanyService {
    Page<CompanyResponse> getAll(Pageable pageable);
    CompanyResponse getById(Long id);
    CompanyResponse createCompanyProfile(CompanyCreateRequest request);
    CompanyResponse updateCompanyProfile(Long id, CompanyUpdateRequest request);
    CompanyProfile getEntityByUser(User user);
    CompanyProfile getEntityById(Long id);
    void deleteCompanyProfile(Long id);
    void save(CompanyProfile companyProfile);
}
