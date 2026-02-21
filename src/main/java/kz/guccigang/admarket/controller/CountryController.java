package kz.guccigang.admarket.controller;

import kz.guccigang.admarket.service.CountryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/country")
public class CountryController {
    private final CountryService countryService;
}
