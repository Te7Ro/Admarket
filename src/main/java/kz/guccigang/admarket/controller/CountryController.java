package kz.guccigang.admarket.controller;

import jakarta.validation.Valid;
import kz.guccigang.admarket.dto.country.CountryCreateRequest;
import kz.guccigang.admarket.dto.country.CountryResponse;
import kz.guccigang.admarket.dto.country.CountryUpdateRequest;
import kz.guccigang.admarket.service.CountryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/country")
public class CountryController {
    private final CountryService countryService;

    @GetMapping
    public ResponseEntity<List<CountryResponse>> getAllCountry() {
        return ResponseEntity.ok(countryService.getAllCountries());
    }

    @GetMapping("{id}")
    public ResponseEntity<CountryResponse> getCountryById(@PathVariable Long id) {
        return ResponseEntity.ok(countryService.getCountryById(id));
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping
    public ResponseEntity<CountryResponse> createCountry(@RequestBody @Valid CountryCreateRequest request) {
        return ResponseEntity.ok(countryService.createCountry(request));
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("{id}")
    public ResponseEntity<CountryResponse> updateCountry(@PathVariable Long id, @RequestBody @Valid CountryUpdateRequest request) {
        return ResponseEntity.ok(countryService.updateCountry(id, request));
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteCountry(@PathVariable Long id) {
        countryService.deleteCountry(id);
        return ResponseEntity.ok().build();
    }
}
