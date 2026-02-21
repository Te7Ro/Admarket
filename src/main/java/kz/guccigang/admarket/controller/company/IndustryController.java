package kz.guccigang.admarket.controller.company;

import kz.guccigang.admarket.service.company.IndustryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/industry")
public class IndustryController {
    private final IndustryService industryService;
}
