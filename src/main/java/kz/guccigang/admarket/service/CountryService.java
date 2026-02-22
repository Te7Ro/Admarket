package kz.guccigang.admarket.service;

import kz.guccigang.admarket.dto.country.CountryCreateRequest;
import kz.guccigang.admarket.dto.country.CountryResponse;
import kz.guccigang.admarket.dto.country.CountryUpdateRequest;

import java.util.List;

public interface CountryService {
    List<CountryResponse> getAllCountries();
    CountryResponse getCountryByCode(String code);
    CountryResponse getCountryById(Long id);
    CountryResponse createCountry(CountryCreateRequest request);
    CountryResponse updateCountry(Long id, CountryUpdateRequest request);
    void deleteCountry(Long id);
}
