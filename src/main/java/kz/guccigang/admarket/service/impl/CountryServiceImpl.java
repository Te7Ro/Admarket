package kz.guccigang.admarket.service.impl;

import jakarta.transaction.Transactional;
import kz.guccigang.admarket.dto.country.CountryCreateRequest;
import kz.guccigang.admarket.dto.country.CountryResponse;
import kz.guccigang.admarket.dto.country.CountryUpdateRequest;
import kz.guccigang.admarket.entity.Country;
import kz.guccigang.admarket.exception.entity.EntityAlreadyExistsException;
import kz.guccigang.admarket.exception.entity.EntityNotFoundException;
import kz.guccigang.admarket.repository.CountryRepository;
import kz.guccigang.admarket.service.CountryService;
import kz.guccigang.admarket.util.mapper.CountryMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CountryServiceImpl implements CountryService {
    private final CountryRepository countryRepository;
    private final CountryMapper mapper;

    public List<CountryResponse> getAllCountries() {
        return countryRepository.findAllByOrderByNameAsc()
                .stream().map(mapper::toDto).collect(Collectors.toList());
    }

    public CountryResponse getCountryByCode(String code){
        return countryRepository.findByCode(code).map(mapper::toDto).orElse(null);
    }

    public CountryResponse getCountryById(Long id){
        return countryRepository.findById(id).map(mapper::toDto).orElse(null);
    }

    @Transactional
    public CountryResponse createCountry(CountryCreateRequest request){
        if(countryRepository.existsByCodeOrName(request.getCode(), request.getName())){
            throw new EntityAlreadyExistsException("Country already exists");
        }
        Country country = mapper.toEntity(request);
        countryRepository.save(country);
        return mapper.toDto(country);
    }

    @Transactional
    public CountryResponse updateCountry(Long id, CountryUpdateRequest request){
        Country country = countryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Country not found"));
        mapper.updateEntity(request, country);
        countryRepository.save(country);
        return mapper.toDto(country);
    }

    @Transactional
    public void deleteCountry(Long id){
        countryRepository.deleteById(id);
    }
}
