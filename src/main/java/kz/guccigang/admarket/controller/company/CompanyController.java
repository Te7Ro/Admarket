package kz.guccigang.admarket.controller.company;

import jakarta.validation.Valid;
import kz.guccigang.admarket.dto.company.CompanyCreateRequest;
import kz.guccigang.admarket.dto.company.CompanyResponse;
import kz.guccigang.admarket.dto.company.CompanyUpdateRequest;
import kz.guccigang.admarket.service.company.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/company")
@Validated
public class CompanyController {
    private final CompanyService companyService;

    @GetMapping("{id}")
    public ResponseEntity<CompanyResponse> getById(@PathVariable Long id){
        return ResponseEntity.ok(companyService.getById(id));
    }

    @GetMapping
    public Page<CompanyResponse> getAll(@PageableDefault Pageable pageable){
        return companyService.getAll(pageable);
    }

    @PostMapping
    public ResponseEntity<CompanyResponse> createCompanyProfile(@RequestBody @Valid CompanyCreateRequest request) {
        return ResponseEntity.ok(companyService.createCompanyProfile(request));
    }

    @PutMapping("{id}")
    public ResponseEntity<CompanyResponse> updateCompanyProfile(@PathVariable Long id, @RequestBody @Valid CompanyUpdateRequest request) {
        return ResponseEntity.ok(companyService.updateCompanyProfile(id, request));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<CompanyResponse> deleteCompanyProfile(@PathVariable Long id){
        companyService.deleteCompanyProfile(id);
        return ResponseEntity.ok().build();
    }
}
