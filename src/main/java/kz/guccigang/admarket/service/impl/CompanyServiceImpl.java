package kz.guccigang.admarket.service.impl;

import jakarta.transaction.Transactional;
import kz.guccigang.admarket.dto.company.CompanyCreateRequest;
import kz.guccigang.admarket.dto.company.CompanyResponse;
import kz.guccigang.admarket.dto.company.CompanyUpdateRequest;
import kz.guccigang.admarket.entity.Country;
import kz.guccigang.admarket.entity.User;
import kz.guccigang.admarket.entity.company.CompanyProfile;
import kz.guccigang.admarket.entity.company.Industry;
import kz.guccigang.admarket.exception.entity.EntityAlreadyExistsException;
import kz.guccigang.admarket.exception.entity.EntityNotFoundException;
import kz.guccigang.admarket.repository.CompanyRepository;
import kz.guccigang.admarket.repository.CountryRepository;
import kz.guccigang.admarket.repository.IndustryRepository;
import kz.guccigang.admarket.repository.UserRepository;
import kz.guccigang.admarket.service.CompanyService;
import kz.guccigang.admarket.util.mapper.CompanyProfileMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CompanyServiceImpl implements CompanyService {
    private final CompanyRepository companyRepository;
    private final CompanyProfileMapper mapper;
    private final UserRepository userRepository;
    private final IndustryRepository industryRepository;
    private final CountryRepository countryRepository;

    public CompanyResponse getById(Long id) {
        CompanyProfile entity = companyRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Company Not Found"));
        return mapper.toDto(entity);
    }

    public Page<CompanyResponse> getAll(Pageable pageable) {
        return companyRepository.findAll(pageable)
                .map(mapper::toDto);
    }

    @Transactional
    public CompanyResponse createCompanyProfile(CompanyCreateRequest request) {
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new EntityNotFoundException("User Not Found"));
        Industry industry = industryRepository.findById(request.getIndustryId())
                .orElseThrow(() -> new EntityNotFoundException("Industry Not Found"));
        Country country = countryRepository.findById(request.getCountryId())
                .orElseThrow(() -> new EntityNotFoundException("Country Not Found"));

        if(companyRepository.existsByUser(user))
            throw new EntityAlreadyExistsException("User Already Exists");

        CompanyProfile entity = mapper.toEntity(request);

        entity.setUser(user);
        entity.setIndustry(industry);
        entity.setCountry(country);

        companyRepository.save(entity);
        return mapper.toDto(entity);
    }

    @Transactional
    public CompanyResponse updateCompanyProfile(Long id, CompanyUpdateRequest request) {
        CompanyProfile entity = companyRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Company Not Found"));

        mapper.updateEntity(request, entity);

        if (request.getIndustryId() != null) {
            Industry industry = industryRepository.findById(request.getIndustryId())
                    .orElseThrow(() -> new EntityNotFoundException("Industry Not Found"));
            entity.setIndustry(industry);
        }

        if (request.getCountryId() != null) {
            Country country = countryRepository.findById(request.getCountryId())
                    .orElseThrow(() -> new EntityNotFoundException("Country Not Found"));
            entity.setCountry(country);
        }

        companyRepository.save(entity);
        return mapper.toDto(entity);
    }

    @Transactional
    public void deleteCompanyProfile(Long id) {
        companyRepository.deleteById(id);
    }
}
